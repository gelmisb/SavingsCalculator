package com.example.savingscalculator.calculatesavings.fragments;

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
import com.example.savingscalculator.databinding.FragmentA11ExpensesEventBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;


public class a11_Expenses_EventFragment extends Fragment {

    private FragmentA11ExpensesEventBinding binding;
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private CollectText light ;
    private CacheData cacheData ;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentA11ExpensesEventBinding.inflate(inflater, container, false);
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

        keyStrings.add(res.getString(R.string.event_birth));
        keyStrings.add(res.getString(R.string.event_hol));
        keyStrings.add(res.getString(R.string.event_house));
        keyStrings.add(res.getString(R.string.event_fur));
        keyStrings.add(res.getString(R.string.event_med));
        keyStrings.add(res.getString(R.string.event_wed));
        keyStrings.add(res.getString(R.string.event_clothes));
        keyStrings.add(res.getString(R.string.event_hair));

        spinners.add(binding.spinnerExpenseStep111);
        spinners.add(binding.spinnerExpenseStep112);
        spinners.add(binding.spinnerExpenseStep113);
        spinners.add(binding.spinnerExpenseStep114);
        spinners.add(binding.spinnerExpenseStep115);
        spinners.add(binding.spinnerExpenseStep116);
        spinners.add(binding.spinnerExpenseStep117);
        spinners.add(binding.spinnerExpenseStep118);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits, "UserExpenses");
            NavHostFragment.findNavController(a11_Expenses_EventFragment.this)
                    .navigate(R.id.action_a11_to_a12);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}