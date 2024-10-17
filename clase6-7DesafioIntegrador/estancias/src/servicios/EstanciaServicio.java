package servicios;

import java.util.List;

import entidades.Casa;
import entidades.Clients;
import entidades.Estancia;
import persistencia.EstanciaDAO;

public class EstanciaServicio {
    private EstanciaDAO estanciaDAO;

    public EstanciaServicio() {
        estanciaDAO = new EstanciaDAO();
    }

    public void listarClientesConEstancia() {
        try {
            List<Estancia> estancias = estanciaDAO.listarEstanciasConClientes();
            if (estancias.isEmpty()) {
                System.out.println("No hay registros.");
            } else {
                System.out.println("Registros disponibles:");
                for (Estancia estancia : estancias) {
                    Casa casa = estancia.getCasa(); // Obtener la casa de la estancia
                    Clients cliente = estancia.getCliente(); // Obtener el cliente

                    System.out.println("Cliente: " + cliente.getNombre());
                    System.out.println("Casa ID: " + casa.getIdCasa() +
                            ", Dirección: " + casa.getCalle() + " " + casa.getNumero() +
                            ", Ciudad: " + casa.getCiudad() +
                            ", Código Postal: " + casa.getCodigoPostal() +
                            ", Precio por día: $" + casa.getPrecioHabitacion());
                    System.out.println("Fechas: Desde " + estancia.getFechaDesde() +
                            " hasta " + estancia.getFechaHasta());
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener estancias: " + e.getMessage());
        }
    }


    public void insertarNuevaEstancia(int idCliente, String nombreHuesped, int idCasa, String fechaInicio, String fechaFin) throws Exception {
        boolean disponible = estanciaDAO.verificarDisponibilidadEstancia(idCasa, fechaInicio, fechaFin);
        if (disponible) {
            estanciaDAO.insertarEstancia(idCliente, nombreHuesped, idCasa, fechaInicio, fechaFin);
            System.out.println("Estancia insertada exitosamente.");
        } else {
            System.out.println("La estancia no está disponible en las fechas indicadas.");
        }
    }
    
    public void listarEstanciasConClientesYCasas() {
        try {
            List<Estancia> estancias = estanciaDAO.listarEstanciasConClientesYCasas();

            if (estancias.isEmpty()) {
                System.out.println("No hay estancias registradas.");
            } else {
                System.out.println("Listado de estancias reservadas por clientes:");
                for (Estancia estancia : estancias) {
                    Clients cliente = estancia.getCliente();
                    Casa casa = estancia.getCasa();

                    // Imprimir datos del cliente
                    System.out.println("Cliente: " + cliente.getNombre());
                    System.out.println("Ubicación del Cliente: " + cliente.getCiudad() + ", " + cliente.getPais());

                    // Imprimir datos de la casa reservada
                    System.out.println("Casa ID: " + casa.getIdCasa());
                    System.out.println("Dirección: " + casa.getCalle() + " " + casa.getNumero());
                    System.out.println("Ciudad: " + casa.getCiudad() + ", País: " + casa.getPais());
                    System.out.println("Código Postal: " + casa.getCodigoPostal());
                    System.out.println("Precio por habitación: $" + casa.getPrecioHabitacion());

                    // Imprimir fechas de la estancia
                    System.out.println("Fechas: Desde " + estancia.getFechaDesde() +
                            " hasta " + estancia.getFechaHasta());
                    System.out.println("-----------------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al listar estancias: " + e.getMessage());
        }
    }
}
