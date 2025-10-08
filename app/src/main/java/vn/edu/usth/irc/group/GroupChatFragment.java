package vn.edu.usth.irc.group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import vn.edu.usth.irc.Adapter.MessageAdapter;
import vn.edu.usth.irc.R;
import vn.edu.usth.irc.databinding.FragmentChatBinding;
import vn.edu.usth.irc.model.Message;
import vn.edu.usth.irc.data.FakeRepository;

/**
 * Group chat screen cho một channel cụ thể (#Java, #Chat, ...).
 * Dùng lại layout fragment_chat.xml và MessageAdapter hiện có.
 */
public class GroupChatFragment extends Fragment {

    public static final String ARG_CHANNEL_ID   = "arg_channel_id";
    public static final String ARG_CHANNEL_NAME = "arg_channel_name";

    public static GroupChatFragment newInstance(@NonNull String channelId, @NonNull String channelName) {
        GroupChatFragment f = new GroupChatFragment();
        Bundle b = new Bundle();
        b.putString(ARG_CHANNEL_ID, channelId);
        b.putString(ARG_CHANNEL_NAME, channelName);
        f.setArguments(b);
        return f;
    }

    private FragmentChatBinding b;
    private MessageAdapter adapter;
    private String channelId;
    private String channelName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        b = FragmentChatBinding.inflate(inflater, container, false);

        // Lấy tham số channel
        Bundle args = getArguments();
        channelId = args != null ? args.getString(ARG_CHANNEL_ID, "chat") : "chat";
        channelName = args != null ? args.getString(ARG_CHANNEL_NAME, "Chat") : "Chat";

        // Toolbar
        b.toolbar.setTitle("#" + channelName);
        b.toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
        b.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_info) {
                // TODO: mở thông tin channel nếu bạn muốn
                return true;
            }
            return false;
        });

        // RecyclerView
        b.rvMessages.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Message> data = FakeRepository.get().messagesOf(channelId);
        adapter = new MessageAdapter(data);
        b.rvMessages.setAdapter(adapter);

        // Gửi bằng IME action "Send"
        b.etMessage.setOnEditorActionListener((tv, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String t = tv.getText().toString().trim();
                if (!t.isEmpty()) {
                    Message m = Message.right(t);
                    FakeRepository.get().addMessage(channelId, m);
                    adapter.addRightMessage(t);
                    b.rvMessages.scrollToPosition(adapter.getItemCount() - 1);
                    tv.setText("");
                }
                return true;
            }
            return false;
        });

        // Gửi bằng nút Send
        b.btnSend.setOnClickListener(v -> {
            String t = b.etMessage.getText().toString().trim();
            if (!t.isEmpty()) {
                Message m = Message.right(t);
                FakeRepository.get().addMessage(channelId, m);
                adapter.addRightMessage(t);
                b.rvMessages.scrollToPosition(adapter.getItemCount() - 1);
                b.etMessage.setText("");
            }
        });

        b.etMessage.requestFocus();
        return b.getRoot();
    }
}
