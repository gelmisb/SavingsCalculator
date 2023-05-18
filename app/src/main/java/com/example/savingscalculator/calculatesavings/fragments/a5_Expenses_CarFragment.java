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
import com.example.savingscalculator.databinding.FragmentA5ExpensesCarBinding;

import java.util.ArrayList;

public class a5_Expenses_CarFragment extends Fragment {

    private FragmentA5ExpensesCarBinding binding;
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
        binding = FragmentA5ExpensesCarBinding.inflate(inflater, container, false);
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

        keyStrings.add(res.getString(R.string.car_petrol));
        keyStrings.add(res.getString(R.string.car_tax));
        keyStrings.add(res.getString(R.string.car_maintenance));
        keyStrings.add(res.getString(R.string.car_service));
        keyStrings.add(res.getString(R.string.car_parking));
        keyStrings.add(res.getString(R.string.car_nct));

        spinners.add(binding.spinnerExpenseStep51);
        spinners.add(binding.spinnerExpenseStep52);
        spinners.add(binding.spinnerExpenseStep53);
        spinners.add(binding.spinnerExpenseStep54);
        spinners.add(binding.spinnerExpenseStep55);
        spinners.add(binding.spinnerExpenseStep56);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            CollectText light = new CollectText(getActivity());
            light.getExpensesFromEdit(expensesEdits);
            CacheData cacheData = new CacheData(getActivity());
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits, "UserExpenses", "Car");
            NavHostFragment.findNavController(a5_Expenses_CarFragment.this)
                    .navigate(R.id.action_a5_to_a6);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}