import java.util.*;

public class Proof extends Way{
	public Boolean proof (){
		Get_Aut();
		Get_all_way();
		Boolean flag = true;
		//перевірка
		for ( int i = 1; i < 2*S; i++){
			boolean fl =true;
			for (String s: way){
				if (s.length() ==i) {
					fl = false;
					break;
					}
			}
			if (fl)
				return false;
		}
		//простий варіант //
		for ( int i = 0; i < S; i++){
			flag = true;
			if (cycle[i]>0)
				if (proof_start( min_l(i))){
					flag = true;
					for ( int cur = min_l(i); cur < min_l(i)+ cycle[i]; cur ++ ){
						if (!len(i,cur)) flag = false;
					}
					if (flag) return true;
				}
		
		}
		//складний варіант
		ArrayList<Pair> list = new ArrayList<Pair>();
		Pair p = new Pair();
		for ( int i = 0; i < S; i++){
			p = new Pair();
			if (cycle[i]>0){ p.b = cycle[i];
			p.a = min_l(i)% p.b;
			boolean fl=true;
			for (Pair cur: list){
				if (cur.a == p.a && cur.b == p.b)
					fl=false;
			}
			if (fl) list.add(p);}
		}//створили функції;
		
		ArrayList<Pair> list_help = new ArrayList<Pair>();
		for ( int i = 0; i <list.size(); i++){
			list_help.clear();
			int max = list.get(i).b;
			for (int j =i+1; j<list.size(); j++){
				if (list.get(0).b % list.get(j).b == 0){
					list_help.add(list.get(j));
					if (max<list.get(j).b) max=list.get(j).b;
					}
			}
			
			Boolean []rez = new Boolean [max];
			for (int j =0; j < max; j++){
				if ((j  %list.get(i).b)-list.get(i).a == 0)
					rez[j]= true;
				else
					rez[j]=false;
			}
			
			for (Pair cur:list_help){
				for (int j =0; j < max; j++){
					if ((j  % cur.b)-cur.a == 0)
						rez[j]= true;}
			}
			
			
			
			boolean f = true;
			for (int j =0; j < max; j++){
				if(rez[j]== false)
					f = false;
			}
			if(f) return true;
			
		}
		
		return false;
		
	}
	
	private int min_l(int i){
		int min = S*S;
		String c = Integer.toString(i);
		for (String cur: way){
			if (cur.contains(c) && cur.length() < min) 
				min = cur.length();
		}
		return min;
	}
	
	private Boolean len(int i, int l){
		String c = Integer.toString(i);
		for (String cur: way){
			if (cur.contains(c) && cur.length()== l) 
				return true;
		}
		return false;
	}
	
	private Boolean proof_start(int min_l){
		boolean f = false;
		for(int i = 1; i< min_l; i++){
			f = false;
			for (String cur: way){
				if (cur.length()== i) 
					f = true;
			}
			if (!f) return false;
		}
			
		return true;
	}
	
}
