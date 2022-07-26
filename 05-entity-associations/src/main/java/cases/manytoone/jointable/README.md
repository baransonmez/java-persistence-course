# Many-to-One relation join table example

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## LeaseContract table's structure

| ID  | STARTDATE                  | ENDDATE                    |
|-----|----------------------------|----------------------------|
| 1   | 2022-06-18 21:56:41.953107 | 2022-06-18 21:56:41.953107 |
| 2   | 2022-06-18 21:56:41.953107 | 2022-06-18 21:56:41.953107 |
| 3   | 2022-06-18 21:56:41.953107 | 2022-06-18 21:56:41.953107 |

## Vehicle table's structure

| ID  | MODEL                     |
|-----|---------------------------|
| 1   | 'Mercedes A180'           |

## Contract_Vehicle table's structure

| vehicle_id | contract_id |
|------------|-------------|
| 1          | 1           |
| 1          | 2           |
| 1          | 3           |
