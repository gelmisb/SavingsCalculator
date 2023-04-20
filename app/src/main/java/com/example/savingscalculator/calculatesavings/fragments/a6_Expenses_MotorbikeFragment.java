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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.CacheData;
import com.example.savingscalculator.calculatesavings.CollectText;
import com.example.savingscalculator.calculatesavings.Income;
import com.example.savingscalculator.calculatesavings.SpinnersAdd;
import com.example.savingscalculator.databinding.FragmentA6ExpensesMotorbikeBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;


public class a6_Expenses_MotorbikeFragment extends Fragment {

    private FragmentA6ExpensesMotorbikeBinding binding;
    private final Resources res = getResources();
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private final CollectText light = new CollectText(getActivity());
    private final CacheData cacheData = new CacheData(getActivity());


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentA6ExpensesMotorbikeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinners = new ArrayList<>();
        expensesEdits = new ArrayList<>();
        keyStrings = new ArrayList<>();

        keyStrings.add(res.getString(R.string.moto_petrol));
        keyStrings.add(res.getString(R.string.moto_tax));
        keyStrings.add(res.getString(R.string.moto_maintenance));
        keyStrings.add(res.getString(R.string.moto_parts));
        keyStrings.add(res.getString(R.string.moto_gear));

        spinners.add(binding.spinnerExpenseStep61);
        spinners.add(binding.spinnerExpenseStep62);
        spinners.add(binding.spinnerExpenseStep63);
        spinners.add(binding.spinnerExpenseStep64);
        spinners.add(binding.spinnerExpenseStep65);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits);
            NavHostFragment.findNavController(a6_Expenses_MotorbikeFragment.this)
                    .navigate(R.id.action_a6_to_a7);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}