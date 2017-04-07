import java.util.*;

public class BNF {

	public  List<LexemBNF> lexemsBNF = new ArrayList<LexemBNF>();
	
	public BNF(List<String> inp){
		for (String cur: inp){
			LexemBNF l = new LexemBNF(cur);
			lexemsBNF.add(l);
		}
		
		
		
	}
}
