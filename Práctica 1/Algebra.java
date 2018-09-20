/**
 * Clase que contiene operaciones algebraicas
 * que auxilian al Cifrado y Descifrado Hill.
 *
 * @author cderian
 * @author len cortez
 */
public class Algebra{

    /**
     * Obtiene el determinante de una matriz.
     * @param matriz la matriz a la cual se obtendrá su determinante.
     * @return det el determinante de la matriz.
     */
    public static int determinante(int[][] matriz){
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
     * Calcula el máximo común divisor de a y b siguiendo
     * el Algoritmo de Euclides.
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
     * Obtiene el módulo inverso: (a^-1)(mod n).
     * @param a el número del cuál se obtendrá su inverso.
     * @param n el módulo de dónde obtendremos su inverso.
     * @return inv el inverso multiplicativo modular de a mod n.
     */
    public static int moduloInverso(int a, int n){
        int inv = 0;
    	int[] resp = new int[3];
    	resp = euclidesExtendido(a,n);

    	if(resp[0]>1){
    		System.out.println("No existe inverso");
    		System.exit(0);
    	}else{

    		if(resp[1]<0)
    			inv = resp[1]+n;
    		else
    			inv = resp[1];
    	}

    	return inv;
    }

    /**
     * Calcula el máximo común divisor de a y b
     * siguiendo el Algoritmo de Euclides Extendido,
     * @param a el primer número.
     * @param b el segundo número.
     * @return un arreglo con la siguiente estructura [d,x,y]
     *         mcd(a,b) = d = a*x + b*y
     */
    public static int[] euclidesExtendido(int a, int b) {
    	int[] mcd = new int[3];
    	int x = 0;
        int y = 0;
        int d = 0;

    	if(b==0){
    		mcd[0] = a;
            mcd[1] = 1;
            mcd[2] = 0;
    	}else{
    		int x2 = 1;
            int x1 = 0;
            int y2 = 0;
            int y1 = 1;
    		int q = 0;
            int r = 0;

    		while(b>0){
    			q = (a/b);
    			r = a -q*b;
    			x = x2-q*x1;
    			y = y2-q*y1;
    			a = b;
    			b = r;
    			x2 = x1;
    			x1 = x;
    			y2 = y1;
    			y1 = y;
    		}

    		mcd[0] = a;
    		mcd[1] = x2;
    		mcd[2] = y2;
    	}

    	return mcd;
    }
}