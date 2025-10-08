package vn.edu.usth.irc.model;

public class Message {
    public static final int LEFT = 0;   // tin nhắn người khác
    public static final int RIGHT = 1;  // tin nhắn của mình
    public static final int SYSTEM = 2; // thông báo hệ thống

    private final String text;
    private final int type;

    public Message(String text, int type) {
        this.text = text;
        this.type = type;
    }

    // Factory methods
    public static Message left(String text)   { return new Message(text, LEFT); }
    public static Message right(String text)  { return new Message(text, RIGHT); }
    public static Message system(String text) { return new Message(text, SYSTEM); }

    public String getText() { return text; }
    public int getType()    { return type; }
}
