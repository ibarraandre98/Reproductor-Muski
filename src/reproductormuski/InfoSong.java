package reproductormuski;
public class InfoSong {
    String nombre,direccion;
    public InfoSong(){
        
    }
    public InfoSong(String nombre,String direccion){
        setNombre(nombre);
        setDireccion(direccion);
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
