# Bidirectional One-to-One relation using Join Table

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## Vehicles table's structure

| ID  | MODEL     |
|-----|-----------|
| 1   | 'Model 1' |
| 2   | 'Model 2' |

## Users table's structure

| ID  | USER_NAME |
|-----|-----------|
| 1   | 'barans'  |

## CurrentOwner table's structure

| USER_ID | VEHICLE_ID |
|---------|------------|
| 1       | 2          |

