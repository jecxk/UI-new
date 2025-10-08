package vn.edu.usth.irc;

public class Message {
    public static final int LEFT=0, RIGHT=1, SYSTEM=2;
    public int type; public String text;
    public Message(int t, String tx){ type=t; text=tx; }
    public static Message left(String t){ return new Message(LEFT,t); }
    public static Message right(String t){ return new Message(RIGHT,t); }
    public static Message system(String t){ return new Message(SYSTEM,t); }
}
