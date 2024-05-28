CREATE SCHEMA IF NOT EXISTS testdb;


CREATE TABLE IF NOT EXISTS url (
                                   id SERIAL PRIMARY KEY,
                                   address VARCHAR(255) NOT NULL);