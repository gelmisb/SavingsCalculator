package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.savingscalculator.R;
import com.example.savingscalculator.databinding.FragmentSecondBinding;

import java.util.ArrayList;

public class a2_Expenses_LoanFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Spinner> spinners = new ArrayList<>();
        ArrayList<String> expensesEdits = new ArrayList<>();

        setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            getExpensesFromEdit(expensesEdits);
            cacheSelected(spinners, expensesEdits);

            NavHostFragment.findNavController(a2_Expenses_LoanFragment.this)
                    .navigate(R.id.action_SecondFragment_to_ThirdFragment);
        });
    }

    public void setSpinners(ArrayList<Spinner> spinners){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinners.add(binding.spinnerExpenseStep21);
        spinners.add(binding.spinnerExpenseStep22);
        spinners.add(binding.spinnerExpenseStep23);
        spinners.add(binding.spinnerExpenseStep24);
        spinners.add(binding.spinnerExpenseStep25);
        spinners.add(binding.spinnerExpenseStep26);
        spinners.add(binding.spinnerExpenseStep27);

        for (int i = 0; i < spinners.size(); i++) {
            spinners.get(i).setSelection(adapter.getPosition(getResources().getString(R.string.weekly)));
            spinners.get(i).setAdapter(adapter);
        }
    }

    public void getExpensesFromEdit(ArrayList<String> expensesEdits) {
        ViewGroup group = (ViewGroup) getActivity().findViewById(R.id.tableLayout);
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            ViewGroup another = (ViewGroup) group.getChildAt(i);
            for (int j = 0, countJ = another.getChildCount(); j < countJ; ++j) {
                View lew = another.getChildAt(j);
                if(lew instanceof LinearLayout) {
                    if(((EditText)((LinearLayout) lew).getChildAt(1)).getText().toString().equals("")) {
                        expensesEdits.add("0");
                    } else {
                        expensesEdits.add(((EditText)((LinearLayout) lew).getChildAt(1)).getText().toString());
                    }
                }
            }
        }
    }

    public void cacheSelected(ArrayList<Spinner> spinners, ArrayList<String> expensesEdits){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Resources res = getResources();

        ArrayList<String> selected = new ArrayList<>();

        for (int i = 0; i < spinners.size(); i++) {
            String temp = spinners.get(i).getSelectedItem().toString();
            selected.add(temp);
        }

        ArrayList<String> keyStrings = new ArrayList<>();
        keyStrings.add(res.getString(R.string.car_motorbike_loan));
        keyStrings.add(res.getString(R.string.education_tuition_loan));
        keyStrings.add(res.getString(R.string.home_loan));
        keyStrings.add(res.getString(R.string.personal_loan));
        keyStrings.add(res.getString(R.string.business_loan));
        keyStrings.add(res.getString(R.string.holiday_loan));
        keyStrings.add(res.getString(R.string.credit_card_loan));

        for (int i = 0; i < expensesEdits.size(); i++) {
            editor.putString(keyStrings.get(i), expensesEdits.get(i) + " " + selected.get(i));
        }
        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}