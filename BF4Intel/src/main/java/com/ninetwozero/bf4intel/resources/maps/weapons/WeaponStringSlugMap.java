package com.ninetwozero.bf4intel.resources.maps.weapons;

import android.util.Log;

import com.ninetwozero.bf4intel.R;

import java.util.HashMap;
import java.util.Map;

/* Contains both WNAME and INAME */

public class WeaponStringSlugMap {
    private static final Map<String, Integer> map = new HashMap<String, Integer>() {
        {

            put("USAS-12-FLIR", R.string.weapon_name_usas12nv);
            put("338-RECON", R.string.weapon_name_338recon);
            put("870-MCS", R.string.weapon_name_870);
            put("93R", R.string.weapon_name_93r);
            put("A-91", R.string.weapon_name_a91);
            put("ACR", R.string.weapon_name_acr);
            put("ACW-R", R.string.weapon_name_acwr);
            put("AEK-971", R.string.weapon_name_aek971);
            put("AK-12", R.string.weapon_name_ak12);
            put("AK-5C", R.string.weapon_name_ak5c);
            put("AKU-12", R.string.weapon_name_aku12);
            put("AMR-2", R.string.weapon_name_amr2);
            put("AMR-2-CQB", R.string.weapon_name_amr2cqb);
            put("AMR-2-MID", R.string.weapon_name_amr2mid);
            put("CBJ-MS", R.string.weapon_name_cbjms);
            put("COMPACT-45", R.string.weapon_name_compact45);
            put("CS-LR4", R.string.weapon_name_cslr4);
            put("CZ-75", R.string.weapon_name_cz75);
            put("CZ-805", R.string.weapon_name_cz805);
            put("CZ-3A1", R.string.weapon_name_cz805);
            put("DBV-12", R.string.weapon_name_dbv12);
            put("FAMAS", R.string.weapon_name_famas);
            put("FN57", R.string.weapon_name_fn57);
            put("FY-JS", R.string.weapon_name_fyjs);
            put("G18", R.string.weapon_name_g18);
            put("G36C", R.string.weapon_name_g36c);
            put("ACE-21-CQB", R.string.weapon_name_galil21);
            put("ACE-23", R.string.weapon_name_galil23);
            put("ACE-52-CQB", R.string.weapon_name_galil52);
            put("ACE-53-SV", R.string.weapon_name_galil53);
            put("GLOCK18", R.string.weapon_name_glock18);
            put("HAWK-12G", R.string.weapon_name_hawk);
            put("HK45-C", R.string.weapon_name_hk45c);
            put("JNG-90", R.string.weapon_name_jng90);
            put("JS2", R.string.weapon_name_js2);
            put("LSAT", R.string.weapon_name_lsat);
            put("M1014", R.string.weapon_name_m1014);
            put("M16A4", R.string.weapon_name_m16a4);
            put("M1911", R.string.weapon_name_m1911);
            put("M200", R.string.weapon_name_m200);
            put("M240B", R.string.weapon_name_m240);
            put("M249", R.string.weapon_name_m249);
            put("M39-EMR", R.string.weapon_name_m39);
            put("M40A5", R.string.weapon_name_m40a5);
            put("M412-REX", R.string.weapon_name_m412rex);
            put("M416", R.string.weapon_name_m416);
            put("M4", R.string.weapon_name_m4a1);
            put("M82A3", R.string.weapon_name_m82a3);
            put("M82A3-CQB", R.string.weapon_name_m82a3cqb);
            put("M82A3-MID", R.string.weapon_name_m82a3mid);
            put("M9", R.string.weapon_name_m9);
            put("M98B", R.string.weapon_name_m98b);
            put("MG4", R.string.weapon_name_mg4);
            put("MK11-MOD-0", R.string.weapon_name_mk11);
            put("MP443", R.string.weapon_name_mp443);
            put("MX4", R.string.weapon_name_mx4);
            put("P226", R.string.weapon_name_p226);
            put("P90", R.string.weapon_name_p90);
            put("PDW-R", R.string.weapon_name_pdr);
            put("PKP-PECHENEG", R.string.weapon_name_pecheneg);
            put("PP-2000", R.string.weapon_name_pp2000);
            put("QBB-95-1", R.string.weapon_name_qbb95);
            put("QBS-09", R.string.weapon_name_qbs09);
            put("QBU-88", R.string.weapon_name_qbu88);
            put("QBZ-95-1", R.string.weapon_name_qbz951);
            put("QSZ-92", R.string.weapon_name_qsz92);
            put("RFB", R.string.weapon_name_rfbtarget);
            put("RPK-12", R.string.weapon_name_rpk12);
            put("SAIGA-12K", R.string.weapon_name_saiga12);
            put("SAR-21", R.string.weapon_name_sar21);
            put("SCAR-H", R.string.weapon_name_scarh);
            put("SCAR-H-SV", R.string.weapon_name_scarhsv);
            put("SCORP", R.string.weapon_name_scorp);
            put("SCOUT-ELITE", R.string.weapon_name_scoutelit);
            put("SG553", R.string.weapon_name_sg553);
            put("SHORTY-12G", R.string.weapon_name_shorty);
            put("SKS", R.string.weapon_name_sks);
            put("SPAS-12", R.string.weapon_name_spas12);
            put("SRR-61", R.string.weapon_name_srr61);
            put("SRS", R.string.weapon_name_srs);
            put("AUG-A3", R.string.weapon_name_steyraug);
            put("SV-98", R.string.weapon_name_sv98);
            put("SVD-12", R.string.weapon_name_svd12);
            put("TAURUS-44", R.string.weapon_name_taurus44);
            put("TYPE-88-LMG", R.string.weapon_name_type88);
            put("TYPE-95B-1", R.string.weapon_name_type95b1);
            put("ULTIM", R.string.weapon_name_ultim);
            put("UMP-45", R.string.weapon_name_ump45);
            put("UMP-9", R.string.weapon_name_ump9);
            put("USAS-12", R.string.weapon_name_usas12);
            put("UTAS", R.string.weapon_name_utas);
            put("ASVAL", R.string.weapon_xp0_name_asval);
            put("DAO12", R.string.weapon_xp0_name_dao12);
            put("F2000", R.string.weapon_xp0_name_f2000);
            put("GOL", R.string.weapon_xp0_name_gol);
            put("M60E4", R.string.weapon_xp0_name_m60e4);
            put("L85A2", R.string.weapon_xp1_name_l85a2);
            put("L96A1", R.string.weapon_xp1_name_l96a1);
            put("MP7", R.string.weapon_xp1_name_mp7);
            put("MTAR-21", R.string.weapon_xp1_name_mtar21);
            put("RPK-74M", R.string.weapon_xp1_name_rpk74);
            put("44-MAGNUM", R.string.weapon_name_44_magnum);
            put("UTS-15", R.string.weapon_name_uts15);
            put("U-100-MK5", R.string.weapon_name_u100_mk5);

            // INAMES
            put("ACB-90", R.string.weapon_iname_acb90);
            put("BAYONET", R.string.weapon_iname_bayonett);
            put("BEACON", R.string.weapon_iname_beacon);
            put("SCOUT", R.string.weapon_iname_bpknife2);
            put("TRENCH", R.string.weapon_iname_bpknife3);
            put("CARBON-FIBER", R.string.weapon_iname_bpknife4);
            put("SURVIVAL", R.string.weapon_iname_bpknife5);
            put("BOWIE", R.string.weapon_iname_bpknife6);
            put("SHANK", R.string.weapon_iname_shank);
            put("BOOT", R.string.weapon_iname_bpknife7);
            put("SEAL", R.string.weapon_iname_bpknife8);
            put("FGM-148", R.string.weapon_iname_fgm148);
            put("FIM-92", R.string.weapon_iname_fim92);
            put("HAND-FLARE", R.string.weapon_iname_flare);
            put("M84-FLASHBANG", R.string.weapon_iname_flashbang);
            put("IGLA", R.string.weapon_iname_igla);
            put("RGO-IMPACT", R.string.weapon_iname_impact);
            put("M136", R.string.weapon_iname_m136);
            put("M15", R.string.weapon_iname_m15);
            put("M18-SMOKE", R.string.weapon_iname_m18);
            put("M2", R.string.weapon_iname_m2);
            put("M26_FLECHETTE", R.string.weapon_iname_m26_flechette);
            put("M26_FRAG", R.string.weapon_iname_m26_frag);
            put("M26_MASS", R.string.weapon_iname_m26_mass);
            put("M26_SLUG", R.string.weapon_iname_m26_slug);
            put("M32MGL", R.string.weapon_iname_m32mgl);
            put("M34-INCENDIARY", R.string.weapon_iname_m34);
            put("M67-FRAG", R.string.weapon_iname_m67);
            put("MACHETE", R.string.weapon_iname_machete);
            put("MAV", R.string.weapon_iname_mav);
            put("MBTLAW", R.string.weapon_iname_mbtlaw);
            put("MORTAR", R.string.weapon_iname_mortar);
            put("MOTION_SENSOR", R.string.weapon_iname_motion_sensor);
            put("MPAPS", R.string.weapon_iname_mpaps);
            put("PLD", R.string.weapon_iname_pld);
            put("RPG7", R.string.weapon_iname_rpg7);
            put("SHADOW", R.string.weapon_iname_shadow);
            put("SMAW", R.string.weapon_iname_smaw);
            put("SPECOPS", R.string.weapon_iname_specops);
            put("SRAW", R.string.weapon_iname_sraw);
            put("STARSTREAK", R.string.weapon_iname_starstreak);
            put("UGS", R.string.weapon_iname_ugs);
            put("V40-MINI", R.string.weapon_iname_v40);
            put("XM25", R.string.weapon_iname_xm25);
          }
    };

    public static int get(final String key) {
        final String uppercaseKey = key.toUpperCase();
        if (map.containsKey(uppercaseKey)) {
            return map.get(uppercaseKey);
        } else {
            Log.d(WeaponStringSlugMap.class.getSimpleName(), "key => " + key);
            return R.string.na;
        }
//        return map.containsKey(key)? map.get(key) : R.string.na;
    }
}