package persistencia;
import entidades.DetallePedido;
import entidades.Pedido;
import java.util.List;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO extends DAO {
    
    public void listarPedidosPorCliente (int idCliente) throws Exception {
        try {
            String sql = "SELECT * FROM pedido WHERE id_cliente = "+idCliente;
            ResultSet rs = consultarDataBase(sql);
            List<Pedido> pedidos = new ArrayList<>();
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setFechaPedido(rs.getDate("fecha_pedido"));
                pedido.setEstado(rs.getString("estado"));

                pedidos.add(pedido);
            }
            
            for (Pedido pedido : pedidos) {
                pedido.imprimirIdFechaEstado();
            }
        } catch (Exception e) {
            throw new Exception("Error no hay pedidos: "+e.getMessage());
        } finally {
            desconectarDataBase();
        }
    }

    public List<Pedido> obtenerListarPedidosPorCliente(int idCliente) throws Exception {
        String sql = "SELECT * FROM pedido WHERE id_cliente = " + idCliente;
        consultarDataBase(sql);
        List<Pedido> pedidos = new ArrayList<>();
        while (resultSet.next()) {
            Pedido pedido = new Pedido();
            pedido.setIdPedido(resultSet.getInt("id_pedido"));
            pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
            pedido.setEstado(resultSet.getNString("estado"));

            pedidos.add(pedido);
        }
        return pedidos;
    }

    public List<Pedido> listarPedidosPorEstado (String estado) throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pedido WHERE estado = '"+estado+"'";
            ResultSet rs = consultarDataBase(sql);
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setFechaPedido(rs.getDate("fecha_pedido"));
                pedido.setEstado(rs.getString("estado"));
                pedidos.add(pedido);
            }
            for (Pedido pedido : pedidos) {
                pedido.imprimirIdFechaEstado();
            }
        } catch (Exception e) {
            throw new Exception("Error no hay pedidos: "+e.getMessage());
        } finally {
            desconectarDataBase();
        }
        return pedidos;
    }

    public List<Pedido> listarPedidosPorProducto (int idProducto) throws Exception {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM pedido p JOIN detalle_pedido dp ON p.id_pedido = dp.id_pedido WHERE dp.id_pedido = "+idProducto;
            ResultSet rs = consultarDataBase(sql);
            while (rs.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(rs.getInt("id_pedido"));
                pedido.setFechaPedido(rs.getDate("fecha_pedido"));
                pedido.setEstado(rs.getString("estado"));
                pedidos.add(pedido);
            }
            for (Pedido pedido : pedidos) {
                pedido.imprimirIdFechaEstado();
            }
        } catch (Exception e) {
            throw new Exception("Error no hay pedidos: "+e.getMessage());
        } finally {
            desconectarDataBase();
        }
        return pedidos;
    }

    public void guardarPedido(Pedido pedido, List<DetallePedido> detalles) throws SQLException, ClassNotFoundException {
        try {
            String sql = "INSERT INTO pedido (codigo_pedido, fecha_pedido, fecha_esperada, fecha_entrega, estado, comentarios, id_cliente) VALUES ('"
                    + pedido.getCodigoPedido() + "', '"
                    + new Date (pedido.getFechaPedido().getTime()) + "', '"
                    + new Date (pedido.getFechaEsperada().getTime()) + "', "
                    + (pedido.getFechaEntrega() != null ? "'"+ new Date(pedido.getFechaEntrega().getTime())+"'" : "NULL") + ", '"
                    + pedido.getEstado() + "', '"
                    + pedido.getComentarios() + "', '"
                    + pedido.getIdCliente() + "')";

            insertarModificarEliminarDataBase(sql);
            String sqlId = "SELECT id_pedido FROM pedido ORDER BY id_pedido DESC LIMIT 1"; // Obtener el ID del pedido recién insertado
            ResultSet rst = consultarDataBase(sqlId);
            int idPedido = 0;
            if (rst.next()) {
                idPedido = rst.getInt(1);  // Si hay un resultado, se almacena en idPedido
            }
            
             // Insertar los detalles del pedido
            for (DetallePedido detalle : detalles) {
                String sqlDetalle = "INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_unidad, numero_linea) "+
                                "VALUES ("+idPedido+", "+detalle.getIdProducto()+", "+detalle.getCantidad()+", "+detalle.getPrecioUnidad()+", "+detalle.getNumero_linea()+")";
                insertarModificarEliminarDataBase(sqlDetalle);
            }
            System.out.println("Pedido guardado correctamente, con ID: "+idPedido);

        } catch (SQLException | ClassNotFoundException e) {
            if (conexion != null && !conexion.isClosed()) {
                try {
                    conexion.rollback();  // Deshacer cambios en caso de error
                } catch (SQLException ex) {
                    System.out.println("Error en rollback: " + ex.getMessage());
                }
            }
            System.out.println("Error al guardar el pedido: " + e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            desconectarDataBase();
        }
    }
}