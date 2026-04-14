INSERT INTO productos (nombre, precio_unitario, categoria, cantidad, unidad_medida)
SELECT 'Arroz', 35.50, 'ALIMENTO', 10, 'LIBRA'
WHERE NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Arroz');

INSERT INTO productos (nombre, precio_unitario, categoria, cantidad, unidad_medida)
SELECT 'Paracetamol', 120.00, 'MEDICAMENTO', 25, 'UNIDAD'
WHERE NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Paracetamol');

INSERT INTO productos (nombre, precio_unitario, categoria, cantidad, unidad_medida)
SELECT 'Leche Entera', 80.00, 'ALIMENTO', 12, 'LITRO'
WHERE NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Leche Entera');

INSERT INTO productos (nombre, precio_unitario, categoria, cantidad, unidad_medida)
SELECT 'Alcohol', 95.00, 'MEDICAMENTO', 8, 'MILILITRO'
WHERE NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Alcohol');

INSERT INTO productos (nombre, precio_unitario, categoria, cantidad, unidad_medida)
SELECT 'Azucar', 42.75, 'MATERIA_PRIMA', 15, 'KILOGRAMO'
WHERE NOT EXISTS (SELECT 1 FROM productos WHERE nombre = 'Azucar');
