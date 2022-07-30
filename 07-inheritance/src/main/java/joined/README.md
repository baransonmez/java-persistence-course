# JOINED Inheritance Example

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## Billings Detail table's structure

| ID  | OWNERNAME |
|-----|-----------|
| 1   | 'owner1'  |
| 2   | 'owner2'  |
| 3   | 'owner3'  |
| 4   | 'owner4'  |

## Bank Account table's structure

| ID  | IBAN                    | BANKNAME      |
|-----|-------------------------|---------------|
| 3   | 'IBAN IBAN 1 IBAN IBAN' | 'BANK NAME'   |
| 4   | 'IBAN IBAN 2 IBAN IBAN' | 'BANK NAME2'  |

## CreditCard table's structure

| ID  | CARDNUMBER                   | EXPMONTH | EXPYEAR | 
|-----|------------------------------|----------|---------|
| 1   | 'CARD NUMBER CARD NUMBER'    | 11       | 25      | 
| 2   | 'CARD NUMBER 2 CARD NUMBER'  | 1        | 23      | 


