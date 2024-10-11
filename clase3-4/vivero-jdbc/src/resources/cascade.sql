use vivero2;

-- Vas a eliminar un registro de tu tabla Producto, por lo que deberás modificar la estructura de tu tabla para que puedas hacerlo.
-- Esto está relacionado al tratamiento de los registros vinculados con otras tablas, en este caso, eliminaremos en cascada con el detalle pedido producto que 
-- está vinculado con un id_producto.
-- Esta es la sentencia que deberás utilizar en MySQL para impactar la tabla “Producto”

ALTER TABLE detalle_pedido DROP FOREIGN KEY detalle_pedido_ibfk_2;  

ALTER TABLE detalle_pedido
ADD CONSTRAINT fk_detalle_pedido_producto
FOREIGN KEY (id_producto)
REFERENCES producto (id_producto)
ON DELETE CASCADE;

DESCRIBE detalle_pedido;

-- Consulta para Ver Claves Foráneas
SELECT 
    CONSTRAINT_NAME, 
    TABLE_NAME, 
    COLUMN_NAME, 
    REFERENCED_TABLE_NAME, 
    REFERENCED_COLUMN_NAME
FROM 
    information_schema.KEY_COLUMN_USAGE
WHERE 
    TABLE_SCHEMA = 'vivero2'
    AND TABLE_NAME = 'detalle_pedido';
