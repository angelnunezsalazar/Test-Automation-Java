create table product(
id int primary key auto_increment,
name varchar(50),
description varchar(200),
price decimal(10,2),
imagename varchar(50)
);

create table "ORDER"(
id int primary key auto_increment,
email varchar(50),
address varchar(50),
quantity int,
productId int
);

INSERT into Product (Id, Name, Description, Price, ImageName) VALUES ('Carrot Cake', 'A scrumptious mini-carrot cake encrusted with sliced almonds', 8.99, 'carrot_cake.jpg');
INSERT into Product (Id, Name, Description, Price, ImageName) VALUES ('Lemon Tart', 'A delicious lemon tart with fresh meringue cooked to perfection', 9.99, 'lemon_tart.jpg');
INSERT into Product (Id, Name, Description, Price, ImageName) VALUES ('Cupcakes', 'Delectable vanilla and chocolate cupcakes', 5.99, 'cupcakes.jpg');
INSERT into Product (Id, Name, Description, Price, ImageName) VALUES ('Bread', 'Fresh baked French-style bread', 1.49, 'bread.jpg');
INSERT into Product (Id, Name, Description, Price, ImageName) VALUES ('Pear Tart', 'A glazed pear tart topped with sliced almonds and a dash of cinnamon', 5.99, 'pear_tart.jpg');
INSERT into Product (Id, Name, Description, Price, ImageName) VALUES ('Chocolate Cake', 'Rich chocolate frosting cover this chocolate lover’s dream.', 8.99, 'chocolate_cake.jpg');
