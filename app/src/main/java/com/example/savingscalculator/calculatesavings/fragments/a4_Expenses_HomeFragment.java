package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.Income;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;


public class a4_Expenses_HomeFragment extends Fragment {

    private FragmentFourthBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentFourthBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Spinner> spinners = new ArrayList<>();
        ArrayList<String> expensesEdits = new ArrayList<>();

        setSpinners(spinners);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getExpensesFromEdit(expensesEdits);
                Log.i("List: ", expensesEdits.toString());
                cacheSelected(spinners);

                NavHostFragment.findNavController(a4_Expenses_HomeFragment.this)
                        .navigate(R.id.action_FourthFragment_to_a5);
            }
        });
    }

    public void setSpinners(ArrayList<Spinner> spinners){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinners.add(binding.spinnerExpenseStep41);
        spinners.add(binding.spinnerExpenseStep42);
        spinners.add(binding.spinnerExpenseStep43);
        spinners.add(binding.spinnerExpenseStep44);
        spinners.add(binding.spinnerExpenseStep45);
        spinners.add(binding.spinnerExpenseStep46);
        spinners.add(binding.spinnerExpenseStep47);
        spinners.add(binding.spinnerExpenseStep48);
        spinners.add(binding.spinnerExpenseStep49);
        spinners.add(binding.spinnerExpenseStep410);

        for (int i = 0; i < spinners.size(); i++) {
            spinners.get(i).setSelection(adapter.getPosition(getResources().getString(R.string.weekly)));
            spinners.get(i).setAdapter(adapter);
        }
    }

    public void getExpensesFromEdit(ArrayList<String> expensesEdits) {
        ViewGroup group = (ViewGroup) getActivity().findViewById(R.id.tableLayout);
        for (int i = 0, count = group.getChildCount(); i < count; ++i) {
            ViewGroup another = (ViewGroup) getActivity().findViewById(group.getChildAt(i).getId());
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

    public void cacheSelected(ArrayList<Spinner> spinners){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Resources res = getResources();

        ArrayList<String> selected = new ArrayList<>();

        for (int i = 0; i < spinners.size(); i++) {
            String temp = spinners.get(i).getSelectedItem().toString();
            selected.add(temp);
        }

        Log.i("Trial: ", selected.toString());

//        String renSpin = binding.spinnerExpenseStep41.getSelectedItem().toString();
//        String eleSpin = binding.spinnerExpenseStep42.getSelectedItem().toString();
//        String gasSpin = binding.spinnerExpenseStep43.getSelectedItem().toString();
//        String houSpin = binding.spinnerExpenseStep44.getSelectedItem().toString();
//        String broSpin = binding.spinnerExpenseStep45.getSelectedItem().toString();
//        String tvLSpin = binding.spinnerExpenseStep46.getSelectedItem().toString();
//        String wasSpin = binding.spinnerExpenseStep47.getSelectedItem().toString();
//        String groSpin = binding.spinnerExpenseStep48.getSelectedItem().toString();
//        String petSpin = binding.spinnerExpenseStep49.getSelectedItem().toString();
//        String proSpin = binding.spinnerExpenseStep410.getSelectedItem().toString();

        String renStr = res.getString(R.string.rent);
        String eleStr = res.getString(R.string.electricity);
        String gasStr = res.getString(R.string.gas);
        String houStr = res.getString(R.string.house);
        String broStr = res.getString(R.string.broadband);
        String tvLStr = res.getString(R.string.tv_license);
        String wasStr = res.getString(R.string.waste);
        String groStr = res.getString(R.string.food);
        String petStr = res.getString(R.string.pets);
        String proStr = res.getString(R.string.property);
//
//        editor.putString(renStr, ((binding.rentEdit.getText().toString().equals("")) ? "0" : binding.rentEdit.getText().toString()) + " " + renSpin);
//
//        editor.putString(renStr, ((binding.rentEdit.getText().toString().equals(""))
//                ? "0" : binding.rentEdit.getText().toString()) + " " + renSpin);
//        editor.putString(eleStr, ((binding.electricityEdit.getText().toString().equals(""))
//                ? "0" : binding.electricityEdit.getText().toString()) + " " + eleSpin);
//        editor.putString(gasStr, ((binding.gasEdit.getText().toString().equals(""))
//                ? "0" : binding.gasEdit.getText().toString()) + " " + gasSpin);
//        editor.putString(houStr, ((binding.houseEdit.getText().toString().equals(""))
//                ? "0" : binding.houseEdit.getText().toString()) + " " + houSpin);
//        editor.putString(broStr, ((binding.broadbandEdit.getText().toString().equals(""))
//                ? "0" : binding.broadbandEdit.getText().toString()) + " " + broSpin);
//        editor.putString(tvLStr, ((binding.tvEdit.getText().toString().equals(""))
//                ? "0" : binding.tvEdit.getText().toString()) + " " + tvLSpin);
//        editor.putString(wasStr, ((binding.wasteEdit.getText().toString().equals(""))
//                ? "0" : binding.wasteEdit.getText().toString()) + " " + wasSpin);
//        editor.putString(groStr, ((binding.foodEdit.getText().toString().equals(""))
//                ? "0" : binding.foodEdit.getText().toString()) + " " + groSpin);
//        editor.putString(petStr, ((binding.petsEdit.getText().toString().equals(""))
//                ? "0" : binding.petsEdit.getText().toString()) + " " + petSpin);
//        editor.putString(proStr, ((binding.propertyTaxEdit.getText().toString().equals(""))
//                ? "0" : binding.propertyTaxEdit.getText().toString()) + " " + proSpin);
//        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}