
public class LexemBNF{
	
	public String Name;
	public char[]  value;
	
	public LexemBNF (String s){		
		char[] c = s.toCharArray();
		for (int i = 3; i < c.length; i++)
				if(c[i]=='=' && c[i-1]==':' && c[i-2]==':'){
					Name = s.substring(0, i-2);
					value = s.substring(i+1,c.length).toCharArray();
				
				}
	}
	
	
	
}
