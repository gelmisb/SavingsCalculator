package com.example.savingscalculator.calculatesavings;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.room.Room;

import com.example.savingscalculator.R;
import com.example.savingscalculator.databinding.FragmentFirstBinding;
import com.example.savingscalculator.user.AppDatabase;
import com.example.savingscalculator.user.User;
import com.example.savingscalculator.user.UserDao;

import java.util.List;

//import com.example.savingscalculator.calculatesavings.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ---------------- Database config for later use ---------------------------
//                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
//                        AppDatabase.class, "database-name").build();
//
//                UserDao userDao = db.userDao();
//                List<User> users = userDao.getAll();



                ((Income) getActivity().getApplicationContext()).setWages((binding.editWagesNumber.getText().toString().equals("")) ? "0" : binding.editWagesNumber.getText().toString());
                ((Income) getActivity().getApplicationContext()).setpWages((binding.editPWages.getText().toString().equals("")) ? "0" : binding.editPWages.getText().toString());
                ((Income) getActivity().getApplicationContext()).setCbp((binding.editCBP.getText().toString().equals("")) ? "0" : binding.editCBP.getText().toString());
                ((Income) getActivity().getApplicationContext()).setMaintenance((binding.editMaintenance.getText().toString().equals("")) ? "0" : binding.editMaintenance.getText().toString());
                ((Income) getActivity().getApplicationContext()).setOtherIncome((binding.editotherIncome.getText().toString().equals("")) ? "0" : binding.editotherIncome.getText().toString());
                ((Income) getActivity().getApplicationContext()).setTotalIncome();

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}