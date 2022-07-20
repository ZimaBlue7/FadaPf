import java.io.*;

public class PuntoB {
        /*
     * Nombres de los archivos de lectura y escritura, modifique como considere.
     */
    static String ARCHIVO_LECTURA = "inB";
    static String ARCHIVO_ESCRITURA = "outB";
    /*
     * Método para realizar la lectura del problema, no modificar.
     */
    public static Entrada input(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        Entrada input = null;
        try {
            archivo = new File (ARCHIVO_LECTURA +".txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            linea = br.readLine();
            String[]data = linea.split(" ");
            int n = Integer.valueOf(data[0]);
            int m = Integer.valueOf(data[1]);
            Libro[] libros = new Libro[m];
            for(int i = 0 ; i < m ; ++i){
                linea = br.readLine();
                data = linea.split(" ");
                libros[i] = new Libro(data[0], Integer.valueOf(data[1]));
            }
            input = new Entrada(n, m, libros);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        try{                    
            if( null != fr ){   
                fr.close();     
            }                  
        }catch (Exception e2){ 
            e2.printStackTrace();
        }
        return input;
    }
    /*
     * Método para realizar la escritura de la respuesta del problema, no modificar.
     */
    public static void output(Respuesta output){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(ARCHIVO_ESCRITURA+".txt");
            pw = new PrintWriter(fichero);
            pw.println(output.tiempoTotal);
            for(int i = 0 ; i < output.libroQueEmpieza.length ; ++i){
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

    /*
     * Implementar el algoritmo y devolver un objeto de tipo Respuesta, el cual servirá
     * para imprimir la solución al problema como se requiere en el enunciado.
     */
    static Respuesta solve(int n,int m, Libro [] libros){

        int mm = 0; // Suma de todas las paginas de los libros
        int mn = 0; // promedio de paginas por todos los libros 
        n=0;// cantidad de escritores
        int e = mm/n; // al resultado tenemos que aplicarle la funcion piso para saber las paginas en numeros enteros
        int d = mm - (e * n);

        while(true){     

        if(mm >= n ){

            if(d == 0){
                return new Respuesta(e, new String[0], new String[0]); // cantidad de dias que se demoran los escritores
            }else{
                e = d/n; // hacer funcion piso para tomar la parte entera
            }

        // si la cantidad de paginas es menor que los escritores entonces
        }else{

        // pensar en retornar esto
            return new Respuesta(e+1, new String[0], new String[0]);
            /* if(mm*2 == n ){
                int e = mm/mm*2
            }else if(){

            }
            int e = mm/  */

        }
        }
        //return new Respuesta(0, new String[0], new String[0]);
    }
    public static void main(String[]args){
        Entrada input = input();
        Respuesta r = solve(input.n, input.m, input.libros);
        output(r);
    }

    static class Entrada{
        int n;
        int m;
        Libro[]libros;
        public Entrada(int n, int m, Libro[] libros) {
            this.n = n;
            this.m = m;
            this.libros = libros;
        }
        
    }
    static class Respuesta{
        int tiempoTotal;
        /*
         * Esta variable contiene en su posición i, el nombre del libro por el que empieza el i-ésimo
         * escritor.
         */
        String[]libroQueEmpieza;
        /*
         * Esta variable contiene en su posición i, el nombre del libro por el que termina el i-ésimo
         * escritor.
         */
        String[]libroQueTermina;
        public Respuesta(int tiempoTotal, String[] libroQueEmpieza, String[] libroQueTermina) {
            this.tiempoTotal = tiempoTotal;
            this.libroQueEmpieza = libroQueEmpieza;
            this.libroQueTermina = libroQueTermina;
        }

        
    }
    /*
     * Clase base para interpretar los objetos tratados en el problema.
     */
    static class Libro{
        String nombre;
        int paginas;
        public Libro(String nombre, int paginas) {
            this.nombre = nombre;
            this.paginas = paginas;
        }
    }
}
