import java.lang.Exception;

public class Hill {

    /*
     * A B C D E F G H I J K  L  M  N  Ñ  O  P  Q  R  S  T  U  V  W  X  Y  Z 
     * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
    */
    static String alfabetoMayusculas = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"; 
    static int dim_clave = 3;

    /**
     * 
     */
    private static String codificar(String cadena, int[][] llave) throws Exception{
        String rtnCadena = "";

        if(llave.length != llave[0].length){
            throw new Exception("La matriz no es cuadrada");
        }

        /*if(mcd(det,alfabetoMayusculas.length()) != 1){
            String error = "El determinante de la matriz debe ser primo"
            error+=        "con el tamaño del alfabeto"
            error+=        "\n |Alfabeto| = " + alfabetoMayusculas.length();
            error+=        "\n |M| = " + det +'\n';
            throw Exception(error);
        }*/

        String bloque = "";
        int i = llave.length;
        int[][] matriz_cif = new int[1][llave.length];
        int[][] matriz_nueva = new int[1][llave.length];
            
        //}

        while(cadena.length() > 0){

            if(i > cadena.length()){
                System.out.println("entro if");
                bloque = cadena.substring(i);

                //Obtener valor numérico de las letras
                for (int l=0; l<bloque.length(); l++) {
                    int pos = alfabetoMayusculas.indexOf(bloque.charAt(l));
                    matriz_cif[1][l] = pos;
                }

                //Obteniendo el valor numérico cifrado de las letras
                for(int m1 = 0; m1 < llave.length; m1++){
                    for(int m2 = 0; m2 < matriz_cif.length; m2++){
                        matriz_nueva[1][m1]+= (m1*m2);
                    }
                }

                //Obteniendo el valor numérico cifrado de las letras mod (long alfabeto)
                for(int m = 0; m < llave.length; m++){
                    matriz_nueva[1][m] = matriz_nueva[1][m]% alfabetoMayusculas.length();
                }

                //Traduciendo el valor numérico a letra
                for(int m = 0; m < llave.length; m++){
                    rtnCadena+=""+ alfabetoMayusculas.charAt(matriz_nueva[1][m]);
                }
            }else{
                bloque = cadena.substring(0, i);
                System.out.println(bloque);

                //Obtener valor numérico de las letras
                for (int l=0; l<bloque.length(); l++) {
                    int pos = alfabetoMayusculas.indexOf(bloque.charAt(l));
                    matriz_cif[0][l] = pos;
                }

                for(int a=0; a<matriz_cif[0].length; a++){
                    System.out.println(matriz_cif[0][a]);
                }

                //Obteniendo el valor numérico cifrado de las letras
                /*for(int m1 = 0; m1 < matriz_cif[0].length; m1++){
                    for(int m2 = 0; m2 < matriz_cif[0].length; m2++){
                        matriz_nueva[0][m1]+= (m1*m2);
                    }
                }

                //Obteniendo el valor numérico cifrado de las letras mod (long alfabeto)
                for(int m = 0; m < llave[0].length; m++){
                    matriz_nueva[0][m] = matriz_nueva[0][m]% alfabetoMayusculas.length();
                }

                //Traduciendo el valor numérico a letra
                for(int m = 0; m < llave.length; m++){
                    rtnCadena+=""+ alfabetoMayusculas.charAt(matriz_nueva[0][m]);
                }*/

                cadena = cadena.substring(i);
            }
        }

        return rtnCadena;
    }

    /*private static int[][] obtenerMatriz(String bloque){
        //Obtener valor numérico de las letras
        for (int l=0; l<bloque.length(); l++) {
            int pos = alfabetoMayusculas.indexOf(bloque.charAt(l));
            matriz_cif[1][l] = pos;
        }

        //Obteniendo el valor numérico cifrado de las letras
        for(int m1 = 0; m1 < llave.length; m1++){
            for(int m2 = 0; m2 < matriz_cif.length; m2++){
                matriz_nueva[1][m1]+= (m1*m2);
            }
        }

        //Obteniendo el valor numérico cifrado de las letras mod (long alfabeto)
        for(int m = 0; m < llave.length; m++){
            matriz_nueva[1][m] = matriz_nueva[1][m]% alfabetoMayusculas.length();
        }

        //Traduciendo el valor numérico a letra
        for(int m = 0; m < llave.length; m++){
            rtnCadena+=""+ alfabetoMayusculas.charAt(matriz_nueva[1][m]);
        }
    }*/

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
    }

        // 3. Decodificar
        //String cadenaDecodificada = decodificar(mensaje, matriz, 2); 
        //System.out.println("Texto decodificado: " + cadenaDecodificada); 
    }

}