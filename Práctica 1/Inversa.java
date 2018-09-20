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

    private static long inv = 0;
     
    /*
         * Procedimiento que calcula el módulo inverso: (a^-1)( mod n)
         * pero devuelve false, si no existe el inverso o true en caso
         * contrario.
         * Si el valor del inverso es calculado, entonces es almacenado
         * en la variable global inv
         **/
        public static boolean moduloInverso(long a, long n)    
        {
               long[] resp = new long[3];           
               resp = euclidesExtendido(a,n);
               
               if(resp[0]>1)
                    return false;
                    
               else
               {
                   if(resp[1]<0)
                       inv = resp[1]+n;
                   else
                       inv = resp[1];
               
                   return true;
               }
        } 

    public static long[] euclidesExtendido(long a, long b)  
        {
             long[] resp = new long[3];
             long x=0,y=0,d=0;
                
             if(b==0)
             {
            resp[0] = a;    resp[1] = 1;    resp[2] = 0;
             }      
             else
             {
            long x2 = 1, x1 = 0, y2 = 0, y1 = 1;
            long q = 0, r = 0;
                    
            while(b>0)
            {
            q = (a/b);
            r = a - q*b;
            x = x2-q*x1;
            y = y2 - q*y1;
            a = b;
            b = r;
            x2 = x1;
            x1 = x;
            y2 = y1;
            y1 = y;
            }
                    
            resp[0] = a;
            resp[1] = x2;
            resp[2] = y2;
            }
         
            return resp;        
        } 

    public static long exponenciacionModular(long a, double k, long n)
    {    
        long b = 1;
            
        if(k==0)
            return b;
            
        long A = a;
        
        if(k%2==1)
            b = a;
            
        while((k = k/2)!=0)
        {                
            A = (A*A)%n;
            if(k%2==1)
                b=(A*b)%n;    
        }
        
        return b;    
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

        int numero = suma;
        int modulo = 27;

        double[][] matriz_n = matrizInversa(matriz);
        multiplicarMatriz (matriz_n, inv);
        for (int i=0; i<matriz_n.length; i++) {
            for (int j=0; j<matriz_n.length; j++) {
                System.out.println(matriz_n[i][j]);
            }
        }
    }
}