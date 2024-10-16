package persistencia;

import entidades.Oficina;
import java.util.List;
import java.util.ArrayList;

public class OficinaDAO extends DAO {
    
    public List<Oficina> listarOficinas() throws Exception {
        List<Oficina> oficinas = new ArrayList<>();
        try {
            String sql = "SELECT * FROM oficina";
            consultarDataBase(sql);
            while (resultSet.next()) {
                Oficina oficina = new Oficina();
                oficina.setIdOficina(resultSet.getInt("id_oficina"));
                oficina.setCiudad(resultSet.getString("ciudad"));
                oficina.setPais(resultSet.getString("pais"));
                oficinas.add(oficina);
            }
            System.out.println("Lista de oficinas:");
            for (Oficina oficina : oficinas) {
                System.out.println(oficina.toString());
            }
        } catch (Exception e) {
            throw new Exception("Error al listar oficinas: "+e.getMessage());
        } finally {
            desconectarDataBase();
        }
        return oficinas;
    }

    public List<Oficina> listarOficinasPorPais (String pais) throws Exception {
        List<Oficina> oficinas = new ArrayList<>();
        try {
            String sql = "SELECT id_oficina, ciudad, region FROM oficina WHERE pais = '"+pais+"'";
            consultarDataBase(sql);
            while (resultSet.next()) {
                Oficina oficina = new Oficina();
                oficina.setIdOficina(resultSet.getInt("id_oficina"));
                oficina.setCiudad(resultSet.getString("ciudad"));
                oficina.setRegion(resultSet.getString("region"));
                oficinas.add(oficina);
            }
            System.out.println("Lista de oficinas en el pais " + pais + ":");
            if (oficinas.isEmpty()) {
                System.out.println("No se encontraron oficinas en este pais.");
            } else {
                for (Oficina oficina : oficinas) {
                    System.out.println(oficina.toString());
                }
            }
        } catch (Exception e) {
            throw new Exception("Error no hay oficinas: "+e.getMessage());
        } finally {
            desconectarDataBase();
        }
        return oficinas;
    }
}
