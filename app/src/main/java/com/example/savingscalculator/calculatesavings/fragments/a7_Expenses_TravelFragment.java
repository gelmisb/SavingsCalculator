package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.CacheData;
import com.example.savingscalculator.calculatesavings.CollectText;
import com.example.savingscalculator.calculatesavings.Income;
import com.example.savingscalculator.calculatesavings.SpinnersAdd;
import com.example.savingscalculator.databinding.FragmentA7ExpensesTravelBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;

public class a7_Expenses_TravelFragment extends Fragment {

    private FragmentA7ExpensesTravelBinding binding;
    private final Resources res = getResources();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentA7ExpensesTravelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Spinner> spinners = new ArrayList<>();
        ArrayList<String> expensesEdits = new ArrayList<>();
        ArrayList<String> keyStrings = new ArrayList<>();

        keyStrings.add(res.getString(R.string.car_motorbike_loan));
        keyStrings.add(res.getString(R.string.education_tuition_loan));
        keyStrings.add(res.getString(R.string.home_loan));
        keyStrings.add(res.getString(R.string.personal_loan));
        keyStrings.add(res.getString(R.string.business_loan));
        keyStrings.add(res.getString(R.string.holiday_loan));
        keyStrings.add(res.getString(R.string.credit_card_loan));

        spinners.add(binding.spinnerExpenseStep21);
        spinners.add(binding.spinnerExpenseStep22);
        spinners.add(binding.spinnerExpenseStep23);
        spinners.add(binding.spinnerExpenseStep24);
        spinners.add(binding.spinnerExpenseStep25);
        spinners.add(binding.spinnerExpenseStep26);
        spinners.add(binding.spinnerExpenseStep27);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            CollectText light = new CollectText(getActivity());
            light.getExpensesFromEdit(expensesEdits);
            CacheData cacheData = new CacheData(getActivity());
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits);
            NavHostFragment.findNavController(a7_Expenses_TravelFragment.this)
                    .navigate(R.id.action_a7_to_a8);
        });
    }


    public void setSpinners(ArrayList<Spinner> spinners){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinners.add(binding.spinnerExpenseStep71);
        spinners.add(binding.spinnerExpenseStep72);
        spinners.add(binding.spinnerExpenseStep73);
        spinners.add(binding.spinnerExpenseStep74);
        spinners.add(binding.spinnerExpenseStep75);

        for (int i = 0; i < spinners.size(); i++) {
            spinners.get(i).setSelection(adapter.getPosition(getResources().getString(R.string.weekly)));
            spinners.get(i).setAdapter(adapter);
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
        keyStrings.add(res.getString(R.string.travel_ticket));
        keyStrings.add(res.getString(R.string.travel_taxi));
        keyStrings.add(res.getString(R.string.travel_bike));
        keyStrings.add(res.getString(R.string.travel_bike_main));
        keyStrings.add(res.getString(R.string.travel_other));

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