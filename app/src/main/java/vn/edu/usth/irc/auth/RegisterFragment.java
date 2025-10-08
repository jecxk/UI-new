package vn.edu.usth.irc.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import vn.edu.usth.irc.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding b;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentRegisterBinding.inflate(inflater, container, false);
        b.btnRegister.setOnClickListener(v -> {
            String u = b.etUser.getText().toString();
            String p = b.etPass.getText().toString();
            if (AuthRepository.get(requireContext()).register(u,p)) {
                Toast.makeText(getContext(),"Registered!",Toast.LENGTH_SHORT).show();
                ((NavTo) requireActivity()).toMain();
            }
        });
        b.tvToLogin.setOnClickListener(v -> ((NavTo) requireActivity()).toLogin());
        return b.getRoot();
    }
}
