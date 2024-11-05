CREATE TABLE DataSourceErrorLog (
    id SERIAL PRIMARY KEY,
    stacktrace TEXT NOT NULL,
    message TEXT NOT NULL,
    method_signature TEXT NOT NULL
);