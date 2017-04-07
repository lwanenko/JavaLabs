/*	Клас Input.java 
 * Приймає і зберігає значення вхідного Автомату;
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Input {
	
	public char []A;//алфавіт 
	public int S; // кількість станів автмату
	public int s0;// початковий стан 
	public int []F;// множина кінцевих станів
	public Node [][]f;//функція переходів 
	public String w0 = "";
	
	// перевіряє чи с==' '
	public Boolean Space(char c){
		if ((int)c == 32)
			return true;
		return false;
	}
	
	
	
	public void Get(){
	      try( BufferedReader in = new BufferedReader(new FileReader("C:/inp.txt") )) 
	      { 
	        	String l;
	            l = in.readLine();
	            Get_w0(l); //введення слова w0
	            
	            l = in.readLine();
	            Get_A(l); //введення всієї множини A
	            
	            l = in.readLine();
	            Get_S(l); //введення S
	            
	            l = in.readLine();
	            s0 = Integer.parseInt(l);
	            
	            l = in.readLine();
	            Get_F(l); //введення всієї множини F
	            
	            f = new Node [S+1][S+1];
	            while((l = in.readLine()) != null)
	            	Get_f(l); 
	            
	            in.close();
	        }
	      catch(IOException ex){
	    	  System.out.println(ex.getMessage());
	      }
	}
	
	private void Get_A(String s ){
		A = new  char[s.length()/2 + 1];
		char [] l = s.toCharArray();
		int i = 0;
		for(char c : l)
			if (!Space(c)){
				A[i]= c;
				i++;
			}		
	}

	private void Get_S(String s){
		char [] l = s.toCharArray();
		s ="";
		for(char c : l)
			if (!Space(c))
				s += c;
	S = Integer.parseInt(s);
	}
	
	private void Get_F(String s){
		char [] l = s.toCharArray();
		F = new int [s.length()/2 +1];
		s ="";
		int i = 0;
		for(char c : l)
			if (!Space(c))
				s += c;
			else 
				if (s != ""){
					F[i] = Integer.parseInt(s);
					i++;
					s="";
				}
		if (s != "")
			F[i] = Integer.parseInt(s);
				
	}

	private void Get_f(String s){
		char [] l = s.toCharArray();
		s ="";
		String s1 = "";
		int s_ = -1;
	    int S_ = -1;
		int i = 0;
		for(char c : l)
		    if (!Space(c) && i != 2)
				s += c;
			else 
			  if (s != "" || i == 2)
				switch(i){
					case 0:
						s_= Integer.parseInt(s);
						i++;
						s="";
						break;
					case 1: 
						s1=s;
						s="";
						i++;
						break;
					case 2:
						s += c;
						S_= Integer.parseInt(s);
						break;
				}
		if (f[s_][S_] == null) f[s_][S_] = new Node();
		 f[s_][S_].Add(s1);
		
	}
	
	private void Get_w0(String s){
		char [] l = s.toCharArray();
		s ="";
		for(char c : l)
			if (!Space(c))
				s += c;
		w0 = s;
	}
}


