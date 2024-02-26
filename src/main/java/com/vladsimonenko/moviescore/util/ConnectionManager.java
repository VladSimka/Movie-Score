package com.vladsimonenko.moviescore.util;

import org.postgresql.Driver;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public final class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";
    private static final int DEFAULT_POOL_SIZE = 10;
    private static final String POOL_SIZE_KEY = "db.pool.size";
    private static BlockingQueue<Connection> pool;

    static {
        try {
            DriverManager.registerDriver(new Driver());
            initConnectionPool();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    private static void initConnectionPool() {
        String sizeStr = PropertiesUtil.get(POOL_SIZE_KEY);
        int size = sizeStr == null ? DEFAULT_POOL_SIZE : Integer.parseInt(sizeStr);

        pool = new ArrayBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            Connection proxyConnection;
            Connection connection = open();
            proxyConnection = (Connection) Proxy.newProxyInstance(ConnectionManager.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) ->
                            method.getName().equals("close")
                                    ? pool.add((Connection) proxy)
                                    : method.invoke(connection, args));

            pool.add(proxyConnection);

        }
    }

    public static Connection get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection open() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    private ConnectionManager() {
    }


}
