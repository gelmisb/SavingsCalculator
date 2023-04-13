package com.example.savingscalculator.calculatesavings.fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.savingscalculator.R;
import com.example.savingscalculator.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class a1_IncomeFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayList<Spinner> spinners = new ArrayList<>();
        setSpinners(spinners, adapter);

        binding.nextBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cacheSelected();
                NavHostFragment.findNavController(a1_IncomeFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    public void setSpinners(ArrayList<Spinner> spinList, ArrayAdapter<CharSequence> adapter){
        spinList.add(binding.timeframe);
        spinList.add(binding.timeframe2);
        spinList.add(binding.timeframe3);
        spinList.add(binding.timeframe4);
        spinList.add(binding.timeframe5);

        for (int i = 0; i < spinList.size(); i++) {
            spinList.get(i).setSelection(adapter.getPosition(getResources().getString(R.string.weekly)));
            spinList.get(i).setAdapter(adapter);
        }
    }

    public void cacheSelected(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserIncome", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Resources res = getResources();

        String wagesT = binding.timeframe.getSelectedItem().toString();
        String pWagesT = binding.timeframe2.getSelectedItem().toString();
        String cbpT = binding.timeframe3.getSelectedItem().toString();
        String mainT = binding.timeframe4.getSelectedItem().toString();
        String otherIT = binding.timeframe5.getSelectedItem().toString();

        String wages = res.getString(R.string.wages_social_welfare);
        String partnerWages = res.getString(R.string.partner_s_wages_social_welfare);
        String cbp = res.getString(R.string.cbp);
        String maintenance = res.getString(R.string.income_maintenance);
        String otherIncome = res.getString(R.string.wages_social_welfare);

        editor.putString(wages, ((binding.editWagesNumber.getText().toString().equals("")) ? "0" : binding.editWagesNumber.getText().toString()) + " " + wagesT);
        editor.putString(partnerWages, ((binding.editWagesNumber.getText().toString().equals("")) ? "0" : binding.editWagesNumber.getText().toString()) + " " + pWagesT);
        editor.putString(cbp, ((binding.editWagesNumber.getText().toString().equals("")) ? "0" : binding.editWagesNumber.getText().toString()) + " " + cbpT);
        editor.putString(maintenance, ((binding.editWagesNumber.getText().toString().equals("")) ? "0" : binding.editWagesNumber.getText().toString()) + " " + mainT);
        editor.putString(otherIncome, ((binding.editWagesNumber.getText().toString().equals("")) ? "0" : binding.editWagesNumber.getText().toString()) + " " + otherIT);

        editor.apply();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}