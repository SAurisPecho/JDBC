import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import entidades.DetallePedido;
import entidades.Pedido;
import persistencia.ProductoDAO;
import servicios.ClienteServicio;
import servicios.PedidoServicio;
import servicios.ProductoServicio;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private ClienteServicio clienteServicio;
    private PedidoServicio pedidoServicio;
    private ProductoDAO productoDAO;
    private ProductoServicio productoServicio;

    public void menuGeneral() {
        clienteServicio = new ClienteServicio();
        pedidoServicio = new PedidoServicio();
        productoDAO = new ProductoDAO();
        productoServicio = new ProductoServicio();
        int opcion = 0;

        do {
            System.out.println("MENU PRINCIPAL");
            System.out.println("1. Servicios de Cliente");
            System.out.println("2. Servicios de Pedido");
            System.out.println("3. Servicios de Producto");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> menuServicioCliente();
                case 2 -> menuServicioPedido();
                case 3 -> menuServicioProducto();
                case 4 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
        
    }
    
    private void menuServicioCliente() {
        int opcion;
        do {
            System.out.println("===== Menú de Clientes =====");
            System.out.println("1. Crear nuevo cliente");
            System.out.println("2. Listar todos los clientes");
            System.out.println("3. Buscar cliente por código");
            System.out.println("4. Salir");
            System.out.println("============================");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> crearNuevoCliente();
                case 2 -> listarTodosLosClientes();
                case 3 -> buscarClientePorCodigo();
                case 4 -> System.out.println("Saliendo al menu principal...");           
                default -> System.out.println("Opción no válida, por favor seleccione una opción válida.");
            }
            System.out.println();
        } while (opcion != 4);
    }
    
    
    private void menuServicioPedido() {
        int opcion;
        do {
            System.out.println("=====  Menú de Pedidos ===== ");
            System.out.println("1. Guardar Pedido");
            System.out.println("2. Listar Pedidos por Cliente");
            System.out.println("3. Listar Pedidos por Estado");
            System.out.println("4. Listar Pedidos por Producto");
            System.out.println("5. Salir");
            System.out.println("============================");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> guardarPedido();
                case 2 -> listarPedidosPorCliente();
                case 3 ->listarPedidosPorEstado();
                case 4 -> listarPedidosPorProducto();
                case 5 -> System.out.println("Saliendo al menu principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            } 
            System.out.println();
        } while (opcion != 5);
    }
    
    private void menuServicioProducto() {
        int opcion;
        do {
            System.out.println("=====  Menú de Productos ===== ");
            System.out.println("1. Eliminar Producto por Código");
            System.out.println("2. Modificar Producto por ID");
            System.out.println("3. Listar Productos con Menor Stock");
            System.out.println("4. Salir");
            System.out.println("==============================");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> eliminarProductoPorCodigo();
                case 2 -> modificarRegistroPorId();
                case 3 -> listarProductosMenorStock();
                case 4 -> System.out.println("Saliendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 4);
    }
    

    //Metodos para menu Cliente
    private void crearNuevoCliente() {
        try {
            System.out.println("Ingrese los datos del cliente:");
            System.out.print("Código del Cliente: ");
            int codigoCliente = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Nombre del Contacto: ");
            String nombreContacto = sc.nextLine();
            System.out.print("Apellido del Contacto: ");
            String apellidoContacto = sc.nextLine();
            System.out.print("Teléfono: ");
            String telefono = sc.nextLine();
            System.out.print("Fax: ");
            String fax = sc.nextLine();
            System.out.print("Ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("Región: ");
            String region = sc.nextLine();
            System.out.print("País: ");
            String pais = sc.nextLine();
            System.out.print("Código Postal: ");
            String codigoPostal = sc.nextLine();
            System.out.print("ID del Empleado: ");
            int idEmpleado = sc.nextInt();
            System.out.print("Límite de Crédito: ");
            double limiteCredito = sc.nextDouble();

            clienteServicio.crearNuevoCliente(codigoCliente, nombre, nombreContacto, apellidoContacto, telefono, fax, ciudad, region, pais, codigoPostal, idEmpleado, limiteCredito);
            System.out.println("Se ha creado el nuevo cliente");
        } catch (Exception e) {
            System.out.println("Error al crear el cliente: "+e.getMessage());
        }
    }
    
    private void listarTodosLosClientes() {
        try {
            System.out.println("Lista de todos los clientes:");
            clienteServicio.listarTodosLosClientes();
        } catch (Exception e) {
            System.out.println("Error al listar los clientes: " + e.getMessage());
        }
    }

    private void buscarClientePorCodigo() {
        try {
            System.out.print("Ingrese el código del cliente a buscar: ");
            int codigo = sc.nextInt();
            clienteServicio.buscarClientePorCodigo(codigo);
        } catch (Exception e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
    }

    // Metodos para menu Pedido
    private void guardarPedido() {
        try {
            System.out.println("Ingrese el código del pedido: ");
            int codigoPedido = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer
            System.out.println("Ingrese la fecha del pedido (formato: yyyy-MM-dd): ");
            Date fechaPedido = Date.valueOf(sc.nextLine()); // Convertir a Date
            System.out.println("Ingrese la fecha esperada (formato: yyyy-MM-dd): ");
            Date fechaEsperada = Date.valueOf(sc.nextLine()); // Convertir a Date
            System.out.println("Ingrese la fecha de entrega (formato: yyyy-MM-dd o deje en blanco si no hay): ");
            String fechaEntregaStr = sc.nextLine();
            Date fechaEntrega = fechaEntregaStr.isEmpty() ? null : Date.valueOf(fechaEntregaStr); // Si no hay, asigna null
            System.out.println("Ingrese el estado del pedido: ");
            String estado = sc.nextLine();
            System.out.println("Ingrese los comentarios del pedido: ");
            String comentarios = sc.nextLine();
            System.out.println("Ingrese el ID del cliente: ");
            int idCliente = sc.nextInt();

            List<DetallePedido> detalles = new ArrayList<>();
            char agregarOtro;
            do {
                System.out.println("Ingrese el ID del producto: ");
                int idProducto = sc.nextInt();
                System.out.println("Ingrese la cantidad del producto: ");
                int cantidad = sc.nextInt();
                System.out.println("Ingrese el precio unitario del producto: ");
                double precioUnidad = sc.nextDouble();
                System.out.println("Ingrese el número de línea del detalle: ");
                short numeroLinea = sc.nextShort();
                sc.nextLine(); // Limpiar el buffer

                detalles.add(new DetallePedido(0, idProducto, cantidad, precioUnidad, numeroLinea));
                System.out.println("¿Desea agregar otro detalle? (si = s/no = n): ");
                agregarOtro = sc.nextLine().toLowerCase().charAt(0);
            } while (agregarOtro == 's');
            
            pedidoServicio.guardarPedido(new Pedido(codigoPedido, fechaPedido, fechaEsperada, fechaEntrega, estado, comentarios, idCliente), detalles);
            System.out.println("Pedido creado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al crear el pedido: " + e.getMessage());
        }
    }

    private void listarPedidosPorCliente() {
        try {
            System.out.print("Ingrese el ID del cliente: ");
            int idCliente = sc.nextInt();
            pedidoServicio.listarPedidosPorCliente(idCliente);
        } catch (Exception e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }
    }

    private void listarPedidosPorEstado() {
        try {
            System.out.print("Ingrese el estado de los pedidos: ");
            String estado = sc.nextLine();
            pedidoServicio.listarPedidosPorEstado(estado);
        } catch (Exception e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }
    }

    private void listarPedidosPorProducto() {
        try {
            System.out.print("Ingrese el ID del producto de los pedidos: ");
            int producto = sc.nextInt();
            pedidoServicio.listarPedidosPorProducto(producto);
        } catch (Exception e) {
            System.out.println("Error al listar pedidos: " + e.getMessage());
        }
    }

    // Metodos para menu Productos
    private void eliminarProductoPorCodigo () {
        try {
            System.out.print("Ingrese código del producto a eliminar: ");
            int idProducto = sc.nextInt();
            productoServicio.eliminarProductoPorCodigo(idProducto);
        } catch (Exception e) {
            System.out.println("Error al eliminar producto: " + e.getMessage());
        }
    }

    private void modificarRegistroPorId () {
        try {
            System.out.print("Ingrese ID del producto a modificar: ");
            int idProducto = sc.nextInt();
            sc.nextLine(); // Limpiar buffer
            System.out.print("Ingrese nuevo nombre del producto: ");
            String nombreNuevo = sc.nextLine();
            System.out.print("Ingrese nuevo precio del producto: ");
            double nuevoPrecio = sc.nextDouble();
            productoServicio.modificarRegistroPorId(idProducto, nombreNuevo, nuevoPrecio);
        } catch (Exception e) {
            System.out.println("Error al modificar producto: " + e.getMessage());
        }
    }

    private void listarProductosMenorStock () {
        try {
            productoDAO.listarProductosMenorStock();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.menuGeneral();
    }
}
