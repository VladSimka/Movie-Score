CREATE TABLE films
(
    id            SERIAL PRIMARY KEY,
    title         VARCHAR(255) NOT NULL,
    description   TEXT,
    path_to_image varchar(255) not null
);

CREATE TABLE users
(
    id          SERIAL PRIMARY KEY,
    username    VARCHAR(255) NOT NULL UNIQUE,
    password    VARCHAR(255) NOT NULL
);

CREATE TABLE reviews
(
    id      SERIAL PRIMARY KEY,
    grade   INTEGER NOT NULL,
    film_id INTEGER ,
    user_id INTEGER ,

    constraint fk_reviews_films foreign key (film_id) references films(id) on delete cascade on update no action ,
    constraint fk_reviews_users foreign key (user_id) references users(id) on delete cascade on update no action
);


CREATE TABLE   users_roles
(
    user_id serial      not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_users_roles_users foreign key (user_id) references users (id) on delete cascade on UPDATE no action
);