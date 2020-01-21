create table user
(
  id           int primary key,
  user_name    text,
  password     text,
  created_time datetime,
  updated_time datetime,
  user_type    text
);

create table contact_address
(
  street_address text,
  state          text,
  city           text,
  zip_code       text,
  user_id        int references user,
  addr_type      text
);

create table contact
(
  contact_ text,
  id       int references user
);