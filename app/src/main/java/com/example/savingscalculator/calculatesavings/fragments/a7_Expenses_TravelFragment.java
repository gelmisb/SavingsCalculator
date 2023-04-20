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
import com.example.savingscalculator.databinding.FragmentA6ExpensesMotorbikeBinding;
import com.example.savingscalculator.databinding.FragmentA7ExpensesTravelBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;

public class a7_Expenses_TravelFragment extends Fragment {

    private FragmentA7ExpensesTravelBinding binding;
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
        binding = FragmentA7ExpensesTravelBinding.inflate(inflater, container, false);
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

        keyStrings.add(res.getString(R.string.travel_ticket));
        keyStrings.add(res.getString(R.string.travel_taxi));
        keyStrings.add(res.getString(R.string.travel_bike));
        keyStrings.add(res.getString(R.string.travel_bike_main));
        keyStrings.add(res.getString(R.string.travel_other));

        spinners.add(binding.spinnerExpenseStep71);
        spinners.add(binding.spinnerExpenseStep72);
        spinners.add(binding.spinnerExpenseStep73);
        spinners.add(binding.spinnerExpenseStep74);
        spinners.add(binding.spinnerExpenseStep75);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits, "UserExpensesTravel");
            NavHostFragment.findNavController(a7_Expenses_TravelFragment.this)
                    .navigate(R.id.action_a7_to_a8);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}