INSERT INTO "Category" (name) VALUES ('Food');
INSERT INTO "Category" (name) VALUES ('Transportation');
INSERT INTO "Category" (name) VALUES ('Utilities');
INSERT INTO "Category" (name) VALUES ('Housing');
INSERT INTO "Category" (name) VALUES ('Healthcare');
INSERT INTO "Category" (name) VALUES ('Entertainment');
INSERT INTO "Category" (name) VALUES ('Education');
INSERT INTO "Category" (name) VALUES ('Clothing');
INSERT INTO "Category" (name) VALUES ('Personal Care');
INSERT INTO "Category" (name) VALUES ('Miscellaneous');

--users

INSERT INTO "User" (username,password,email) VALUES ('micheal','micheal123','micheal@email.com');
INSERT INTO "User" (username,password,email) VALUES ('joe','joe123','joe@email.com');
INSERT INTO "User" (username,password,email) VALUES ('lilly','lilly123','lilly@email.com');

INSERT INTO "Expense" (user_id,category_id,amount,date,description) VALUES (3,1,600.00,'2024-09-20','had dinner at the food house restaurant');
INSERT INTO "Expense" (user_id,category_id,amount,date,description) VALUES (3,7,100.00,'2024-08-30','Paid school fees');
INSERT INTO "Expense" (user_id,category_id,amount,date,description) VALUES (3,5,600.00,'2024-09-19','Went to buy dress for the dinner tomorrow');
INSERT INTO "Expense" (user_id,category_id,amount,date,description) VALUES (3,1,2500.00,'2024-09-19','Bought groceries');



