package com.example.savingscalculator.calculatesavings;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.savingscalculator.R;

import java.util.ArrayList;

public class CollectText {
    Activity activity;

    public CollectText(Activity activity){
        this.activity = activity;
    }

    public void getExpensesFromEdit(ArrayList<String> expensesEdits) {
        ViewGroup group = (ViewGroup) activity.findViewById(R.id.tableLayout);
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            ViewGroup another = (ViewGroup) group.getChildAt(i);
            for (int j = 0, countJ = another.getChildCount(); j < countJ; ++j) {
                View lew = another.getChildAt(j);
                if(lew instanceof LinearLayout) {
                    if(((EditText)((LinearLayout) lew).getChildAt(1)).getText().toString().equals("")) {
                        expensesEdits.add("0");
                    } else {
                        expensesEdits.add(((EditText)((LinearLayout) lew).getChildAt(1)).getText().toString());
                    }

                }
            }
        }
    }
}
