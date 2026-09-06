CREATE TABLE Movie(
    id serial PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description text,
    release_date date,
    rating numeric,
    created_at timestamp,
    updated_at timestamp
);