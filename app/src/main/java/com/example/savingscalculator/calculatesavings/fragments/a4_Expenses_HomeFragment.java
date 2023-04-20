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
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;


public class a4_Expenses_HomeFragment extends Fragment {

    private FragmentFourthBinding binding;
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private CollectText light ;
    private CacheData cacheData ;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentFourthBinding.inflate(inflater, container, false);
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

        keyStrings.add(res.getString(R.string.rent));
        keyStrings.add(res.getString(R.string.electricity));
        keyStrings.add(res.getString(R.string.gas));
        keyStrings.add(res.getString(R.string.house));
        keyStrings.add(res.getString(R.string.broadband));
        keyStrings.add(res.getString(R.string.tv_license));
        keyStrings.add(res.getString(R.string.waste));
        keyStrings.add(res.getString(R.string.food));
        keyStrings.add(res.getString(R.string.pets));
        keyStrings.add(res.getString(R.string.property));

        spinners.add(binding.spinnerExpenseStep41);
        spinners.add(binding.spinnerExpenseStep42);
        spinners.add(binding.spinnerExpenseStep43);
        spinners.add(binding.spinnerExpenseStep44);
        spinners.add(binding.spinnerExpenseStep45);
        spinners.add(binding.spinnerExpenseStep46);
        spinners.add(binding.spinnerExpenseStep47);
        spinners.add(binding.spinnerExpenseStep48);
        spinners.add(binding.spinnerExpenseStep49);
        spinners.add(binding.spinnerExpenseStep410);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits, "UserExpensesHome");

            NavHostFragment.findNavController(a4_Expenses_HomeFragment.this)
                    .navigate(R.id.action_FourthFragment_to_a5);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}