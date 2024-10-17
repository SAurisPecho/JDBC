package servicios;
import java.util.List;
import entidades.Casa;
import persistencia.CasaDAO;

public class CasaServicio {
    private CasaDAO casaDAO;

    public CasaServicio() {
        casaDAO = new CasaDAO();
    }

    public void listarCasasDisponiblesEntreFechasAgosto() throws Exception {
        try {
            List<Casa> casas = casaDAO.listarCasasDisponiblesEntreFechasAgosto();
            if (casas.isEmpty()) {
                System.out.println("- No hay casas disponibles en Reino Unido para el periodo solicitado.");
            } else {
                System.out.println("Casas disponibles en Reino Unido entre 1/08/2020 y 31/08/2020: ");
                for (Casa casa : casas) {
                    System.out.println(casa.toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void numeroCasasPorCadaPais () throws Exception {
        try {
            List<String> casasPorPais = casaDAO.numeroCasasPorCadaPais();
            for (String resultado : casasPorPais) {
                System.out.println(resultado);
            }
        } catch (Exception e) {
            System.out.println("Error al listar casas por pais: " + e.getMessage());
        }
    }

    public void buscarCasasPorFechaYDias(String fecha, int dias) throws Exception {
        List<Casa> casas = casaDAO.buscarCasasPorFechaYDias(fecha, dias);
        if (casas.isEmpty()) {
            System.out.println("No se encontraron casas disponibles.");
        } else {
            for (Casa casa : casas) {
                System.out.println(casa);
            }
        }
    }

    public void incrementarPrecioCasasUK() throws Exception {
        System.out.println("Incrementando precio por día de las casas en Reino Unido en un 5%:");
        int filasActualizadas = casaDAO.incrementarPrecioCasasUK();
        System.out.println("Precios actualizados de " + filasActualizadas + " casas en Reino Unido.");
    }
}
