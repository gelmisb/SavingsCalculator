package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
import com.example.savingscalculator.calculatesavings.SpinnersAdd;
import com.example.savingscalculator.databinding.FragmentThirdBinding;

import java.util.ArrayList;

public class a3_Expenses_WorkFragment extends Fragment {

    private FragmentThirdBinding binding;
    private final Resources res = getResources();
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private CollectText light = new CollectText(getActivity());
    private CacheData cacheData = new CacheData(getActivity());


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinners = new ArrayList<>();
        expensesEdits = new ArrayList<>();
        keyStrings = new ArrayList<>();

        keyStrings.add(res.getString(R.string.lunch));
        keyStrings.add(res.getString(R.string.snacks));
        keyStrings.add(res.getString(R.string.transport));
        keyStrings.add(res.getString(R.string.other_daily_expenses));

        spinners.add(binding.spinnerExpenseStep31);
        spinners.add(binding.spinnerExpenseStep32);
        spinners.add(binding.spinnerExpenseStep33);
        spinners.add(binding.spinnerExpenseStep34);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits);
            NavHostFragment.findNavController(a3_Expenses_WorkFragment.this)
                    .navigate(R.id.action_ThirdFragment_to_FourthFragment);
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}