import java.lang.Exception;
/**
 * Codificador y decodificador de textos con Cifrado de Hill.
 *
 * @author cderian
 */

public class Hill {

    /*
     * A B C D E F G H I J K  L  M  N  Ñ  O  P  Q  R  S  T  U  V  W  X  Y  Z 
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
     */
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 

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
                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;
                        nm[indice][k-1]=matriz[j][k];
                    }
                }
            }
            if(i%2==0)
                det+=matriz[i][0] * determinante(nm);
            else
                det-=matriz[i][0] * determinante(nm);
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
            throw new Exception("La matriz no es cuadrada");
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

            if(i > cadena.length()){
                //Parte aún no funcional
                break;
            }else{
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
        }

        return rtnCadena;
    }

    /**
     * 
     */
    private static String decodificar(String cadena, int[][] llave) {
        String rtnCadena = "";

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
            //int[][] matriz = new int[3][2];  

            //Matriz 3x3 de Prueba 
            int[][] matriz = new int[3][3];
            matriz[0][0] = 1;
            matriz[0][1] = 2;
            matriz[0][2] = 3;
            matriz[1][0] = 0;
            matriz[1][1] = 4;
            matriz[1][2] = 5;
            matriz[2][0] = 1;
            matriz[2][1] = 0;
            matriz[2][2] = 6;

            //NOTA: pueden agregar los métodos necesarios para obtener la matriz de la palabra clave

            //int[][] matriz = obtenerClave(llave);

            String mensajeCodificado = codificar(mensaje, matriz);
            System.out.println("Texto codificado:   " + mensajeCodificado); 
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        // 3. Decodificar
        //String cadenaDecodificada = decodificar(mensaje, matriz, 2); 
        //System.out.println("Texto decodificado: " + cadenaDecodificada); 
    }
}