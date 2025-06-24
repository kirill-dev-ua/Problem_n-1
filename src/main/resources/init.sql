
create TABLE IF NOT EXISTS clients (
    id   INT PRIMARY KEY,
    name VARCHAR(100)
);


create TABLE IF NOT EXISTS payments (
    id         INT PRIMARY KEY,
    amount     DECIMAL(10,2),
    client_id  INT,
    FOREIGN KEY (client_id) REFERENCES client(id)
);


insert into clients (id, name)
select i, 'Client ' || i
from generate_series(1, 50) AS i;


insert into payments (id, amount, client_id)
select i,
       round((RANDOM() * 1000 + 100)::NUMERIC, 2) AS amount,
       FLOOR(RANDOM() * 50 + 1)::INT          AS client_id
FROM generate_series(1, 500) AS i;
