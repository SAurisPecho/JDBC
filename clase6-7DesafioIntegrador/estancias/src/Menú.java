import java.util.Scanner;

import servicios.CasaServicio;
import servicios.ComentarioServicio;
import servicios.EstanciaServicio;
import servicios.FamiliaServicio;

public class Menú {
    private Scanner sc = new Scanner(System.in);
    private FamiliaServicio familiaServicio;
    private CasaServicio casaServicio;
    private ComentarioServicio comentarioServicio;
    private EstanciaServicio estanciaServicio;

    public void menuGeneral () throws Exception {
        familiaServicio = new FamiliaServicio();
        casaServicio = new CasaServicio();
        comentarioServicio = new ComentarioServicio();
        estanciaServicio = new EstanciaServicio();

        int opcion = -1;
        do {
            mostrarOpciones();
            opcion = sc.nextInt();
            elegirOpcion(opcion);
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.println("\n--- Menú de Funcionalidades ---");
        System.out.println("1. Listar familias con al menos 3 hijos y edad máxima inferior a 10 años.");
        System.out.println("2. Listar casas disponibles en Reino Unido entre 1/08/2020 y 31/08/2020.");
        System.out.println("3. Listar familias con email de Hotmail.");
        System.out.println("4. Listar casas disponibles desde una fecha y número de días específicos.");
        System.out.println("5. Listar clientes con descripción de la casa donde se alojaron.");
        System.out.println("6. Listar estancias reservadas por clientes con información de la casa.");
        System.out.println("7. Incrementar precio diario de casas del Reino Unido en 5%.");
        System.out.println("8. Obtener número de casas por país.");
        System.out.println("9. Listar casas del Reino Unido con comentarios que mencionan 'limpias'.");
        System.out.println("10. Insertar una nueva estancia.");
        System.out.println("0. Salir.");
        System.out.print("Elija una opcion: ");
    }
    
    private void elegirOpcion(int opcion) throws Exception {
        switch (opcion) {
            case 1 -> familiaServicio.listarFamilias3HijosYMenores10Años();
            case 2 -> casaServicio.listarCasasDisponiblesEntreFechasAgosto();
            case 3 -> familiaServicio.listarFamiliasConHotmail();
            case 4 -> buscarCasasPorFechaYDias();
            case 5 -> estanciaServicio.listarClientesConEstancia();
            case 6 -> estanciaServicio.listarEstanciasConClientesYCasas();
            case 7 -> casaServicio.incrementarPrecioCasasUK();
            case 8 -> casaServicio.numeroCasasPorCadaPais();
            case 9 -> comentarioServicio.listarCasasLimpiasReinoUnidoPorComentario();
            case 10 -> insertarNuevaEstancia();
            case 0 -> System.out.println("Saliendo del programa...");
            default -> System.out.println("Opcion no valida. Intente nuevamente.");
        }
    }

   private void buscarCasasPorFechaYDias() throws Exception {
        System.out.print("Introduce la fecha (YYYY-MM-DD): ");
        String fecha = sc.next();
        sc.nextLine();
        System.out.print("Introduce el número de días: ");
        int dias = sc.nextInt();
        sc.nextLine();

        casaServicio.buscarCasasPorFechaYDias(fecha, dias);
    }

    private void insertarNuevaEstancia() throws Exception {
        System.out.print("Introduce la ID del cliente: "); //verificar en la lista de clientes el id
        int idCliente = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Introduce la ID de la casa: ");
        int idCasa = sc.nextInt();
        sc.nextLine(); 
        System.out.print("Introduce el nombre del huesped: "); // colocar el mismo nombre del id Cliente 
        String nombreHuesped = sc.nextLine();
        System.out.print("Introduce la fecha de inicio (YYYY-MM-DD): ");
        String fechaInicio = sc.nextLine();
        System.out.print("Introduce la fecha de fin (YYYY-MM-DD): ");
        String fechaFin = sc.nextLine();

        estanciaServicio.insertarNuevaEstancia(idCliente, nombreHuesped, idCasa, fechaInicio, fechaFin);
    }
}
