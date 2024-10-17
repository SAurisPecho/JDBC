package servicios;
import java.util.List;
import entidades.Casa;
import persistencia.ComentarioDAO;

public class ComentarioServicio {
    private ComentarioDAO comentarioDAO;

    public ComentarioServicio() {
        comentarioDAO = new ComentarioDAO();
    }

    public void listarCasasLimpiasReinoUnidoPorComentario() throws Exception {
        List<Casa> casasLimpiasRU = comentarioDAO.listarCasasLimpiasReinoUnidoPorComentario();
        if (casasLimpiasRU.isEmpty()) {
            System.out.println("- Ninguna casa fue comentada como limpia.");
        } else {
            System.out.println("Casas limpias Reino Unido: ");
            for (Casa casa : casasLimpiasRU) {
                System.out.println(casa.toString());
            }
        }
    }
}
