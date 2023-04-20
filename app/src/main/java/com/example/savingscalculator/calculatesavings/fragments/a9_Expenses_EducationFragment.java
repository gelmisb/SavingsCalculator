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
import android.widget.Spinner;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.CollectText;
import com.example.savingscalculator.calculatesavings.Income;
import com.example.savingscalculator.databinding.FragmentA9ExpensesEducationBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;


public class a9_Expenses_EducationFragment extends Fragment {

    private FragmentA9ExpensesEducationBinding binding;
    private final Resources res = getResources();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentA9ExpensesEducationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Spinner> spinners = new ArrayList<>();
        ArrayList<String> expensesEdits = new ArrayList<>();

        setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            CollectText light = new CollectText(getActivity());
            light.getExpensesFromEdit(expensesEdits);
            cacheSelected(spinners, expensesEdits);
            NavHostFragment.findNavController(a9_Expenses_EducationFragment.this)
                    .navigate(R.id.action_a9_to_a10);
        });
    }

    public void setSpinners(ArrayList<Spinner> spinners){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinners.add(binding.spinnerExpenseStep91);
        spinners.add(binding.spinnerExpenseStep92);
        spinners.add(binding.spinnerExpenseStep93);
        spinners.add(binding.spinnerExpenseStep94);
        spinners.add(binding.spinnerExpenseStep95);

        for (int i = 0; i < spinners.size(); i++) {
            spinners.get(i).setSelection(adapter.getPosition(getResources().getString(R.string.weekly)));
            spinners.get(i).setAdapter(adapter);
        }
    }

    public void cacheSelected(ArrayList<Spinner> spinners, ArrayList<String> expensesEdits){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Resources res = getResources();

        ArrayList<String> selected = new ArrayList<>();

        for (int i = 0; i < spinners.size(); i++) {
            String temp = spinners.get(i).getSelectedItem().toString();
            selected.add(temp);
        }

        ArrayList<String> keyStrings = new ArrayList<>();
        keyStrings.add(res.getString(R.string.edu_tuition));
        keyStrings.add(res.getString(R.string.edu_fees));
        keyStrings.add(res.getString(R.string.edu_books));
        keyStrings.add(res.getString(R.string.edu_equip));
        keyStrings.add(res.getString(R.string.edu_clothes));

        for (int i = 0; i < expensesEdits.size(); i++) {
            editor.putString(keyStrings.get(i), expensesEdits.get(i) + " " + selected.get(i));
        }
        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}//108