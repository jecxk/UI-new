package vn.edu.usth.irc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import vn.edu.usth.irc.databinding.FragmentChatBinding;

public class ChatFragment extends Fragment {

    private static final String ARG_TITLE = "arg_title";
    public static ChatFragment newInstance(String title) {
        ChatFragment f = new ChatFragment();
        Bundle b = new Bundle();
        b.putString(ARG_TITLE, title);
        f.setArguments(b);
        return f;
    }

    private FragmentChatBinding b;
    private MessageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentChatBinding.inflate(inflater, container, false);

        String title = getArguments() != null ? getArguments().getString(ARG_TITLE, "Chat") : "Chat";
        b.toolbar.setTitle(title);
        b.toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
        b.toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_info) {
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new ChatInfoFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            }
            return false;
        });

        b.rvMessages.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MessageAdapter(dummyMessages());
        b.rvMessages.setAdapter(adapter);

        b.btnStar.setOnClickListener(v -> {}); // TODO: toggle favorite
        b.btnUpload.setOnClickListener(v -> {}); // TODO: upload
        b.btnAttach.setOnClickListener(v -> {}); // TODO: attach

        b.etMessage.setOnEditorActionListener((tv, actionId, event) -> {
            String text = tv.getText().toString().trim();
            if (!text.isEmpty()) {
                adapter.addRightMessage(text);
                b.rvMessages.scrollToPosition(adapter.getItemCount() - 1);
                tv.setText("");
            }
            return true;
        });

        return b.getRoot();
    }

    private List<Message> dummyMessages() {
        List<Message> list = new ArrayList<>();
        list.add(Message.left("Is this your uni?"));
        list.add(Message.left("I think it's great!"));
        list.add(Message.system("10d"));
        list.add(Message.left("Yes! Thank you"));
        list.add(Message.right("Message 1"));
        list.add(Message.right("Message 2"));
        list.add(Message.right("I hate IRC client"));
        return list;
    }
}
