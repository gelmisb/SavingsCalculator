package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
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
import com.example.savingscalculator.calculatesavings.CacheData;
import com.example.savingscalculator.calculatesavings.CollectText;
import com.example.savingscalculator.calculatesavings.SpinnersAdd;
import com.example.savingscalculator.databinding.FragmentFirstBinding;
import com.example.savingscalculator.databinding.FragmentThirdBinding;

import java.util.ArrayList;

public class a1_IncomeFragment extends Fragment {

    private FragmentFirstBinding binding;
    private final Resources res = getResources();
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private CollectText light = new CollectText(getActivity());
    private CacheData cacheData = new CacheData(getActivity());

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinners = new ArrayList<>();
        expensesEdits = new ArrayList<>();
        keyStrings = new ArrayList<>();

        keyStrings.add(res.getString(R.string.wages_social_welfare));
        keyStrings.add(res.getString(R.string.partner_s_wages_social_welfare));
        keyStrings.add(res.getString(R.string.cbp));
        keyStrings.add(res.getString(R.string.income_maintenance));
        keyStrings.add(res.getString(R.string.other_income));

        spinners.add(binding.timeframe);
        spinners.add(binding.timeframe2);
        spinners.add(binding.timeframe3);
        spinners.add(binding.timeframe4);
        spinners.add(binding.timeframe5);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits);

            NavHostFragment.findNavController(a1_IncomeFragment.this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}