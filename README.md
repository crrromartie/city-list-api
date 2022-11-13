# Service methods

- [Update city](#update_city)
- [Find city by name](#find_city_by_name)
- [Find cities per page](#find_cities_per_page)

# Open-api command

- http://server:port/context-path/swagger-ui.html - open ui
- http://server:port/context-path/v3/api-docs - generate docs json
- http://server:port/context-path/v3/api-docs.yaml - generate docs yaml

# Authorization

To call the update city method, you need to get a token in Keycloak
with the required role: allowed_edit in realm: test.
x-wwww-form-urlencoded parameters:
client_id: test, grant_type: client_credentials, client_secret: fWKpsBzWG4CpbaYxgwvNwgaTe4vAD7H3

# REST-endpoints

## Update city <a name="update_city"></a>

```
PUT /cities
```

### Request parameters

| Parameter | Type   | Description | Required |
|-----------|--------|-------------|----------|
| id        | string | City id     | yes      |
| name      | string | City name   | no       |
| photo     | long   | City photo  | no       |

### Request example

```json
{
  "id": 1,
  "name": "York",
  "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/IN-DL.svg/439px-IN-DL.svg.png"
}
```

### Response example

* `200` http status

```json
{
  "name": "York",
  "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/IN-DL.svg/439px-IN-DL.svg.png"
}
```

## Find city by name <a name="find_city_by_name"></a>

```
GET /cities?name=York
```

### Request parameters

| Parameter | Type   | Description | Required |
|-----------|--------|-------------|----------|
| name      | string | City name   | yes      |

### Response example

* `200` http status

```json
[
  {
    "name": "York",
    "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/IN-DL.svg/439px-IN-DL.svg.png"
  }
]
```

## Find cities per page <a name="find_cities_per_page"></a>

```
GET /cities?page=1&size=5
```

### Request parameters

| Parameter | Type | Description | Required |
|-----------|------|-------------|----------|
| page      | int  | Page number | yes      |
| size      | int  | Page size   | yes      |

### Response example

* `200` http status

```json
[
  {
    "name": "Jakarta",
    "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Jakarta_Pictures-1.jpg/327px-Jakarta_Pictures-1.jpg"
  },
  {
    "name": "Delhi",
    "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/IN-DL.svg/439px-IN-DL.svg.png"
  },
  {
    "name": "Mumbai",
    "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Mumbai_Skyline_at_Night.jpg/500px-Mumbai_Skyline_at_Night.jpg"
  },
  {
    "name": "Manila",
    "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/0/06/Manila_Cathedral_Facade_at_Sunset.jpg/500px-Manila_Cathedral_Facade_at_Sunset.jpg"
  },
  {
    "name": "Shanghai",
    "photo": "https://upload.wikimedia.org/wikipedia/commons/thumb/d/df/Pudong_Shanghai_November_2017_panorama.jpg/500px-Pudong_Shanghai_November_2017_panorama.jpg"
  }
]
```