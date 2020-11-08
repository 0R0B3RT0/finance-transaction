# Create Account

METHOD: `POST`<br>
URL: `/accounts`

Request Body:
```json
{
    "documentNumber": "123"
}
```

Response Header:
```json
Location: /accounts/42
```

Example: 
```bash
curl --location --request POST 'localhost:8080/accounts' \
--header 'Content-Type: application/json' \
--data-raw '{
    "documentNumber": "123"
}'
```
