package com.ninetwozero.bf4intel.json.awards;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MedalAward implements Serializable {
    @SerializedName("dependencies")
    private List<MedalDependency> medalDepencies = new ArrayList<MedalDependency>();
    @SerializedName("descriptionID")
    private String descriptionId;
    @SerializedName("stringID")
    private String uniqueName;

    public List<MedalDependency> getMedalDepencies() {
        return medalDepencies;
    }

    public String getDescriptionId() {
        return descriptionId;
    }

    public String getUniqueName() {
        return uniqueName;
    }
}
