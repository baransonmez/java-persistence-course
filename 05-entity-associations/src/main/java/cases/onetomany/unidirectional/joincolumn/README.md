# Unidirectional One-to-Many relation @JoinColumn example

!IMPORTANT: To test this relationship, you need to make sure that only the models
in this package are activated in the hibernate.cfg.xml file.

## The structure of the LeaseContract table after the insertNewContract() test

| ID  | STARTDATE                  | ENDDATE                    | VEHICLE_ID |
|-----|----------------------------|----------------------------|------------|
| 1   | 2022-06-01 21:56:41.953107 | 2022-06-06 21:56:41.953107 | 1          |
| 2   | 2022-06-01 21:56:41.953107 | 2022-06-11 21:56:41.953107 | 1          |
| 3   | 2022-06-11 21:56:41.953107 | 2022-06-16 21:56:41.953107 | 1          |
| 4   | 2022-06-16 21:56:41.953107 | 2022-06-21 21:56:41.953107 | 1          |

## The structure of the Vehicle table after the insertNewContract() test

| ID  | MODEL                     |
|-----|---------------------------|
| 1   | 'Mercedes A180'           |

## deleteFirstContractTest() result:

    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - select next value for vehicles_SEQ
    Hibernate: select next value for vehicles_SEQ
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - insert into vehicles (vehicle_model, id) values (?, ?)
    Hibernate: insert into vehicles (vehicle_model, id) values (?, ?)
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?
    
    ----------------DELETE FIRST ITEM-------------------
    
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    Hibernate: select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - select c1_0.vehicle_id,c1_0.id,c1_0.endDate,c1_0.startDate from leaseContracts c1_0 where c1_0.vehicle_id=?
    Hibernate: select c1_0.vehicle_id,c1_0.id,c1_0.endDate,c1_0.startDate from leaseContracts c1_0 where c1_0.vehicle_id=?
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=null where vehicle_id=? and id=?
    Hibernate: update leaseContracts set vehicle_id=null where vehicle_id=? and id=?
    2022-07-26 22:01:03 DEBUG org.hibernate.SQL - delete from leaseContracts where id=?
    Hibernate: delete from leaseContracts where id=?

## deleteLastContractTest() result:

    2022-07-26 22:03:30 DEBUG org.hibernate.SQL - select next value for vehicles_SEQ
    Hibernate: select next value for vehicles_SEQ
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - insert into vehicles (vehicle_model, id) values (?, ?)
    Hibernate: insert into vehicles (vehicle_model, id) values (?, ?)
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?

    ----------------DELETE LAST ITEM-------------------

    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    Hibernate: select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - select c1_0.vehicle_id,c1_0.id,c1_0.endDate,c1_0.startDate from leaseContracts c1_0 where c1_0.vehicle_id=?
    Hibernate: select c1_0.vehicle_id,c1_0.id,c1_0.endDate,c1_0.startDate from leaseContracts c1_0 where c1_0.vehicle_id=?
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=null where vehicle_id=? and id=?
    Hibernate: update leaseContracts set vehicle_id=null where vehicle_id=? and id=?
    2022-07-26 22:03:31 DEBUG org.hibernate.SQL - delete from leaseContracts where id=?
    Hibernate: delete from leaseContracts where id=?

## insertNewContract() result:

    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - select next value for vehicles_SEQ
    Hibernate: select next value for vehicles_SEQ
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - select next value for leaseContracts_SEQ
    Hibernate: select next value for leaseContracts_SEQ
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - insert into vehicles (vehicle_model, id) values (?, ?)
    Hibernate: insert into vehicles (vehicle_model, id) values (?, ?)
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?
    
    ----------------INSERT NEW CONTRACT-------------------
    
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    Hibernate: select v1_0.id,v1_0.vehicle_model from vehicles v1_0 where v1_0.id=?
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - select c1_0.vehicle_id,c1_0.id,c1_0.endDate,c1_0.startDate from leaseContracts c1_0 where c1_0.vehicle_id=?
    Hibernate: select c1_0.vehicle_id,c1_0.id,c1_0.endDate,c1_0.startDate from leaseContracts c1_0 where c1_0.vehicle_id=?
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    Hibernate: insert into leaseContracts (endDate, startDate, id) values (?, ?, ?)
    2022-07-26 22:04:38 DEBUG org.hibernate.SQL - update leaseContracts set vehicle_id=? where id=?
    Hibernate: update leaseContracts set vehicle_id=? where id=?