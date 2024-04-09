package entity;

public class Vacante {
    private int idVacante;
    private String titulo;
    private String descripcion;
    private String duracion;
    private String estado;
    private String tecnologia;
    private int idEmpresa;
    private Empresa empresa;

    //Construsctores

    public Vacante() {

    }

    public Vacante(int idVacante, String titulo, String descripcion, String duracion, String estado, String tecnologia, int idEmpresa, Empresa empresa) {
        this.idVacante = idVacante;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.estado = estado;
        this.tecnologia = tecnologia;
        this.idEmpresa = idEmpresa;
        this.empresa = empresa;
    }
    //Getter y Setter

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    //ToString

    @Override
    public String toString() {
        return "\nVacante{" +
                "idVacante=" + idVacante +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracion='" + duracion + '\'' +
                ", estado='" + estado + '\'' +
                ", tecnologia='" + tecnologia + '\'' +
                ", idEmpresa=" + idEmpresa +
                ", empresa=" + empresa +
                '}';
    }
}
