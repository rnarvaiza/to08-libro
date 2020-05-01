import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Biblioteca {

    public final ArrayList<Libro> bibliotecaEscritura;
     {
        bibliotecaEscritura = new ArrayList<>();
    }

    private ArrayList<Libro> bibliotecaLectura;
    {
        bibliotecaLectura= new ArrayList<>();
    }
    Scanner sc=null;
    File archivo = new File("datos.txt");

    /*
    El siguiente método invoca a una instancia llamada libro del objeto Libro de la clase Libro. Se obtienen por consola el contenido de las variables
    y luego se pasan al constructor a través de métodos Set.
    Esta forma permite declarar al constructor con los parámetros que se estimen convenientes. Es la forma más apropiada, por ejemplo, para poder utilizar
    LocalDate. Si pasasemos directamente por parámetros los valores al constructor, sería mucho más sucio.
    Con los parámetros pasados por variables, se añaden los objetos libro a un arraylist de objetos libro.
    La llamada al método escribeBiblioteca, hace referencia al requisito del ejercicio de escribir en un fichero los datos del arrayList.
     */
    public void addLibros() throws ParseException {
        Scanner readText = new Scanner(System.in);
        Scanner readText2= new Scanner(System.in);
        Scanner readNumbers= new Scanner(System.in);
        System.out.println("¿Cuantos libros desea añadir?");
        int cantidad=readNumbers.nextInt();
        String titulo, autor, editorial, fechaPub;
        double precio;

        do {
            System.out.println("Título del libro: ");
            titulo=readText.nextLine();
            System.out.println("Autor del libro: ");
            autor=readText.nextLine();
            System.out.println("Editorial del libro: ");
            editorial=readText.nextLine();
            System.out.println("Precio del libro: ");
            precio=readNumbers.nextDouble();
            System.out.println("Fecha de publicación: ");
            fechaPub=readText2.nextLine();
            LocalDate fechaDePublicacion = LocalDate.parse(fechaPub);
            Libro libro=new Libro(titulo, autor, editorial, precio, fechaDePublicacion);
            bibliotecaEscritura.add(libro);
            cantidad --;
        } while(cantidad>0);

        escribeBiblioteca();
    }

    /*
    Este método es el encargado de escribir el arrayList en un fichero llamado datos.txt. Su función es la de realizar un almacenamiento no volatil
    de los datos contenidos en el arrayList para recuperarlos cuando sea necesario.
    Se realiza una serialización en la clase libro, para que, pueda cumplir los requisitos del ejercicio y separar cada campo (autor, título, fecha...) con ";".
     */
    public void escribeBiblioteca(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("datos.txt"));
            for (Libro libro:bibliotecaEscritura
            ) {
                bw.write(libro.serializar());
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    /*
    Este método realiza la lectura del fichero datos.txt. Es el encargado de, leer un string y realizar la "deserialización" de esa cadena,
    para ir añadiendo al objeto libro, cada uno de los parámetros que lo conforman. Después de que estos parámetros se añaden al objeto, se
    almacena esa objeto en un nuevo arrayList. Éste será usado en el tratamiento de los datos.
     */
    public void leeBiblioteca(){
        try {
            LocalDate date=null;
            sc = new Scanner(archivo);
            while (sc.hasNextLine()){
                String linea=sc.nextLine();
                String [] support=linea.split(";");
                Libro libro = new Libro();
                libro.setTitulo(support[0]);
                libro.setAutor(support[1]);
                libro.setEditorial(support[2]);
                libro.setPrecio(Double.parseDouble(support[3]));
                libro.setFechaDePublicacion(date.parse(support[4]));
                bibliotecaLectura.add(libro);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            e.getStackTrace();
        } finally {
            try {
                if (sc !=null){
                    sc.close();
                }
            }catch (Exception e2){
                System.out.println(e2.getMessage());
                e2.getStackTrace();
            }
        }
    }

    /*
    Este método es el encargado de, una vez ordenados los resultados de la forma que queremos, realice la escritura del arrayList de lectura
    en el archivo "resultado.txt".
     */
    public void escribeResultadoOrdenado(){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("resultado.txt"));
            for (Libro libro:bibliotecaLectura
            ) {
                bw.write(libro.toString());
                bw.write("\n");
                bw.flush();
            }
            bw.close();
            System.out.println("Puedes ver el resultado de la reordenación de libros en el archivo resultado.txt");
            System.out.println(" ");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        for (Libro libro:bibliotecaLectura){
            System.out.println(libro.toString());
        }

    }

    /*
    En los siguientes métodos, utilizaré collections.sort para hacer la comparativa de los valores según los que quiero ordenar.
    Título, autor, editorial, precio y fecha.
    Los tres primeros métodos son similares ya que comparan Strings. El cuarto y el quinto, han sido modificados para hacer la comparación
    de double y LocalDate.
     */
    public void ordenTituloAscendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return new String(p1.getTitulo()).compareToIgnoreCase(new String(p2.getTitulo()));
            }
        });
        escribeResultadoOrdenado();
        System.out.println("");
        System.out.println("Libros ordenados por título de forma ascendente (A-Z).");
    }

    public void ordenTituloDescendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return new String(p2.getTitulo()).compareToIgnoreCase(new String(p1.getTitulo()));
            }
        });
        escribeResultadoOrdenado();
        System.out.println("");
        System.out.println("Libros ordenados por título de forma descendente (Z-A).");
    }

    public void ordenAutorAscendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return new String(p1.getAutor()).compareToIgnoreCase(new String(p2.getAutor()));
            }
        });
        escribeResultadoOrdenado();
        System.out.println(" ");
        System.out.println("Libros ordenados por autor de forma ascendente (A-Z).");
    }

    public void ordenAutorDescendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return new String(p2.getAutor()).compareToIgnoreCase(new String(p1.getAutor()));
            }
        });
        escribeResultadoOrdenado();
        System.out.println(" ");
        System.out.println("Libros ordenados por autor de forma descendente (Z-A).");
    }

    public void ordenEditorialAscendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return new String(p1.getEditorial()).compareToIgnoreCase(new String(p2.getEditorial()));
            }
        });
        escribeResultadoOrdenado();
        System.out.println(" ");
        System.out.println("Libros ordenados por editorial de forma ascendente (A-Z).");
    }

    public void ordenEditorialDescendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return new String(p2.getEditorial()).compareToIgnoreCase(new String(p1.getEditorial()));
            }
        });
        escribeResultadoOrdenado();
        System.out.println(" ");
        System.out.println("Libros ordenados por editorial de forma descendente (Z-A).");
    }

    public void ordenPrecioAscendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return Double.compare(p1.getPrecio(),p2.getPrecio());
            }
        });
        escribeResultadoOrdenado();
        System.out.println(" ");
        System.out.println("Libros ordenados por precio de menor a mayor (0~99999).");
    }

    public void ordenPrecioDescendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return Double.compare(p2.getPrecio(),p1.getPrecio());
            }
        });
        escribeResultadoOrdenado();
        System.out.println(" ");
        System.out.println("Libros ordenados por precio de mayor a menor (99999~0).");
    }

    public void ordenFechaAscendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return (p1.getFechaDePublicacion()).compareTo(p2.getFechaDePublicacion());
            }
        });
        escribeResultadoOrdenado();
        System.out.println(" ");
        System.out.println("Libros ordenados por fecha de publicación, de más antiguo a más nuevo.");
    }

    public void ordenFechaDescendente(){
        Collections.sort(bibliotecaLectura, new Comparator<Libro>(){

            @Override
            public int compare(Libro p1, Libro p2) {
                return (p2.getFechaDePublicacion()).compareTo(p1.getFechaDePublicacion());
            }
        });
        escribeResultadoOrdenado();
        System.out.println(" ");
        System.out.println("Libros ordenados por fecha de publicación, de más nuevo a más antiguo.");
    }

}
