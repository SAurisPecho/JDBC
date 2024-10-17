package servicios;
import java.util.List;

import entidades.Familia;
import persistencia.FamiliaDAO;

public class FamiliaServicio {
    private FamiliaDAO familiaDAO ;

    public FamiliaServicio() {
        familiaDAO = new FamiliaDAO() ;
    }

    public void listarTodasLasFamilias () throws Exception {
        System.out.println("Familias registradas: ");
        List<Familia> familias = familiaDAO.listarTodasLasFamilias();
        for (Familia familia : familias) {
            System.out.println("- "+familia.getNombre());
        }
    }

    public void listarFamilias3HijosYMenores10Años () throws Exception {
        System.out.println("Familias con al menos 3 hijos y edad máxima inferior a 10 años: ");
        List<Familia> familias = familiaDAO.listarFamilias3HijosYMenores10Años();
        if (familias.isEmpty()) {
            System.out.println("- No hay familias con al menos 3 hijos y edad maxima 10 años");
        } else {
            for (Familia familia : familias) {
            System.out.println(familia.getNombre());
            }
        }
    }

    public void listarFamiliasConHotmail () throws Exception {
        System.out.println("Familias con dirección de email que sea Hotmail: ");
        List<Familia> familias = familiaDAO.listarFamiliasConHotmail();
        if (familias.isEmpty()) {
            System.out.println("- No hay familias registradas con hotmail.");
        } else {
            for (Familia familia : familias) {
                System.out.println("- "+familia.getNombre());
            }
        }
    }
}
