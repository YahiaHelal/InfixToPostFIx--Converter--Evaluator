import java.util.Stack;

public class ParensValidator {
	public static boolean ValidParens(String exp) {
		String Only_parens = exp.replaceAll("[^()]","");
		 Stack<Character> parens = new Stack<Character>();
		 boolean balanced = true;
		 int index = 0;
		 while(balanced && index < Only_parens.length()) {
			 char current = Only_parens.charAt(index);
			 switch(current) {
			 case '(': case'{':case'[':
				 parens.push(current);
				 index++;
				 break;
			 case ')': case'}':case']':
				 if(parens.isEmpty()) {
					 balanced = false;
					 return balanced;
				 }else {
					 char openDelimeter = parens.peek();
					 if(openDelimeter == '(' && current ==')' || openDelimeter == '{' && current =='}' || openDelimeter == '[' && current ==']') {
						 parens.pop();
						 index++;
					 }else {
						 balanced = false;
						 return balanced;
					 }
				 }
				 break;
			 }
			 
		 }
		 if(parens.isEmpty())
			 return true;
		 return false;
	 }
}
