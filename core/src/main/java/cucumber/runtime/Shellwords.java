package cucumber.runtime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// http://stackoverflow.com/questions/6030400/regular-expression-troubles-escaped-quotes
// https://github.com/marayl/doogal/blob/f413e12640ef655256d273aa4e5f6538ed5428f7/src/main/java/org/doogal/core/Shellwords.java
// http://stackoverflow.com/questions/197233/how-to-parse-a-command-line-with-regular-expressions
public class Shellwords {
//    private static final Pattern PATTERN = Pattern.compile("(\"[^\"]*\"|[^\"]+)(\\s+|$)");
    private static final Pattern PATTERN = Pattern.compile("\\s*(?:([^\\s\\\\\\'\\\"]+)|'((?:[^\\'\\\\]|\\\\.)*)'|\"((?:[^\\\"\\\\]|\\\\.)*)\"|(\\\\.?)|(\\S))(\\s|$)?");

    public static String[] split(String string) {
        Matcher matcher = PATTERN.matcher(string);
        matcher.find();
        System.out.println(matcher.matches());
        System.out.println(matcher.groupCount());
        for(int i = 0; i < matcher.groupCount(); i++) {
            System.out.println("matcher = " + matcher.group(i));
        }
        return PATTERN.split(string);
    }
}
