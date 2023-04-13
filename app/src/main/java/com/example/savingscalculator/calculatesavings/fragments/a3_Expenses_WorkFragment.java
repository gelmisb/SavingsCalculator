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
import com.example.savingscalculator.calculatesavings.Income;
import com.example.savingscalculator.databinding.FragmentThirdBinding;

import java.util.ArrayList;


public class a3_Expenses_WorkFragment extends Fragment {

    private FragmentThirdBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentThirdBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinners();

        binding.nextBtn.setOnClickListener(view1 -> {

            cacheSelected();

            NavHostFragment.findNavController(a3_Expenses_WorkFragment.this)
                    .navigate(R.id.action_ThirdFragment_to_FourthFragment);
        });
    }

    public void setSpinners(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<Spinner> spinners = new ArrayList<>();

        spinners.add(binding.spinnerExpenseStep31);
        spinners.add(binding.spinnerExpenseStep32);
        spinners.add(binding.spinnerExpenseStep33);
        spinners.add(binding.spinnerExpenseStep34);

        for (int i = 0; i < spinners.size(); i++) {
            spinners.get(i).setSelection(adapter.getPosition(getResources().getString(R.string.weekly)));
            spinners.get(i).setAdapter(adapter);
        }
    }

    public void cacheSelected(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Resources res = getResources();

        String lunSpin = binding.spinnerExpenseStep31.getSelectedItem().toString();
        String snaSpin = binding.spinnerExpenseStep32.getSelectedItem().toString();
        String traSpin = binding.spinnerExpenseStep33.getSelectedItem().toString();
        String othSpin = binding.spinnerExpenseStep34.getSelectedItem().toString();

        String lunStr = res.getString(R.string.lunch);
        String snaStr = res.getString(R.string.snacks);
        String traStr = res.getString(R.string.transport);
        String othStr = res.getString(R.string.other_daily_expenses);

        editor.putString(lunStr, ((binding.lunchEdit.getText().toString().equals(""))
                ? "0" : binding.lunchEdit.getText().toString()) + " " + lunSpin);
        editor.putString(snaStr, ((binding.snacksEdit.getText().toString().equals(""))
                ? "0" : binding.snacksEdit.getText().toString()) + " " + snaSpin);
        editor.putString(traStr, ((binding.transportEdit.getText().toString().equals(""))
                ? "0" : binding.transportEdit.getText().toString()) + " " + traSpin);
        editor.putString(othStr, ((binding.otherEdit.getText().toString().equals(""))
                ? "0" : binding.otherEdit.getText().toString()) + " " + othSpin);

        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}