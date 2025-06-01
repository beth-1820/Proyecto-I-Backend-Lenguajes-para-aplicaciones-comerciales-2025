-- 1. Eliminar datos en orden por dependencias
DELETE FROM Ejercicio;
DELETE FROM Equipamiento;
DELETE FROM CategoriaEjercicio;

-- 2. Reiniciar contadores de identidad
DBCC CHECKIDENT ('Ejercicio', RESEED, 0);
DBCC CHECKIDENT ('Equipamiento', RESEED, 0);
DBCC CHECKIDENT ('CategoriaEjercicio', RESEED, 0);


-- Categorías
INSERT INTO CategoriaEjercicio (nombre_categoria) VALUES ('Espalda');

-- Equipamiento
INSERT INTO Equipamiento (nombre_equipo, tipo) VALUES ('Mancuernas', 'Libre');

-- Ejercicios
INSERT INTO Ejercicio (nombre_ejercicio, descripcion, cod_categoria, cod_equipo)
VALUES ('Press Banca', 'Ejercicio de pecho', 1, 1);