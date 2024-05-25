

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
    battle_counter NUMBER,
    missile_launcher_remaining NUMBER,
    ion_cannon_remaining NUMBER,
    plasma_cannon_remaining NUMBER,
    light_hunter_remaining NUMBER,
    heavy_hunter_remaining NUMBER,
    battle_ship_remaining NUMBER,
    armored_ship_remaining NUMBER,
    FOREIGN KEY (user_id) REFERENCES user_credentials(user_id)
);

--CREAR TABLA DE ESTADISTICAS DE LAS BATALLAS
CREATE TABLE battle_stats(
    planet_id NUMBER,
    num_battles NUMBER PRIMARY KEY NOT NULL,
    resource_metal_acquires NUMBER,
    resource_deuterion_acquires NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id)
);

--CREAR TABLA DE LOS REGISTROS DE LAS BATALLAS
CREATE TABLE battle_log (
    planet_id NUMBER,
    num_battles NUMBER,
    num_line NUMBER PRIMARY KEY NOT NULL,
    log_entry LONG,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id),
    FOREIGN KEY (num_battles) REFERENCES battle_stats(num_battles)
);


--CREAR TABLA DE LAS FLOTAS ENEMIGAS
CREATE TABLE enemy_army (
    num_battles NUMBER,
    light_hunter_sent NUMBER,
    light_hunter_destroyed NUMBER,
    heavy_hunter_sent NUMBER,
    heavy_hunter_destroyed NUMBER,
    battle_ship_sent NUMBER,
    battle_ship_destroyed NUMBER,
    armored_ship_sent NUMBER,
    armored_ship_destroyed NUMBER,
    FOREIGN KEY (num_battles) REFERENCES battle_stats(num_battles)
);

--CREAR TABLA DE LAS DEFENSAS DE LOS PLANETAS EN LAS BATALLAS
CREATE TABLE planet_battle_defense (
    planet_id NUMBER,
    num_battles NUMBER,
    missile_launcher_builded NUMBER,
    missile_launcher_destroyed NUMBER,
    ion_cannon_builded NUMBER,
    ion_cannon_destroyed NUMBER,
    plasma_cannon_builded NUMBER,
    plasma_cannon_destroyed NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id),
    FOREIGN KEY (num_battles) REFERENCES battle_stats(num_battles)
);

--CREAR TABLA DE LA FLOTA DE LOS PLANETAS EN LAS BATALLAS
CREATE TABLE planet_battle_army (
    planet_id NUMBER,
    num_battles NUMBER,
    light_hunter_builded NUMBER,
    light_hunter_destroyed NUMBER,
    heavy_hunter_builded NUMBER,
    heavy_hunter_destroyed NUMBER,
    battle_ship_builded NUMBER,
    battle_ship_destroyed NUMBER,
    armored_ship_builded NUMBER,
    armored_ship_destroyed NUMBER,
    FOREIGN KEY (planet_id) REFERENCES planet_stats(planet_id),
    FOREIGN KEY (num_battles) REFERENCES battle_stats(num_battles)
);


--······························    INSERTAR DATOS    ······························--

--INSERTAR DATOS EN LA TABLA user_credentials
INSERT INTO user_credentials (
    user_id,
    user_password,
    user_name
)
VALUES (
    1,
    'ogame',
    'ogame'
);

--INSERTAR DATOS EN LA TABLA planet_stats 
INSERT INTO planet_stats (
    user_id ,
    planet_id ,
    planet_name ,
    resource_metal_amount ,
    resource_dauterion_amount ,
    resource_defense ,
    resource_attack ,
    battle_counter ,
    missile_launcher_remaining ,
    ion_cannon_remaining ,
    plasma_cannon_remaining ,
    light_hunter_remaining ,
    heavy_hunter_remaining ,
    battle_ship_remaining ,
    armored_ship_remaining
)
VALUES (
    (SELECT user_id FROM user_credentials),
    1,
    'PlanetaPrueba',
    10000,
    10000,
    0,
    0,
    0,
    10,
    10,
    10,
    100,
    10000,
    10,
    12
);

--INSERTAR DATOS EN LA TABLA battle_stats
INSERT INTO battle_stats (
    planet_id,
    num_battles,
    resource_metal_acquires,
    resource_deuterion_acquires
)
VALUES (
    (SELECT planet_id FROM planet_stats),
    1,
    100000,
    100000
);

--INSERTAR DATOS EN LA TABLA battle_log
INSERT INTO battle_log (
    planet_id,
    num_battles,
    num_line,
    log_entry
)
VALUES (
    (SELECT planet_id FROM planet_stats),
    (SELECT num_battles FROM battle_stats),
    1,
    'Esta es la primera entrada del LOG'
);

--INSERTAR DATOS EN LA TABLA enemy_army
INSERT INTO enemy_army (
    num_battles,
    light_hunter_sent,
    light_hunter_destroyed,
    heavy_hunter_sent,
    heavy_hunter_destroyed,
    battle_ship_sent,
    battle_ship_destroyed,
    armored_ship_sent,
    armored_ship_destroyed
)
VALUES (
    (SELECT num_battles FROM battle_stats),
    10,
    5,
    20,
    13,
    30,
    10,
    40,
    15
);

--INSERTAR DATOS EN LA TABLA planet_battle_defense
INSERT INTO planet_battle_defense (
    planet_id,
    num_battles,
    missile_launcher_builded,
    missile_launcher_destroyed,
    ion_cannon_builded,
    ion_cannon_destroyed,
    plasma_cannon_builded,
    plasma_cannon_destroyed
)
VALUES (
    (SELECT planet_id FROM planet_stats),
    (SELECT num_battles FROM battle_stats),
    15,
    3,
    32,
    20,
    80,
    27
);

--INSERTAR DATOS EN LA TABLA planet_battle_army
INSERT INTO planet_battle_army (
    planet_id,
    num_battles,
    light_hunter_builded,
    light_hunter_destroyed,
    heavy_hunter_builded,
    heavy_hunter_destroyed,
    battle_ship_builded,
    battle_ship_destroyed,
    armored_ship_builded,
    armored_ship_destroyed
)
VALUES (
    (SELECT planet_id FROM planet_stats),
    (SELECT num_battles FROM battle_stats),
    80,
    23,
    90,
    29,
    28,
    20,
    89,
    10
);
