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
import com.example.savingscalculator.databinding.FragmentA8ExpensesInsuranceBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;


public class a8_Expenses_InsuranceFragment extends Fragment {

    private FragmentA8ExpensesInsuranceBinding binding;
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private CollectText light ;
    private CacheData cacheData ;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentA8ExpensesInsuranceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Resources res = getResources();
        light = new CollectText(getActivity());
        cacheData = new CacheData(getActivity());

        spinners = new ArrayList<>();
        expensesEdits = new ArrayList<>();
        keyStrings = new ArrayList<>();

        keyStrings.add(res.getString(R.string.insurance_car1));
        keyStrings.add(res.getString(R.string.insurance_car2));
        keyStrings.add(res.getString(R.string.insurance_motorbike1));
        keyStrings.add(res.getString(R.string.insurance_motorbike2));
        keyStrings.add(res.getString(R.string.insurance_health));
        keyStrings.add(res.getString(R.string.insurance_travel));
        keyStrings.add(res.getString(R.string.insurance_home));
        keyStrings.add(res.getString(R.string.insurance_life));
        keyStrings.add(res.getString(R.string.insurance_mortgage));

        spinners.add(binding.spinnerExpenseStep81);
        spinners.add(binding.spinnerExpenseStep82);
        spinners.add(binding.spinnerExpenseStep83);
        spinners.add(binding.spinnerExpenseStep84);
        spinners.add(binding.spinnerExpenseStep85);
        spinners.add(binding.spinnerExpenseStep86);
        spinners.add(binding.spinnerExpenseStep87);
        spinners.add(binding.spinnerExpenseStep88);
        spinners.add(binding.spinnerExpenseStep89);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits, "UserExpenses", "Insurance");
            NavHostFragment.findNavController(a8_Expenses_InsuranceFragment.this)
                    .navigate(R.id.action_a8_to_a9);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}