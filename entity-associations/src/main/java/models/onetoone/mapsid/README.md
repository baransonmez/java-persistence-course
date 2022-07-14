# Bidirectional One-to-One relation using @MapsId

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.


## Users table's structure

| ID  | USER_NAME |
|-----|-----------|
| 1   | 'barans'  |
| 2   | 'ali'     |
| 3   | '3.user ' |

## UsersDetail table's structure

| USER_ID    | PROFILEPHOTOURL     |
|------------|---------------------|
| 1          | photourl.com        |
| 3          | 3.userphotourl.com  |

