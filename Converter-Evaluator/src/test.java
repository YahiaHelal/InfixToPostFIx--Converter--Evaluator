import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class test {
	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(System.in);
		System.out.println("1: Convert infix form to postfix\n\n2: Evaluate given postfix form");
		String userChoice = input.next();
		if (!(userChoice.matches("[0-9]+"))) {
			System.err.println("No choice called \"" + userChoice + "\"");
		} else if (Integer.parseInt(userChoice) > 2 || Integer.parseInt(userChoice) < 1) {
			System.err.println("Input out of range");
		} else if (Integer.parseInt(userChoice) == 1) {
			System.out.println("Enter the infix form you wish to convert(WITHOUT SPACES): \n");
			String Infix = input.next();
			if (isInfix(Infix)) {
				String FormattedPostFix = FormatPostFix(Infix);
				System.out.println("The posrfix form for the infix \"" + Infix + "\" is\n---> " + FormattedPostFix);
			} else {
				System.err.println("The given expression does not seem to be correct infix form");
			}
		} else if (Integer.parseInt(userChoice) == 2) {
			System.out.println("Enter the postfix form you wish to evaluate(WITH SPACES bETWEEN DIGITS) : \n");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String GivenPostfix = reader.readLine();
			if (IsOperator((GivenPostfix.charAt(GivenPostfix.length() - 1))) && (isDigit(GivenPostfix.charAt(0)))) {
				double result = Evaluator.Evaluate(GivenPostfix);
				System.out.println("The result of the given postfix \"" + GivenPostfix + "\" is \n--->" + result);
			} else {
				System.err.println("the given postfix expression does not seem to be valid to evaluate");
			}

		}
	}

	/**
	 * @param char ch check if the given character is an operator
	 */
	public static boolean IsOperator(char ch) {
		switch (ch) {
		case '+':
		case '-':
		case '/':
		case '*':
		case '^':
		case '%':
			return true;
		}
		return false;
	}

	/**
	 * @param String infix.
	 * 				 converting the infix form to postFix the numeric values
	 *               above 1 digit maybe will concatenate together forming condensed
	 *               form FormatPostFix will format the spaces between the whole
	 *               digit and operators for the given infix
	 */
	public static String FormatPostFix(String infix) {
		//String PostFix = Converter.toPostfix(infix);
		String FormattedPostFix = "";
		String WholeValue = "";
		for (char ch : infix.toCharArray()) {
			if (IsOperator(ch)) {
				FormattedPostFix += String.format("%s$", WholeValue);
				FormattedPostFix += ch;
				WholeValue = "";
			} else {
				WholeValue += ch;
			}
		}
		FormattedPostFix += WholeValue;
		FormattedPostFix = FormattedPostFix.replace("$", " ");
		FormattedPostFix = Converter.toPostfix(FormattedPostFix);
		for (char ch : FormattedPostFix.toCharArray()) {
			if (IsOperator(ch)) {
				String temp = Character.toString(ch);
				temp = " " + ch + " ";
				FormattedPostFix = FormattedPostFix.replace(ch + "", temp);
			}
		}
		FormattedPostFix = FormattedPostFix.trim().replaceAll(" +", " ");
		return FormattedPostFix;
	}

	public static boolean isInfix(String exp) {

		int First = 0;
		int Last = exp.length() - 1;
		while(exp.charAt(First) == '(' || exp.charAt(First) == ' ') {
			First++;
		}
		while(exp.charAt(Last) == ')' || exp.charAt(Last) == ' ') {
			Last--;
		}
		return ((isDigit(exp.charAt(First)) || exp.charAt(First) == '-') && isDigit(exp.charAt(Last))
				|| (isAlphabetic(exp.charAt(First)) || exp.charAt(First) == '-') && isAlphabetic(exp.charAt(Last))
				|| (isDigit(exp.charAt(First)) || exp.charAt(First) == '-') && isAlphabetic(exp.charAt(Last))
				|| (isAlphabetic(exp.charAt(First)) || exp.charAt(First) == '-') && isDigit(exp.charAt(Last)))&& ParensValidator.ValidParens(exp);
	}

	public static boolean isDigit(char number) {
		String DigitPattern = "[0-9]+";
		return Character.toString(number).matches(DigitPattern);
	}

	public static boolean isAlphabetic(char alphabet) {
		String AlphabetPattern = "[a-zA-Z]+";
		return Character.toString(alphabet).matches(AlphabetPattern);
	}
}
