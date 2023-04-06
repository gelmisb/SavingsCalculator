package com.example.savingscalculator.calculatesavings;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.savingscalculator.R;
import com.example.savingscalculator.databinding.FragmentSecondBinding;

//import com.example.savingscalculator.calculatesavings.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        float totalIncome = ((Income) getActivity().getApplicationContext()).getTotalIncome();

        Log.i("Total income: ", String.valueOf(totalIncome));

//        ((Income) getActivity().getApplicationContext()).setWages(
//                (binding.carLoanEdit.getText().toString().equals("")) ? "0" : binding.carLoanEdit.getText().toString());
//        ((Income) getActivity().getApplicationContext()).setpWages(
//                (binding.educationEdit.getText().toString().equals("")) ? "0" : binding.educationEdit.getText().toString());
//        ((Income) getActivity().getApplicationContext()).setCbp(
//                (binding.homeLoanEdit.getText().toString().equals("")) ? "0" : binding.homeLoanEdit.getText().toString());
//        ((Income) getActivity().getApplicationContext()).setMaintenance(
//                (binding.personalLoanEdit.getText().toString().equals("")) ? "0" : binding.personalLoanEdit.getText().toString());
//        ((Income) getActivity().getApplicationContext()).setOtherIncome(
//                (binding.businessLoanEdit.getText().toString().equals("")) ? "0" : binding.businessLoanEdit.getText().toString());
//        ((Income) getActivity().getApplicationContext()).setOtherIncome(
//                (binding.holidayLoanEdit.getText().toString().equals("")) ? "0" : binding.holidayLoanEdit.getText().toString());
//        ((Income) getActivity().getApplicationContext()).setOtherIncome(
//                (binding.creditCardLoanEdit.getText().toString().equals("")) ? "0" : binding.creditCardLoanEdit.getText().toString());
//
//        ((Income) getActivity().getApplicationContext()).setTotalIncome();


//        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(SecondFragment.this)
//                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
//            }
//        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}