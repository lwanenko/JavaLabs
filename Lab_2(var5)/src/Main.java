import java.io.IOException;

public abstract class Main{

	public static void main(String[] args)  {
		try{
		Proof A = new Proof();
		if (A.Ans())  System.out.println("+");
		else System.out.println("-");
		}
		catch (Exception ex){  
			System.out.println(0);}
	}

}
/*
 * Результати :
 * 				+/- умова виконується/невиконується
 * 				0 десь є помилка чи при веденні автомату чи в коді
 */