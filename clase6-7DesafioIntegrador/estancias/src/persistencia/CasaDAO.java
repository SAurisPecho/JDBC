package persistencia;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidades.Casa;

public class CasaDAO extends DAO{
    
    public List<Casa> listarCasasDisponiblesEntreFechasAgosto() throws Exception{
        List<Casa> casas = new ArrayList<>();
        try {
            String sql = "SELECT *FROM casas WHERE pais = 'Reino Unido' AND (fecha_desde >= '2020-08-01'AND fecha_hasta <= '2020-08-31')";
            ResultSet rs = consultarDataBase(sql);

            while (rs.next()) {
                Casa casa = new Casa();
                casa.setIdCasa(rs.getInt("id_casa"));
                casa.setCalle(rs.getString("calle"));
                casa.setNumero(rs.getInt("numero"));
                casa.setCodigoPostal(rs.getString("codigo_postal"));
                casa.setCiudad(rs.getString("ciudad"));
                casa.setPais(rs.getString("pais"));
                casa.setFechaDesde(rs.getDate("fecha_desde"));
                casa.setFechaHasta(rs.getDate("fecha_hasta"));
                casa.setTiempoMinimo(rs.getInt("tiempo_minimo"));
                casa.setTiempoMaximo(rs.getInt("tiempo_maximo"));
                casa.setPrecioHabitacion(rs.getDouble("precio_habitacion"));
                casa.setTipoVivienda(rs.getString("tipo_vivienda"));
                casas.add(casa);
            }
        } catch (Exception e) {
            System.out.println("Error al listar casas: " + e.getMessage());
        } finally {
            desconectarDataBase();
        }
        return casas;
    }

    public List<String> numeroCasasPorCadaPais () throws Exception {
        List<String> casasPorPais = new ArrayList<>();
        try {
            String sql = "SELECT pais, COUNT(*) as numero_casas FROM casas GROUP BY pais";
            ResultSet rs = consultarDataBase(sql);

            while (rs.next()) {
                String resultado = "Pais: "+rs.getString(1)+" - Cantidad: "+rs.getString(2);
                casasPorPais.add(resultado);
            }
        } finally {
            desconectarDataBase();
        }
        return casasPorPais;
    }

    public List<Casa> buscarCasasPorFechaYDias(String fecha, int dias) throws Exception {
        try {
            String sql = "SELECT * FROM casas WHERE fecha_desde >= '" + fecha + "' AND DATEDIFF(fecha_hasta, fecha_desde) >= " + dias;
            ResultSet rs = consultarDataBase(sql);

            List<Casa> casas = new ArrayList<>();
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
                casas.add(casa);
            }
            return casas;
        } finally {
            desconectarDataBase();
        }
    }
    
    public int incrementarPrecioCasasUK() throws Exception {
        String sql = "UPDATE casas SET precio_habitacion = precio_habitacion * (1 + " + (5 / 100.0) + ") WHERE pais = 'Reino Unido'";
        
        int filasActualizadas = 0; 
        try {
            insertarModificarEliminarDataBase(sql);
            String conteoSQL = "SELECT COUNT(*) FROM casas WHERE pais = 'Reino Unido'";
            ResultSet rs = consultarDataBase(conteoSQL);
            if (rs.next()) {
                filasActualizadas = rs.getInt(1); // Obtén el número de filas afectadas
            }        
        } catch (Exception e) {
            System.out.println("Error al incrementar el precio: " + e.getMessage());
        }
        
        return filasActualizadas; // Retorna el número de filas afectadas
    }

}
