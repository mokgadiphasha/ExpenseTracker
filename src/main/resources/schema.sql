
--Table creation

CREATE TABLE IF NOT EXISTS "User" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "Category" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "Expense" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES "User"(id),
    FOREIGN KEY (category_id) REFERENCES "Category"(id)
);


CREATE TABLE IF NOT EXISTS "Budget" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "User"(id),
    FOREIGN KEY (category_id) REFERENCES "Category"(id)
);


--adding data to tables

INSERT INTO "Category" (name) VALUES ('Food');
INSERT INTO "Category" (name) VALUES ('Transportation');
INSERT INTO "Category" (name) VALUES ('Utilities');
INSERT INTO "Category" (name) VALUES ('Housing');
INSERT INTO "Category" (name) VALUES ('Healthcare');
INSERT INTO "Category" (name) VALUES ('Entertainment');
INSERT INTO "Category" (name) VALUES ('Education');
INSERT INTO "Category" (name) VALUES ('Clothing');
INSERT INTO "Category" (name) VALUES ('Personal Care');
INSERT INTO "Category" (name) VALUES ('Miscellaneous');

