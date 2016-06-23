CREATE KEYSPACE cwt WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

CREATE TABLE cwt.person (
    id text PRIMARY KEY,
    age int,
    name text
);

CREATE TABLE cwt.event (
    id text PRIMARY KEY,
    event_time timestamp,
    name text
);

CREATE TABLE cwt.watchlist (
    watchlistcode text,
    symbol text,
    active text,
    PRIMARY KEY (watchlistcode, symbol)
);

CREATE TABLE cwt.stockquote (
    symbol text,
    price_time timestamp,
    close_price float,
    high_price float,
    low_price float,
    open_price float,
    volume double,
    PRIMARY KEY (symbol, price_time)
);

CREATE TABLE cwt.member (
    username text PRIMARY KEY,
    active text,
    email text,
    fullname text,
    password text,
    role text
);

CREATE TABLE cwt.signalhistory (
    symbol text,
    signal_time timestamp,
    signal_name text,
    signal_price float,
    PRIMARY KEY (symbol, signal_time)
);

CREATE TABLE cwt.signalperday (
    signal_day text,
    symbol text,
    signal_name text,
    signal_price float,
    PRIMARY KEY (signal_day, symbol)
);
