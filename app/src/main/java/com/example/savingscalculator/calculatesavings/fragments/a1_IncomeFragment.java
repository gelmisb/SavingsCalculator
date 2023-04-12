package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.Income;
import com.example.savingscalculator.databinding.FragmentFirstBinding;

//import com.example.savingscalculator.calculatesavings.databinding.FragmentFirstBinding;

public class a1_IncomeFragment extends Fragment {

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

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.timeframe.setSelection(adapter.getPosition("Weekly"));
        binding.timeframe2.setSelection(adapter.getPosition("Weekly"));
        binding.timeframe3.setSelection(adapter.getPosition("Weekly"));
        binding.timeframe4.setSelection(adapter.getPosition("Weekly"));
        binding.timeframe5.setSelection(adapter.getPosition("Weekly"));

        binding.timeframe.setAdapter(adapter);
        binding.timeframe2.setAdapter(adapter);
        binding.timeframe3.setAdapter(adapter);
        binding.timeframe4.setAdapter(adapter);
        binding.timeframe5.setAdapter(adapter);


        binding.nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                ---------------- Database config for later use ---------------------------
//                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
//                        AppDatabase.class, "database-name").build();
//
//                UserDao userDao = db.userDao();
//                List<User> users = userDao.getAll();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putString("wages", (binding.editWagesNumber.getText().toString().equals("")) ? "0" : binding.editWagesNumber.getText().toString());

                editor.putString("temp1", "value");
                editor.putInt("temp2", 1);
//                editor.putString("partnerWages", (binding.editPWages.getText().toString().equals("")) ? "0" : binding.editPWages.getText().toString());
//                editor.putString("cbp", (binding.editCBP.getText().toString().equals("")) ? "0" : binding.editCBP.getText().toString());
//                editor.putString("maintenance", (binding.editMaintenance.getText().toString().equals("")) ? "0" : binding.editMaintenance.getText().toString());
//                editor.putString("otherIncome", (binding.editotherIncome.getText().toString().equals("")) ? "0" : binding.editotherIncome.getText().toString());
                editor.apply();

//
//                ((Income) getActivity().getApplicationContext()).setWages(
//                        (binding.editWagesNumber.getText().toString().equals("")) ? "0" : binding.editWagesNumber.getText().toString());
//                ((Income) getActivity().getApplicationContext()).setpWages(
//                        (binding.editPWages.getText().toString().equals("")) ? "0" : binding.editPWages.getText().toString());
//                ((Income) getActivity().getApplicationContext()).setCbp(
//                        (binding.editCBP.getText().toString().equals("")) ? "0" : binding.editCBP.getText().toString());
//                ((Income) getActivity().getApplicationContext()).setMaintenance(
//                        (binding.editMaintenance.getText().toString().equals("")) ? "0" : binding.editMaintenance.getText().toString());
//                ((Income) getActivity().getApplicationContext()).setOtherIncome(
//                        (binding.editotherIncome.getText().toString().equals("")) ? "0" : binding.editotherIncome.getText().toString());
//                ((Income) getActivity().getApplicationContext()).setTotalIncome();

                NavHostFragment.findNavController(a1_IncomeFragment.this)
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