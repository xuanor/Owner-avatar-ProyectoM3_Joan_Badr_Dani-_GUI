

--····························-- Borrar tablas antiguas ······························--
--drop table armored_ship;
--drop table battle_ship;
--drop table light_hunter;
--drop table heavy_hunter;
--drop table ion_cannon;
--drop table plasma_cannon;
--drop table missile_launcher;
--drop table battle;
--drop table planet_stats;
--drop table user_credentials;


--······························    CREAR TABLAS    ······························--

--CREAR TABLA DE CREDENCIALES DE USUARIOS
CREATE TABLE user_credentials (
    user_id NUMBER PRIMARY key not NULL,
    user_password VARCHAR2(50),
    user_name VARCHAR(15)
);

--CREAR TABLA DE ESTADISTICAS DE LOS PLANETAS
CREATE TABLE planet_stats (
    user_id NUMBER,
    planet_id NUMBER PRIMARY KEY NOT NULL,
    planet_name VARCHAR2(50),
    resource_metal_amount NUMBER,
    resource_dauterion_amount NUMBER,
    resource_defense NUMBER,
    resource_attack NUMBER,
    FOREIGN KEY (user_id) REFERENCES user_credentials(user_id)
);


--CREAR TABLA LighHunters
CREATE TABLE light_hunter (
    id NUMBER PRIMARY KEY NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);

--CREAR TABLA heavy_hunters
CREATE TABLE heavy_hunter (
    id NUMBER PRIMARY KEY NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);

--CREAR TABLA battle_ship
CREATE TABLE battle_ship (
    id NUMBER PRIMARY KEY NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);

--CREAR TABLA armored_ship
CREATE TABLE armored_ship (
    id NUMBER PRIMARY KEY NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);

--CREAR TABLA ion_cannon
CREATE TABLE ion_cannon (
   id NUMBER PRIMARY KEY NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);

--CREAR TABLA battle_ship
CREATE TABLE missile_launcher (
    id NUMBER PRIMARY KEY NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);

--CREAR TABLA plasma_cannon
CREATE TABLE plasma_cannon (
   id NUMBER PRIMARY KEY NOT NULL,
    planet_id NUMBER NOT NULL,
    armour NUMBER,
    atack NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);

--CREAR TABLA DE ESTADISTICAS DE LAS BATALLAS
CREATE TABLE battle(
    planet_id NUMBER,
    num_battles NUMBER PRIMARY KEY NOT NULL,
    battle_stats CLOB,
    battle_log CLOB,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);


--······························    INSERTAR DATOS    ······························--

--INSERTAR DATOS EN LA TABLA user_credentials
INSERT INTO user_credentials (user_id,user_password,user_name)VALUES (1,'ogame','ogame');
INSERT INTO user_credentials (user_id,user_password,user_name)VALUES (2,'pepe','pepe');

--INSERTAR DATOS EN LA TABLA planet_stats 
INSERT INTO planet_stats (
    user_id ,
    planet_id ,
    planet_name ,
    resource_metal_amount ,
    resource_dauterion_amount ,
    resource_defense ,
    resource_attack 
)
VALUES ( 1,1,'ANDROMEDA-5',180000, 26000, 0, 0);

INSERT INTO planet_stats (
    user_id ,
    planet_id ,
    planet_name ,
    resource_metal_amount ,
    resource_dauterion_amount ,
    resource_defense ,
    resource_attack 
)
VALUES (2,2,'ALFA CENTAURI',25000, 31000, 0, 0);


-- 15
INSERT INTO light_hunter (id, planet_id,armour,atack)VALUES (1,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (2,1,400,80);
INSERT INTO light_hunter (id, planet_id,armour,atack)VALUES (3,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (4,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (5,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (6,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (7,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (8,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (9,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (10,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (11,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (12,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (13,1,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (14,1,400,80);
INSERT INTO light_hunter (id, planet_id,armour,atack)VALUES (15,1,400,80);

-- 5 
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (1,1,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (2,1,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (3,1,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (4,1,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (5,1,1000,150);

-- 1
INSERT INTO armored_ship ( id, planet_id,armour,atack)VALUES (1,1,8000,700);

-- EJERCITO OGAME
-- 10
INSERT INTO missile_launcher (id, planet_id,armour,atack)VALUES (1,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (2,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (3,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (4,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (5,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (6,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (7,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (8,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (9,1,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (10,1,200,80);

-- 2
INSERT INTO ion_cannon (id, planet_id,armour,atack)VALUES (1,1,1200,250);
INSERT INTO ion_cannon (id, planet_id,armour,atack)VALUES (2,1,1200,250);


-- EJERCITO PEPE
-- 12
INSERT INTO light_hunter (id, planet_id,armour,atack)VALUES (1,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (2,2,400,80);
INSERT INTO light_hunter (id, planet_id,armour,atack)VALUES (3,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (4,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (5,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (6,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (7,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (8,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (9,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (10,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (11,2,400,80);
INSERT INTO light_hunter ( id, planet_id,armour,atack)VALUES (12,2,400,80);


-- 10
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (1,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (2,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (3,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (4,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (5,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (6,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (7,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (8,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (9,2,1000,150);
INSERT INTO heavy_hunter (id, planet_id,armour,atack)VALUES (10,2,1000,150);

-- 2
INSERT INTO armored_ship ( id, planet_id,armour,atack)VALUES (1,2,8000,700);
INSERT INTO armored_ship ( id, planet_id,armour,atack)VALUES (2,2,8000,700);



-- 7
INSERT INTO missile_launcher (id, planet_id,armour,atack)VALUES (1,2,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (2,2,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (3,2,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (4,2,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (5,2,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (6,2,200,80);
INSERT INTO missile_launcher ( id, planet_id,armour,atack)VALUES (7,2,200,80);


-- 1
INSERT INTO ion_cannon (id, planet_id,armour,atack)VALUES (1,2,1200,250);




