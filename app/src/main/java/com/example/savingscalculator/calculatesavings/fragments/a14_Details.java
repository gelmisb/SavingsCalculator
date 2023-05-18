package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.savingscalculator.R;
import com.example.savingscalculator.databinding.FragmentA14DetailsBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class a14_Details extends Fragment {

    private FragmentA14DetailsBinding binding;
    TextView mostSpentTV;
    TextView leastSpentTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentA14DetailsBinding.inflate(inflater, container, false);


//        1. Top expense
//        2. Top 5 expenses
//        3. Click on every top expense to check what was spent most
//        4. Graph
//        5. Export or save details

        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView currentIncomeTV = getActivity().findViewById(R.id.your_current_income_TV);
        TextView currentExpensesTV = getActivity().findViewById(R.id.your_current_expenses_TV);
        mostSpentTV = getActivity().findViewById(R.id.most_spent_TV);
        leastSpentTV = getActivity().findViewById(R.id.least_spent_TV);

        SharedPreferences sharedPreferencesIncome = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        String total_income = sharedPreferencesIncome.getString(getActivity().getString(R.string.total_income_saved), "");
//        currentIncomeTV.setText();
        Log.i("Total income", total_income);


        SharedPreferences sharedPreferencesExpenses = getActivity().getSharedPreferences("UserTotalExpenses", MODE_PRIVATE);
        String total_expenses = sharedPreferencesExpenses.getString(getActivity().getString(R.string.total_expenses_saved), "");
        Log.i("Total expenses", total_expenses);

        Map<String, Float> topValues = getMaxValues();
        System.out.println("Key: " + topValues);
    }


    public Map<String, Float> getMaxValues() {
//        SharedPreferences userExpensesLoan = getActivity().getSharedPreferences("Loan", MODE_PRIVATE);
//        SharedPreferences userExpensesWork = getActivity().getSharedPreferences("Work", MODE_PRIVATE);
//        SharedPreferences userExpensesHome = getActivity().getSharedPreferences("Home", MODE_PRIVATE);
//        SharedPreferences userExpensesCar = getActivity().getSharedPreferences("Car", MODE_PRIVATE);
//        SharedPreferences userExpensesMoto = getActivity().getSharedPreferences("Motorbike", MODE_PRIVATE);
//        SharedPreferences userExpensesTravel = getActivity().getSharedPreferences("Travel", MODE_PRIVATE);
//        SharedPreferences userExpensesInsurance = getActivity().getSharedPreferences("Insurance", MODE_PRIVATE);
//        SharedPreferences userExpensesEdu = getActivity().getSharedPreferences("Education", MODE_PRIVATE);
//        SharedPreferences userExpensesLeisure = getActivity().getSharedPreferences("Leisure", MODE_PRIVATE);
//        SharedPreferences userExpensesEvent = getActivity().getSharedPreferences("Event", MODE_PRIVATE);
//        SharedPreferences userExpensesSubs = getActivity().getSharedPreferences("Subs", MODE_PRIVATE);
//
//        Map<String, ?> loanAll = userExpensesLoan.getAll();
//        Map<String, ?> workAll = userExpensesWork.getAll();
//        Map<String, ?> homeAll = userExpensesHome.getAll();
//        Map<String, ?> carAll = userExpensesCar.getAll();
//        Map<String, ?> motoAll = userExpensesMoto.getAll();
//        Map<String, ?> travelAll = userExpensesTravel.getAll();
//        Map<String, ?> insuranceAll = userExpensesInsurance.getAll();
//        Map<String, ?> eduAll = userExpensesEdu.getAll();
//        Map<String, ?> leisureAll = userExpensesLeisure.getAll();
//        Map<String, ?> eventAll = userExpensesEvent.getAll();
//        Map<String, ?> subsAll = userExpensesSubs.getAll();
//
//        Map<String, Map<String, ?>> all = new HashMap<>();
//        all.put("loanAll", loanAll);
//        all.put("workAll", workAll);
//        all.put("homeAll", homeAll);
//        all.put("carAll", carAll);
//        all.put("motoAll", motoAll);
//        all.put("travelAll", travelAll);
//        all.put("insuranceAll", insuranceAll);
//        all.put("eduAll", eduAll);
//        all.put("leisureAll", leisureAll);
//        all.put("eventAll", eventAll);
//        all.put("subsAll", subsAll);

        SharedPreferences userExpenses = getActivity().getSharedPreferences("UserExpenses", MODE_PRIVATE);
        Resources res = getActivity().getResources();

        Map<String, ?> getAll = userExpenses.getAll();

        TreeMap<Float, String> topValues = new TreeMap<>();
        TreeMap<Float, String> bottomValues = new TreeMap<>();

        for (Map.Entry<String, ?> entry : getAll.entrySet()) {
            String keyString = entry.getKey();
            String incomeStr = entry.getValue().toString().replaceAll("\\d+", "");

            String valueString = entry.getValue().toString().replaceAll("\\b[^\\d\\W]+\\b", "");
            Log.i("value ",keyString + " " + incomeStr + " " + valueString + "");

            float value = multiplyIncome(valueString, incomeStr);
            Log.i("value 2",keyString + " " + incomeStr + " " + value + "");

            topValues.put(value, keyString);
            bottomValues.put(value, keyString);

            Log.i("topValues",topValues + " " );
            Log.i("bottomValues",bottomValues + " " );

        }

//        for (Map.Entry<String, Map<String, ?>> route : all.entrySet()) {
//            for (Map.Entry<String, ?> entry : route.getValue().entrySet()) {
//                String keyString = entry.getKey();
//                String incomeStr = entry.getValue().toString().replaceAll("\\d+", "");
//
//                String valueString = entry.getValue().toString().replaceAll("\\b[^\\d\\W]+\\b", "");
//                float value = multiplyIncome(valueString, incomeStr);
//                Log.i("value ",  value + "");
//
//                topValues.put(value, keyString);
//                bottomValues.put(value, keyString);
//            }
//        }


        Map.Entry<Float, String> smallestEntry = bottomValues.firstEntry();

        Log.i("Small value", smallestEntry +" ");

        Map.Entry<Float, String> biggestEntry = topValues.lastEntry();
        Log.i("Big value", biggestEntry +" ");


//        Log.i("Small value", smallestEntry.getValue() + " " + smallestEntry.getKey());

//        System.out.println("Key: " + smallestEntry.getValue() + ", Value: " + smallestEntry.getKey());

        leastSpentTV.setText(getString(R.string.least_spent_formatted, smallestEntry.getValue(), smallestEntry.getKey()));


        mostSpentTV.setText(getString(R.string.most_spent_formatted, biggestEntry.getValue(), biggestEntry.getKey()));

        Map<String, Float> tops = new HashMap<>();

//        System.out.println("\nTop 5 Maximum Values:");
//        int count = 0;
//        for (Map.Entry<Float, String> entry : topValues.descendingMap().entrySet()) {
//            if (count >= 5) {
//                break;
//            }
//            tops.put(entry.getValue(), entry.getKey());
//            System.out.println("Key: " + entry.getValue() + ", Value: " + entry.getKey());
//            count++;
//        }

        return tops;
    }

    public float multiplyIncome(String nfloat, String nStr){

        float tempNum = Float.parseFloat(nfloat);
        String tempStr = nStr.replaceAll("\\s", "");
        switch (tempStr) {
            case "Weekly":
                tempNum = tempNum * 4;
                break;
            case "Quarterly":
                tempNum = tempNum / 6;
                break;
            case "Yearly":
                tempNum = tempNum / 12;
                break;
        }
        return tempNum;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    {Business loan=0 Weekly, Car / motorbike loan=123 Weekly, Mortgage=0 Weekly, Holiday loan=0 Weekly, Personal loan=0 Weekly, Education / Tuition loan=0 Weekly, Credit card loan=0 Weekly}


}