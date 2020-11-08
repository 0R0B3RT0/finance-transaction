# Find Account

METHOD: `GET`<br>
URL: `/accounts/{accountId}`

Response Body:
```json
{
    "accountId": 1,
    "documentNumber": "123"
}
```

Example: 
```bash
curl --location --request GET 'localhost:8080/accounts/1'
```
