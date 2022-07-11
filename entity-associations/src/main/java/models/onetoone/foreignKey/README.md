# Bidirectional One-to-One relation using Foreign Key

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## BillingDetails table's structure

| ID  | CARD_NUMBER            | EXPIRATION_DATE |
|-----|------------------------|-----------------|
| 1   | '1111 2222 3333 4444'  | 2022-07-16      |
| 3   | '3333 2222 5555 4444'  | 2022-07-16      |


## Users table's structure

| ID  | USER_NAME | BILLINGINFO_ID |
|-----|-----------|----------------|
| 1   | 'barans'  | 1              |
| 2   | 'barans3' | -              |
| 3   | 'barans2' | 3              |

