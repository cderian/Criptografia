import java.lang.Exception;

/**
 * Codificador y decodificador de textos con Cifrado de Hill.
 *
 * @author cderian
 * @author len cortez
 */

public class Hill {

    /*
     * A B C D E F G H I J K  L  M  N  Ñ  O  P  Q  R  S  T  U  V  W  X  Y  Z 
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
     */
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 
    
    private static Algebra operaciones = new Algebra();
    private static Matriz matriz = new Matriz(alfabetoMayusculas);

    /**
     * Obtiene una matriz.
     * Los valores de esta matriz representan la posición en el alfabeto
     * que tengan las letras de una palabra clave.
     *
     * @param clave la palabra clave se la cual se obtendrá una matriz.
     * @return matriz la matriz clave.
     */
    private static int[][] obtenerClave(String clave) throws Exception{
        int[][] matriz_clave = new int[0][0];
        double raiz = Math.sqrt(clave.length());

        if(raiz == (int)raiz){
            int raizInt = (int)raiz;
            matriz_clave = new int[raizInt][raizInt];
            int bandera = 0;

            for(int i=0; i<raizInt; i++){
                for(int j=0; j<raizInt; j++){
                    matriz_clave[i][j] = alfabetoMayusculas.indexOf(clave.charAt(bandera));
                    bandera++;
                }
            }

        }else{
            throw new Exception("Llave inválida. No se puede formar una matriz nxn con dicha clave");
        }

        return matriz_clave;
    }

    /**
     * Obtiene un texto.
     * @param cadena la cadena que se traducirá.
     * @param llave la matriz clave que ayudará al descifrado.
     * @return el texto traducido.
     */
    private static String obtenerTexto(String cadena, int[][] llave){
        String texto = "";
        String bloque = "";
        int i = llave.length;
        int[][] matriz_cif = new int[1][llave.length];
        int[][] matriz_nueva = new int[1][llave.length];

        while(cadena.length() > 0){

            //Segmentando el texto
            bloque = cadena.substring(0, i);

            //Obteniendo valor numérico de las letras
            for (int l=0; l<bloque.length(); l++) {
                int pos = alfabetoMayusculas.indexOf(bloque.charAt(l));
                matriz_cif[0][l] = pos;
            }

            //Obteniendo el valor numérico cifrado de las letras mod (long alfabeto)
            for(int m1 = 0; m1 < llave.length; m1++){
                int valor = 0;

                for(int m2 = 0; m2 < matriz_cif[0].length; m2++){
                    valor+= (llave[m1][m2] * matriz_cif[0][m2]);
                }

                matriz_nueva[0][m1] = valor % alfabetoMayusculas.length();
            }

            //Traduciendo el valor numérico a letra
            for(int m = 0; m < llave.length; m++){
                texto+=""+ alfabetoMayusculas.charAt(matriz_nueva[0][m]);
            }
            cadena = cadena.substring(i);
        }
        
        return texto;
    }

    /**
     * Cifra un texto con Cifrado de Hill.
     *
     * Antes de cifrar tiene que verificar que la matriz clave sea cuadrada y que
     * sea invertible en Zn (n = tamaño del alfabeto).
     * Si el determinante de la matriz y el tamaño del alfabeto son números primos
     * entonces, la matriz es invertible en Zn.
     *
     * @param cadena el texto que se cifrará
     * @param llave la matriz clave que ayudará a cifrar
     * @return rtnCadena el texto cifrado
     */
    private static String codificar(String cadena, int[][] llave) throws Exception{
        String rtnCadena = "";

        int det = 0;
        
        //La matriz debe ser cuadrada.
        if(llave.length != llave[0].length){
            throw new Exception("La matriz clave no es cuadrada");
        }else{
            det = operaciones.determinante(llave);
        }

        int mcd = operaciones.mcd(det, alfabetoMayusculas.length());

        //La matriz debe ser invertible en Z(27).
        if(mcd != 1){
            throw new Exception("El |M| y |Alfabeto| deben ser primos");
        }
        
        rtnCadena = obtenerTexto(cadena, llave);
        return rtnCadena;
    }


    /**
     * Descifra un texto con Cifrado de Hill.
     *
     * Antes de cifrar tiene que verificar que la matriz clave sea cuadrada y que
     * sea invertible en Zn (n = tamaño del alfabeto).
     * Si el determinante de la matriz y el tamaño del alfabeto son números primos
     * entonces, la matriz es invertible en Zn.
     *
     * @param cadena el texto que se descifrará
     * @param llave la matriz clave que ayudará a descifrar
     * @return rtnCadena el texto descifrado
     */
    private static String decodificar(String cadena, int[][] llave) throws Exception{
        String rtnCadena = "";
    	
        int det = 0;
        
        //La matriz debe ser cuadrada.
        if(llave.length != llave[0].length){
            throw new Exception("La matriz clave no es cuadrada");
        }else{
            det = operaciones.determinante(llave);
        }

        int mcd = operaciones.mcd(det, alfabetoMayusculas.length());

        //La matriz debe ser invertible en Z(27).
        if(mcd == 0){
            throw new Exception("La matriz clave no es invertible. No se puede decodificar.");
        }

        int[][] inversa = matriz.matrizInversa(llave);

        rtnCadena = obtenerTexto(cadena, inversa);
        return rtnCadena;        
    }

    /**
     * Método principal
     */
    public static void main(String[] args) {
        try{
            String mensaje = "CUADERNODECULTURACIENTIFICA";
            String llave   = "FORTALEZA";
            
            // 1. Texto original
            System.out.println("Texto original:     " + mensaje);
            System.out.println("Llave:              " + llave);
            System.out.println();  
            
            // 2. Codificar
            int[][] matriz = obtenerClave(llave);

            String mensajeCodificado = codificar(mensaje, matriz);
            System.out.println("Texto codificado:   " + mensajeCodificado);

            //3. Decodificar
            String cadenaDecodificada = decodificar(mensajeCodificado, matriz); 
            System.out.println("Texto decodificado: " + cadenaDecodificada);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
