package modelo;

/**
 * @author Diego
 */

public class Correo {
    
    private int id;
    private String nombre;
    private Origen origen;
    private Grupo grupo;
    private boolean habilitado;
    
    public Correo(){}
    
    public Correo(String nombre){
        this.nombre = nombre;
    }

    public Correo(int id, String nombre, Origen origen, Grupo grupo, boolean habilitado) {
        this.id = id;
        this.nombre = nombre;
        this.origen = origen;
        this.grupo = grupo;
        this.habilitado = habilitado;
    }

    public Correo(String nombre, Origen origen, Grupo grupo, boolean habilitado) {
        this.nombre = nombre;
        this.origen = origen;
        this.grupo = grupo;
        this.habilitado = habilitado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Origen getOrigen() {
        return origen;
    }

    public void setOrigen(Origen origen) {
        this.origen = origen;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
    public String[] toArray() {
        return new String[]{
            id+"",
            nombre,
            origen.getNombre(),
            grupo.getNombre(),
            habilitado+""
        };
    }
    
    
}
