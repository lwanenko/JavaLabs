import java.util.*;
public class Node {
	public ArrayList<String> c = new ArrayList<String>();// значення переходів
	// так як їх може бути декілька 
	// Наприклад при переході (1) -> (3) 
	// значення  можуть бути   a або b 
	
	public Boolean chek(String s){
		for(String cur : c)
			if ( s.equals(cur)) 
				return true;
		return false;
	}
	
	public void Add(String s){
		c.add(s);
	}
	
}
