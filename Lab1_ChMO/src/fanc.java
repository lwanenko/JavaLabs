
public class fanc {
	public double e = 0.00001;
	public  double a = 3;
	public double b = 10;
	private double pi = 3.1415926;
	private double E  = 2.7182818;

	public double f(double x){
		x = Math.pow((x - E),3)*( Math.log(x/pi));
		if (Math.abs(x) < e) return 0;
		return x;
	}
	
	public double F(double x){
		x = Math.pow((x - E),2) * (- E/x + 3*Math.log(x/pi) +1);
		if (Math.abs(x) < e) return 0;
		return x;
	}

}
