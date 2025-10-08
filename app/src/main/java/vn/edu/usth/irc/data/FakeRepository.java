package vn.edu.usth.irc.data;

import java.util.*;
import vn.edu.usth.irc.model.Channel;
import vn.edu.usth.irc.model.Message;
import vn.edu.usth.irc.Adapter.MessageAdapter;
import vn.edu.usth.irc.model.Message;


public class FakeRepository {

    private static FakeRepository I;
    public static FakeRepository get(){ if(I==null) I=new FakeRepository(); return I; }

    private final List<Channel> channels = new ArrayList<>();
    private final Map<String, List<Message>> roomMessages = new HashMap<>();

    private FakeRepository(){
        channels.add(new Channel("chat", "#Chat"));
        channels.add(new Channel("java", "#Java"));
        channels.add(new Channel("ubuntu", "#Ubuntu"));
        channels.add(new Channel("cpp", "#C/C++"));
        channels.add(new Channel("anime", "#Anime"));

        for(Channel c: channels){
            ArrayList<Message> msgs = new ArrayList<>();
            msgs.add(Message.left("Welcome to " + c.name));
            msgs.add(Message.right("Hello everyone!"));
            roomMessages.put(c.id, msgs);
        }
    }

    public List<Channel> listChannels(){ return channels; }

    public List<Message> messagesOf(String channelId){
        return roomMessages.getOrDefault(channelId, new ArrayList<>());
    }

    public void addMessage(String channelId, Message m){
        roomMessages.get(channelId).add(m);
    }
}
