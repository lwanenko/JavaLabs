/* Chek_w0  -> рекурсія 
 * Chek_s0  -> ДП
 * 
 * */
public class ProofDP extends Input{
	
	public ProofDP(){
		Get();
	}
	
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
	int level ; 
	int [] Lev ;
	private void Clean(){
		level = 0;
		Lev = new int [S+1];
		for (int i = 0;i<=S; i++) 
			Lev[i] = -1;
	}
	
	public Boolean Chek_s0(int cur){
		if (cur == -1) return false;
		Clean();
		
		for (int i =0; i <= S; i++)
			if (f[i][cur]!= null)
				if (i == s0) return true;
				else Lev[i]= level;
		
		for (level = 1;level<=S;level++)
			for (int ii = 0; ii <= S; ii ++)
				if (Lev[ii]== level-1)
				for(int i = 0; i<= S;i++) //↓ перевірка на цикли ↓//
					if ( f[i][ii] != null && ii != i && Lev[i]==-1)
						if (i == s0) return true;
						else Lev[i]= level;
		return false;
	}
}
