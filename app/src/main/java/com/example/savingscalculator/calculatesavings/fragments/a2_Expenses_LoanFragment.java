package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<Spinner> spinners = new ArrayList<>();
        setSpinners(spinners, adapter);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cacheSelected();

                NavHostFragment.findNavController(a2_Expenses_LoanFragment.this)
                        .navigate(R.id.action_SecondFragment_to_ThirdFragment);
            }
        });
    }

    public void setSpinners (ArrayList<Spinner> spinners, ArrayAdapter<CharSequence> adapter){
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

    public void cacheSelected(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Resources res = getResources();

        String carSpin = binding.spinnerExpenseStep21.getSelectedItem().toString();
        String educationSpin = binding.spinnerExpenseStep22.getSelectedItem().toString();
        String mortgageSpin = binding.spinnerExpenseStep23.getSelectedItem().toString();
        String personalSpin = binding.spinnerExpenseStep24.getSelectedItem().toString();
        String businessSpin = binding.spinnerExpenseStep25.getSelectedItem().toString();
        String holidaySpin = binding.spinnerExpenseStep26.getSelectedItem().toString();
        String creditSpin = binding.spinnerExpenseStep27.getSelectedItem().toString();

        String carStr = res.getString(R.string.car_motorbike_loan);
        String eduStr = res.getString(R.string.education_tuition_loan);
        String morStr = res.getString(R.string.home_loan);
        String perStr = res.getString(R.string.personal_loan);
        String busStr = res.getString(R.string.business_loan);
        String holStr = res.getString(R.string.holiday_loan);
        String creStr = res.getString(R.string.credit_card_loan);

        editor.putString(carStr, ((binding.carLoanEdit.getText().toString().equals(""))
                ? "0" : binding.carLoanEdit.getText().toString()) + " " + carSpin);
        editor.putString(eduStr, ((binding.educationEdit.getText().toString().equals(""))
                ? "0" : binding.educationEdit.getText().toString()) + " " + educationSpin);
        editor.putString(morStr, ((binding.homeLoanEdit.getText().toString().equals(""))
                ? "0" : binding.homeLoanEdit.getText().toString()) + " " + mortgageSpin);
        editor.putString(perStr, ((binding.personalLoanEdit.getText().toString().equals(""))
                ? "0" : binding.personalLoanEdit.getText().toString()) + " " + personalSpin);
        editor.putString(busStr, ((binding.businessLoanEdit.getText().toString().equals(""))
                ? "0" : binding.businessLoanEdit.getText().toString()) + " " + businessSpin);
        editor.putString(holStr, ((binding.holidayLoanEdit.getText().toString().equals(""))
                ? "0" : binding.holidayLoanEdit.getText().toString()) + " " + holidaySpin);
        editor.putString(creStr, ((binding.creditCardLoanEdit.getText().toString().equals(""))
                ? "0" : binding.creditCardLoanEdit.getText().toString()) + " " + creditSpin);

        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}