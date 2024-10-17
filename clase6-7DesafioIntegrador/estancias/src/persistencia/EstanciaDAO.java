package persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Casa;
import entidades.Clients;
import entidades.Estancia;

public class EstanciaDAO extends DAO{
    public void guardarEstancia(Estancia estancia) throws Exception {
        try {
            String sql = "INSERT INTO estancias (nombre_huesped, fecha_desde, fecha_hasta) VALUES (" 
                        + estancia.getNombreHuesped() + "', '" 
                        + estancia.getFechaDesde() + "', '" 
                        + estancia.getFechaHasta() + "');";
            insertarModificarEliminarDataBase(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean verificarDisponibilidadEstancia(int idCasa, String fechaInicio, String fechaFin) throws Exception {
        try {
            String sql = "SELECT * FROM estancias WHERE id_casa = " + idCasa + 
                        " AND (fecha_desde <= '" + fechaFin + "' AND fecha_hasta >= '" + fechaInicio + "')";
            ResultSet rs = consultarDataBase(sql);
            return !rs.next(); 
        } finally {
            desconectarDataBase();
        }
    }

    public void insertarEstancia(int idCliente, String nombreHuesped, int idCasa, String fechaInicio, String fechaFin) throws Exception {
        try {
            String sql = "INSERT INTO estancias (id_cliente, nombre_huesped, id_casa, fecha_desde, fecha_hasta) VALUES (" + 
                        idCliente  + ", '" + nombreHuesped + "', " + idCasa + ", '" + fechaInicio + "', '" + fechaFin + "')";
            insertarModificarEliminarDataBase(sql);
        } finally {
            desconectarDataBase();
        }
    }

    public List<Estancia> listarEstanciasConClientes() throws Exception {
        List<Estancia> estancias = new ArrayList<>();
        
        String sql = "SELECT c.id_cliente, c.nombre AS cliente_nombre, "
                    + "c.calle AS cliente_calle, c.numero AS cliente_numero, "
                    + "c.codigo_postal AS cliente_codigo_postal, c.ciudad AS cliente_ciudad, "
                    + "c.pais AS cliente_pais, ca.id_casa, "
                    + "ca.calle AS casa_calle, ca.numero AS casa_numero, "
                    + "ca.codigo_postal AS casa_codigo_postal, ca.ciudad AS casa_ciudad, "
                    + "ca.pais AS casa_pais, e.fecha_desde, e.fecha_hasta, ca.precio_habitacion "
                    + "FROM clientes c "
                    + "JOIN estancias e ON c.id_cliente = e.id_cliente "
                    + "JOIN casas ca ON e.id_casa = ca.id_casa";
        ResultSet rs = consultarDataBase(sql);

        try  {
            while (rs.next()) {
                Estancia estancia = new Estancia();

                // Crear y establecer el cliente
                Clients cliente = new Clients();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombre(rs.getString("cliente_nombre"));
                cliente.setCalle(rs.getString("cliente_calle"));
                cliente.setNumero(rs.getInt("cliente_numero"));
                cliente.setCodigoPostal(rs.getString("cliente_codigo_postal"));
                cliente.setCiudad(rs.getString("cliente_ciudad"));
                cliente.setPais(rs.getString("cliente_pais"));
                estancia.setCliente(cliente); // Asignar cliente a la estancia

                // Crear y establecer la casa
                Casa casa = new Casa();
                casa.setIdCasa(rs.getInt("id_casa"));
                casa.setCalle(rs.getString("casa_calle"));
                casa.setNumero(rs.getInt("casa_numero"));
                casa.setCodigoPostal(rs.getString("casa_codigo_postal"));
                casa.setCiudad(rs.getString("casa_ciudad"));
                casa.setPais(rs.getString("casa_pais"));
                casa.setPrecioHabitacion(rs.getDouble("precio_habitacion"));
                estancia.setCasa(casa); // Asignar casa a la estancia

                // Establecer fechas
                estancia.setFechaDesde(rs.getDate("fecha_desde"));
                estancia.setFechaHasta(rs.getDate("fecha_hasta"));
                
                estancias.add(estancia);
            }
        } catch (Exception e) {
            System.out.println("Error al listar estancias: " + e.getMessage());
        } finally {
            desconectarDataBase();
        }
        return estancias;
    }

    public List<Estancia> listarEstanciasConClientesYCasas() throws Exception {
        List<Estancia> estancias = new ArrayList<>();
    
        String sql = "SELECT c.id_cliente, c.nombre AS cliente_nombre, "
                    + "c.ciudad AS cliente_ciudad, c.pais AS cliente_pais, "
                    + "ca.id_casa, ca.calle, ca.numero, ca.ciudad, ca.pais, "
                    + "ca.codigo_postal, ca.precio_habitacion, "
                    + "e.fecha_desde, e.fecha_hasta "
                    + "FROM Estancias e "
                    + "JOIN Clientes c ON e.id_cliente = c.id_cliente "
                    + "JOIN Casas ca ON e.id_casa = ca.id_casa";
    
        ResultSet rs = consultarDataBase(sql);
    
        try {
            while (rs.next()) {
                // Crear instancia de Cliente
                Clients cliente = new Clients();
                cliente.setNombre(rs.getString("cliente_nombre"));
                cliente.setCiudad(rs.getString("cliente_ciudad"));
                cliente.setPais(rs.getString("cliente_pais"));
    
                // Crear instancia de Casa
                Casa casa = new Casa();
                casa.setIdCasa(rs.getInt("id_casa"));
                casa.setCalle(rs.getString("calle"));
                casa.setNumero(rs.getInt("numero"));
                casa.setCiudad(rs.getString("ciudad"));
                casa.setPais(rs.getString("pais"));
                casa.setCodigoPostal(rs.getString("codigo_postal"));
                casa.setPrecioHabitacion(rs.getDouble("precio_habitacion"));
    
                // Crear instancia de Estancia
                Estancia estancia = new Estancia();
                estancia.setCliente(cliente);
                estancia.setCasa(casa);
                estancia.setFechaDesde(rs.getDate("fecha_desde"));
                estancia.setFechaHasta(rs.getDate("fecha_hasta"));
    
                // Agregar estancia a la lista
                estancias.add(estancia);
            }
        } catch (Exception e) {
            System.out.println("Error al listar estancias: " + e.getMessage());
        } finally {
            desconectarDataBase(); 
        }
        return estancias;
    }
}
