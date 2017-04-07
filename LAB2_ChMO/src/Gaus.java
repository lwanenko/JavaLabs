
public class Gaus extends Array {
	public Gaus {
		double det = 1;
		int n =4;
		 Clear(n);
		 double[][] a = new double[n][];
         double[][] b = new double[1][];
		 for (int i=0; i<n; ++i) 
         {
             int k = i;
             for (int j=i+1; j<n; ++j)
                 if (Math.abs(A(j,i)) > Math.abs(A(k,i)))
                     k = j;
             if (Math.abs(A(k,i)) < eps) 
             {
                 det = 0;
                 break;
             }
             
             b[0] = a[i];
             a[i] = a[k];
             a[k] = b[0];
             //если i не равно k
             if (i != k)
             //то меняем знак определителя
                 det = -det;
             //умножаем det на элемент a[i][i]
             det *= a[i][i];
             //идем по строке от i+1 до конца
             for (int j=i+1; j<n; ++j)
             //каждый элемент делим на a[i][i]
                 a[i][j] /= a[i][i];
             //идем по столбцам
             for (int j=0; j<n; ++j)
             //проверяем
                 if ((j != i)&&(Math.Abs(a[j][i]) > EPS))
                 //если да, то идем по k от i+1
                     for (k = i+1; k < n; ++k)
                         a[j][k] -= a[i][k] * a[j][i];
         }
         //выводим результат
         Console.WriteLine(det);
	}
}
