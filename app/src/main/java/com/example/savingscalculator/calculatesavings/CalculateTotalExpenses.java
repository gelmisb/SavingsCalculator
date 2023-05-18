package com.example.savingscalculator.calculatesavings;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;

import com.example.savingscalculator.R;

import java.util.ArrayList;
import java.util.Map;

public class CalculateTotalExpenses {

    private Activity activity;


    public CalculateTotalExpenses(Activity activity){
        this.activity = activity;
    }

    public float getExpenses(){
        // Getting cached files
        SharedPreferences userExpenses = activity.getSharedPreferences("UserExpenses", MODE_PRIVATE);
        SharedPreferences userTotalExpenses = activity.getSharedPreferences("UserTotalExpenses", MODE_PRIVATE);
        SharedPreferences.Editor editor = userTotalExpenses.edit();
        Resources res = activity.getResources();

        Map<String, ?> getAll = userExpenses.getAll();

        ArrayList<Float> total = new ArrayList<Float>();

        for (Map.Entry<String, ?> entry : getAll.entrySet()) {
            String incomeStr = entry.getValue().toString().replaceAll("\\d+", "");
            String incomeFloat = entry.getValue().toString().replaceAll("\\b[^\\d\\W]+\\b", "");
            total.add(multiplyIncome(incomeStr, incomeFloat));
        }

        float tempFloatsy = 0;

        for (int j = 0; j < total.size(); j++) {
            tempFloatsy += total.get(j);
        }

        editor.putString(res.getString(R.string.total_expenses_saved), tempFloatsy + "");
        editor.apply();

        return tempFloatsy;
    }

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
}
