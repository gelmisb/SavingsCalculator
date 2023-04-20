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
import com.example.savingscalculator.databinding.FragmentA10ExpensesLeisureBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;


public class a10_Expenses_LeisureFragment extends Fragment {

    private FragmentA10ExpensesLeisureBinding binding;
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private CollectText light ;
    private CacheData cacheData ;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentA10ExpensesLeisureBinding.inflate(inflater, container, false);
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

        keyStrings.add(res.getString(R.string.leisure_member));
        keyStrings.add(res.getString(R.string.leisure_out));
        keyStrings.add(res.getString(R.string.leisure_cinema));
        keyStrings.add(res.getString(R.string.leisure_festival));
        keyStrings.add(res.getString(R.string.leisure_enter));
        keyStrings.add(res.getString(R.string.leisure_alcohol));
        keyStrings.add(res.getString(R.string.leisure_cigs));

        spinners.add(binding.spinnerExpenseStep101);
        spinners.add(binding.spinnerExpenseStep102);
        spinners.add(binding.spinnerExpenseStep103);
        spinners.add(binding.spinnerExpenseStep104);
        spinners.add(binding.spinnerExpenseStep105);
        spinners.add(binding.spinnerExpenseStep106);
        spinners.add(binding.spinnerExpenseStep107);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits, "UserExpensesLeisure");
            NavHostFragment.findNavController(a10_Expenses_LeisureFragment.this)
                    .navigate(R.id.action_a10_to_a11);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}