import java.text.ParseException;
import java.util.*;

public class Ejercicio1 {
    public static void main(String[] args) throws Exception{
        muestraMenu menu = new muestraMenu();
        menu.muestraMenu();
    }

}

    class muestraMenu extends Biblioteca {
        public void muestraMenu() {
            Scanner s = new Scanner(System.in);
            leeBiblioteca();
            boolean continuar = true;
            int opcion;
            do {
                System.out.println("");
                System.out.println("Biblioteca ordenada");
                System.out.println("");
                System.out.println("#1 Añadir libros a la biblioteca: ");
                System.out.println("#2 Ordenar libros por titulo: Ascendente.");
                System.out.println("#3 Ordenar libros por titulo: Descendente.");
                System.out.println("#4 Ordenar libros por autor: Ascendente.");
                System.out.println("#5 Ordenar libros por autor: Descendente.");
                System.out.println("#6 Ordenar libros por editorial: Ascendente.");
                System.out.println("#7 Ordenar libros por editorial: Descendente.");
                System.out.println("#8 Ordenar libros por precio: Ascendente.");
                System.out.println("#9 Ordenar libros por precio: Descendente.");
                System.out.println("#10 Ordenar libros por fecha de publicación: Ascendente.");
                System.out.println("#11 Ordenar libros por fecha de publicación: Descendente.");
                System.out.println("#12 Salir del programa.");

                try {
                    do {
                        System.out.println(" ");
                        System.out.println("Pulse para acción.");
                        opcion = s.nextInt();
                    } while (opcion < 1 || opcion > 13);

                    switch (opcion) {
                        case 1:
                            addLibros();
                            break;

                        case 2:
                            ordenTituloAscendente();
                            break;

                        case 3:
                            ordenTituloDescendente();
                            break;

                        case 4:
                            ordenAutorAscendente();
                            break;

                        case 5:
                            ordenAutorDescendente();
                            break;

                        case 6:
                            ordenEditorialAscendente();
                            break;

                        case 7:
                            ordenEditorialDescendente();
                            break;

                        case 8:
                            ordenPrecioAscendente();
                            break;

                        case 9:
                            ordenPrecioDescendente();
                            break;

                        case 10:
                            ordenFechaAscendente();
                            break;

                        case 11:
                            ordenFechaDescendente();
                            break;

                        case 12:
                            continuar = false;

                            break;
                    }
                } catch (InputMismatchException | ParseException e) {
                    System.out.println(e.getMessage());
                    e.getStackTrace();
                }
            } while (continuar);
        }
    }


