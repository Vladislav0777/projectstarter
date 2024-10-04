CREATE TABLE product_details (
                                 id bigserial PRIMARY KEY,
                                 name varchar,
                                 description varchar,
                                 product_id int8);
CREATE TABLE product (
                         id bigserial PRIMARY KEY,
                         name varchar,
                         price double precision,
                         category_id bigint);
CREATE TABLE customer (
                          id bigserial PRIMARY KEY,
                          name varchar,
                          customer_since timestamp);
CREATE TABLE customer_order (
                                id bigserial PRIMARY KEY,
                                date timestamp,
                                customer_id int8);
CREATE TABLE order_line (
                            id bigserial PRIMARY KEY,
                            amount int8,
                            purchase_price double precision,
                            product_id int8,
                            customer_order_id int8);
CREATE TABLE category (
                          id bigserial PRIMARY KEY,
                          name varchar,
                          price double precision,
                          brand_id int8);
CREATE TABLE brand (
                       id bigserial PRIMARY KEY,
                       name varchar);

insert into brand (name)
values ('Uni-Puni' ),
       ('Hooks' ),
       ('WhiteOrDark' );

insert into category (name, price, brand_id)
values ('Office', 30, 1),
       ('Foods', 90, 1),
       ('Cars', 99900, 2),
       ('Flowers', 150, 3);
insert into product (name, price, category_id)
values ('Pencil', 30, 1),
       ('Coca-cola 1L', 90, 2 ),
       ('Skoda Oktavia 2024', 99900, 3 ),
       ('Daisy', 150, 4),
       ('Pansy', 140, 4);