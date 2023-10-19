CREATE TABLE users(
    id VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    phone bigint NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
)