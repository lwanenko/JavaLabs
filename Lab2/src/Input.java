/*	Клас Input.java 
 * Приймає і зберігає значення вхідного Автомату;
 */

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Input {
	
	private char []A;
	public int S;
	public int s0;
	public int []F;
	public int [][]f;
	
	public Boolean Space(char c){
		if ((int)c == 32)
			return true;
		return false;
	}
	
	
	
	public void Get(){
	      try( BufferedReader in = new BufferedReader(new FileReader("C:/Users/iwane/workspace/Lab2/src/inp.txt") )) 
	      { 
	        	String l;
	        	l = in.readLine();
	            Get_A(l); 
	            l = in.readLine();
	            Get_S(l);	            
	            l = in.readLine();
	            s0 = Integer.parseInt(l);
	            l = in.readLine();
	            Get_F(l);
	            
	            f = new int [S][S];
	            for (int i = 0; i < S; i++)
	            	for (int ii = 0; ii < S; ii++)
	            		f[i][ii]=-1;
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
						s="";
						i++;
						break;
					case 2:
						s += c;
						S_= Integer.parseInt(s);
						i++;
						break;
				}
		 f[s_][S_]= 0;;
	}
}


