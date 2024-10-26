-- Table creation for PostgreSQL

CREATE TABLE IF NOT EXISTS "User" (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS "Category" (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS "Expense" (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    date DATE NOT NULL,
    description TEXT,
    FOREIGN KEY (user_id) REFERENCES "User"(id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES "Category"(id) ON DELETE RESTRICT
);





