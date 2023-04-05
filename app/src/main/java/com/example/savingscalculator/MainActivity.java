package com.example.savingscalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.savingscalculator.calculatesavings.CalcSavingsActivity;

public class MainActivity extends AppCompatActivity {

    Button calcSavingsBtn;
    Button checkSavingsBtn;
    Button setGoalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcSavingsBtn = (Button)findViewById(R.id.calc_savings_activity_btn);
        checkSavingsBtn = (Button)findViewById(R.id.check_goals_activity_btn);
        setGoalBtn = (Button)findViewById(R.id.set_goals_activity_btn);

        calcSavingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, CalcSavingsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        checkSavingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, CheckGoalsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        setGoalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, SetGoalsActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }




}