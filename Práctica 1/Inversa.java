public class Inversa{

    public static double[][] matrizInversa(double[][] matriz) {
        double n = 1/determinante(matriz);
        double[][] matriz_nueva = matrizTranspuesta(matrizCofactores(matriz));
        multiplicarMatriz(matriz_nueva, n);

        return matriz_nueva;
    }

    public static void multiplicarMatriz(double[][] matriz, double n) {

        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz.length; j++){
                matriz[i][j]*=n;
            }
        }
    }

    public static double[][] matrizCofactores(double[][] matriz){
        double[][] matriz_nueva = new double[matriz.length][matriz.length];

        for(int i=0; i<matriz.length; i++) {
            for(int j=0; j<matriz.length; j++) {

                double[][] det = new double[matriz.length-1][matriz.length-1];
                double detValor;

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
                matriz_nueva[i][j] = detValor * (double)Math.pow(-1, i+j+2);
            }
        }
        return matriz_nueva;
    }

    public static double[][] matrizTranspuesta(double [][] matriz){
        double[][] matriz_nueva = new double[matriz[0].length][matriz.length];

        for(int i=0; i<matriz.length; i++){
            for(int j=0; j<matriz.length; j++){
                matriz_nueva[i][j] = matriz[j][i];
            }
        }
        return matriz_nueva;
    }

    public static double determinante(double[][] matriz){
        double det;
        
        if(matriz.length == 2){
            det = (matriz[0][0]*matriz[1][1])-(matriz[1][0]*matriz[0][1]);
            return det;
        }

        double suma=0;
        for(int i=0; i<matriz.length; i++){
            double[][] matriz_nueva = new double[matriz.length-1][matriz.length-1];

            for(int j=0; j<matriz.length; j++){
                
                if(j!=i){

                    for(int k=1; k<matriz.length; k++){
                        int indice=-1;

                        if(j<i)
                            indice=j;
                        else if(j>i)
                            indice=j-1;

                        matriz_nueva[indice][k-1] = matriz[j][k];
                    }
                }
            }

            if(i%2==0)
                suma+= matriz[i][0]*determinante(matriz_nueva);
            else
                suma-= matriz[i][0]*determinante(matriz_nueva);
        }

        return suma;
    }
    
    public static void main(String[] args) {
        double[][] matriz = new double[3][3];
            matriz[0][0] = 1;
            matriz[0][1] = 2;
            matriz[0][2] = 3;
            matriz[1][0] = 0;
            matriz[1][1] = 4;
            matriz[1][2] = 5;
            matriz[2][0] = 1;
            matriz[2][1] = 0;
            matriz[2][2] = 6;

        double[][] matriz_n = matrizInversa(matriz);
        for (int i=0; i<matriz_n.length; i++) {
            for (int j=0; j<matriz_n.length; j++) {
                System.out.println(matriz_n[i][j]);
            }
        }
    }
}