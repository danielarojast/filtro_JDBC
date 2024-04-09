package entity;

public class Contratacion {
    private int idContratacion;
    private String fecha_aplicacion;
    private String estado;
    private double salario;
    private int idVacante;
    private Vacante vacante;
    private int idCoder;
    private Coder coder;

    //Constructores

    public Contratacion() {

    }

    public Contratacion(int idContratacion, String fecha_aplicacion, String estado, double salario, int idVacante, Vacante vacante, int idCoder, Coder coder) {
        this.idContratacion = idContratacion;
        this.fecha_aplicacion = fecha_aplicacion;
        this.estado = estado;
        this.salario = salario;
        this.idVacante = idVacante;
        this.vacante = vacante;
        this.idCoder = idCoder;
        this.coder = coder;
    }
    //getter y setter

    public int getIdContratacion() {
        return idContratacion;
    }

    public void setIdContratacion(int idContratacion) {
        this.idContratacion = idContratacion;
    }

    public String getFecha_aplicacion() {
        return fecha_aplicacion;
    }

    public void setFecha_aplicacion(String fecha_aplicacion) {
        this.fecha_aplicacion = fecha_aplicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(int idVacante) {
        this.idVacante = idVacante;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public int getIdCoder() {
        return idCoder;
    }

    public void setIdCoder(int idCoder) {
        this.idCoder = idCoder;
    }

    public Coder getCoder() {
        return coder;
    }

    public void setCoder(Coder coder) {
        this.coder = coder;
    }

    //toString

    @Override
    public String toString() {
        return "\nContratacion{" +
                "idContratacion=" + idContratacion +
                ", fecha_aplicacion='" + fecha_aplicacion + '\'' +
                ", estado='" + estado + '\'' +
                ", salario='" + salario + '\'' +
                ", idVacante=" + idVacante +
                ", vacante=" + vacante +
                ", idCoder=" + idCoder +
                ", coder=" + coder +
                '}';
    }
}
