package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class VehicleUnlock implements Comparable<VehicleUnlock>, UnlockContainer {
    @SerializedName("unlockId")
    private String name;
    @SerializedName("guid")
    private String guid;
    @SerializedName("unlockedBy")
    private UnlockCriteria criteria;
    @SerializedName("category")
    private String category;

    public VehicleUnlock(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGuid() {
        return guid;
    }

    public UnlockCriteria getCriteria() {
        return criteria;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    @Override
    public int compareTo(VehicleUnlock otherUnlock) {
        final int compareValue = criteria.compareTo(otherUnlock.getCriteria());
        if (compareValue == 0) {
            return name.compareToIgnoreCase(otherUnlock.getName());
        }
        return compareValue;
    }
}
