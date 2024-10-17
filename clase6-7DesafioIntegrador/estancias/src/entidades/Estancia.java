package entidades;
import java.util.Date;

public class Estancia {
    private int idEstancia;
    private Clients cliente;
    private Casa casa;
    private String nombreHuesped;
    private Date fechaDesde;
    private Date fechaHasta;
    
    public Estancia() {
    }

    public Estancia(Clients cliente, Casa casa, String nombreHuesped, Date fechaDesde, Date fechaHasta) {
        this.cliente = cliente;
        this.casa = casa;
        this.nombreHuesped = nombreHuesped;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
    }

    public int getIdEstancia() {
        return idEstancia;
    }

    public void setIdEstancia(int idEstancia) {
        this.idEstancia = idEstancia;
    }

    public Clients getCliente() {
        return cliente;
    }

    public void setCliente(Clients cliente) {
        this.cliente = cliente;
    }

    public Casa getCasa() {
        return casa;
    }

    public void setCasa(Casa casa) {
        this.casa = casa;
    }
    public String getNombreHuesped() {
        return nombreHuesped;
    }

    public void setNombreHuesped(String nombreHuesped) {
        this.nombreHuesped = nombreHuesped;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
}
