package persistencia;

import entidades.Producto;
import java.util.List;
import java.util.ArrayList;

public class ProductoDAO extends DAO{
    
    public void eliminarProductoPorCodigo (int idProducto) throws Exception{
        try {
            String sql = "DELETE FROM producto WHERE id_producto = "+ idProducto;
            insertarModificarEliminarDataBase(sql);
            System.out.println("El producto " + idProducto + " eliminado");
        } catch (Exception e) {
            throw new Exception("Producto no eliminado correctamente: " + e.getMessage());
        } 
    }

    public void modificarRegistroPorId (int idProducto, String nombreNuevo, double nuevoPrecio ) throws Exception {
        try {
            String sql = "UPDATE producto SET nombre = '"+nombreNuevo+"', precio_venta = "+nuevoPrecio+" WHERE id_producto = "+idProducto;
            insertarModificarEliminarDataBase(sql);
            System.out.println("El producto con ID " + idProducto + " ha sido modificado");
        } catch (Exception e) {
            throw new Exception("Producto no modificado correctamente: " + e.getMessage());
        }
    }

    public List<Producto> listarProductosMenorStock () throws Exception {
        List<Producto> productos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM producto ORDER BY cantidad_en_stock ASC LIMIT 3"; //ordena los resultados en orden ascendente según los valores de una columna, y LIMIT restringe el número de filas devueltas.
            consultarDataBase(sql);
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(resultSet.getInt("id_producto"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setIdGama(resultSet.getInt("id_gama"));
                producto.setCantidadStock(resultSet.getShort("cantidad_en_stock"));
                producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                
                productos.add(producto);
            }
            System.out.println("Productos con menor stock: ");
            for(Producto producto : productos) {
                System.out.println(producto.toString());
            }
        } catch (Exception e) {
            System.out.println("Error al listar los productos con bajo stock: " + e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        }
        return productos;
    }

    public List<Producto> listarProductosMenorPrecioVenta () throws Exception {
        List<Producto> productos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM producto ORDER BY precio_venta ASC LIMIT 4";
            consultarDataBase(sql);
            while (resultSet.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(resultSet.getInt("id_producto"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setCantidadStock(resultSet.getShort("cantidad_en_stock"));
                producto.setPrecioVenta(resultSet.getDouble("precio_venta"));
                
                productos.add(producto);
            }
            for (Producto producto : productos) {
                System.out.println(producto.toString());
            }
        } catch (Exception e) {
            System.out.println("Error al listar los productos con menor precio de venta: " + e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        }
        return productos;
    }
}
