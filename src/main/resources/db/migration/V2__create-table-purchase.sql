CREATE TABLE purchase(
    id VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(30,2) NOT NULL,
    date_purchase DATE NOT NULL,
    user_id VARCHAR(255) NOT NULL,
    PRIMARY KEY(id),

    CONSTRAINT fk_purchase_user_id FOREIGN KEY(user_id) REFERENCES users(id)

);