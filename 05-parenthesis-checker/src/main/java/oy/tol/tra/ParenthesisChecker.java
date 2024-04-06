package oy.tol.tra;
public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      int count = 0;

      for (char c : fromString.toCharArray()) {
         if (c == '(' || c == '{' || c == '[') {
            try {
               stack.push(c);
               count++;
            } catch (Exception e) {
               throw new ParenthesesException("Failed to push", ParenthesesException.STACK_FAILURE);
            }
         } else if (c == ')' || c == '}' || c == ']') {
            if (stack.isEmpty()) {
               throw new ParenthesesException("There are too many closing parentheses", ParenthesesException.TOO_MANY_CLOSING_PARENTHESES);
            }
            char top = stack.pop();
            if (!isMatching(top, c)) {
               throw new ParenthesesException("Wrong kind of parenthesis were in the text", ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
            }
            count++;
         }
      }

      if (!stack.isEmpty()) {
         throw new ParenthesesException("The string has more opening than closing parentheses.", ParenthesesException.TOO_FEW_CLOSING_PARENTHESES);
      }

      return count;
   }

   private static boolean isMatching(char opening, char closing) {
      return (opening == '(' && closing == ')') || (opening == '{' && closing == '}') || (opening == '[' && closing == ']');
   }
}

