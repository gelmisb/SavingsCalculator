package com.example.savingscalculator.calculatesavings.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.CalculateTotalIncome;
import com.example.savingscalculator.databinding.FragmentA12ExpensesSubsBinding;
import com.example.savingscalculator.databinding.FragmentA13FinalBinding;


public class a13_Final extends Fragment {

    private FragmentA13FinalBinding binding;
    private CalculateTotalIncome calculateTotalIncome;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calculateTotalIncome = new CalculateTotalIncome(getActivity());

    }


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        TextView incomeTv = getActivity().findViewById(R.id.totalIncomeTV);
//        incomeTv.setText(String.valueOf(calculateTotalIncome.getIncome()));
        Log.i("asdasdasdasdasdasdasdasdasdasd", calculateTotalIncome.getIncome() + "");


        binding = FragmentA13FinalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}