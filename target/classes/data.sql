INSERT INTO users (username, password) VALUES ('admin', '$2a$12$aM65i/IxUbVof3gkA4rMxOdiiyiLq8cqm5DrLcNc66eIr2UsAfuMa');
INSERT INTO users (username, password) VALUES ('user1', '$2a$12$DDKbBljuUG.C8VQ8B6oymeGVE7eBmUgfJ6Sp7NiO0lv0tAKqqevqa');
INSERT INTO users (username, password) VALUES ('user2', '$2a$12$l6rvGUuFmfVgxLoJtKHoIOQJ/PNfu6Hz6qaiMCSPJAJMuCILaTBNe');
INSERT INTO users (username, password) VALUES ('user3', '$2a$12$VumOqPrDKp0xQ5i1tUobV.nTih1h1wjIwWFz0jEQuG24xBj7XZeMO');



INSERT INTO transactions (transaction_id, account_number, amount, transaction_date, transaction_status, transaction_type, currency_code) 
VALUES (1, 12345678933, 120, parsedatetime('17-09-2021 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 'SUCCESS', 'CREDIT', 'AED');
INSERT INTO transactions (transaction_id, account_number, amount, transaction_date, transaction_status, transaction_type, currency_code) 
VALUES (2, 12345678933, 21, parsedatetime('19-09-2021 18:47:52.69', 'dd-MM-yyyy hh:mm:ss.SS'), 'SUCCESS', 'CREDIT', 'AED');



INSERT INTO accounts (account_number, available_amount, userid, currency_code) 
VALUES (12345678933, 2000, 1, 'AED');
INSERT INTO accounts (account_number, available_amount, userid, currency_code) 
VALUES (12345678934, 200, 2, 'AED');