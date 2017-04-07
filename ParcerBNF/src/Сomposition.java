import java.awt.List;
import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class Сomposition {
    public ArrayList<Сomposition> comp = new ArrayList<Сomposition>();
   
    
    
    public Сomposition(int cur,char []c){
    	
		for(int i = 0; i < c.length; i++){
			switch(c[i]){
			case '<':
				break;
			case '"':
				break;
			case '{':
				break;
			case '[':
				break;
			}
		}
	}
}
