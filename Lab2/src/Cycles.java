/*
 * 	Клас Automaton.java 
 * Мітить допоміжні функції для доведення
 * 	-NotF(перевіряє чи вжодить ця вершина в множину кінцевих)
 *  -Cycle(перевіряє чи є цикли в певній вершині)
*/
public class Cycles extends Input {   
	
	public int []cycle;

	public void Get_Aut () {
		Get();
		cycle = new int [S];
		/*for(int i = 0; i < S; i++)
			if (NotF(i)) cycle[i] = 0;
			else cycle[i] = -1;*/
		cycles();
		return;
	}

	private Boolean NotF(int i){
		for (int cur : F)
			if (cur == i) return false;
		return true;
	}
	
	private void Clean (){
		for (int i = 0; i < S; i++)
        	for (int ii = 0; ii < S; ii++)
        		if (f[i][ii]>0)f[i][ii] = 0;
	}
	
	
    private void Сycle(int point_Start,int point,int level ){    
		for (int ii=0; ii< S; ii++){
				if (f[point][ii] == 0 )
				{	
					if ( ii == point_Start){
						level ++;
						if (cycle[ii]> level || cycle[ii]==0)
							{cycle[ii]= level; }
				        return ;
				        }
					else {
							if (level >= S) return;
							Сycle(point_Start, ii,level+1);
					} 
				}
		}
	}
	
	public void cycles(){
		Clean();
		for (int i=0; i < S; i++){
			Clean();
			if ( cycle[i]==0) 
				 Сycle(i,i,0);
		}
		return;
	}
	
	
}
