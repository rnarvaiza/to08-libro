import java.text.ParseException;
import java.time.LocalDate;

public class Libro {

    private String titulo;
    private String autor;
    private String editorial;
    private double precio;
    private LocalDate fechaDePublicacion;

    public Libro(){
    }
    
    public Libro(String titulo, String autor, String editorial, double precio, LocalDate fechaDePublicacion) throws ParseException {
        this.setTitulo(titulo);
        this.setAutor(autor);
        this.setEditorial(editorial);
        this.setPrecio(precio);
        this.setFechaDePublicacion(fechaDePublicacion);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(LocalDate fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }


    /*
    Método toString para imprimir por pantalla la concatenación de los parámetros del objeto y tener una lectura cómoda.
     */

    @Override
    public String toString(){
        return this.titulo + " " + this.autor + " " + this.editorial + " " + this.precio + " " + this.fechaDePublicacion + " ";
    }

    /*
    Método serializar, encargado de concatenar los diferentes parámetros en un string separado por ";"
     */
    public String serializar(){
        return this.titulo + ";" + this.autor + ";" + this.editorial + ";" + this.precio + ";" + this.fechaDePublicacion + ";";
    }
}
