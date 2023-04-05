package com.example.savingscalculator.calculatesavings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.savingscalculator.CheckGoalsActivity;
import com.example.savingscalculator.MainActivity;
import com.example.savingscalculator.R;

public class A1_CalcSavings_Income_Activity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_savings);


        Spinner spinner = (Spinner) findViewById(R.id.timeframe);
        Spinner spinner2 = (Spinner) findViewById(R.id.timeframe2);
        Spinner spinner3 = (Spinner) findViewById(R.id.timeframe3);
        Spinner spinner4 = (Spinner) findViewById(R.id.timeframe4);
        Spinner spinner5 = (Spinner) findViewById(R.id.timeframe5);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        spinner.setSelection(adapter.getPosition("Weekly"));
        spinner2.setSelection(adapter.getPosition("Weekly"));
        spinner3.setSelection(adapter.getPosition("Weekly"));
        spinner4.setSelection(adapter.getPosition("Weekly"));
        spinner5.setSelection(adapter.getPosition("Weekly"));

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);

//        next = (Button)findViewById(R.id.nextBtn);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent myIntent = new Intent(A1_CalcSavings_Income_Activity.this, ExpensesActivity.class);
//                A1_CalcSavings_Income_Activity.this.startActivity(myIntent);
//            }
//        });

    }
}