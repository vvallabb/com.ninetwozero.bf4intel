package com.ninetwozero.bf4intel.json.stats.vehicles;

import java.io.Serializable;
import java.util.List;

public class GroupedVehicleStats implements Comparable<GroupedVehicleStats>, Serializable {

    private final String groupName;
    private final int serviceStarsCount;
    private final int serviceStarProgress;
    private int killCount;
    private long timeInVehicle;
    private List<VehicleStats> vehicleList;

    public GroupedVehicleStats(
        String groupName, int serviceStarsCount, int serviceStarProgress,
        int killCount, long timeInVehicle, List<VehicleStats> vehicleList
    ) {
        this.groupName = groupName;
        this.serviceStarsCount = serviceStarsCount;
        this.serviceStarProgress = serviceStarProgress;
        this.killCount = killCount;
        this.timeInVehicle = timeInVehicle;
        this.vehicleList = vehicleList;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getServiceStarsCount() {
        return serviceStarsCount;
    }

    public int getServiceStarProgress() {
        return serviceStarProgress;
    }

    public int getKillCount() {
        return killCount;
    }

    public long getTimeInVehicle() {
        return timeInVehicle;
    }

    public List<VehicleStats> getVehicleList() {
        return vehicleList;
    }

    public void addVehicleStats(VehicleStats stat) {
        killCount += stat.getKillsCount();
        timeInVehicle += stat.getTimeInVehicle();
        vehicleList.add(stat);
    }

    @Override
    public int compareTo(GroupedVehicleStats other) {
        if (killCount == other.getKillCount()) {
            return timeInVehicle > other.getTimeInVehicle() ? -1 : timeInVehicle < other.getTimeInVehicle() ? +1 : 0;
        } else {
            return killCount > other.getKillCount() ? -1 : +1;
        }
    }
}
