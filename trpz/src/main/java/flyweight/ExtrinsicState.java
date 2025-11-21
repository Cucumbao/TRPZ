package flyweight;

public class ExtrinsicState {
    private final String text;
    private final int position;

    public ExtrinsicState(String text, int position) {
        this.text = text;
        this.position = position;
    }

    public String getText() { return text; }
    public int getPosition() { return position; }
}
