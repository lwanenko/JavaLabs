public class Newton extends fanc {
	public double xn;

	public double Rez(double x0){
		xn= x0;
		for (int i = 0; i < 100000; i++){
			xn = xn - f(xn)/F(xn);
			System.out.println(i +") "+xn);
			if (Math.abs(f(xn)) < e ) return xn;
		}
		return -1;
	}
	
	
}
