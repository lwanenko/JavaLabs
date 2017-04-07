
public class Dehotomia extends  fanc {
	
public Boolean flag = false;
private double c;

public  Dehotomia(){
	 int i = 0;
		while(true){
			c = (a+b)/2;
			if ( f(c) == 0 ) {flag = true; break;}
			if ( f(a) * f(c) < 0 ) b = c;
			if ( f(b) * f(c) < 0 ) a = c;
			System.out .println(i+") "+ c );
			i++;
		}
		System.out.print("Дехотомія: ");
}

public double Rez(){
	 return c;
}

}
