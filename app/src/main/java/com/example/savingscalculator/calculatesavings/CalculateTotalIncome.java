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

    public void getIncome(){
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
        Log.i("Income 4 ", income_main);
        incomeStr.add(other);

        calculateIncome(incomeStr);
    }

    private void calculateIncome(ArrayList<String> income){

        ArrayList<String> incomeListF = new ArrayList<String>();
        ArrayList<String> incomeListS = new ArrayList<String>();


        for(int i =0; i< income.size(); i++) {
            String incomeFloat = income.get(i).replaceAll("\\d+", "");
            String incomeStr = income.get(i).replaceAll("\\b[^\\d\\W]+\\b", "");

            incomeListF.add(incomeFloat);
            incomeListS.add(incomeStr);

//            totalIncome += Float.parseFloat(income.get(i));
        }



        Log.i("Total String ", incomeListS + "");
        Log.i("Total Float ", incomeListF + "");
    }
}
