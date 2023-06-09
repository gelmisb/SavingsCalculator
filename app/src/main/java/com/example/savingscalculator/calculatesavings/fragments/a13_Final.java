package com.example.savingscalculator.calculatesavings.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.CalculateTotalExpenses;
import com.example.savingscalculator.calculatesavings.CalculateTotalIncome;
import com.example.savingscalculator.databinding.FragmentA13FinalBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;


public class a13_Final extends Fragment {

    private FragmentA13FinalBinding binding;
    private CalculateTotalIncome calculateTotalIncome;
    private CalculateTotalExpenses calculateTotalExpenses;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        calculateTotalIncome = new CalculateTotalIncome(getActivity());
        calculateTotalExpenses = new CalculateTotalExpenses(getActivity());
    }


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentA13FinalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String income = String.format("%.02f", calculateTotalIncome.getIncome());
        String expenses = String.format("%.02f", calculateTotalExpenses.getExpenses());

        TextView incomeTV = getActivity().findViewById(R.id.totalIncomeTV);
        TextView expensesTV = getActivity().findViewById(R.id.totalExpensesTV);
        TextView saveText = getActivity().findViewById(R.id.totalSaveText);
        TextView saveTV = getActivity().findViewById(R.id.totalSaveTV);

        incomeTV.setText(getString(R.string.year_calc, income));
        expensesTV.setText(getString(R.string.year_calc, expenses));

        float diff = Float.parseFloat(income) - Float.parseFloat(expenses);

        String diffStr = String.format("%.02f", diff);
        saveTV.setText(getString(R.string.savings_str, diffStr));

        if(diff <= 0) {
            saveText.setText(getString(R.string.losing_savings));
            saveText.setTextColor(Color.parseColor("#B00020"));
        } else {
            saveText.setText(getString(R.string.winning_savings));
            saveText.setTextColor(Color.parseColor("#2A980B"));
        }

        setCharts();

        binding.detailsBtn.setOnClickListener(view1 -> {
            NavHostFragment.findNavController(a13_Final.this)
                    .navigate(R.id.action_final_to_a14);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setCharts() {
        BarChart barChart = (BarChart) getActivity().findViewById(R.id.chart);

        ArrayList<BarEntry> barEntries = new ArrayList<>(getBarEntries());

        BarDataSet barDataSet = new BarDataSet(barEntries, "Budget");
        barDataSet.setColors(new int[] { R.color.purple_700, R.color.red }, getActivity());

        BarData theData = new BarData(barDataSet);

        ArrayList<String> theDates = new ArrayList<>();

        theDates.add("Income");
        theDates.add("Expenses");

        barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(theDates));
        barChart.setData(theData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barChart.animateXY(1500, 1500);
    }

    public ArrayList<BarEntry> getBarEntries() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0f, calculateTotalIncome.getIncome()));
        barEntries.add(new BarEntry(1f, calculateTotalExpenses.getExpenses()));
        return barEntries;
    }

}