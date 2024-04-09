package entity;

public class Empresa {
    private int idEmpresa;
    private String nombre;
    private String sector;
    private String ubicacion;
    private String contacto;

    //Constructores

    public Empresa() {

    }

    public Empresa(int idEmpresa, String nombre, String sector, String ubicacion, String contacto) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.sector = sector;
        this.ubicacion = ubicacion;
        this.contacto = contacto;
    }

    //Getter y Setter

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    //ToString

    @Override
    public String toString() {
        return "\nEmpresa: {" +
                "idEmpresa=" + idEmpresa +
                ", nombre='" + nombre + '\'' +
                ", sector='" + sector + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", contacto='" + contacto + '\'' +
                '}';
    }
}
