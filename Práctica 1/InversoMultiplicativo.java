public class InversoMultiplicativo{

    private static int inv = 0;

    /**
     * Procedimiento que calcula el módulo inverso: (a^-1)( mod n)
     * pero devuelve false, si no existe el inverso o true en caso
     * contrario.
     * Si el valor del inverso es calculado, entonces es almacenado
     * en la variable global inv
     */
    public static int moduloInverso(int a, int n){
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
     * procedimiento que calcula el valor del maximo común divisor
     * de a y b siguiendo el algoritmo de euclides extendido,
     * devolviendo en un arreglo la siguiente estructura [d,x,y]
     * donde:
     *    mcd(a,b) = d = a*x + b*y
     */
    public static int[] euclidesExtendido(int a, int b) {
    	int[] resp = new int[3];
    	int x=0,y=0,d=0;

    	if(b==0){
    		resp[0] = a; resp[1] = 1; resp[2] = 0;
    	}else{
    		int x2 = 1, x1 = 0, y2 = 0, y1 = 1;
    		int q = 0, r = 0;

    		while(b>0){
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


	public static void main(String[] args) {
		System.out.println(moduloInverso(22, 27));
	}
}