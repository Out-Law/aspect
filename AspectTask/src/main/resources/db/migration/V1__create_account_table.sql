CREATE TABLE Account (
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL,
    account_type VARCHAR(20) NOT NULL CHECK (account_type IN ('DEBIT', 'CREDIT')),
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00
);
