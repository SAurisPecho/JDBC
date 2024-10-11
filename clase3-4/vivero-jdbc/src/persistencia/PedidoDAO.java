package persistencia;
import entidades.Pedido;
import java.util.List;
import java.util.ArrayList;

public class PedidoDAO extends DAO {
    
    public void listarPedidosPorCliente (int idCliente) throws Exception {
        try {
            String sql = "SELECT * FROM pedido WHERE id_cliente = "+idCliente;
            consultarDataBase(sql);
            List<Pedido> pedidos = new ArrayList<>();
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(resultSet.getInt("id_pedido"));
                pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
                pedido.setEstado(resultSet.getNString("estado"));

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
            consultarDataBase(sql);
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(resultSet.getInt("id_pedido"));
                pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
                pedido.setEstado(resultSet.getNString("estado"));
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
            consultarDataBase(sql);
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setIdPedido(resultSet.getInt("id_pedido"));
                pedido.setFechaPedido(resultSet.getDate("fecha_pedido"));
                pedido.setEstado(resultSet.getNString("estado"));
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
}
