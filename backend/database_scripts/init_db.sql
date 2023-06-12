CREATE USER 'batim2'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON rezervacijaKarata.* TO 'batim2'@'%';
GRANT ALL PRIVILEGES ON events.* TO 'batim2'@'%';
GRANT ALL PRIVILEGES ON preporucivanje_sadrzaja_pogodnosti.* TO 'batim2'@'%';
GRANT ALL PRIVILEGES ON auth_service.* TO 'batim2'@'%';
GRANT ALL PRIVILEGES ON upravljanje_filmovima_kartama.* TO 'batim2'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS rezervacijaKarata;
CREATE DATABASE IF NOT EXISTS events;
CREATE DATABASE IF NOT EXISTS preporucivanje_sadrzaja_pogodnosti;
CREATE DATABASE IF NOT EXISTS auth_service;
CREATE DATABASE IF NOT EXISTS upravljanje_filmovima_kartama;