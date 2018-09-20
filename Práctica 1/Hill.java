import java.lang.Exception;

/**
 * Codificador y decodificador de textos con Cifrado de Hill.
 *
 * @author cderian
 * @author len cortez
 */

public class Hill {

    static InversoMultiplicativo iv = new InversoMultiplicativo();

    /*
     * A B C D E F G H I J K  L  M  N  Ñ  O  P  Q  R  S  T  U  V  W  X  Y  Z 
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
     */
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

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
     * Obtiene el determinante de una matriz
     * @param matriz la matriz a la cual se obtendrá su determinante.
     * @return det el determinante de la matriz
     */

    private static int determinante(int[][] matriz){
        int det = 0;

        if(matriz.length == 2){
            det = (matriz[0][0]*matriz[1][1]) - (matriz[0][1]*matriz[1][0]);
        }

        for(int i=0; i<matriz.length; i++){
            int[][] nm = new int[matriz.length-1][matriz.length-1];

            for(int j=0; j<matriz.length; j++){
                
                if(j!=i){
                
                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;
                
                        if(j<i){
                            indice=j;
                        }else if(j>i){
                            indice=j-1;
                        }

                        nm[indice][k-1]=matriz[j][k];
                    }
                }
            }

            if(i%2==0){
                det+=matriz[i][0] * determinante(nm);
            }else{
                det-=matriz[i][0] * determinante(nm);
            }
        }

        return det;
    }

    /**
     * Obtiene el máximo común divisor de dos números.
     * @param n el primer número.
     * @param m el segundo número.
     * @return res el mcd de n y m.
     */

    public static int mcd(int n, int m){
        int a = Math.max(n,m);
        int b = Math.min(n,m);
        int res;

        do{
            res = b;
            b = a%b;
            a = res;
        }while(b != 0);

        return res;
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
            det = determinante(llave);
        }

        int mcd = mcd(det, alfabetoMayusculas.length());

        //La matriz debe ser invertible en Z(27).
        if(mcd != 1){
            throw new Exception("El |M| y |Alfabeto| deben ser primos");
        }
        
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
                rtnCadena+=""+ alfabetoMayusculas.charAt(matriz_nueva[0][m]);
            }
            cadena = cadena.substring(i);
        }

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
            det = determinante(llave);
        }

        int mcd = mcd(det, alfabetoMayusculas.length());

        //La matriz debe ser invertible en Z(27).
        if(mcd == 0){
            throw new Exception("La matriz clave no es invertible. No se puede decodificar.");
        }
        
        rtnCadena = codificar(cadena, matrizInversa(llave));

        return rtnCadena;
        
    }

    /**
     * Obtiene la matriz inversa de una matriz
     * @param matriz la matriz a la cual se obtendrá su inversa.
     * @return matriz_inv la inversa de la matriz
     */

    public static int[][] matrizInversa(int[][] matriz) {
        int det = determinante(matriz);
        int mod = alfabetoMayusculas.length();
        int inv = iv.moduloInverso(det, mod);

        int[][] matriz_inversa = matrizCofactores(matriz);
        matriz_inversa = matrizTranspuesta(matriz_inversa);
        matriz_inversa = multiplicarMatriz(matriz_inversa, inv);

        for(int i=0; i<matriz_inversa.length; i++){
            for(int j=0; j<matriz_inversa.length; j++){
                matriz_inversa[i][j] = matriz_inversa[i][j]%mod;
            }
        }

        return matriz_inversa;
    }

    /**
     * Obtiene la multiplicacion de un escalar por una matriz
     * @param n un entero, el escalar para multiplicar
     * @param matriz la matriz a la cual se va a multiplicar.
     * @return una multiplicación de una matriz por un escalar
     */

    public static int[][] multiplicarMatriz(int[][] matriz, int n) {
        int[][] m = new int[matriz.length][matriz[0].length];

        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz.length; j++){
                m[i][j] = matriz[i][j]*n;
            }
        }

        return m;
    }

    /**
     * Obtiene los cofactores de una matriz
     * @param matriz la matriz a la cual se obtendrá sus cofactores.
     * @return matriz_nueva los matriz cofactores de la matriz
     */

    public static int[][] matrizCofactores(int[][] matriz){
        int[][] matriz_nueva = new int[matriz.length][matriz.length];

        for(int i=0; i<matriz.length; i++) {
            for(int j=0; j<matriz.length; j++) {

                int[][] det = new int[matriz.length-1][matriz.length-1];
                int detValor;

                for(int k=0; k<matriz.length; k++) {
                    if(k!=i) {
                        for(int l=0; l<matriz.length; l++) {
                            
                            if(l!=j) {
                                int indice1 = k<i ? k : k-1 ;
                                int indice2 = l<j ? l : l-1 ;
                                det[indice1][indice2] = matriz[k][l];
                            }
                        }
                    }
                }

                detValor = determinante(det);
                matriz_nueva[i][j] = detValor * (int)Math.pow(-1, i+j+2);
            }
        }
        return matriz_nueva;
    }

    /**
     * Obtiene la matriz transpuesta de una matriz
     * @param matriz la matriz a la cual se obtendrá su transpuesta.
     * @return matriz_nueva la transpuesta de la matriz
     */

    public static int[][] matrizTranspuesta(int [][] matriz){
        int[][] matriz_nueva = new int[matriz[0].length][matriz.length];

        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz.length; j++){
                matriz_nueva[i][j] = matriz[j][i];
            }
        }
        return matriz_nueva;
    }



    /**
     * Método principal
     */

    public static void main(String[] args) {
        try{
            //String mensaje = "CUADERNODECULTURACIENTIFICA";
            String mensaje = "DIVULGANDOLASMATEMATICAS";
            //String llave   = "FORTALEZA";
            String llave   = "BCDAEFBAG";

            // 1. Texto original
            System.out.println("Texto original:     " + mensaje);
            System.out.println("Llave:              " + llave);
            System.out.println();  
            
            // 2. Codificar
            int[][] matriz = obtenerClave(llave);

            String mensajeCodificado = codificar(mensaje, matriz);
            System.out.println("Texto codificado:   " + mensajeCodificado);

            //3. Decodificar
            String cadenaDecodificada = decodificar(mensaje, matriz); 
            System.out.println("Texto decodificado: " + cadenaDecodificada);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
