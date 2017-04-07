
public class Proof extends Input {
	
	public Proof(){
		Get();
	}
	
	//Перевіряю чи можна побудувати слово w0 
	//Рухатись починаю з кінцевих точок F
	//результат стан на якому завершиться функція
	public Boolean Ans(){
		for (int i : F){
			if ( Chek_w0(w0, i) ) return true;
		}
		return false;
	}
	
	private Boolean Chek_w0 (String rez, int cur){
		if (rez == null) return false;
		for (int i = 0; i<=S; i++){
			if (f[i][cur] != null)
			if (f[i][cur].chek(rez.substring (rez.length()-1, rez.length()))) 
			{
				if(rez.substring(0,rez.length()-1).equals(""))
					return Chek_s0(i);
				if (Chek_w0(rez.substring(0,rez.length()-1), i)) 
					return true;
			}	
		}
		return false;
	}
	
	//Перевіряю чи можна з s0 дійти 
	//в стан cur
	private Boolean Chek_s0(int cur){
		for (int i =0; i < S; i++){
			if (f[i][cur]!= null)
				if (i == s0) return true;
				if (Chek_s0(i))return true; 
		}
		return false;
	}
	
	//Вдповідь до варінту 5 
	
}
