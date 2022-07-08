# Unidirectional Many-to-One relation

!IMPORTANT: To test this relationship, only the models in this package were activated in the hibernate.cfg.xml file.

## LeaseContract table's structure  

| ID  | STARTDATE                  | ENDDATE                    | VEHICLE_ID |
|-----|----------------------------|----------------------------|------------|
| 1   | 2022-06-18 21:56:41.953107 | 2022-06-18 21:56:41.953107 | 1          |
| 2   | 2022-06-18 21:56:41.953107 | 2022-06-18 21:56:41.953107 | 1          |
| 3   | 2022-06-18 21:56:41.953107 | 2022-06-18 21:56:41.953107 | 1          |

## Vehicle table's structure  

| ID  | MODEL                     |
|-----|---------------------------|
| 1   | 'Mercedes A180'           |
