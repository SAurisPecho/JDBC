package persistencia;
import java.sql.SQLException;
import entidades.DetallePedido;

public class DetallePedidoDAO extends DAO {

    public void guardarDetallePedido(DetallePedido detalle) throws Exception {
        try {
            if (detalle == null) {
                throw new Exception("El detalle del pedido no puede ser nulo.");
            }
            String sql = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unidad, numero_linea) VALUES ('"+
                        detalle.getIdPedido()+"', '"+
                        detalle.getIdProducto()+"', '"+
                        detalle.getCantidad()+"', '"+
                        detalle.getPrecioUnidad()+"', '"+
                        detalle.getNumero_linea()+"')";

            insertarModificarEliminarDataBase(sql);
            System.out.println("Se guardo el detalle pedido correctamente.");
        } catch (SQLException e) {
            throw new Exception("Error al guardar el detalle del pedido: "+e.getMessage());
        } finally {
            desconectarDataBase();
        }
    }
}