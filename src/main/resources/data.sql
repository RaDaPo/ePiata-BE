INSERT INTO user(id, address, no, name, email)
VALUES (1, 'Oradea, Bihor', 2, 'Gica Contra', 'gica@email.com'),
       (2, 'Beius, Bihor', 12, 'Andrei Contra', 'gica@email.com'),
       (3, 'Tinca, Bihor', 22, 'Vitel Capra', 'capra@email.com'),
       (4, 'Stei, Bihor', 23, 'Intrusul Intrusilor', 'intrusul@email.com'),
       (5, 'Marghita, Bihor', 52, 'Ionel Busuioc', 'gica@email.com');

INSERT INTO categories(id, type, name)
VALUES (1, 'FRUITS', 'Mere'),
       (2, 'FRUITS', 'Capsuni'),
       (3, 'FRUITS', 'Visine'),
       (4, 'VEGETABLES', 'Rosii'),
       (5, 'VEGETABLES', 'Castraveti'),
       (6, 'VEGETABLES', 'Ceapa');

INSERT INTO tender(id, description, distance, name, owner, price_unit, status, unit)
VALUES (1, 'Rosii vrem', 'Aci aproape', 'First tender', 'Auchan', '10', true, 'kg'),
       (2, 'Ardei vrem', 'Aci langa', 'Second tender', 'Carrefour', '12', true, 'kg'),
       (3, 'Mango vrem', 'Aci in zona', 'Third tender', 'Profi', '20', true, 'kg'),
       (4, 'Avocado vrem', 'Aci dincolo', 'Fourth tender', 'Livela', '4', true, 'kg'),
       (5, 'Lebenita vrem', 'langa tine', 'Fifth tender', 'Kaufland', '5', true, 'kg'),
       (6, 'Ridichi vrem', 'Bihor', 'Sixth tender', 'Penny', '8', true, 'kg');

