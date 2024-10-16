package servicios;

import persistencia.ProductoDAO;

public class ProductoServicio {
    private ProductoDAO prodD;

    public ProductoServicio() {
        this.prodD = new ProductoDAO();
    }

    public void eliminarProductoPorCodigo(int idProducto) throws Exception {
        // Verificar si el producto existe
        if (idProducto <= 0) {
            throw new Exception("El ID del producto debe ser mayor a 0.");
        }
        prodD.eliminarProductoPorCodigo(idProducto);
    }

    public void modificarRegistroPorId(int idProducto, String nombreNuevo, double nuevoPrecio) throws Exception {
        // Validaciones
        if (idProducto <= 0) {
            throw new Exception("El ID del producto debe ser mayor a 0.");
        }
        if (nuevoPrecio <= 0) {
            throw new Exception("El nuevo precio debe ser mayor que 0.");
        }
        if (nombreNuevo == null || nombreNuevo.trim().isEmpty()) {
            throw new Exception("El nombre del producto no puede estar vacío.");
        }

        // Llamar al método de modificación en ProductoDAO
        prodD.modificarRegistroPorId(idProducto, nombreNuevo, nuevoPrecio);
    }

    
}
