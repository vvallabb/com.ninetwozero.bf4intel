package com.ninetwozero.bf4intel.jsonmodels.battlefeed.generators;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ninetwozero.bf4intel.datatypes.EventType;
import com.ninetwozero.bf4intel.interfaces.EventGenerator;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.BaseEvent;
import com.ninetwozero.bf4intel.jsonmodels.battlefeed.events.UnknownEvent;

public class UnknownEventGenerator implements EventGenerator {
    @Override
    public BaseEvent generate(final Gson gson, final JsonObject jsonObject) {
        return new UnknownEvent(EventType.UNKNOWN);
    }
}
