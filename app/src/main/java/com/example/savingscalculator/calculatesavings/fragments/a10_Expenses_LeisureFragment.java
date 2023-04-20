package com.example.savingscalculator.calculatesavings.fragments;

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
import com.example.savingscalculator.databinding.FragmentA10ExpensesLeisureBinding;
import com.example.savingscalculator.databinding.FragmentFourthBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link a10_Expenses_LeisureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class a10_Expenses_LeisureFragment extends Fragment {

    private FragmentA10ExpensesLeisureBinding binding;
    private final Resources res = getResources();


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentA10ExpensesLeisureBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Spinner> spinners = new ArrayList<>();
        ArrayList<String> expensesEdits = new ArrayList<>();

//        setSpinners(spinners);

        binding.nextBtn.setOnClickListener(view1 -> {
            CollectText light = new CollectText(getActivity());
            light.getExpensesFromEdit(expensesEdits);
//            cacheSelected(spinners, expensesEdits);
            NavHostFragment.findNavController(a10_Expenses_LeisureFragment.this)
                    .navigate(R.id.action_a10_to_a11);
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}