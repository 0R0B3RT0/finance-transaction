# Create Transaction

METHOD: `POST`<br>
URL: `/transactions`

Request Body:
```json
{
    "accountId": 1,
    "operationTypeId": 4,
    "amount": 123.45
}
```

Response:
```json
{
    "transactionId": 41
}
```

Request example: 
```bash
curl --location --request POST 'localhost:8080/transactions' \
--header 'Content-Type: application/json' \
--data-raw '{
    "accountId": 1,
    "operationTypeId": 4,
    "amount": 123.45
}'
```
