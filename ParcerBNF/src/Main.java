import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		inputBNF();
	}
	
	private static List<String> bnf ;
	private static String code = new String();
	
	public static void inputBNF(){
		try {
			bnf = Files.readAllLines(Paths.get("C:/Users/iwane/workspace/ParcerBNF/src/JavaBNF.txt"),StandardCharsets.UTF_8);
			FileReader reader = new FileReader("C:/Users/iwane/workspace/ParcerBNF/src/code.txt");
			 int c;
	            while((c=reader.read())!=-1){
	            	char cur =(char)c;
	                if (c!=13 && c !=10 && c !=9) 
	                	code = code+cur;
	                
	       
	            } 
	          reader.close();  
			} catch (IOException e) {
					e.printStackTrace();
			}
		    BNF p = new BNF(bnf);
		    
		    Lexem  l = new Lexem(p,code.toCharArray(),"<Програма>",0,0);
		   if (l.isLexem()) return;

	}

}

