import java.util.Stack;

public class Converter {
	
	
	public static String toPostfix(String exp) {
		Stack<Character> operators = new Stack<>();
		int currentCharIndex = 0;
		String posted = "";
		while(currentCharIndex < exp.length()) {
			
			char NextChar = exp.charAt(currentCharIndex);
			switch(NextChar) {
			case '^':
				operators.push(NextChar);
				currentCharIndex++;
				break;
			case '+':case '-':case '*':case '/':
				while((!operators.isEmpty()) && PrecedenceLevel(NextChar)<=PrecedenceLevel(operators.peek())) {
					posted+= operators.peek();
					operators.pop();
				}
				operators.push(NextChar);
				currentCharIndex++;
				break;
			case '(':
				operators.push(NextChar);
				currentCharIndex++;
				break;
			case ')':
				while(operators.peek() != '(') {
					posted+= operators.peek();
					operators.pop();
				}
				operators.pop();
				currentCharIndex++;
				break;
			default:
					posted+= NextChar;
					currentCharIndex++;
					
					break;
			}
		}
		while(!operators.isEmpty()) {
			posted += operators.pop();
		}
		return posted;
		
	}
	public static int PrecedenceLevel(char op) {
		switch(op) {
		case '+':case'-':
			return 0;
		case '*':case'/':
			return 1;
		case '^':
			return 2;
		case '(':
			return -1;
		default:
			throw new IllegalArgumentException();
		}
	}
}
