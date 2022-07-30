# Single Table Inheritance Example

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## BILLINGDETAILS table's structure

| ID  | TYPE           | IBAN                    | BANKNAME     | CARDNUMBER                   | EXPMONTH | EXPYEAR |
|-----|----------------|-------------------------|--------------|------------------------------|----------|---------|
| 1   | 'creditCard'   |                         |              | 'CARD NUMBER CARD NUMBER'    | 11       | 25      |
| 2   | 'creditCard'   |                         |              | 'CARD NUMBER 2 CARD NUMBER'  | 1        | 23      |
| 3   | 'bankAccount'  | 'IBAN IBAN 1 IBAN IBAN' | 'BANK NAME'  |                              |          |         |
| 4   | 'bankAccount'  | 'IBAN IBAN 2 IBAN IBAN' | 'BANK NAME2  |                              |          |         |




