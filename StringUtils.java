
public class StringUtils {

    public static boolean isNotAscii(char ch) {
      return ((int) ch <= 0 || (int) ch >= 127);
    }

    public static boolean isAsciiNumeric(char ch) {
      if (isNotAscii(ch)) {
        throw new IllegalArgumentException();
      }
      return ((int) ch >= 48 && (int) ch <= 57);
    }
  
    public static char[] concatStrings(char[][] input) {
      int len = input.length;
      if (len == 0) {
        return new char[0];
      }
      int totalLength = 0;
      for (int i = 0; i < len; i++) {
        totalLength += input[i].length; 
      }
      char[] result = new char[totalLength];
      int i = 0;
      int r = 0;
      while (i < len) {
        for (int j = 0; j < input[i].length; j++) {
          result[r] = input[i][j];
          r++;
        }
        i++;
      }
      return result;
    }
  
    public static int toInt(char[] input) {
      if (input.length == 0) {
        throw new IllegalArgumentException();
      }
      int num = 0;
      int i = 0;
      int sign = 1;
      if (input[0] == '-') {
        sign = -1;
        i++;
      }
      while (i < input.length) {
        if (isAsciiNumeric(input[i])) {
          num *= 10;
          num += (int) (input[i] - '0');
          i++;
        } else {
          throw new NumberFormatException();
        }
      }
      return num * sign;
    }
  }