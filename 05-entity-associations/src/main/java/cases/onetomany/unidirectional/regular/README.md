# Unidirectional One-to-Many relation regular example

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## The structure of the LeaseContract table after the insertNewContract() test

| ID  | STARTDATE                  | ENDDATE                    |
|-----|----------------------------|----------------------------|
| 1   | 2022-06-01 21:56:41.953107 | 2022-06-06 21:56:41.953107 |
| 2   | 2022-06-01 21:56:41.953107 | 2022-06-11 21:56:41.953107 |
| 3   | 2022-06-11 21:56:41.953107 | 2022-06-16 21:56:41.953107 |
| 4   | 2022-06-16 21:56:41.953107 | 2022-06-21 21:56:41.953107 |

## The structure of the Vehicle table after the insertNewContract() test

| ID  | MODEL                     |
|-----|---------------------------|
| 1   | 'Mercedes A180'           |

## The structure of the VEHICLES_LEASECONTRACTS table after the insertNewContract() test

| VEHICLES_ID   | CONTRACTHISTORY_ID |
|---------------|--------------------|
| 1             | 1                  |
| 1             | 2                  |
| 1             | 3                  |
| 1             | 4                  |

## deleteFirstContractTest() result:

    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - select next value for vehicles_SEQ
    Hibernate: select next value for vehicles_SEQ
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into vehicles (vehicle_model, id) values (?, ?)
    Hibernate: insert into vehicles (vehicle_model, id) values (?, ?)
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    
    ----------------DELETE FIRST CONTRACT-------------------
    
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    Hibernate: select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - select c1_0.vehicles_id,c1_1.id,c1_1.endDate,c1_1.startDate from vehicles_leaseContracts c1_0 join leaseContracts c1_1 on c1_1.id=c1_0.contractHistory_id where c1_0.vehicles_id=?
    Hibernate: select c1_0.vehicles_id,c1_1.id,c1_1.endDate,c1_1.startDate from vehicles_leaseContracts c1_0 join leaseContracts c1_1 on c1_1.id=c1_0.contractHistory_id where c1_0.vehicles_id=?
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - delete from vehicles_leaseContracts where vehicles_id=?
    Hibernate: delete from vehicles_leaseContracts where vehicles_id=?
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:11:25 DEBUG org.hibernate.SQL - delete from leaseContracts where id=?
    Hibernate: delete from leaseContracts where id=?

## deleteLastContractTest() result:

    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - select next value for vehicles_SEQ
    Hibernate: select next value for vehicles_SEQ
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into vehicles (vehicle_model, id) values (?, ?)
    Hibernate: insert into vehicles (vehicle_model, id) values (?, ?)
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    
    ----------------DELETE LAST ITEM-------------------
    
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    Hibernate: select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - select c1_0.vehicles_id,c1_1.id,c1_1.endDate,c1_1.startDate from vehicles_leaseContracts c1_0 join leaseContracts c1_1 on c1_1.id=c1_0.contractHistory_id where c1_0.vehicles_id=?
    Hibernate: select c1_0.vehicles_id,c1_1.id,c1_1.endDate,c1_1.startDate from vehicles_leaseContracts c1_0 join leaseContracts c1_1 on c1_1.id=c1_0.contractHistory_id where c1_0.vehicles_id=?
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - delete from vehicles_leaseContracts where vehicles_id=?
    Hibernate: delete from vehicles_leaseContracts where vehicles_id=?
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:10:35 DEBUG org.hibernate.SQL - delete from leaseContracts where id=?
    Hibernate: delete from leaseContracts where id=?

## insertNewContract() result:

    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - select next value for vehicles_SEQ
    Hibernate: select next value for vehicles_SEQ
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - insert into vehicles (vehicle_model, id) values (?, ?)
    Hibernate: insert into vehicles (vehicle_model, id) values (?, ?)
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    
    ----------------INSERT NEW CONTRACT-------------------
    
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    Hibernate: select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - select c1_0.vehicles_id,c1_1.id,c1_1.endDate,c1_1.startDate from vehicles_leaseContracts c1_0 join leaseContracts c1_1 on c1_1.id=c1_0.contractHistory_id where c1_0.vehicles_id=?
    Hibernate: select c1_0.vehicles_id,c1_1.id,c1_1.endDate,c1_1.startDate from vehicles_leaseContracts c1_0 join leaseContracts c1_1 on c1_1.id=c1_0.contractHistory_id where c1_0.vehicles_id=?
    2022-07-26 22:08:21 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:08:22 DEBUG org.hibernate.SQL - delete from vehicles_leaseContracts where vehicles_id=?
    Hibernate: delete from vehicles_leaseContracts where vehicles_id=?
    2022-07-26 22:08:22 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:08:22 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:08:22 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    2022-07-26 22:08:22 DEBUG org.hibernate.SQL - insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
    Hibernate: insert into vehicles_leaseContracts (vehicles_id, contractHistory_id) values (?, ?)
