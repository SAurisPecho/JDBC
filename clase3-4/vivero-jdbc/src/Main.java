// import entidades.Cliente;
// import persistencia.ClienteDAO;
// import persistencia.ClienteDAO;
// import persistencia.ProductoDAO;
// import persistencia.PedidoDAO;
import persistencia.OficinaDAO;

public class Main {
    public static void main(String[] args) throws Exception {
        // ClienteDAO clienteDAO = new ClienteDAO();
        // Cliente uno = new Cliente(20, "Security S.A.", "Manuel", "Flores", "4578696658", "4578696639", "Miami", "Miami",
        //         "USA", "24010", 7, 15000.00);
        // Cliente dos = new Cliente(21, "Suns S.A.", "Emilio", "Brans", "4978525256", "4978525245", "Madrid", "Madrid",
        //         "SPAIN", "27010", 5, 13000.00);
        
        // clienteDAO.guardarCliente(uno);
        // clienteDAO.guardarCliente(dos);

        // System.out.println("Lista de clientes:");
        // clienteDAO.listarTodosLosClientes();
        // clienteDAO.buscarClientePorCodigo(8);
        // clienteDAO.eliminarClientePorId(22);
        // clienteDAO.listarClientesPorEmpleado(7);
        // clienteDAO.incrementarLimiteCredito();

        // ProductoDAO productoDAO = new ProductoDAO();
        // productoDAO.eliminarProductoPorCodigo(100);
        // productoDAO.modificarRegistroPorId(50, "Ciruelo Friarr", 7.00);
        // productoDAO.listarProductosMenorStock();
        // productoDAO.listarProductosMenorPrecioVenta();

        // PedidoDAO pedidoDAO = new PedidoDAO();
        // pedidoDAO.listarPedidosPorCliente(5);
        // pedidoDAO.obtenerListarPedidosPorCliente(5);
        // pedidoDAO.listarPedidosPorEstado("Entregado");
        // pedidoDAO.listarPedidosPorProducto(9);

        OficinaDAO oficinaDAO = new OficinaDAO();
        // oficinaDAO.listarOficinas();
        oficinaDAO.listarOficinasPorPais("EEUU");
    }
}