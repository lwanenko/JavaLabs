//Метод хорд 
public class ChordM extends fanc{
	
	public double  Rez(){
		int i =0;

		while(Math.abs(f(b)) > e)
		{
			a = b - ((b - a) * f(b))/(f(b) - f(a));
			b = a - ((a - b) * f(a))/(f(a) - f(b));
			System.out.println(i +") "+a);
		i++;
    	}
    return b;
	}

}
