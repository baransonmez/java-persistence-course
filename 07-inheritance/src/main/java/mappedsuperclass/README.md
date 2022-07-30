# Mapped Super Class Inheritance Example

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## Bank Account table's structure

| ID  | IBAN                    | BANKNAME      | OWNERNAME |
|-----|-------------------------|---------------|-----------|
| 1   | 'IBAN IBAN 1 IBAN IBAN' | 'BANK NAME'   | 'owner3'  |
| 2   | 'IBAN IBAN 2 IBAN IBAN' | 'BANK NAME2'  | 'owner4'  |

## Credit Card table's structure

| ID  | CARDNUMBER                   | EXPMONTH | EXPYEAR | OWNERNAME |
|-----|------------------------------|----------|---------|-----------|
| 1   | 'CARD NUMBER CARD NUMBER'    | 11       | 25      | 'owner1'  |
| 2   | 'CARD NUMBER 2 CARD NUMBER'  | 1        | 23      | 'owner2'  |


