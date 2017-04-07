import java.util.*;

public class Lexem{
	private String cout_level(){
		String cur = "";
		for(int i = 0; i< level;i++)
			cur = cur+" ";
		return cur;
	}
	
	public Lexem(BNF bnf, char[] cod, String name, int pos_start,int level_) {
		level=level_;
		code = cod;
		Bnf = bnf;
		Name = name;
		System.out.println(cout_level()+Name);
		start_pos=pos_start;
		pos = start_pos;
		isString = false;
		if (name.equals("<;>"));
			Name = name;
		for(LexemBNF cur:bnf.lexemsBNF){
			if (cur.Name.equals(name)){
				bnf_l = cur;
				break;
			}
		}
		
	}
	
	public Lexem( char[] cod, String name, int pos_start,int level_) {
		level=level_;
		code = cod;
		Bnf = null;
		Name = name;
		start_pos=pos_start;
		pos = start_pos;
		isString = true;
		//System.out.println(cout_level() + Name);
		
	}
	
	public boolean isLexem(){
		if (isString){
			if (Name.equals(""))
				Name =""+'"';
			if (Name.equals("\n"))
				Name =""+'\n';
			if (Name.equals("\r"))
				Name ='\r'+"";
			if (Name.equals("\t"))
				Name ='\t'+"";
			if (Name.equals("!!"))
				Name ="||";
			for(int i = pos; i<code.length && i<Name.length()+pos;i++)
			{
				if (code[i]!=Name.toCharArray()[i-pos]) 
				{
					//System.out.println(cout_level()+"-" + Name); 
					return false; }
			}
			System.out.println(cout_level()+"+"+'"' + Name+'"');
			pos = Name.length()+pos;
			return true;
		}
		
		
		for (int cur =0; cur<bnf_l.value.length;cur++ ){
			switch (bnf_l.value[cur]){
			case'<':
				String q = "";
				for (int j = cur;j<=bnf_l.value.length;j++){
					q=q+bnf_l.value[j];
					if (bnf_l.value[j] == '>'){
						Lexem lex = new Lexem(Bnf,code,q,pos,level+1);
						
						if(lex.isLexem()){
						pos = lex.pos;
						cur = j;
						children.add(lex);
						break;
						}
						else { System.out.println(cout_level()+"-" + Name); return false;}
					}
				}
				
				break;
			case'"':
			    q = "";
				for (int j = cur+1;j<bnf_l.value.length;j++){
					if (bnf_l.value[j] == '"'){
						Lexem lex = new Lexem(code,q,pos,level+1);
						if(lex.isLexem()){
						pos = lex.pos;
						cur = j;
						children.add(lex);
						break;
						}
						else  {System.out.println(cout_level()+"-" + Name); return false;}
					}
					q=q+bnf_l.value[j];
				}
				break;
			case'[':
				int k = 0;
				for (int j = cur+1;j<bnf_l.value.length;j++){
					if(bnf_l.value[j] == '[' && j >0 && j <bnf_l.value.length)
						if (bnf_l.value[j-1]!= '"')  
							if(bnf_l.value[j+1]!= '"')
								k++;
					if (bnf_l.value[j] == ']'&& k==0){
						boolean b =flag;
						flag = false;
						int p = pos;
						ArrayList<Lexem> lex = kvad(cur+1,j-1);
						cur = j;
						if (flag){
							children.addAll(lex);
							if (!lex.isEmpty())
							pos = lex.get(lex.size()-1).pos;
							break;
							}
						else {flag = b; pos = p; break;}
						}
					else
					if(bnf_l.value[j] == ']'&& j >0 && j <bnf_l.value.length)
						if (bnf_l.value[j-1]!= '"')  
							if(bnf_l.value[j+1]!= '"') k--;
					}
				break;
			case'{':
				k = 0;
				for (int j = cur+1;j<bnf_l.value.length;j++){
					if(bnf_l.value[j] == '{' && j >0 && j <bnf_l.value.length)
						if (bnf_l.value[j-1]!= '"')  
							if(bnf_l.value[j+1]!= '"')
								k++;
					if (bnf_l.value[j] == '}'&& k==0){
						boolean b =flag;
						flag = false;
						int p = pos;
						ArrayList<Lexem> lex = fig(cur+1,j-1);
						cur = j;
						if (flag){
							pos = lex.get(lex.size()-1).pos;
							children.addAll(lex);
							break;
							}
						else {flag = b; pos = p; }
						}
					else
					if(bnf_l.value[j] == '}'&& j >0 && j <bnf_l.value.length)
						if (bnf_l.value[j-1]!= '"')  
							if(bnf_l.value[j+1]!= '"') k--;
					}
				break;
			case'|':
				if (bnf_l.value[cur+1] == '|')
				for (int j = cur+2;j<bnf_l.value.length;j++){
					
					if (bnf_l.value[j] == '|' && bnf_l.value[j+1]=='|' ){
						boolean b =flag;
						flag = false;
						ArrayList<Lexem> lex = or(cur+2,j-1);
						cur = j+1;
						if (flag){
							children.addAll(lex);
							pos = lex.get(lex.size()-1).pos;
							break;
						}
						else { System.out.println(cout_level()+"-" + Name);
						 return false;}
					}
				}
				break;
		}
			
	}
		
	System.out.println(cout_level()+"+" + Name);
	return true;
		
}
	
	
	private ArrayList<Lexem> fig(int i, int ii){
		ArrayList<Lexem> list = new ArrayList<Lexem>();;
		
		int posfig= pos;
		int pos_cur = pos;
		while (true){
			ArrayList<Lexem> list_for_iteration= new ArrayList<Lexem>();
			for (int cur =i; cur<=ii;cur++ ){
				switch (bnf_l.value[cur]){
				case'<':
					String q = "";
					for (int j = cur;j<=ii;j++){
						q=q+bnf_l.value[j];
						if (bnf_l.value[j] == '>'){
							Lexem lex = new Lexem(Bnf,code,q,pos_cur, level+1);
							
							if(lex.isLexem()){
							pos_cur = lex.pos;
							
							list_for_iteration.add(lex);
							if (j != ii) cur = j +1;
							}
							else { pos = posfig; return list;}
						}
					}
					
					break;
				case'"':
				    q = "";
					for (int j = cur+1;j<=ii;j++){
						
						if (bnf_l.value[j] == '"'){
							Lexem lex = new Lexem(code,q,pos_cur,level+1);
							if(lex.isLexem()){
							pos_cur = lex.pos;
							
							list_for_iteration.add(lex);
							if (j != ii) continue;
							}
							else { pos = posfig; return list;}
						}
						q=q+bnf_l.value[j];
					}
					break;
				case'[':
					int k = 0;
					for (int j = i+1;j<=ii;j++){
						if(bnf_l.value[j] == '[' && j >0 && j <ii)
							if (bnf_l.value[j-1]!= '"')  
								if(bnf_l.value[j+1]!= '"')
									k++;
						if (bnf_l.value[j] == ']'&& k==0){
							boolean b =flag;
							flag = false;
							int p = pos;
							ArrayList<Lexem> lex = kvad(cur+1,j-1);
							pos_cur = pos;
							pos = p;
							if (flag){
								list_for_iteration.addAll(lex);
								pos = lex.get(lex.size()-1).pos;
								flag = b;}
							if (j != ii)cur = j+1;
							}
						else
						if(bnf_l.value[j] == ']'&& j >0 && j <ii)
							if (bnf_l.value[j-1]!= '"')  
								if(bnf_l.value[j+1]!= '"') k--;
						}
					break;
				case'{':
					k = 0;
					for (int j = cur+1;j<=ii;j++){
						if(bnf_l.value[j] == '}' && j <0 && j >ii)
							if (bnf_l.value[j-1]!= '"')  
								if(bnf_l.value[j+1]!= '"')
									k++;
						if (bnf_l.value[j] == '}' && k ==0){
							boolean b =flag;
							flag = false;
							int p = pos;
							ArrayList<Lexem> lex = fig(cur+1,j-1);
							if (j != ii)cur = j+1;
							pos_cur = pos;
							pos = p;
							if (flag){
								list_for_iteration.addAll(lex);
								pos = lex.get(lex.size()-1).pos;
								flag = b;}
							}
						else
						if(bnf_l.value[j] == '}')
							if (bnf_l.value[j-1]!= '"')  
								if(bnf_l.value[j+1]!= '"') k--;
						}
					break;
					
				case'|':
					if (bnf_l.value[cur+1] == '|')
					for (int j = cur+2;j<ii;j++){
						
						if (bnf_l.value[j] == '|' && bnf_l.value[j+1]=='|' ){
							boolean b =flag;
							flag = false;
							int p = pos;
							ArrayList<Lexem> lex = or(cur+2,j-1);
							if (j < ii-1)cur = j+2;
							pos_cur = pos;
							pos = p;
							if (flag){
								list_for_iteration.addAll(lex);
								pos = lex.get(lex.size()-1).pos;
								flag = b;
								cur = j +2;
								continue;
							}
							 else { flag= b; return list;}
							}
						}
					
					break;
			}
				
			}
			posfig = pos_cur;
			list.addAll(list_for_iteration);
			flag = true;
		}
	}
	

	private ArrayList<Lexem> kvad(int i, int ii){while (true){
		int posfig= pos;
		int pos_cur = pos;
		ArrayList<Lexem> list_for_iteration= new ArrayList<Lexem>();;
		for (int cur =i; cur<=ii;cur++ ){
			switch (bnf_l.value[cur]){
			case'<':
				String q = "";
				for (int j = cur;j<=ii;j++){
					q=q+bnf_l.value[j];
					if (bnf_l.value[j] == '>'){
						Lexem lex = new Lexem(Bnf,code,q,pos_cur,level+1);
						
						if(lex.isLexem()){
						pos_cur = lex.pos;
						
						list_for_iteration.add(lex);
						if (j != ii) {cur = j ; break;}
						}
						else { pos = posfig; return list_for_iteration;}
					}
				}
				
				break;
			case'"':
			    q = "";
				for (int j = cur+1;j<=ii;j++){
					
					if (bnf_l.value[j] == '"'){
						Lexem lex = new Lexem(code,q,pos_cur, level+1);
						if(lex.isLexem()){
						pos_cur = lex.pos;
						
						list_for_iteration.add(lex);
						if (j != ii){cur = j ; break;}
						}
						else { pos = posfig; return list_for_iteration;}
					}
					q=q+bnf_l.value[j];
				}
				break;
			case'[':
				int k = 0;
				for (int j = i+1;j<=ii;j++){
					if(bnf_l.value[j] == '[' && j >0 && j <ii)
						if (bnf_l.value[j-1]!= '"')  
							if(bnf_l.value[j+1]!= '"')
								k++;
					if (bnf_l.value[j] == ']'&& k==0){
						boolean b =flag;
						flag = false;
						int p = pos;
						ArrayList<Lexem> lex = kvad(cur+1,j-1);
						pos_cur = pos;
						pos = p;
						if (flag){
							list_for_iteration.addAll(lex);
							flag = b;}
						if (j != ii)cur = j+1;
						}
					else
					if(bnf_l.value[j] == ']'&& j >0 && j <ii)
						if (bnf_l.value[j-1]!= '"')  
							if(bnf_l.value[j+1]!= '"') k--;
					}
				break;
			case'{':
				k = 0;
				for (int j = cur+1;j<=ii;j++){
					if(bnf_l.value[j] == '}' && j <0 && j >ii)
						if (bnf_l.value[j-1]!= '"')  
							if(bnf_l.value[j+1]!= '"')
								k++;
					if (bnf_l.value[j] == '}' && k ==0){
						boolean b =flag;
						flag = false;
						int p = pos;
						ArrayList<Lexem> lex = fig(cur+1,j-1);
						if (j != ii)cur = j+1;
						pos_cur = pos;
						pos = p;
						if (flag){
							list_for_iteration.addAll(lex);
							flag = b;}
						}
					else
					if(bnf_l.value[j] == '}')
						if (bnf_l.value[j-1]!= '"')  
							if(bnf_l.value[j+1]!= '"') k--;
					}
				break;
				
			case'|':
				if (bnf_l.value[cur+1] == '|')
				for (int j = cur+2;j<=ii;j++){
					
					if (bnf_l.value[j] == '|' && bnf_l.value[j+1]=='|' ){
						boolean b =flag;
						flag = false;
						int p = pos;
						ArrayList<Lexem> lex = or(cur+2,j-1);
						if (j < ii-1)cur = j+2;
						pos_cur = pos;
						pos = p;
						if (flag){
							list_for_iteration.addAll(lex);
							flag = b;}
						 else { flag= b; return list_for_iteration;}
						}
					}
				
				break;
		}
			
		}
		posfig = pos_cur;
		flag = true;
		return list_for_iteration;
	}
	}
	
 	
	//в блоці або така і лише така форма ||<>..<>|...||
	private ArrayList<Lexem> or(int i , int ii){
		int p= i;
		int pos_cur = pos;
		boolean next= false;
		boolean open_s = false;
		boolean open_f = false;
		String s = "";
		ArrayList<Lexem> list_for_iteration= new ArrayList<Lexem>();;
		for (int cur =i; cur<=ii;cur++ ){
			
			if(next){
				if ( bnf_l.value[cur] == '"')
					open_s = !open_s;
				if (bnf_l.value[cur] == '|' && !open_s)
					next =false;
			}
			else{
				if (open_f || open_s)
					s= s+bnf_l.value[cur];
				else{
						if (bnf_l.value[cur] == '<')
						{
							open_f=true;
							s = "<";
						}
						if (bnf_l.value[cur] == '"')
						{
							open_s=true;
							s = ""+bnf_l.value[cur];
						}
				}
				if (open_f && bnf_l.value[cur] == '>'){
					Lexem lex = new Lexem(Bnf,code,s,pos_cur, level+1);
					if(lex.isLexem()){
					pos_cur = lex.pos;
					list_for_iteration.add(lex);
					open_f = false;
					continue;
					}
					else {list_for_iteration = new ArrayList<Lexem>();; next = true; open_f = false;}
				}
				if (open_s && bnf_l.value[cur] == '"' && s.length()> 1){
					Lexem lex = new Lexem(code,s.substring(1, s.length() -1),pos_cur, level+1);
					if(lex.isLexem()){
					pos_cur = lex.pos;
					list_for_iteration.add(lex);
					open_s= false;
					continue;
					} else {list_for_iteration.isEmpty(); next = true; open_s = false;}
				}
				if (bnf_l.value[cur] == '|' && !open_f && !open_s)
					if (list_for_iteration.isEmpty())
						flag = false;
					else {flag = true;
					return list_for_iteration;}
				
			}
		}
		if (list_for_iteration.isEmpty()){flag = false; return null;}
		else {flag =true; pos =pos_cur;}
		return list_for_iteration;
	}
	
	public BNF Bnf;
	public boolean isString = false;
	public boolean flag;
	public char[] code;
	public LexemBNF bnf_l; 
	public ArrayList<Lexem> children = new ArrayList<Lexem>();
	public int start_pos;
	public int pos;
	public String Name;
	public int level;
	
}
