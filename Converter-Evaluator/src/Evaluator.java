import java.util.Stack;

public class Evaluator {
	
	public static double Evaluate(String exp) {
		int index = 0;
		Stack<Double> evaluator = new Stack<>();
		String wholeValue = "";
		while(index < exp.length()) {
			char current = exp.charAt(index);
			if(Character.isDigit(current)) {
				wholeValue += current;
			}else if(current == ' ') {
				if(wholeValue.length()>0) {
					evaluator.push(Double.parseDouble(wholeValue));
					wholeValue = "";
				}
			}else if(test.IsOperator(current)) {
				double result = Calculate(evaluator.pop(),evaluator.pop(),current);
				evaluator.push(result);
			}
			index++;
		}
		return evaluator.peek();
	}
	
	public static double Calculate(double SecondOperand,double FirstOperand,char operator) {
		
		switch(operator) {
		case'+':
			return FirstOperand + SecondOperand;
		case'-':
			return FirstOperand - SecondOperand;
		case '/':
			return FirstOperand / SecondOperand;
		case '*':
			return FirstOperand * SecondOperand;
		case '^':
			return Math.pow(FirstOperand, SecondOperand);
		case '%':
			return FirstOperand % SecondOperand;
		default:
			throw new IllegalArgumentException("undefined operator --> "+operator);
		}
	}
}
