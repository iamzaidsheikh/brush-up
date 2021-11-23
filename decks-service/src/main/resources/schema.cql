CREATE TABLE IF NOT EXISTS card (
    id UUID PRIMARY KEY,
    front TEXT,
    back TEXT,
);

CREATE TYPE IF NOT EXISTS card_udt (
    id UUID,
    front TEXT,
    back TEXT);

CREATE TABLE IF NOT EXISTS deck (
    id UUID PRIMARY KEY,
    num_cards INT,
    cards LIST<FROZEN <card_udt>>,
);    