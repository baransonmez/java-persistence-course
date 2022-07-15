# Bidirectional Many-to-Many simple relation

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

## TAG_VEHICLE table's structure

| TAG_ID | VEHICLE_ID |
|--------|------------|
| 1      | 1          |
| 2      | 1          |
