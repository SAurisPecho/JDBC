package persistencia;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidades.Familia;

public class FamiliaDAO extends DAO{
    
    public List<Familia> listarTodasLasFamilias () throws Exception  {
        List<Familia> familias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM familias";
            ResultSet rs = consultarDataBase(sql);

            while (rs.next()) {
                Familia familia = new Familia(
                    rs.getInt("id_familia"),
                    rs.getString("nombre"),
                    rs.getInt("edad_minima"),
                    rs.getInt("edad_maxima"),
                    rs.getInt("num_hijos"),
                    rs.getString("email"));
                    familias.add(familia);
            }
        } catch (Exception e) {
            throw new Exception("Ocurrio un error al listar familias.");
        } finally {
            desconectarDataBase();
        }
        
        return familias;
    }

    public List<Familia> listarFamilias3HijosYMenores10Años () throws Exception {
        List<Familia> familias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM familias WHERE num_hijos >= 3 AND edad_maxima < 10";
            ResultSet rs = consultarDataBase(sql);
            while (rs.next()) {
                Familia familia = new Familia(
                    rs.getInt("id_familia"),
                    rs.getString("nombre"),
                    rs.getInt("edad_minima"),
                    rs.getInt("edad_maxima"),
                    rs.getInt("num_hijos"),
                    rs.getString("email"));
                familias.add(familia);
            }
        } catch (Exception e) {
            throw new Exception("Ocurrio un error al listar familias con al menos 3 hijos.");
        } finally {
            desconectarDataBase();
        }
        return familias;
    }

    public List<Familia> listarFamiliasConHotmail () throws Exception {
        List<Familia> familias = new ArrayList<>();
        try {
            String sql = "SELECT * FROM familias WHERE email LIKE '%hotmail%'";
            ResultSet rs = consultarDataBase(sql);

            while (rs.next()) {
                Familia familia = new Familia(
                    rs.getInt("id_familia"),
                    rs.getString("nombre"),
                    rs.getInt("edad_minima"),
                    rs.getInt("edad_maxima"),
                    rs.getInt("num_hijos"),
                    rs.getString("email"));
                familias.add(familia);
            }
        } catch (Exception e) {
            throw new Exception("Ocurrio un error al listar familias registradas con hotmail");
        } finally {
            desconectarDataBase();
        }
        return familias;
    }

}