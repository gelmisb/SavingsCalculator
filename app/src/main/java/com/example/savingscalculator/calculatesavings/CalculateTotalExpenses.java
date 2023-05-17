package com.example.savingscalculator.calculatesavings;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.savingscalculator.R;

import java.util.ArrayList;
import java.util.Map;

public class CalculateTotalExpenses {

    private Activity activity;
    private float totalIncome;


    public CalculateTotalExpenses(Activity activity){
        this.activity = activity;
    }

    public float getExpenses(){
        // Getting cached files
        SharedPreferences userExpenses = activity.getSharedPreferences("UserExpenses", MODE_PRIVATE);

        Map<String, ?> getAll = userExpenses.getAll();

        ArrayList<Float> total = new ArrayList<Float>();

        for (Map.Entry<String, ?> entry : getAll.entrySet()) {
            String incomeStr = entry.getValue().toString().replaceAll("\\d+", "");
            String incomeFloat = entry.getValue().toString().replaceAll("\\b[^\\d\\W]+\\b", "");

            System.out.println("STR: " + incomeStr);
            System.out.println("FLOAT: " + incomeFloat);

            total.add(multiplyIncome(incomeStr, incomeFloat));
        }

        System.out.println("All: " + total);

        float tempFloatsy = 0;

        for (int j = 0; j < total.size(); j++) {
            tempFloatsy += total.get(j);
        }

        System.out.println("tempFloatsy: " + tempFloatsy);

        return tempFloatsy;

//        SharedPreferences userExpensesWork = activity.getSharedPreferences("UserExpensesWork", MODE_PRIVATE);
//        SharedPreferences userExpensesHome = activity.getSharedPreferences("UserExpensesHome", MODE_PRIVATE);
//        SharedPreferences userExpensesCar = activity.getSharedPreferences("UserExpensesCar", MODE_PRIVATE);
//        SharedPreferences userExpensesMoto = activity.getSharedPreferences("UserExpensesMoto", MODE_PRIVATE);
//        SharedPreferences userExpensesTravel = activity.getSharedPreferences("UserExpensesTravel", MODE_PRIVATE);
//        SharedPreferences userExpensesInsurance = activity.getSharedPreferences("UserExpensesInsurance", MODE_PRIVATE);
//        SharedPreferences userExpensesEdu = activity.getSharedPreferences("UserExpensesEdu", MODE_PRIVATE);
//        SharedPreferences userExpensesLeisure = activity.getSharedPreferences("UserExpensesLeisure", MODE_PRIVATE);
//        SharedPreferences userExpensesEvent = activity.getSharedPreferences("UserExpensesEvent", MODE_PRIVATE);
//        SharedPreferences userExpensesSubs = activity.getSharedPreferences("UserExpensesSubs", MODE_PRIVATE);
//
//        ArrayList<SharedPreferences> allShares = new ArrayList<>();
//        allShares.add(userExpensesLoan);
//        allShares.add(userExpensesWork);
//        allShares.add(userExpensesHome);
//        allShares.add(userExpensesCar);
//        allShares.add(userExpensesMoto);
//        allShares.add(userExpensesTravel);
//        allShares.add(userExpensesInsurance);
//        allShares.add(userExpensesEdu);
//        allShares.add(userExpensesLeisure);
//        allShares.add(userExpensesEvent);
//        allShares.add(userExpensesSubs);

//
//        for(int i=0; i< allShares.size(); i++) {
//
//        }

//        String wages = sharedPreferences.getString(activity.getString(R.string.wages_social_welfare), "");
//        String pwages = sharedPreferences.getString(activity.getString(R.string.partner_s_wages_social_welfare), "");
//        String cbp = sharedPreferences.getString(activity.getString(R.string.cbp), "");
//        String income_main = sharedPreferences.getString(activity.getString(R.string.allowance), "");
//        String other = sharedPreferences.getString(activity.getString(R.string.other_income), "");


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

//        ArrayList<Float> total = new ArrayList<Float>();
//
//        for (Map.Entry<String, ?> entry : loanAll.entrySet()) {
//            String incomeStr = entry.getValue().toString().replaceAll("\\d+", "");
//            String incomeFloat = entry.getValue().toString().replaceAll("\\b[^\\d\\W]+\\b", "");
//
//            System.out.println("STR: " + incomeStr);
//            System.out.println("FLOAT: " + incomeFloat);
//
//            total.add(multiplyIncome(incomeStr, incomeFloat));
//        }

    }

    private float calculateIncome(Map<String, ?> allEntries){

        ArrayList<String> incomeListF = new ArrayList<String>();
        ArrayList<String> incomeListS = new ArrayList<String>();
        ArrayList<Float> totalIncomeList = new ArrayList<Float>();



        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }


//
//        for(int i =0; i< income.size(); i++) {
//            String incomeStr = income.get(i).replaceAll("\\d+", "");
//            String incomeFloat = income.get(i).replaceAll("\\b[^\\d\\W]+\\b", "");
//
//            incomeListF.add(incomeFloat);
//            incomeListS.add(incomeStr);
//
//            totalIncomeList.add(multiplyIncome(incomeFloat, incomeStr));
//        }
//        Log.i("_______ Income List final ________", totalIncomeList.toString());
//
//        float total = 0;
//
//        for (int j = 0 ; j < totalIncomeList.size(); j++) {
//            total += totalIncomeList.get(j);
//        }

        return 0;
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
