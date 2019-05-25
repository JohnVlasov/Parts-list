CREATE DATABASE IF NOT EXISTS test COLLATE utf8_general_ci;

USE test;

DROP TABLE IF EXISTS part;

CREATE TABLE part
(
    id BIGINT(20)  NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE NOT NULL,
    need BIT(1) DEFAULT 0,
    quantity INT(4)  NOT NULL DEFAULT 0,
	PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = UTF8 COLLATE utf8_unicode_ci;
	
insert into part(name, need, quantity)
values ('материнская плата', true, 20)
     , ('звуковая карта', false, 4)
	 , ('сетевой адаптер 10/100', false, 5)
	 , ('WiFi адаптер', false, 10)
	 , ('процессор', true, 15)
	 , ('память', true, 24)
	 , ('диск HDD 1TB', false, 8)
	 , ('диск SSD 240GB', true, 2)
	 , ('DVD привод', false, 2)
	 , ('корпус', true, 30)
	 , ('видеокарта', false, 10)
	 , ('шнур питания', true, 100)
	 , ('USB контроллер PCI-E', false, 2)
	 , ('RAID контроллер SATA', false, 2)
	 , ('кабель VGA 1.8м', false, 2)
	 , ('кабель SATA 0.4м',true, 100)
	 , ('кулер для CPU', true, 50)
	 , ('кулер для корпуса', false, 10)
	 , ('блок питания 600W', false, 5)
	 , ('клавиатура', true, 25)
	 , ('мышка', true, 2)
	 , ('устройство для чтения SD/CF карт', false, 5)
	 , ('монитор', false, 14)
	 , ('коврик для мышки', false, 30)
	 , ('салазки для SSD 2\,5'' на 3\,5''', false, 8);