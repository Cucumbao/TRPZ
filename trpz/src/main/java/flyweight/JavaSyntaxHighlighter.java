package flyweight;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaSyntaxHighlighter {
    private final FlyweightFactory factory;

    public JavaSyntaxHighlighter(FlyweightFactory factory) {
        this.factory = factory;
    }

    public void highlight(String code) {
        String regex = "\"([^\"]*)\"|//[^\n]*|/\\*.*?\\*/|\\w+|\\d+|==|<=|>=|!=|[{}();=<>+-/*]|\\s+";
        Pattern pattern = Pattern.compile(regex, Pattern.DOTALL);
        Matcher matcher = pattern.matcher(code);

        int position = 0;
        while (matcher.find()) {
            String token = matcher.group();
            String type = "DEFAULT";

            if (token.matches("if|for|while|switch|public|class|void|static")) type = "KEYWORD";
            else if (token.matches("\\d+")) type = "NUMBER";
            else if (token.startsWith("\"") && token.endsWith("\"")) type = "STRING";
            else if (token.startsWith("//")|| (token.startsWith("/*") && token.endsWith("*/"))) type = "COMMENT";


            Flyweight flyweight = factory.getFlyweight(type);
            ExtrinsicState state = new ExtrinsicState(token, position);
            flyweight.render(state);

            position += token.length();
        }
    }
}
