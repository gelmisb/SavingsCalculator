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
import com.example.savingscalculator.databinding.FragmentA12ExpensesSubsBinding;

import java.util.ArrayList;


public class a12_Expenses_SubsFragment extends Fragment {

    private FragmentA12ExpensesSubsBinding binding;
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private CollectText light ;
    private CacheData cacheData ;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentA12ExpensesSubsBinding.inflate(inflater, container, false);
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

        keyStrings.add(res.getString(R.string.subs_spot));
        keyStrings.add(res.getString(R.string.subs_net));
        keyStrings.add(res.getString(R.string.subs_hulu));
        keyStrings.add(res.getString(R.string.subs_amazon));
        keyStrings.add(res.getString(R.string.subs_disney));
        keyStrings.add(res.getString(R.string.subs_prime));
        keyStrings.add(res.getString(R.string.subs_apple));
        keyStrings.add(res.getString(R.string.subs_pea));
        keyStrings.add(res.getString(R.string.subs_crunch));
        keyStrings.add(res.getString(R.string.subs_chew));
        keyStrings.add(res.getString(R.string.subs_espn));
        keyStrings.add(res.getString(R.string.subs_hbo));
        keyStrings.add(res.getString(R.string.subs_other));

        spinners.add(binding.spinnerExpenseStep121);
        spinners.add(binding.spinnerExpenseStep122);
        spinners.add(binding.spinnerExpenseStep123);
        spinners.add(binding.spinnerExpenseStep124);
        spinners.add(binding.spinnerExpenseStep125);
        spinners.add(binding.spinnerExpenseStep126);
        spinners.add(binding.spinnerExpenseStep127);
        spinners.add(binding.spinnerExpenseStep128);
        spinners.add(binding.spinnerExpenseStep129);
        spinners.add(binding.spinnerExpenseStep1210);
        spinners.add(binding.spinnerExpenseStep1211);
        spinners.add(binding.spinnerExpenseStep1212);
        spinners.add(binding.spinnerExpenseStep1213);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits);
            NavHostFragment.findNavController(a12_Expenses_SubsFragment.this)
                    .navigate(R.id.action_a12_to_final);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}