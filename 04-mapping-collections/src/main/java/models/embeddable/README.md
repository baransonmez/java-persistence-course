# Mapping Set Fields

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## VEHICLES table's structure

| ID  | VEHICLE_CONDITION | DAILYPRICE | VEHICLE_MODEL  | VEHICLE_TYPE | MODEL_YEAR |
|-----|-------------------|------------|----------------|--------------|------------|
| 1   | NEAR_NEW          | 202.7      | Mercedes A180  | 2            | 2017       |

## IMAGE_SET_EMBEDDABLE table's structure

| VEHICLEWITHOBJECT_ID | FILENAME    | HEIGHT | TITLE  | WIDTH |
|----------------------|-------------|--------|--------|-------|
| 1                    | image1.jpg  | 10     | title1 | 15    |
| 1                    | image2.jpg  | 25     | title2 | 20    |
| 1                    | image3.jpg  | 35     | title3 | 30    |

