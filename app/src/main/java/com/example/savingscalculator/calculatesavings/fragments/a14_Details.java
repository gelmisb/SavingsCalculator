package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.savingscalculator.R;
import com.example.savingscalculator.databinding.FragmentA14DetailsBinding;


public class a14_Details extends Fragment {

    private FragmentA14DetailsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentA14DetailsBinding.inflate(inflater, container, false);

        TextView currentIncomeTV = getActivity().findViewById(R.id.your_current_income_TV);
        TextView currentExpensesTV = getActivity().findViewById(R.id.your_current_expenses_TV);
        TextView mostSpentTV = getActivity().findViewById(R.id.most_spent_TV);
        TextView leastSpentTV = getActivity().findViewById(R.id.least_spent_TV);

        SharedPreferences sharedPreferencesIncome = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        String total_income = sharedPreferencesIncome.getString(getActivity().getString(R.string.total_income_saved), "");

        SharedPreferences sharedPreferencesExpenses = getActivity().getSharedPreferences("UserExpenses", MODE_PRIVATE);
        String total_expenses = sharedPreferencesExpenses.getString(getActivity().getString(R.string.total_expenses_saved), "");


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