# Crypto Trading API

This API is part of a crypto trading application that aggregates prices from different exchanges and stores the best price for each symbol.

## Technologies Used

- Java
- SQL
- Spring Boot
- Maven

## Setup

To run this project, you need to have Java and Maven installed on your machine.

1. Clone the repository:

```bash
git clone https://github.com/bchooxg/cryptoTrading.git
```

2. Navigate to the project directory:

```bash
cd cryptoTrading
```

3. Build the project:

```bash
mvn clean install
```

4. Run the application:

```bash
mvn spring-boot:run
```

## Prices API Endpoints


### GET /prices/{symbol}

Fetches the latest price for a given currency pair.

#### Parameters

- `symbol`: The currency pair for which to fetch the latest price. This should be a string like "BTCUSDT" or "ETHUSDT".

#### Response

Returns a JSON object with the latest price information for the specified currency pair. The object has the following properties:

- `symbol`: The currency pair.
- `bidPrice`: The highest price that a buyer is willing to pay for an asset.
- `askPrice`: The lowest price that a seller is willing to accept for an asset.
- `exchange`: The exchange where the best price was found.
- `timestamp`: The time when the price was fetched.

#### Example

Response:

```json
{
  "symbol": "BTCUSDT",
  "bidPrice": 45000.00,
  "askPrice": 45010.00,
  "exchange": "Binance",
  "timestamp": "2022-03-01T10:00:00"
}
```

## Trades API Endpoints

### GET /trades

Fetches all trades.

#### Response

Returns a JSON array with all the trades. Each object in the array has the following properties:

- `id`: The unique identifier of the trade.
- `username`: The username of the user who made the trade.
- `symbol`: The currency pair that was traded.
- `quantity`: The quantity of the currency that was traded.
- `price`: The price at which the trade was made.
- `timestamp`: The time when the trade was made.

#### Example

Request:

```
GET /trades
```

Response:

```json
[
  {
    "id": 1,
    "username": "bchooxg",
    "symbol": "BTCUSDT",
    "quantity": 0.1,
    "price": 45000.00,
    "timestamp": "2022-03-01T10:00:00"
  },
  ...
]
```

### GET /trades/{username}

Fetches all trades made by a specific user.

#### Parameters

- `username`: The username of the user whose trades you want to fetch.

#### Response

Returns a JSON array with all the trades made by the specified user. Each object in the array has the same properties as described in the `GET /trades` endpoint.

#### Example

Request:

```
GET /trades/bchooxg
```

Response:

```json
[
  {
    "id": 1,
    "username": "bchooxg",
    "symbol": "BTCUSDT",
    "quantity": 0.1,
    "price": 45000.00,
    "timestamp": "2022-03-01T10:00:00"
  },
  ...
]
```

### POST /trades

Creates a new trade.

#### Request Body

The request body should be a JSON object with the following properties:

- `username`: The username of the user who is making the trade.
- `symbol`: The currency pair that is being traded.
- `quantity`: The quantity of the currency that is being traded.

#### Response

Returns a JSON object with the details of the trade that was created. The object has the same properties as described in the `GET /trades` endpoint.

#### Example

Request:

```
POST /trades
Content-Type: application/json

{
  "username": "bchooxg",
  "symbol": "BTCUSDT",
  "quantity": 0.1,
}
```

Response:

```json
{
  "id": 2,
  "username": "bchooxg",
  "symbol": "BTCUSDT",
  "quantity": 0.1,
  "price": 45000.00, -- Will take the current best price available 
  "timestamp": "2022-03-01T10:05:00"
}
```

## Wallet API Endpoints

#### GET /wallets/{userName}

Fetches all wallets associated with a specific user.

#### Parameters

- `userName`: The username of the user whose wallets you want to fetch.

#### Response

Returns a JSON array with all the wallets associated with the specified user. Each object in the array has the following properties:

- `id`: The unique identifier of the wallet.
- `username`: The username of the user who owns the wallet.
- `currency`: The type of currency stored in the wallet.
- `balance`: The balance of the wallet.

#### Example

Request:

```
GET /wallets/bchooxg
```

Response:

```json
[
  {
    "id": 1,
    "username": "bchooxg",
    "currency": "BTC",
    "balance": 0.5
  },
  {
    "id": 2,
    "username": "bchooxg",
    "currency": "ETH",
    "balance": 10
  },
  ...
]
```

## User API Endpoints

#### GET /users/{username}

Fetches user information by username.

#### Parameters

- `username`: The username of the user whose information you want to fetch.

#### Response

Returns a JSON object with the user's information. The object has the following properties:

- `id`: The unique identifier of the user.
- `username`: The username of the user.
- `email`: The email of the user.
- `registeredAt`: The date and time when the user registered.

#### Example

Request:

```
GET /users/bchooxg
```

Response:

```json
{
  "id": 1,
  "username": "bchooxg",
  "email": "bchooxg@example.com",
  "registeredAt": "2022-01-01T00:00:00"
}
```


## Scheduled Tasks

The application runs a scheduled task every 10 seconds to fetch prices from Binance and Huobi, determine the best price for each symbol, and store it in the database.

## Database

The application uses a SQL database to store the best prices. The database configuration can be found in the `application.properties` file.

## License

[MIT](https://choosealicense.com/licenses/mit/)
```

Remember to replace the placeholder text with the actual information about your project.
