package com.example.savingscalculator.calculatesavings.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.savingscalculator.R;
import com.example.savingscalculator.calculatesavings.Income;
import com.example.savingscalculator.databinding.FragmentA7ExpensesTravelBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

public class a7_Expenses_TravelFragment extends Fragment {

    private FragmentA7ExpensesTravelBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentA7ExpensesTravelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        float totalIncome = ((Income) getActivity().getApplicationContext()).getTotalIncome();

        Log.i("Total income: ", String.valueOf(totalIncome));

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
                R.array.timeframe_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//        binding.spinnerExpenseStep41.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep42.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep43.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep44.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep45.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep46.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep47.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep48.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep49.setSelection(adapter.getPosition("Weekly"));
//        binding.spinnerExpenseStep410.setSelection(adapter.getPosition("Weekly"));
//
//        binding.spinnerExpenseStep41.setAdapter(adapter);
//        binding.spinnerExpenseStep42.setAdapter(adapter);
//        binding.spinnerExpenseStep43.setAdapter(adapter);
//        binding.spinnerExpenseStep44.setAdapter(adapter);
//        binding.spinnerExpenseStep45.setAdapter(adapter);
//        binding.spinnerExpenseStep46.setAdapter(adapter);
//        binding.spinnerExpenseStep47.setAdapter(adapter);
//        binding.spinnerExpenseStep48.setAdapter(adapter);
//        binding.spinnerExpenseStep49.setAdapter(adapter);
//        binding.spinnerExpenseStep410.setAdapter(adapter);

//        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(FourthFragment.this)
//                        .navigate(R.id.action_FourthFragment_to_FifthFragment);
//            }
//        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}