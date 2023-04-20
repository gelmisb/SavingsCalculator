package com.example.savingscalculator.calculatesavings;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.savingscalculator.R;

import java.util.ArrayList;

public class SpinnersAdd {

    ArrayList<Spinner> spinners;
    Activity activity;

    public SpinnersAdd(Activity activity, ArrayList<Spinner> spinners){
        this.activity = activity;
        this.spinners = spinners;
    }

    public void setSpinners(ArrayList<Spinner> spinners){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(activity.getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (int i = 0; i < spinners.size(); i++) {
            spinners.get(i).setSelection(adapter.getPosition(activity.getResources().getString(R.string.weekly)));
            spinners.get(i).setAdapter(adapter);
        }
    }

}
