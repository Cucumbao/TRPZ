package flyweight;

public class ConcreteFlyweight implements Flyweight {
    private final String color;
    private final boolean bold;
    private final boolean italic;

    public ConcreteFlyweight(String color, boolean bold, boolean italic) {
        this.color = color;
        this.bold = bold;
        this.italic = italic;
    }

    @Override
    public void render(ExtrinsicState state) {
        String token = state.getText();
        int position = state.getPosition();
        System.out.print("<span style=\"color:" + color + ";"
                + (bold ? "font-weight:bold;" : "")
                + (italic ? "font-style:italic;" : "")
                + "\" data-position=\"" + position + "\">" + escapeHtml(token) + "</span>");
    }

    private String escapeHtml(String str) {
        return str.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
