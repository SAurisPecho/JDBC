import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class App extends DAO {
    public static void main(String[] args) {
        App app = new App();
        try {
            //conectamos a la base de datos
            app.connectarDataBase();

            // buscarClientes(app.conexion);
            // buscarClientePorCodigo(app.conexion, 2);
            // buscarClientesPorEmpleado(app.conexion, 11);
            // getProductosParaReponer(app.conexion, 14);
            // getProductosGama(app.conexion, 4);
            // getPedidosPorCliente(app.conexion, 3);
            // getPedidosPorEstado(app.conexion, "Rechazado");

            // getProductosPorGamaList(app.conexion);
            // getProductosNoComprados(app.conexion);
            // getPedidosPorProducto(app.conexion, 10);
            // getPedidosPorFechas(app.conexion, "2005-01-01", "2008-12-12");
            // getProductosPorProveedor(app.conexion, "Frutales Talavera S.A");
            getPedidosPorClienteCompleto(app.conexion, 3);
        } catch (Exception e) {
            System.out.println("Error:"+e.getMessage());
        } finally {
            try {
                // Desconectamos de la base de datos
                app.desconectarDataBase();
            } catch (Exception e) {
                System.out.println("Error al desconectar: " + e.getMessage());
            }
        }
    }

public static void buscarClientes(Connection conexion) {
        String sql = "SELECT nombre_contacto, apellido_contacto, telefono FROM cliente ";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                String nombre = rs.getString("nombre_contacto");
                String apellido = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                count++;
                System.out.println(count + " - " + nombre + " " + apellido + " -  " + telefono);
            }
            // Cerrar ResultSet y Statement dentro del bloque try-catch-finally
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void buscarClientePorCodigo(Connection conexion , int codigo ){
        String sql = "SELECT * FROM cliente WHERE codigo_cliente = "+codigo;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println("Código: " + rs.getInt("codigo_cliente"));
                System.out.println("Nombre: " + rs.getString("nombre_contacto"));
                System.out.println("Apellido: " + rs.getString("apellido_contacto"));
                System.out.println("Teléfono: " + rs.getString("telefono"));
                System.out.println("Pais: " + rs.getString("pais"));
            } else {
                System.out.println("No se encontro ningun cliente con el codigo "+codigo);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void buscarClientesPorEmpleado(Connection conexion, int codigoEmpleado){
        String sql = "SELECT cliente.* "+
                    "FROM cliente "+
                    "JOIN empleado ON empleado.id_empleado = cliente.id_empleado "+
                    "WHERE empleado.codigo_empleado = "+codigoEmpleado;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;

            System.out.println("Num. | cod. |     Nombre cliente     |   Telefono    |   Pais   | Cod.Postal | Limite De Credito ");
            while (rs.next()) {
                int codigoCliente = rs.getInt("codigo_cliente");
                String nombreContacto = rs.getString("nombre_contacto");
                String apellidoContacto = rs.getString("apellido_contacto");
                String telefono = rs.getString("telefono");
                String pais = rs.getString("pais");
                String codigoPostal = rs.getString("codigo_postal");
                double limiteCredito = rs.getDouble("limite_credito");
                count++;
                System.out.println(" "+count+ "   -  " + codigoCliente + "       " + nombreContacto + " " + apellidoContacto + "    -   " + telefono + "  -    "+ pais + "  -    "+ codigoPostal + "    -     " + limiteCredito);
                System.out.println("------------------------------------------------------------------------------------------------------");
            }
            if (count == 0) {
                System.out.println("No se encontro ningun cliente asociado a el codigo del empleado: "+codigoEmpleado);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getProductosParaReponer(Connection conexion, int puntoReposicion){
        String sql = "SELECT id_producto, codigo_producto, nombre, cantidad_en_stock FROM producto WHERE cantidad_en_stock < "+puntoReposicion;
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String codigo = rs.getString("codigo_producto");
                String nombre = rs.getString("nombre");
                int stock = rs.getInt("cantidad_en_stock");
                count++;
                System.out.println("ID: " + id);
                System.out.println("Código: " + codigo);
                System.out.println("Nombre: " + nombre);
                System.out.println("Cantidad en Stock: " + stock);
                System.out.println("---------------------------");
            }
            if (count == 0) {
                System.out.println("No se encontro ningun producto con stock menor a : "+puntoReposicion);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    public static void getProductosGama(Connection conexion, int id) {
        String sql = "SELECT p.codigo_producto, p.nombre, g.id_gama, g.gama "+
                    "FROM producto p "+
                    "JOIN gama_producto g ON g.id_gama = p.id_gama "+
                    "WHERE g.id_gama = "+id;

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int count = 0;
            while (rs.next()) {
                String codigoProducto = rs.getString("codigo_producto");
                String nombreProducto = rs.getString("nombre");
                int idGama = rs.getInt("id_gama");
                String nombrGama = rs.getString("gama");
                count++;
                System.out.println("Codigo Producto: "+codigoProducto+" - Nombre: "+nombreProducto+" - ID Gama: "+idGama+" - Gama: "+nombrGama);
                System.out.println("------------------------------------------------------------------------------------------------");
            }
            if (count == 0 ) {
                System.out.println("No se encontro ningun producto de la gama con ID: "+id);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getPedidosPorCliente(Connection conexion, int idCliente){
        String sql = "SELECT pedido.id_pedido, pedido.codigo_pedido, pedido.estado, pedido.id_cliente FROM pedido WHERE pedido.id_cliente = ? ";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            if (count == 0) {
                System.out.println("Pedidos del cliente con ID " + idCliente + ":");
            }
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                int codigoPedido = rs.getInt("codigo_pedido");
                String estado = rs.getString("estado");
                int idClient = rs.getInt("id_cliente");
                count++;
                
                System.out.println("Pedido "+count+" : ");
                System.out.println("ID Pedido: " + idPedido);
                System.out.println("Codigo Pedido: " + codigoPedido);
                System.out.println("Estado del Pedido: "+estado);
                System.out.println("ID Cliente: " + idClient);
                System.out.println("---------------------------");
                }
                if (count == 0) {
                    System.out.println("No se encontraron pedidos para el cliente con ID " + idCliente);
                } else{
                    System.out.println("Total de pedidos del cliente es: " + count);
                }

                rs.close();
                pstmt.close();
        } catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getPedidosPorEstado(Connection conexion, String estado) {
        String sql = "SELECT id_pedido, estado, id_cliente FROM pedido WHERE pedido.estado = ?";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, estado);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            if (count == 0) {
                System.out.println("Pedidos en estado " + estado + ":");
            }
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                String estad = rs.getString("estado");
                int idClient = rs.getInt("id_cliente");
                count++;
                System.out.println("Pedido "+count+" : ");
                System.out.println("ID Pedido: " + idPedido);
                System.out.println("Estado del Pedido: "+estad);
                System.out.println("ID Cliente: " + idClient);
                System.out.println("---------------------------");
            }
            if (count == 0) {
                System.out.println("No se encontraron pedidos con estado " + estado);
            } else{
                System.out.println("Total de pedidos con estado "+estado+": " + count);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    //Complementarios
    
    public static void getProductosPorGamaList(Connection conexion) {
        String sql = "SELECT COUNT(*) AS cantidad, gama_producto.gama "+
                        "FROM producto "+
                        "JOIN gama_producto ON producto.id_gama = gama_producto.id_gama "+
                        "GROUP BY gama_producto.gama";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int cantidad =  rs.getInt("cantidad");
                String gama = rs.getString("gama");
                System.out.println("Gama: "+gama+" - Cantidad: "+cantidad);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getProductosNoComprados(Connection conexion ) {
        String sql = "SELECT nombre FROM producto WHERE id_producto NOT IN(SELECT id_producto FROM detalle_pedido)";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                System.out.println("Producto no comprado: "+nombre);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }
    
    public static void getPedidosPorProducto(Connection conexion, int idProducto) {
        String sql = "SELECT pedido.id_pedido, pedido.estado FROM pedido JOIN detalle_pedido ON pedido.id_pedido = detalle_pedido.id_pedido WHERE detalle_pedido.id_producto = ?";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, idProducto);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                String estado = rs.getString("estado");
                System.out.println("Pedido ID: "+idPedido+" - Estado: "+estado);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getPedidosPorFechas(Connection conexion, String desde, String hasta) {
        String sql = "SELECT id_pedido, estado, fecha_pedido FROM pedido WHERE fecha_pedido BETWEEN ? AND ?";
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // Convertir las cadenas de texto a objetos java.util.Date
            java.util.Date parsedDesde = dateFormat.parse(desde);
            java.util.Date parsedHasta = dateFormat.parse(hasta);
            // Convertir java.util.Date a java.sql.Date
            java.sql.Date DateDesde = new java.sql.Date(parsedDesde.getTime());
            java.sql.Date DateHasta = new java.sql.Date(parsedHasta.getTime());

            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setDate(1, DateDesde);
            pstmt.setDate(2, DateHasta);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                String estado = rs.getString("estado");
                Date fechaPedido = rs.getDate("fecha_pedido");
                System.out.println("Pedido ID: " + idPedido + " - Estado: "+estado+" - Fecha: " + fechaPedido);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException | ParseException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getProductosPorProveedor(Connection conexion, String provedor) {
        String sql = "SELECT nombre, precio_venta FROM producto WHERE proveedor = ?";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, provedor);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String nombreProducto = rs.getString("nombre");
                Double precioVenta = rs.getDouble("precio_venta");
                System.out.println("Producto: "+nombreProducto+" - Precio de Venta: "+precioVenta);
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

    public static void getPedidosPorClienteCompleto(Connection conexion, int idCliente) {
        String sql = "SELECT pedido.id_pedido, pedido.estado, detalle_pedido.id_producto, detalle_pedido.cantidad "+
                    "FROM pedido JOIN detalle_pedido ON pedido.id_pedido = detalle_pedido.id_pedido WHERE pedido.id_cliente = ? ";
        try {
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setInt(1, idCliente);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;
            if (count == 0) {
                System.out.println("Pedidos del cliente con ID " + idCliente + ":");
            }
            while (rs.next()) {
                int idPedido = rs.getInt("id_pedido");
                String estado = rs.getString("estado");
                int idProducto = rs.getInt("id_producto");
                int cantidadProducto = rs.getInt("cantidad");
                count++;
                
                System.out.println("Pedido "+count+" : ");
                System.out.println("ID Pedido: " + idPedido);
                System.out.println("Estado del Pedido: "+estado);
                System.out.println("ID Producto: " + idProducto);
                System.out.println("Cantidad: " + cantidadProducto);
                System.out.println("---------------------------");
                }
                if (count == 0) {
                    System.out.println("No se encontraron pedidos para el cliente con ID " + idCliente);
                } else{
                    System.out.println("Total de pedidos del cliente es: " + count);
                }

                rs.close();
                pstmt.close();
        } catch (Exception e) {
            System.out.println("Error en la consulta: " + e.getMessage());
        }
    }

}
