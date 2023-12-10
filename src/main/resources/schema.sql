create table if not exists fighter (
  id identity,
  name varchar(100) not null,
  brand varchar(100) not null,
  year_Of_Creation int not null,
  price double not null,
  created_at timestamp not null
);