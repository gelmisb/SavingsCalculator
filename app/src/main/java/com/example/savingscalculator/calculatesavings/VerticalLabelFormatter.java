package com.example.savingscalculator.calculatesavings;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.List;

// Custom label formatter
public class VerticalLabelFormatter implements IAxisValueFormatter {
    private List<String> labels;

    public VerticalLabelFormatter(List<String> labels) {
        this.labels = labels;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        int index = (int) value;
        if (index >= 0 && index < labels.size()) {
            return labels.get(index);
        }
        return "";
    }
}
