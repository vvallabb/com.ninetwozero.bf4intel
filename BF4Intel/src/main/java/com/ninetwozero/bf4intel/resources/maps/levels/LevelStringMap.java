package com.ninetwozero.bf4intel.resources.maps.levels;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LevelStringMap {
    private static final Map<String, String> map = new HashMap<String, String>() {
        {
            // SP
            put("SP_AIRFIELD", "SINGAPORE");
            put("SP_DAM", "TASHGAR");
            put("SP_NAVAL", "SOUTH CHINA SEA");
            put("SP_PRISON", "KUNLUN MOUNTAINS");
            put("SP_PROLOGUE", "BAKU");
            put("SP_SHANGHAI", "SHANGHAI");
            put("SP_SUEZ", "SUEZ");

            // MP
            put("MP_ABANDONED", "Zavod 311");
            put("MP_DAMAGE", "Lancang Dam");
            put("MP_THEDISH", "Rogue Transmission");
            put("MP_FLOODED", "Flood Zone");
            put("MP_JOURNEY", "Golmud Railway");
            put("MP_NAVAL", "Paracel Storm");
            put("MP_PRISON", "Operation Locker");
            put("MP_RESORT", "Hainan Resort");
            put("MP_SIEGE", "Siege of Shanghai");
            put("MP_TREMORS", "Dawnbreaker");

            // XP0
            put("XP0_CASPIAN", "Caspian Border 2014");
            put("XP0_FIRESTORM", "Operation Firestorm 2014");
            put("XP0_METRO", "Operation Metro 2014");
            put("XP0_OMAN", "Gulf of Oman 2014");

            // XP1
            put("XP1_001", "Silk Road");
            put("XP1_002", "Altai Range");
            put("XP1_003", "Guilin Peaks");
            put("XP1_004", "Dragon Pass");

            // XP2
            put("XP2_001", "Lost Islands");
            put("XP2_002", "Nansha Strike");
            put("XP2_003", "Wave Breaker");
            put("XP2_004", "Operation Mortar");

            //XP3
            put("XP3_PRPGANDA", "Propaganda");
            put("XP3_MARKETPL", "Pearl Market");
            put("XP3_WTRFRONT", "Sunken Dragon");
            put("XP3_URBANGDN", "Lumphini Garden");

            //XP4
            put("XP4_TITAN","Hangar 21");
            put("XP4_ARCTIC","Operation Whiteout");
            put("XP4_WLKRFTRY","Giants Of Karelia");
            put("XP4_SUBBASE","Hammerhead");
        }
    };

    public static String get(final String key) {
        return map.containsKey(key.toUpperCase(Locale.getDefault()))? map.get(key.toUpperCase(Locale.getDefault())) : key;
    }
}
