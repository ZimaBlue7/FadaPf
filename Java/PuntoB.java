import java.io.*;

public class PuntoB {
    /*
     * Nombres de los archivos de lectura y escritura, modifique como considere.
     */
    static String ARCHIVO_LECTURA = "EntradaB";
    static String ARCHIVO_ESCRITURA = "SalidaB";

    /*
     * Método para realizar la lectura del problema, no modificar.
     */
    public static Entrada input() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        Entrada input = null;
        try {
            archivo = new File(ARCHIVO_LECTURA + ".txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String linea;
            linea = br.readLine();
            String[] data = linea.split(" ");
            int n = Integer.valueOf(data[0]);
            int m = Integer.valueOf(data[1]);
            Libro[] libros = new Libro[m];
            for (int i = 0; i < m; ++i) {
                linea = br.readLine();
                data = linea.split(" ");
                libros[i] = new Libro(data[0], Integer.valueOf(data[1]));
            }
            input = new Entrada(n, m, libros);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (null != fr) {
                fr.close();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return input;
    }

    /*
     * Método para realizar la escritura de la respuesta del problema, no modificar.
     */
    public static void output(Respuesta output) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(ARCHIVO_ESCRITURA + ".txt");
            pw = new PrintWriter(fichero);
            pw.println(output.tiempoTotal);
            for (int i = 0; i < output.libroQueEmpieza.length; ++i) {
                pw.println(output.libroQueEmpieza[i] + " " + output.libroQueTermina[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (null != fichero)
                fichero.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    static class EscritorP {
        int primerLibro;
        int ultimoLibro;

        EscritorP(int primerLibro, int ultimoLibro) {
            this.primerLibro = primerLibro;
            this.ultimoLibro = ultimoLibro;
        }
    }

    /*
     * Implementar el algoritmo y devolver un objeto de tipo Respuesta, el cual
     * servirá
     * para imprimir la solución al problema como se requiere en el enunciado.
     */
    static Respuesta solve(int n, int m, Libro[] libros) {

        int sumaPaginas = 0;
        int dias = 0;
        for (int i = 0; i < libros.length; i++) {
            sumaPaginas += libros[i].paginas; // Suma de todas las paginas de los libros
        }
        int ppe = sumaPaginas / n; // paginas por escritor
        int escritor[];
        escritor = new int[n];
        EscritorP[] arr;
        arr = new EscritorP[n];

        for (int i = 0; i < n; i++) {
            escritor[i] = ppe;
        }

        int diferencia = (sumaPaginas - (ppe - 1));

        while (true) {

            if (sumaPaginas <= n) {
                return new Respuesta(1, new String[0], new String[m - 1]);
            }

            int i = 0, j = 0, k = 0, u = 0;
            if (escritor[i] - libros[j].paginas >= -diferencia) {
                escritor[i] -= libros[j].paginas;
                u = j;
                j++;
            } else {
                arr[i] = new EscritorP(k, u);
                k = u;
                i++;
            }
            if (j == m - 1) {
                String primerlibrito[];
                primerlibrito = new String[n];
                String ultimolibrito[];
                ultimolibrito = new String[n];
                for (int l = 0; l < escritor.length; l++) {
                    primerlibrito[i] = Integer.toString(arr[i].primerLibro);
                    ultimolibrito[i] = Integer.toString(arr[i].ultimoLibro);
                }
                return new Respuesta(dias, primerlibrito, ultimolibrito);
            }
        }
    }

    public static void main(String[] args) {
        Entrada input = input();
        Respuesta r = solve(input.n, input.m, input.libros);
        output(r);

    }

    static class Entrada {
        int n;
        int m;
        Libro[] libros;

        public Entrada(int n, int m, Libro[] libros) {
            this.n = n;
            this.m = m;
            this.libros = libros;
        }

    }

    static class Respuesta {
        int tiempoTotal;
        /*
         * Esta variable contiene en su posición i, el nombre del libro por el que
         * empieza el i-ésimo
         * escritor.
         */
        String[] libroQueEmpieza;
        /*
         * Esta variable contiene en su posición i, el nombre del libro por el que
         * termina el i-ésimo
         * escritor.
         */
        String[] libroQueTermina;

        public Respuesta(int tiempoTotal, String[] libroQueEmpieza, String[] libroQueTermina) {
            this.tiempoTotal = tiempoTotal;
            this.libroQueEmpieza = libroQueEmpieza;
            this.libroQueTermina = libroQueTermina;
        }

    }

    /*
     * Clase base para interpretar los objetos tratados en el problema.
     */
    static class Libro {
        String nombre;
        int paginas;

        public Libro(String nombre, int paginas) {
            this.nombre = nombre;
            this.paginas = paginas;
        }
    }
}
