import java.util.*;
public class Array {
	
	private	int q = 0;
	private  ArrayList<ArrayList<Double>> a = new ArrayList<ArrayList<Double>>();
	public double [] B;
	public double[] X;
	public double [] X_1;
	public double eps = 0.00001; 
	
	//вводить значення в масив розміру n*n//
	public void Clear(int n){
		System.out.println(n);
		a.clear();
		q =0;
		B = new double [n];
		X = new double [n];
		X_1 = new double [n];
		for(int i = 0; i< n; i++){
			X_1[i] = 0.0;
		}
		
		ArrayList<Double> b = new ArrayList<Double>();
		for ( int i = 1;i < n+1; i++ ){
			b = new ArrayList<Double>();
			for(int j=1; j < n+1 ; j++){
				if (i!=j) b.add((double) (j+i));
				else b.add(1.0/i);
			}
			a.add(b);
			B[i-1]=i;
		}
		return;
	}
	
	// A[i][j]//
	public double A(int i, int j){
		return a.get(i).get(j);
	} 
	
	//  A[i][j]=c//
	public void A(int i, int j, double c){
		ArrayList<Double> b = a.get(i);
		b.set(j, c);
		a.set(i, b);
	}
	
	public void outX(int n){
		String S="";
		q++;
		for ( int i = 0; i< n; i++)
			S=S + X[i]+" ";
		System.out.println(q+") " +S);
	}
	
}
