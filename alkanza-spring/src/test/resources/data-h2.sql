insert into tbl_result(id, createDateTime, userLatitude, userLongitude, calculation) values(1, CURRENT_DATE, 10.0, 10.0, 500.0);

insert into tbl_place(id, createDateTime, name, latitude, longitude, distance, result_id) values(hibernate_sequence.nextval, CURRENT_DATE, 'Point 1', 10.0, 10.0, 20.0, 1);
insert into tbl_place(id, createDateTime, name, latitude, longitude, distance, result_id) values(hibernate_sequence.nextval, CURRENT_DATE, 'Point 2', 20.0, 20.0, 50.0, 1);
insert into tbl_place(id, createDateTime, name, latitude, longitude, distance, result_id) values(hibernate_sequence.nextval, CURRENT_DATE, 'Point 3', 30.0, 30.0, 150.0, 1);