package entidades;

public class Familia {
    private int idFamilia;
    private String nombre;
    private int edad_minima;
    private int edad_maxima;
    private int num_hijos;
    private String email;
    private Casa id_casa;

    public Familia() {
    }

    public Familia(int idFamilia, String nombre, int edad_minima, int edad_maxima, int num_hijos, String email) {
        this.idFamilia = idFamilia;
        this.nombre = nombre;
        this.edad_minima = edad_minima;
        this.edad_maxima = edad_maxima;
        this.num_hijos = num_hijos;
        this.email = email;
    }

    public Familia(String nombre, int edad_minima, int edad_maxima, int num_hijos, String email) {
        this.nombre = nombre;
        this.edad_minima = edad_minima;
        this.edad_maxima = edad_maxima;
        this.num_hijos = num_hijos;
        this.email = email;
    }

    public Familia(String nombre, int edad_minima, int edad_maxima, int num_hijos, String email, Casa id_casa) {
        this.nombre = nombre;
        this.edad_minima = edad_minima;
        this.edad_maxima = edad_maxima;
        this.num_hijos = num_hijos;
        this.email = email;
        this.id_casa = id_casa;
    }

    public int getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(int idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad_minima() {
        return edad_minima;
    }

    public void setEdad_minima(int edad_minima) {
        this.edad_minima = edad_minima;
    }

    public int getEdad_maxima() {
        return edad_maxima;
    }

    public void setEdad_maxima(int edad_maxima) {
        this.edad_maxima = edad_maxima;
    }

    public int getNum_hijos() {
        return num_hijos;
    }

    public void setNum_hijos(int num_hijos) {
        this.num_hijos = num_hijos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Casa getId_casa() {
        return id_casa;
    }

    public void setId_casa(Casa id_casa) {
        this.id_casa = id_casa;
    }

    @Override
    public String toString() {
        return "Familia {"+
                "idFamilia= " + idFamilia + 
                ", nombre= " + nombre + 
                ", edad_minima= " + edad_minima + 
                ", edad_maxima= " + edad_maxima + 
                ", num_hijos= " + num_hijos + 
                ", email= " + email + 
                ", id_casa= " + id_casa + 
                "}";
    }  
}
