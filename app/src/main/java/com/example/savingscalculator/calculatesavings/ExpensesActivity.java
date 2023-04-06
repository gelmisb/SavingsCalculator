package com.example.savingscalculator.calculatesavings;

import android.content.Intent;
import android.os.Bundle;

import com.example.savingscalculator.databinding.ActivityExpensesBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

//import com.example.savingscalculator.calculatesavings.databinding.ActivityExpensesBinding;

import com.example.savingscalculator.R;

public class ExpensesActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityExpensesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityExpensesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_expenses);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.timeframe);
        Spinner spinner2 = (Spinner) findViewById(R.id.timeframe2);
        Spinner spinner3 = (Spinner) findViewById(R.id.timeframe3);
        Spinner spinner4 = (Spinner) findViewById(R.id.timeframe4);
        Spinner spinner5 = (Spinner) findViewById(R.id.timeframe5);



//        Spinner spinner_expense_step2_1 = (Spinner) findViewById(R.id.spinner_expense_step2_1);
//        Spinner spinner_expense_step2_2 = (Spinner) findViewById(R.id.spinner_expense_step2_2);
//        Spinner spinner_expense_step2_3 = (Spinner) findViewById(R.id.spinner_expense_step2_3);
//        Spinner spinner_expense_step2_4 = (Spinner) findViewById(R.id.spinner_expense_step2_4);
//        Spinner spinner_expense_step2_5 = (Spinner) findViewById(R.id.spinner_expense_step2_5);
//        Spinner spinner_expense_step2_6 = (Spinner) findViewById(R.id.spinner_expense_step2_6);
//        Spinner spinner_expense_step2_7 = (Spinner) findViewById(R.id.spinner_expense_step2_7);


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


//        spinner_expense_step2_1.setSelection(adapter.getPosition("Weekly"));
//        spinner_expense_step2_2.setSelection(adapter.getPosition("Weekly"));
//        spinner_expense_step2_3.setSelection(adapter.getPosition("Weekly"));
//        spinner_expense_step2_4.setSelection(adapter.getPosition("Weekly"));
//        spinner_expense_step2_5.setSelection(adapter.getPosition("Weekly"));
//        spinner_expense_step2_6.setSelection(adapter.getPosition("Weekly"));
//        spinner_expense_step2_7.setSelection(adapter.getPosition("Weekly"));


        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);


//        spinner_expense_step2_1.setAdapter(adapter);
//        spinner_expense_step2_2.setAdapter(adapter);
//        spinner_expense_step2_3.setAdapter(adapter);
//        spinner_expense_step2_4.setAdapter(adapter);
//        spinner_expense_step2_5.setAdapter(adapter);
//        spinner_expense_step2_6.setAdapter(adapter);
//        spinner_expense_step2_7.setAdapter(adapter);


    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_expenses);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}