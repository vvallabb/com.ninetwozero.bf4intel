package com.ninetwozero.bf4intel.json.assignments;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Assignment implements Comparable<Assignment>, Serializable {
    private static final int COMPLETED = 100;

    @SerializedName("award")
    private AssignmentAward award;
    @SerializedName("criterias")
    private List<AssignmentCriteria> criterias;
    @SerializedName("completion")
    private int completion;
    @SerializedName("isTracking")
    private boolean isTracking;
    @SerializedName("unlockDependencies")
    private List<AssignmentPrerequisite> prerequisites;
    @SerializedName("upcomingUnlocks")
    private List<AssignmentReward> rewards;
    private String group;

    public AssignmentAward getAward() {
        return award;
    }

    public int getCompletion() {
        return completion;
    }

    public boolean isCompleted() {
        return completion == COMPLETED;
    }

    public boolean isTracking() {
        return isTracking;
    }

    public String getDependencyGroup() {
        if (prerequisites == null || prerequisites.isEmpty()) {
            return AssignmentPrerequisite.Type.NONE.toString();
        }
        return prerequisites.get(0).getGroup();
    }

    public List<AssignmentCriteria> getCriterias() {
        return criterias;
    }

    public List<AssignmentPrerequisite> getPrerequisites() {
        return prerequisites;
    }

    public List<AssignmentReward> getRewards() {
        return rewards;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int compareTo(Assignment assignment) {
        if (completion > assignment.getCompletion()) {
            return -1;
        } else if (completion < assignment.getCompletion()) {
            return 1;
        } else {
            return 0;
        }
    }
}
