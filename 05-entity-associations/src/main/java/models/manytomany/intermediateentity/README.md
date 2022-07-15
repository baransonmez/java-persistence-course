# Bidirectional Many-to-Many relation using intermediate entity

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## VEHICLE table's structure

| ID  | MODEL              |
|-----|--------------------|
| 1   | 'vehicle-model-1 ' |
| 2   | 'vehicle-model-2 ' |

## TAG table's structure

| ID  | DESCRIPTION |
|-----|-------------|
| 1   | 'tag-1'     |
| 2   | 'tag-2'     |

## TAGGED_VEHICLE table's structure

| TAG_ID | VEHICLE_ID | CREATEDBY | CREATEDON                |
|--------|------------|-----------|--------------------------|
| 1      | 1          | 'barans'  |  2022-07-15 12:02:04.694 |
| 2      | 1          | 'ihsanb'  |  2022-07-15 12:02:04.694 |
| 2      | 2          | 'ibarans' |  2022-07-15 12:02:04.694 |
