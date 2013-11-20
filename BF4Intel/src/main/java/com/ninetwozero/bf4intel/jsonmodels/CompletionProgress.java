package com.ninetwozero.bf4intel.jsonmodels;

import com.google.gson.annotations.SerializedName;

public class CompletionProgress {
    @SerializedName("name")
    private String name;

    @SerializedName("current")
    private int currentValue;

    @SerializedName("max")
    private int maxValue;

    @SerializedName("percentage")
    private int percentage;

    public String getName() {
        return name;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getPercentage() {
        return percentage;
    }

    @Override
    public String toString() {
        return "CompletionProgress{" +
            "name='" + name + '\'' +
            ", currentValue=" + currentValue +
            ", maxValue=" + maxValue +
            ", percentage=" + percentage +
            '}';
    }
}
