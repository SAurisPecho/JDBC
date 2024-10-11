package persistencia;
import java.util.ArrayList;
import java.util.List;

import entidades.Cliente;

public class ClienteDAO extends DAO {

    public void guardarCliente(Cliente cliente) throws Exception {
        try {
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
        } catch (Exception e) {
            System.out.println("Error al guardar cliente: "+e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        }
    }

    public void listarTodosLosClientes () throws Exception {
        try {
            String sql = "SELECT id_cliente, nombre_contacto, apellido_contacto FROM cliente";
            consultarDataBase(sql);
            List<Cliente> clientes = new ArrayList<>();
            
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                clientes.add(cliente);
            }
            if (clientes.isEmpty()) {
                throw new Exception("No hay clientes registrados en la base de datos");
            }

            for (Cliente cliente : clientes) {
                cliente.imprimirNyA();
            }
            
        } catch (Exception e) {
            System.out.println("Error al listar los clientes: " + e.getMessage());
            throw e;
        } finally {
            desconectarDataBase();
        }
    }

    public Cliente buscarClientePorCodigo(int codigo) throws Exception {
        Cliente cliente = null;
        try {
            String sql = "SELECT * FROM cliente WHERE id_cliente = "+ codigo;
            consultarDataBase(sql);
            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setFax(resultSet.getString("fax"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setRegion(resultSet.getString("region"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
                cliente.setLimiteCredito(resultSet.getDouble("limite_credito"));

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
            consultarDataBase(sql);
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setCodigoCliente(resultSet.getInt("codigo_cliente"));
                cliente.setNombreCliente(resultSet.getString("nombre_cliente"));
                cliente.setNombreContacto(resultSet.getString("nombre_contacto"));
                cliente.setApellidoContacto(resultSet.getString("apellido_contacto"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setFax(resultSet.getString("fax"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setRegion(resultSet.getString("region"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setIdEmpleado(resultSet.getInt("id_empleado"));
                cliente.setLimiteCredito(resultSet.getDouble("limite_credito"));
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