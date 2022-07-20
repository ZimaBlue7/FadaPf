import java.io.*;

public class PuntoA{
    /*
     * Nombres de los archivos de lectura y escritura, modifique como considere.
     */
    static String ARCHIVO_LECTURA = "inA";
    static String ARCHIVO_ESCRITURA = "outA";
    /*
     * Método para realizar la lectura del problema, no modificar.
     */
    public static Procedimiento[] input(){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        Procedimiento[] p = null;
        try {
            archivo = new File (ARCHIVO_LECTURA +".txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            int n = Integer.parseInt(br.readLine());
            p = new Procedimiento[n];
            String linea;
            for(int i = 0 ; i < n ; ++i){
                linea=br.readLine();
                String[]data = linea.split(" ");
                String[]horas = data[1].split("-");
                int horaI, minI, horaF, minF;
                String[]tiempo = horas[0].split(":");
                horaI = Integer.valueOf(tiempo[0]);
                minI = Integer.valueOf(tiempo[1]);
                tiempo = horas[1].split(":");
                horaF = Integer.valueOf(tiempo[0]);
                minF = Integer.valueOf(tiempo[1]);
                p[i] = new Procedimiento(data[0], new Hora(horaI, minI), new Hora(horaF, minF));
            }   
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
        return p;
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

            pw.println(output.n);
            pw.println(output.tiempoTotal);
            for(int i = 0 ; i < output.n ; ++i){
                pw.println(output.nombreProcedimientos[i]);
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
    static Respuesta solve(int n, Procedimiento[] procedimientos){
        return new Respuesta(0, new Hora(0, 0), new String[0]);
    }
    public static void main(String[]args){
        Procedimiento[]input = input();
        Respuesta r = solve(input.length, input);
        output(r);
    }

    static class Respuesta{
        int n;
        Hora tiempoTotal;
        String []nombreProcedimientos;
        public Respuesta(int n, Hora tiempoTotal, String[] nombreProcedimientos) {
            this.n = n;
            this.tiempoTotal = tiempoTotal;
            this.nombreProcedimientos = nombreProcedimientos;
        }

        
    }

    /*
     * Clase base para interpretar los objetos tratados en el problema.
     */
    static class Procedimiento{
        String nombre;
        Hora horaInicio, horaFin;

        public Procedimiento(String nombre, Hora horaInicio, Hora horaFin) {
            this.nombre = nombre;
            this.horaInicio = horaInicio;
            this.horaFin = horaFin;
        }

        @Override
        public String toString() {
            return "Procedimiento [horaFin=" + horaFin + ", horaInicio=" + horaInicio + ", nombre=" + nombre + "]";
        }

        
    }
    static class Hora{
        int hora, minutos;

        public Hora(int hora, int minutos) {
            this.hora = hora;
            this.minutos = minutos;
        }
        @Override
        public String toString(){
            String res = "";
            if (hora < 10)
                res+="0"+hora;
            else
                res+=hora; 
            res+=":";
            if (minutos < 10)
                res+="0"+minutos;
            else
                res+=minutos; 
            
            return res;
        }
    }
}