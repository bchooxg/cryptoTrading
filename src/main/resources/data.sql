insert into user_details (username, first_name, last_name, cash_balance) values ('bchoo', 'Bryan', 'Choo', 50000);
insert into prices (symbol,exchange, bid_price, ask_price, timestamp) values ('BTCUSDT','Huobi', 10000, 10001, current_timestamp());
insert into prices (symbol,exchange, bid_price, ask_price, timestamp) values ('ETHUSDT','Binance', 8000, 8001, current_timestamp());
insert into wallets (user_name, symbol, amount) values ('bchoo', 'BTCUSDT', 1);
insert into wallets (user_name, symbol, amount) values ('bchoo', 'ETHUSDT', 1);
INSERT INTO Trades (username, trade_type, symbol, amount, price, timestamp) VALUES ('bchoo', 'BUY','BTCUSDT', 1, 10000, current_timestamp());
INSERT INTO Trades (username, trade_type, symbol, amount, price, timestamp) VALUES ('bchoo', 'BUY','ETHUSDT', 1, 8000, current_timestamp());