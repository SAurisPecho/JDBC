package persistencia;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

import entidades.Cliente;

public class ClienteDAO extends DAO {

    public void guardarCliente(Cliente cliente) throws Exception {
        if (cliente == null) {
            throw new Exception("El cliente no puede ser nulo.");
        }
        String sql = "INSERT INTO cliente (codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono"+
                    ", fax, ciudad, region, pais, codigo_postal, id_empleado, limite_credito) VALUES ("+
                    cliente.getCodigoCliente()+", '"+
                    cliente.getNombreCliente()+"', '"+
                    cliente.getNombreContacto()+"', '"+
                    cliente.getApellidoContacto()+"', '"+
                    cliente.getTelefono()+"', '"+
                    cliente.getFax()+"', '"+
                    cliente.getCiudad()+"', '"+
                    cliente.getRegion()+"', '"+
                    cliente.getPais()+"', '"+
                    cliente.getCodigoPostal()+"', "+
                    cliente.getIdEmpleado()+", "+
                    cliente.getLimiteCredito()+");";
        insertarModificarEliminarDataBase(sql);
    }

    public void listarTodosLosClientes () throws Exception {
            String sql = "SELECT id_cliente, nombre_contacto, apellido_contacto FROM cliente";
            ResultSet rs = consultarDataBase(sql);
            List<Cliente> clientes = new ArrayList<>();
            
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombreContacto(rs.getString("nombre_contacto"));
                cliente.setApellidoContacto(rs.getString("apellido_contacto"));
                clientes.add(cliente);
            }
            if (clientes.isEmpty()) {
                throw new Exception("No hay clientes registrados en la base de datos");
            }

            for (Cliente cliente : clientes) {
                cliente.imprimirNyA();
            }
    }

    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        Cliente cliente = null;
        try {
            String sql = "SELECT * FROM cliente WHERE id_cliente = "+ codigo;
            ResultSet rs = consultarDataBase(sql);
            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setCodigoCliente(rs.getInt("codigo_cliente"));
                cliente.setNombreCliente(rs.getString("nombre_cliente"));
                cliente.setNombreContacto(rs.getString("nombre_contacto"));
                cliente.setApellidoContacto(rs.getString("apellido_contacto"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFax(rs.getString("fax"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setRegion(rs.getString("region"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCodigoPostal(rs.getString("codigo_postal"));
                cliente.setIdEmpleado(rs.getInt("id_empleado"));
                cliente.setLimiteCredito(rs.getDouble("limite_credito"));

                System.out.println(cliente.toString());
            } else {
                System.out.println("No se encontró un cliente con el código: " + codigo);
            }
        } catch (Exception e) {
            System.out.println("Error al buscar el cliente: " + e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        } 
        
        return cliente;
    }

    public void eliminarClientePorId (int idCliente) throws Exception {
        try {
            String sql = "DELETE FROM cliente WHERE id_cliente = "+idCliente;
            insertarModificarEliminarDataBase(sql);
            System.out.println("Cliente con ID: "+idCliente+" eliminado");
        } catch (Exception e) {
            throw new Exception("Cliente no eliminado correctamente: "+e.getMessage());
        } finally {
            desconectarDataBase();
        }
    }

    public List<Cliente> listarClientesPorEmpleado (int idEmpleado) throws Exception {
        List<Cliente> clientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cliente WHERE id_empleado = "+idEmpleado;
            ResultSet rs = consultarDataBase(sql);
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setCodigoCliente(rs.getInt("codigo_cliente"));
                cliente.setNombreCliente(rs.getString("nombre_cliente"));
                cliente.setNombreContacto(rs.getString("nombre_contacto"));
                cliente.setApellidoContacto(rs.getString("apellido_contacto"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setFax(rs.getString("fax"));
                cliente.setCiudad(rs.getString("ciudad"));
                cliente.setRegion(rs.getString("region"));
                cliente.setPais(rs.getString("pais"));
                cliente.setCodigoPostal(rs.getString("codigo_postal"));
                cliente.setIdEmpleado(rs.getInt("id_empleado"));
                cliente.setLimiteCredito(rs.getDouble("limite_credito"));
                clientes.add(cliente);
            }
            if (clientes.isEmpty()) {
                System.out.println("No hay clientes registrados por el empleado en la base de datos.");
            } else {
                System.out.println("Lista de Clientes:");
                for (Cliente c : clientes) {
                    System.out.println(c.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar los clientes del empleado: " + e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        }
        return clientes;
    }

    public void incrementarLimiteCredito () throws Exception{
        try {
            String sql = "UPDATE cliente SET limite_credito = limite_credito * 1.15";
            insertarModificarEliminarDataBase(sql);
            System.out.println("¡Se aumento el limite de credito de todos los clientes!");
        } catch (Exception e) {
            System.out.println("Error al aumentar el limite de credito de todos los clientes: " + e.getMessage());
            throw e;
        }   
    }
}