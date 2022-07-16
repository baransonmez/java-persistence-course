# Mapping List Fields

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## VEHICLES table's structure

| ID  | VEHICLE_CONDITION | DAILYPRICE | VEHICLE_MODEL  | VEHICLE_TYPE | MODEL_YEAR |
|-----|-------------------|------------|----------------|--------------|------------|
| 1   | NEAR_NEW          | 202.7      | Mercedes A180  | 2            | 2017       |


## IMAGE_LIST table's structure

| VEHICLEWITHLIST_ID | IMAGE_PATH | IMAGES_ORDER |
|--------------------|------------|--------------|
| 1                  | image-1    | 0            |
| 1                  | image-2    | 1            |
| 1                  | image-3    | 2            |

