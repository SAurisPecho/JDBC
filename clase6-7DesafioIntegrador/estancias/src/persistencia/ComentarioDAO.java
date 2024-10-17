package persistencia;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidades.Casa;

public class ComentarioDAO extends DAO {
    
    public List<Casa> listarCasasLimpiasReinoUnidoPorComentario () throws Exception{
        List<Casa> casasLimpiasRU = new ArrayList<>();
        try {
            String sql = "SELECT ca.* FROM casas ca JOIN comentarios co ON ca.id_casa = co.id_casa WHERE ca.pais = 'Reino Unido' AND co.comentario LIKE '%limpia%'";
            ResultSet rs = consultarDataBase(sql);
            while (rs.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(rs.getInt(1));
                casa.setCalle(rs.getString(2));
                casa.setNumero(rs.getInt(3));
                casa.setCodigoPostal(rs.getString(4));
                casa.setCiudad(rs.getString(5));
                casa.setPais(rs.getString(6));
                casa.setFechaDesde(rs.getDate(7));
                casa.setFechaHasta(rs.getDate(8));
                casa.setPrecioHabitacion(rs.getDouble(9));
                casasLimpiasRU.add(casa);
            }
        } catch (Exception e) {
            System.out.println("Error al listar casas limpias en R.U. :"+ e.getMessage());
        } finally {
            desconectarDataBase();
        }
        return casasLimpiasRU;
    }
}
