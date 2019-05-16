create table storages(
id number,
constraint storage_id primary key(id),
formatsSupported nvarchar2(10) default 'txt, jpg',
storageCountry nvarchar2(50),
storageMaxSize number not null
);

create table files(
id number,
constraint file_id primary key(id),
fileName nvarchar2(50),
fileFormat nvarchar2(3) check (fileFormat = 'txt' or fileFormat = 'jpg'),
fileSize number,
storage_id number,
constraint storage_fk foreign key(storage_id) references storages(id)
);