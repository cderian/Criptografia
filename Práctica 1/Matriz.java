/**
 * Clase que contiene operaciones necesarias
 * para obtener la matriz inversa.
 *
 * @author cderian
 * @author len cortez
 */
public class Matriz{

	private static Algebra operaciones = new Algebra();
	static String alfabeto;

	/**
	 * Constructor con el alfabeto de cifrado.
	 */
	public Matriz(String alfabeto){
		this.alfabeto = alfabeto;
	}

	/**
     * Obtiene la matriz inversa de una matriz.
     * @param matriz la matriz a la cual se obtendrá su inversa.
     * @return matriz_inv la inversa de la matriz.
     */
    public static int[][] matrizInversa(int[][] matriz) {
        int det = operaciones.determinante(matriz);
        int mod = alfabeto.length();
        int inv = operaciones.moduloInverso(det, mod);

        int[][] matriz_inversa = matrizCofactores(matriz);
        matriz_inversa = matrizTranspuesta(matriz_inversa);
        matriz_inversa = multiplicarMatriz(matriz_inversa, inv);

        for(int i=0; i<matriz_inversa.length; i++){
            for(int j=0; j<matriz_inversa.length; j++){
                matriz_inversa[i][j] = matriz_inversa[i][j]%mod;
            }
        }

        for(int i=0; i<matriz_inversa.length; i++){
            for(int j=0; j<matriz_inversa.length; j++){
                if(matriz_inversa[i][j] < 0){
                    matriz_inversa[i][j]+= mod;
                }
            }
        }

        return matriz_inversa;
    }

    /**
     * Obtiene la multiplicacion de un escalar por una matriz.
     * @param n un entero, el escalar para multiplicar.
     * @param matriz la matriz a la cual se va a multiplicar.
     * @return una multiplicación de una matriz por un escalar.
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
     * Obtiene los cofactores de una matriz.
     * @param matriz la matriz a la cual se obtendrá sus cofactores.
     * @return matriz_nueva los matriz cofactores de la matriz.
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

                detValor = operaciones.determinante(det);
                matriz_nueva[i][j] = detValor * (int)Math.pow(-1, i+j+2);
            }
        }
        return matriz_nueva;
    }

    /**
     * Obtiene la matriz transpuesta de una matriz.
     * @param matriz la matriz a la cual se obtendrá su transpuesta.
     * @return matriz_nueva la transpuesta de la matriz.
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
}