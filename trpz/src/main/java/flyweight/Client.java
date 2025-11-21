package flyweight;

import java.io.*;

public class Client {
    public static void main(String[] args) {
        String code = "//привіт коментар\n if (x == 10) {\n    System.out.println(\"Hello\");\n}\nfor (int i = 0; i < 5; i++) {\n}";
        String filename = "highlighted_output_client_only.html";

        PrintStream originalOut = System.out;

        try {
            PrintStream fileOut = new PrintStream(new FileOutputStream(filename));

            System.setOut(fileOut);

            System.out.println("<!DOCTYPE html>");
            System.out.println("<html lang=\"uk\">");
            System.out.println("<head><meta charset=\"UTF-8\"><title>Syntax Highlighted Code</title></head><body>");
            System.out.println("<pre style=\"font-family: monospace; background-color: white; padding: 10px;\">");

            FlyweightFactory factory = new FlyweightFactory();
            JavaSyntaxHighlighter highlighter = new JavaSyntaxHighlighter(factory);

            highlighter.highlight(code);

            System.out.println("</pre>");
            System.out.println("</body></html>");

            fileOut.close();

            System.setOut(originalOut);
            System.out.println("Код успішно записано у файл: " + filename);

        } catch (FileNotFoundException e) {
            System.setOut(originalOut);
            e.printStackTrace();
        }
    }
}