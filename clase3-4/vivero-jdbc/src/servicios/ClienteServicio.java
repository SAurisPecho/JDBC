package servicios;
import entidades.Cliente;
import persistencia.ClienteDAO;

public class ClienteServicio {
    private ClienteDAO cd;
    
    public ClienteServicio(){
        this.cd = new ClienteDAO();
    }
    
    public Cliente crearNuevoCliente(int codigoCliente, String nombre, String nombreContacto, String apellidoContacto,
            String telefono, String fax, String ciudad, String region, String pais, String codigoPostal, int idEmpleado, double limiteCredito) throws Exception {
        // Validaciones - Pueden estar metodo independiente.
        validacionesNyA(nombreContacto, apellidoContacto);
        Cliente cliente = new Cliente(codigoCliente, nombre, nombreContacto, apellidoContacto, telefono, fax, ciudad, region,
                pais, codigoPostal, idEmpleado, limiteCredito);
        cd.guardarCliente(cliente);
        return cliente;
    }

    public void validacionesNyA(String nombreContacto, String apellidoContacto) throws Exception{
        if (nombreContacto == null) {
            throw new Exception("El nombre del contacto no puede ser nulo.");
        }
        if (apellidoContacto == null) {
            throw new Exception("El apellido del contacto no puede ser nulo.");
        }
    }

    public void listarTodosLosClientes () throws Exception {
        cd.listarTodosLosClientes();
    }

    public Cliente buscarClientePorCodigo (int codigoCliente) throws Exception{
        if (codigoCliente <= 0) {
            throw new Exception("El codigo del cliente debe ser mayor a 0.");
        }
        Cliente cliente = cd.buscarClientePorCodigo(codigoCliente);
        if (cliente == null) {
            throw new Exception("No existe ningun cliente con el codigo: "+codigoCliente);
        }
        return cliente;
    }
}