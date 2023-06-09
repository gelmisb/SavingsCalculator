package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.CategoryAdapter;
import com.example.savingscalculator.calculatesavings.VerticalLabelFormatter;
import com.example.savingscalculator.databinding.FragmentA14DetailsBinding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


public class a14_Details extends Fragment {

    private FragmentA14DetailsBinding binding;
    private TextView mostSpentTV;
    private TextView leastSpentTV;
    private TextView currentIncomeTV;
    private TextView currentExpensesTV;
    private TextView spentOverTV;
    private TextView top5Val;
    private DecimalFormat decimalFormat;
    private BarChart chart;

    private RecyclerView recyclerView;

    private Map<String, Float> topValues;
    private List<String> top5ValForChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentA14DetailsBinding.inflate(inflater, container, false);
//        recyclerView = binding.top5RecyclerView;
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);

        return binding.getRoot();
    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        decimalFormat = new DecimalFormat("#0.0");

        currentIncomeTV = getActivity().findViewById(R.id.your_current_income_Val);
        currentExpensesTV = getActivity().findViewById(R.id.your_current_expenses_Val);

        spentOverTV = getActivity().findViewById(R.id.you_spent_over_Val);

        mostSpentTV = getActivity().findViewById(R.id.most_spent_Val);
        leastSpentTV = getActivity().findViewById(R.id.least_spent_Val);
        top5Val = getActivity().findViewById(R.id.top_5_Val);

        SharedPreferences sharedPreferencesIncome = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        String total_income = sharedPreferencesIncome.getString(getActivity().getString(R.string.total_income_saved), "");
        total_income = decimalFormat.format(Float.parseFloat(total_income));
        currentIncomeTV.setText(getString(R.string.val, total_income));

        SharedPreferences sharedPreferencesExpenses = getActivity().getSharedPreferences("UserTotalExpenses", MODE_PRIVATE);
        String total_expenses = sharedPreferencesExpenses.getString(getActivity().getString(R.string.total_expenses_saved), "");
        total_expenses = decimalFormat.format(Float.parseFloat(total_expenses));
        currentExpensesTV.setText(getString(R.string.val, total_expenses));

        String expOverInc = "";
        expOverInc = decimalFormat.format(getExpensesOverIncome(total_income, total_expenses));

        spentOverTV.setText(getString(R.string.val, expOverInc));

        topValues = getMaxValues();
        top5ValForChart = new ArrayList<>();
        StringBuilder topValuesText = new StringBuilder();
        for (Map.Entry<String, Float> entry : topValues.entrySet()) {
            topValuesText.append(entry.getKey()).append(": ").append(decimalFormat.format(entry.getValue())).append("\n");
            top5ValForChart.add(entry.getKey());
        }
        top5Val.setText(topValuesText.toString());



        // Setting up a chart
        List<String> categories = getCategories();

        // Create the adapter
        CategoryAdapter adapter = new CategoryAdapter(categories);

        // Set the adapter on the RecyclerView
//        recyclerView.setAdapter(adapter);

        chart = binding.getRoot().findViewById(R.id.chart);

        setupChart(top5ValForChart);
    }

    public List<String> getCategories(){
        SharedPreferences userExpenses = getActivity().getSharedPreferences("UserExpenses", MODE_PRIVATE);
        Map<String, ?> getAll = userExpenses.getAll();
        List<String> cats = new ArrayList<>();

        for (Map.Entry<String, ?> entry : getAll.entrySet()) {
            String keyString = entry.getKey();
            String incomeStr = entry.getValue().toString().replaceAll("[^\\d.]", "");
            float value = multiplyIncome(keyString, incomeStr);
            if(value > 0)
                cats.add(keyString);
        }
        return cats;
    }

    public float getExpensesOverIncome(String income, String expenses) {

        float diff = 0;

        diff = (Float.parseFloat(expenses) / Float.parseFloat(income)) * 100;

        return diff ;
    }

    private void setupChart(List<String> categories) {
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.getDescription().setEnabled(false);

        // Customize chart appearance as desired
        chart.getAxisLeft().setAxisMinimum(0f); // Set the minimum value on the y-axis to 0

        // Prepare data for the chart
        BarData barData = generateBarData(categories);

        // Set the data to the chart
        chart.setData(barData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(true);
//        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        xAxis.setDrawLabels(true);
        xAxis.setLabelRotationAngle(270);
//        xAxis.setValueFormatter(new VerticalLabelFormatter(categories));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(categories));

//        xAxis.setGranularityEnabled(true); // Enable granularity control
//        xAxis.setGranularity(1f); // Set granularity to 1 to avoid label duplication
        xAxis.setLabelCount(categories.size()); // Set the custom label count

        // Hide unnecessary chart elements
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getXAxis().setDrawGridLines(false);

        // Refresh the chart
        chart.invalidate();
    }


    private BarData generateBarData(List<String> categories) {
        ArrayList<BarEntry> entries = new ArrayList<>();

        // Add income and expense values for each category

        for (int i = 0; i< categories.size(); i++) {
            entries.add(new BarEntry((float) i, (int) getExpenseValue(categories.get(i))));
            Log.i("Entry", categories.get(i) + "  " + getExpenseValue(categories.get(i)));
        }
//
//        entries.add(new BarEntry(1f, 1));
//        entries.add(new BarEntry(2f, 1));
//        entries.add(new BarEntry(3f, 1));
//        entries.add(new BarEntry(4f, 1));
//        entries.add(new BarEntry(5f, 1));
        // ... Add entries for other categories

        BarDataSet dataSet = new BarDataSet(entries, "Income vs Expenses");

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        return new BarData(dataSets);
    }

//    private float getIncomeValue(String category) {
//        // Retrieve the income value for the given category from SharedPreferences or any other source
//        // Replace the dummy value with your actual implementation
//        return 100f;
//    }

    private float getExpenseValue(String category) {
        SharedPreferences userExpenses = getActivity().getSharedPreferences("UserExpenses", MODE_PRIVATE);
        Map<String, ?> getAll = userExpenses.getAll();
        float catsValue = 0;

        for (Map.Entry<String, ?> entry : getAll.entrySet()) {
            String keyString = entry.getKey();
            String incomeStr = entry.getValue().toString().replaceAll("[^\\d.]", "");
            float value = multiplyIncome(keyString, incomeStr);

            if(category.equals(keyString)){
//                if(value > 0) {
                catsValue = value;
                Log.i("catsValue", category + " " + catsValue);
//                }
            }
//            Log.i("Value", catsValue + " " + keyString);
        }

        return catsValue;
    }

    public Map<String, Float> getMaxValues() {
        SharedPreferences userExpenses = getActivity().getSharedPreferences("UserExpenses", MODE_PRIVATE);
        Map<String, ?> getAll = userExpenses.getAll();

        TreeMap<Float, List<String>> sortedValues = new TreeMap<>(Collections.reverseOrder());

        for (Map.Entry<String, ?> entry : getAll.entrySet()) {
            String keyString = entry.getKey();
            String incomeStr = entry.getValue().toString().replaceAll("[^\\d.]", "");
            float value = multiplyIncome(keyString, incomeStr);

            List<String> categories = sortedValues.getOrDefault(value, new ArrayList<>());
            categories.add(keyString);
            sortedValues.put(value, categories);
        }

        Map<String, Float> topValues = new LinkedHashMap<>();
        int count = 0;

        for (Map.Entry<Float, List<String>> entry : sortedValues.entrySet()) {
            if (count >= 5) {
                break;
            }
            List<String> categories = entry.getValue();
            for (String category : categories) {
                topValues.put(category, entry.getKey());
                count++;
                if (count >= 5) {
                    break;
                }
            }
        }

        Map.Entry<Float, List<String>> smallestEntry = sortedValues.lastEntry();
        Map.Entry<Float, List<String>> biggestEntry = sortedValues.firstEntry();

        String smallestVal = decimalFormat.format(smallestEntry.getKey());
        String biggestVal = decimalFormat.format(biggestEntry.getKey());

        String smallFormattedValue = smallestEntry.getValue().toString().replaceAll("\\[|\\]", "");
        String bigFormattedValue = biggestEntry.getValue().toString().replaceAll("\\[|\\]", "");

        leastSpentTV.setText(getString(R.string.val1, smallFormattedValue, smallestVal));
        mostSpentTV.setText(getString(R.string.val1,  bigFormattedValue, biggestVal));

        return getTopValues(sortedValues);
    }


//
//    public Map<String, Float> getMaxValues() {
//        SharedPreferences userExpenses = getActivity().getSharedPreferences("UserExpenses", MODE_PRIVATE);
//        Map<String, ?> getAll = userExpenses.getAll();
//
//        TreeMap<Float, String> sortedValues = new TreeMap<>(Collections.reverseOrder());
//
//        for (Map.Entry<String, ?> entry : getAll.entrySet()) {
//            String keyString = entry.getKey();
//            String incomeStr = entry.getValue().toString().replaceAll("[^\\d.]", "");
//            float value = multiplyIncome(keyString, incomeStr);
//
//            sortedValues.put(value, keyString);
//        }
//
//        Map<String, Float> topValues = new LinkedHashMap<>();
//        int count = 0;
//
//        for (Map.Entry<Float, String> entry : sortedValues.entrySet()) {
//            if (count >= 5) {
//                break;
//            }
//
//            topValues.put(entry.getValue(), entry.getKey());
//            count++;
//        }
//
//        return topValues;
//    }




//
//    public List<Map.Entry<String, Float>> getMaxValues() {
//
//
//        SharedPreferences userExpenses = getActivity().getSharedPreferences("UserExpenses", MODE_PRIVATE);
//        Resources res = getActivity().getResources();
//
//        Map<String, ?> getAll = userExpenses.getAll();
//
//        TreeMap<String, Float> topValues = new TreeMap<>();
//        TreeMap<String, Float> bottomValues = new TreeMap<>();
//
//        for (Map.Entry<String, ?> entry : getAll.entrySet()) {
//            String keyString = entry.getKey();
//            String incomeStr = entry.getValue().toString().replaceAll("\\d+", "");
//
//            String valueString = entry.getValue().toString().replaceAll("\\b[^\\d\\W]+\\b", "");
//            Log.i("value ",keyString + " " + incomeStr + " " + valueString + "");
//
//            float value = multiplyIncome(incomeStr, valueString);
//            Log.i("value 2",keyString + " " + incomeStr + " " + value + "");
//
//            topValues.put(keyString, value);
//            bottomValues.put(keyString, value);
//        }
//
//        Map.Entry<String, Float> smallestEntry = bottomValues.lastEntry();
//
//        Log.i("Small value", smallestEntry +" ");
//
//        Map.Entry<String, Float> biggestEntry = topValues.firstEntry();
//        Log.i("Big value", biggestEntry +" ");
//
//
//
//        String smallestVal = decimalFormat.format(smallestEntry.getValue());
//        String biggestVal = decimalFormat.format(biggestEntry.getValue());
//
//        leastSpentTV.setText(getString(R.string.val1, smallestVal, smallestEntry.getValue()));
//
//        mostSpentTV.setText(getString(R.string.val1, biggestVal, biggestEntry.getValue()));
//
//        return getTopValues(topValues);
//    }


    public Map<String, Float> getTopValues(TreeMap<Float, List<String>> sortedValues) {
        Map<String, Float> tops = new LinkedHashMap<>();

        int count = 0;
        for (Map.Entry<Float, List<String>> entry : sortedValues.entrySet()) {
            List<String> categories = entry.getValue();
            for (String category : categories) {
                if (count >= 5) {
                    break;
                }
                tops.put(category, entry.getKey());
                count++;
            }
            if (count >= 5) {
                break;
            }
        }

        return tops;
    }


//    public List<Map.Entry<String, Float>> getTopValues(TreeMap<String, Float> topValues){
//        List<Map.Entry<String, Float>> tops = new ArrayList<>();
//
//        System.out.println("\nTop 5 Maximum Values:");
//        int count = 0;
//        for (Map.Entry<String, Float> entry : topValues.descendingMap().entrySet()) {
//            if (count >= 5) {
//                break;
//            }
//            tops.add(entry);
//            System.out.println("Key: " + entry.getValue() + ", Value: " + entry.getKey());
//            count++;
//        }
//
//        return tops;
//    }

    public float multiplyIncome(String nStr, String nFloat){

        float tempNum = Float.parseFloat(nFloat);
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
