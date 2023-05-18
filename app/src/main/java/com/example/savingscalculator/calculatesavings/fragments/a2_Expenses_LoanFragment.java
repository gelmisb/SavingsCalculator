package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
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
import com.example.savingscalculator.databinding.FragmentSecondBinding;

import java.util.ArrayList;

public class a2_Expenses_LoanFragment extends Fragment {

    private FragmentSecondBinding binding;
    private ArrayList<Spinner> spinners;
    private ArrayList<String> expensesEdits;
    private ArrayList<String> keyStrings;
    private CollectText light ;
    private CacheData cacheData ;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
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

        keyStrings.add(res.getString(R.string.car_motorbike_loan));
        keyStrings.add(res.getString(R.string.education_tuition_loan));
        keyStrings.add(res.getString(R.string.home_loan));
        keyStrings.add(res.getString(R.string.personal_loan));
        keyStrings.add(res.getString(R.string.business_loan));
        keyStrings.add(res.getString(R.string.holiday_loan));
        keyStrings.add(res.getString(R.string.credit_card_loan));

        spinners.add(binding.spinnerExpenseStep21);
        spinners.add(binding.spinnerExpenseStep22);
        spinners.add(binding.spinnerExpenseStep23);
        spinners.add(binding.spinnerExpenseStep24);
        spinners.add(binding.spinnerExpenseStep25);
        spinners.add(binding.spinnerExpenseStep26);
        spinners.add(binding.spinnerExpenseStep27);

        SpinnersAdd spinnersAdd = new SpinnersAdd(getActivity(), spinners);
        spinnersAdd.setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            light.getExpensesFromEdit(expensesEdits);
            cacheData.cacheSelected(spinners, keyStrings, expensesEdits, "UserExpenses", "Loan");

            NavHostFragment.findNavController(a2_Expenses_LoanFragment.this)
                    .navigate(R.id.action_SecondFragment_to_ThirdFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}