CREATE TABLE users (
    id VARCHAR(100) NOT NULL,
    email VARCHAR(1000) NOT NULL,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    g_number VARCHAR(100) NOT NULL,
    password VARCHAR(1000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE session (
    id VARCHAR(100) NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    start_date DATETIME NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE classes (
    id VARCHAR(100) NOT NULL,
    title VARCHAR(100) NOT NULL,
    subject VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL,
    section VARCHAR(100) NOT NULL,
    instructor VARCHAR(1000) NOT NULL,
    rate_my_professor VARCHAR(100) NOT NULL,
    credit VARCHAR(100) NOT NULL,
    level VARCHAR(100) NOT NULL,
    location VARCHAR(1000) NOT NULL,
    campus VARCHAR(1000) NOT NULL,
    days VARCHAR(100) NOT NULL,
    time VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE class_registration (
    id VARCHAR(100) NOT NULL,
    class_id VARCHAR(100) NOT NULL,
    user_id VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (class_id, user_id)
);
