
public class Zeydel extends Array {
	
	public Zeydel (){
		
		
			for (int n = 2;n <5; n++){
					Clear(n);
					
					for(int qq = 1; qq <1000; qq++){
						X = new double [n];
						for (int i = 0; i < n; i++){ 
				            X[i] = B[i];

				            for (int j = 0; j < n; j++){
				                if (j < i){
				                    X[i] -= A(i,j) * X[j];
				                }

				                if (j > i){
				                    X[i] -= A(i,j) * X_1[j];
				                }
				            }
				            X[i] /= A(i,i);
				        }
				        double error = 0.0; 
				        for (int i = 0; i < n; i++){
				            error += Math.abs (X[i] - X_1[i]);
				        }
				        if (error < eps){
				            break;
				        }
				        X_1 = X;
				        outX(n);
					}
					
			}
	}
	
}
