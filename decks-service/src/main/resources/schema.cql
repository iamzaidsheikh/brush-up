CREATE TYPE IF NOT EXISTS user_udt (
    id TIMEUUID,
    username TEXT,
    first_name TEXT,
    last_name TEXT);

CREATE TYPE IF NOT EXISTS card_udt (
    id TIMEUUID,
    author user_udt,
    num_saves INT,
    front TEXT,
    back TEXT,
    date_created TIMESTAMP,
);

CREATE TABLE IF NOT EXISTS card (
    id TIMEUUID PRIMARY KEY,
    author user_udt,
    front TEXT,
    back TEXT,
    num_saves INT,
    date_created TIMESTAMP,
    saved_by LIST<FROZEN <user_udt>>
);

CREATE TABLE IF NOT EXISTS deck (
    id TIMEUUID PRIMARY KEY,
    author user_udt,
    title TEXT,
    description TEXT,
    num_stars INT,
    num_saves INT,
    num_cards INT,
    cards LIST<FROZEN <card_udt>>,
    date_created TIMESTAMP,
    date_updated TIMESTAMP,
    saved_by LIST<FROZEN <user_udt>>
);    