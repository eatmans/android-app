package net.ivpn.client.ui.connect;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.ivpn.client.R;
import net.ivpn.client.databinding.BottomSheetBinding;
import net.ivpn.client.ui.login.LoginActivity;

public class CreateSessionFragment extends BottomSheetDialogFragment {

    private CreateSessionNavigator navigator;
    private BottomSheetBinding binding;

    public CreateSessionFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ConnectActivity || context instanceof LoginActivity) {
            navigator = (CreateSessionNavigator) context;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.bottom_sheet, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commit();
        } catch (IllegalStateException e) {
            Log.d("CreateSessionFragment", "show exception: " + e);
        }
    }

    private void init() {
        binding.tryAgain.setOnClickListener(view -> {
            if (navigator != null) {
                navigator.tryAgain();
            }
        });
        binding.close.setOnClickListener(view -> {
            if (navigator != null) {
                navigator.cancel();
            }
        });
        binding.forceLogout.setOnClickListener(view -> {
            if (navigator != null) {
                navigator.onForceLogout();
            }
        });
    }
}