import java.util.*;

public class Way extends Cycles{
	
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

	private Boolean Clear_lev(){
		lev.clear();
		if(lev_next==null)
			return false;
		for ( int cur: lev_next){
			lev.add(cur);
		}
		lev_next.clear();
		return true;
	}
	
	ArrayList <Integer> lev = new ArrayList<Integer>();
	ArrayList <Integer> lev_next = new ArrayList<Integer>();
	public ArrayList<String> way = new ArrayList<String> ();
	// для запису всіх можливих шляхів 
	
	private ArrayList<ArrayList <String>>  S_way = 
			new ArrayList<ArrayList <String>>() ;
	// для створення шляхів//другий вимір для альтернатив 

	private void add_S_way(int level, int i, int ii){
		 ArrayList<String> list = new  ArrayList<String>();
		for(String cur : S_way.get(i)){
				 if (cur.length()== level){
						list.add (cur);
				 }
		 }
		for (String cur : list)
			S_way.get(ii).add(cur+ii);
	}
	 
	public void Get_all_way(){
		
		lev.add(s0);
		int level = 0;
		for (int i = 0; i < S; i++)
			S_way.add (new ArrayList<String>());
		
   		S_way.get(s0).add("") ;
		while(true){
			for( int cur: lev){
				for (int ii= 0; ii < S; ii++ ){
					if (f [cur][ii] >= 0){
						add_S_way(level, cur, ii);
						if (!lev_next.contains(ii))
							lev_next.add(ii);	
					}
				}
			}
			level++;
			if ( !Clear_lev() || level > 2*S)
				break;
		}
		//збиваємо всі шляхи в один список
		for (int i: F){
			for(String cur : S_way.get(i)){
					 way.add(cur);
		}
	}
}
	
}
