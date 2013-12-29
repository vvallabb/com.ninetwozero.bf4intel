package com.ninetwozero.bf4intel.json.stats.reports;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsGameReport extends GameReports {

    @SerializedName("gameMode")
    private int gameMode;
    @SerializedName("playerTeams")
    private Map<Integer, List<Integer>> teamPlayers = new HashMap<Integer, List<Integer>>();
    @SerializedName("mapModeResult")
    private MapModeResult mapModeResult;


    public int getGameMode() {
        return gameMode;
    }

    public Map<Integer, List<Integer>> getTeamPlayers() {
        return teamPlayers;
    }

    public MapModeResult getMapModeResult() {
        return mapModeResult;
    }

    @Override
    public boolean isWinner() {
        return false;
    }

    public class MapModeResult {
        @SerializedName("ticket")
        private Map<Integer, Integer> teamTickets = new HashMap<Integer, Integer>();
    }
}
