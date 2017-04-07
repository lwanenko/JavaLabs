// Метод Мюллера

public class Mueller extends fanc{
	double A, B, C, q;
	
	
	public double Rez(double x0,double x1,double x2){
		
		a =2;
		
		while(Math.abs(B - A) > e)
		{
			while (Math.abs(x0)< e){
				q = (x0 - x1)/(x1 - x2);
				A = q * f(x0) - q *(1+q)*f(x1)+ q * q* f (x2);
				B = (2*q+1)*f(x0) - (1+q) *(1+q)*f(x1)+ q * q* f (x2);
				C = (1+q)*f(x0);
			
				q = x0 - (x0 - x1)* ( (2*C)/(B + Math.sqrt(B*B - 4* A* C) ));
				if (Math.abs(q)< Math.abs(x0 - (x0 - x1)* ( (2*C)/(B - Math.sqrt(B*B - 4* A* C) ))))
					q = x0 - (x0 - x1)* ( (2*C)/(B - Math.sqrt(B*B - 4* A* C) ));
			
				x2 = x1;
				x1 =x0;
				x0 = q;
			}
		}
    return B;
	}
}

