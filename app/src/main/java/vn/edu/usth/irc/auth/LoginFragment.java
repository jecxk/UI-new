package vn.edu.usth.irc.auth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import vn.edu.usth.irc.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding b;

    @Nullable @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        b = FragmentLoginBinding.inflate(inflater, container, false);

        b.btnLogin.setOnClickListener(v -> {
            String user = b.etUser.getText().toString();
            String pass = b.etPass.getText().toString();
            if (AuthRepository.get(requireContext()).login(user, pass)) {
                ((NavTo) requireActivity()).toMain();
            } else {
                Toast.makeText(getContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
            }
        });

        b.tvToRegister.setOnClickListener(v -> ((NavTo) requireActivity()).toRegister());
        return b.getRoot();
    }
}
