use dohyunyun;

start transaction;

update accounts set balance = balance - 5000 where accounts.id = 1;
update accounts set balance = balance + 5000 where accounts.id = 2;
INSERT INTO transfer_logs VALUES(1, 'out', 'success', 'yun', 'kim', 5000, now());
INSERT INTO account_logs VALUES(1, 508101283, 'out', 'succees', 5000, now());


savepoint a1;

update accounts set balance = balance -1000000 where accounts.id = 1;

rollback to a1;