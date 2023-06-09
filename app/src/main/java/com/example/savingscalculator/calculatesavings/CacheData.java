package com.example.savingscalculator.calculatesavings;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Spinner;

import com.example.savingscalculator.R;

import java.util.ArrayList;

public class CacheData {

    private Activity activity;

    public CacheData(Activity activity) {
        this.activity = activity;
    }

    public void cacheSelected(ArrayList<Spinner> spinners, ArrayList<String> keyStrings, ArrayList<String> expensesEdits, String type, String cat){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(type, MODE_PRIVATE);
        SharedPreferences subCategorySharedPreferences = activity.getSharedPreferences(cat, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        SharedPreferences.Editor editorForCat = subCategorySharedPreferences.edit();

        ArrayList<String> selected = new ArrayList<>();

        for (int i = 0; i < spinners.size(); i++) {
            String temp = spinners.get(i).getSelectedItem().toString();
            selected.add(temp);
        }

        for (int i = 0; i < expensesEdits.size(); i++) {
            editor.putString(keyStrings.get(i), expensesEdits.get(i) + " " + selected.get(i));
            editorForCat.putString(keyStrings.get(i), expensesEdits.get(i) + " " + selected.get(i));
        }

        editor.apply();
        editorForCat.apply();
    }
}
