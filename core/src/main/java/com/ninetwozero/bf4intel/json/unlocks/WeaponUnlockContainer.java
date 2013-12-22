package com.ninetwozero.bf4intel.json.unlocks;

import com.google.gson.annotations.SerializedName;

public class WeaponUnlockContainer implements Comparable<WeaponUnlockContainer> {
    @SerializedName("weaponUnlock")
    private WeaponUnlock unlock;

    public WeaponUnlock getUnlock() {
        return unlock;
    }

    @Override
    public int compareTo(final WeaponUnlockContainer otherUnlock) {
        return unlock.compareTo(otherUnlock.getUnlock());
    }
}