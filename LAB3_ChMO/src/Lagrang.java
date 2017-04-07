import java.util.ArrayList;

public class Lagrang {
	
	Table t ;
	
	public Lagrang(){
		t = new Table();
		get_dw_n1();
		
	}
	
	
	
	
	double [] dw_n1 = new double [6];
	
	private void get_dw_n1(){
		for (int i = 0; i < 6; i++){
			double rez = 1;
			for(int j = 0;j < 6;j++){
				if (j != i)
					rez = rez *(t.t[1][i]-t.t[1][j]); 
			}
			dw_n1[i]=rez;
		}
	}
	
	
	
	
	private double set_w_n1(double x,int j){
		double rez = 1;	
		for ( int i = 0; i < 5 && i!=j;i ++){
			rez = rez * (x - t.t[1][j]);
		}
		return rez;
	}
	
	private double l(int j, double x){
		double rez = 1;
		for ( int i = 0; i < 5 && i!=j;i ++){
			rez = rez * (x - t.t[1][j]);
		}
		return rez/dw_n1[j];
	}
	
	
	public double L (double x ){
		double rez = 0;
		for (int i =0; i < 5;i++){
		
			rez= rez+ (t.t[0][i] *l(i,x));
		}
		
		
		return rez;
	}
	
	public double l(double x){
		//double rez =	(7.0/1500000)*x*x*x*x*x -  (63.0/80000)*x*x*x*x+(589.0/12000)*x*x*x    -(1109.0/800)*x*x+    (3449.0/200)*x-77;
		return    (7.0/1500000) *x*x*x*x*x - (63.0/80000)*x*x*x*x+(589.0/12000.0)*x*x*x - (1109.0/800.0)*x*x + (3499.0/200.0)*x - 77;
	}
	
}
