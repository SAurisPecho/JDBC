// import entidades.Cliente;
// import persistencia.ClienteDAO;
// import persistencia.ClienteDAO;
// import persistencia.ProductoDAO;
// import persistencia.PedidoDAO;
// import persistencia.OficinaDAO;
// import servicios.ClienteServicio;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import entidades.DetallePedido;
import entidades.Pedido;
import servicios.PedidoServicio;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            /*ClienteDAO clienteDAO = new ClienteDAO(); 
            Cliente uno = new Cliente(20, "Security S.A.", "Manuel", "Flores", "4578696658", "4578696639", "Miami", "Miami",
                    "USA", "24010", 7, 15000.00);
            Cliente dos = new Cliente(21, "Suns S.A.", "Emilio", "Brans", "4978525256", "4978525245", "Madrid", "Madrid",
                    "SPAIN", "27010", 5, 13000.00);
            clienteDAO.guardarCliente(uno);
            clienteDAO.guardarCliente(dos);
            System.out.println("Lista de clientes:");
            clienteDAO.listarTodosLosClientes();
            clienteDAO.buscarClientePorCodigo(8);
            clienteDAO.eliminarClientePorId(32);
            clienteDAO.listarClientesPorEmpleado(7);
            clienteDAO.incrementarLimiteCredito();*/

            /*ProductoDAO productoDAO = new ProductoDAO();
            productoDAO.eliminarProductoPorCodigo(100);
            productoDAO.modificarRegistroPorId(50, "Ciruelo Friarr", 7.00);
            productoDAO.listarProductosMenorStock();
            productoDAO.listarProductosMenorPrecioVenta();*/

            /*PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.listarPedidosPorCliente(5);
            pedidoDAO.obtenerListarPedidosPorCliente(5);
            pedidoDAO.listarPedidosPorEstado("Entregado");
            pedidoDAO.listarPedidosPorProducto(9);*/

            /*OficinaDAO oficinaDAO = new OficinaDAO();
            oficinaDAO.listarOficinas();
            oficinaDAO.listarOficinasPorPais("EEUU");*/

            /*ClienteServicio cs = new ClienteServicio();
            cs.crearNuevoCliente(99, "Empresa XYZ", "", "Perez", "123456", "78910", "Ciudad", "Region", "Pais", "12345", 10, 5000.0);
            cs.crearNuevoCliente(201, "Empresa XYZ", null, "Perez", "123456", "78910", "Ciudad", "Region", "Pais", "12345", 10, 5000.0);
            cs.listarTodosLosClientes();
            cs.buscarClientePorCodigo(0);*/

            testCrearPedido();
        } catch (Exception e) { 
            System.out.println("Error al crear el pedido: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testCrearPedido() {
        PedidoServicio ps = new PedidoServicio();
        try {
            // Convertir las fechas de tipo String a java.sql.Date
            Date fechaPedido = Date.valueOf("2014-11-17");
            Date fechaEsperada = Date.valueOf("2014-12-17");
            Date fechaEntrega = Date.valueOf("2014-12-10");

            // Crear una lista de DetallePedido
            List<DetallePedido> detalles = new ArrayList<>();
            DetallePedido detallePedido1 = new DetallePedido();
            detallePedido1.setIdProducto(5);
            detallePedido1.setCantidad(11);
            detallePedido1.setPrecioUnidad(9.5);
            detallePedido1.setNumero_linea((short)4);
            detalles.add(detallePedido1);

            ps.guardarPedido(new Pedido(67, fechaPedido, fechaEsperada, fechaEntrega, "Entregado", "Prueba", 21 ), detalles);
            System.out.println("Pedido creado exitosamente");
        } catch (Exception e) {
            System.out.println("Error al crear el pedido: " + e.getMessage());
        }
    }
}