package com.example.savingscalculator.calculatesavings;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.savingscalculator.R;

import java.util.ArrayList;

public class CalculateTotalIncome {


    private Activity activity;
    private float totalIncome;

    public CalculateTotalIncome(Activity activity){
           this.activity = activity;
    }

    public float getIncome(){
        // Getting cached files
        SharedPreferences sharedPreferences = activity.getSharedPreferences("UserIncome", MODE_PRIVATE);

        String wages = sharedPreferences.getString(activity.getString(R.string.wages_social_welfare), "");
        String pwages = sharedPreferences.getString(activity.getString(R.string.partner_s_wages_social_welfare), "");
        String cbp = sharedPreferences.getString(activity.getString(R.string.cbp), "");
        String income_main = sharedPreferences.getString(activity.getString(R.string.allowance), "");
        String other = sharedPreferences.getString(activity.getString(R.string.other_income), "");

        ArrayList<String> incomeStr = new ArrayList<>();

        incomeStr.add(wages);
        incomeStr.add(pwages);
        incomeStr.add(cbp);
        incomeStr.add(income_main);
        incomeStr.add(other);

        return calculateIncome(incomeStr);
    }

    private float calculateIncome(ArrayList<String> income){

        ArrayList<String> incomeListF = new ArrayList<String>();
        ArrayList<String> incomeListS = new ArrayList<String>();
        ArrayList<Float> totalIncomeList = new ArrayList<Float>();

        for(int i =0; i< income.size(); i++) {
            String incomeStr = income.get(i).replaceAll("\\d+", "");
            String incomeFloat = income.get(i).replaceAll("\\b[^\\d\\W]+\\b", "");

            incomeListF.add(incomeFloat);
            incomeListS.add(incomeStr);

            totalIncomeList.add(multiplyIncome(incomeFloat, incomeStr));
        }
        float total = 0;

        for (int j = 0 ; j < totalIncomeList.size(); j++) {
            total += totalIncomeList.get(j);
        }

        return total;
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
}
