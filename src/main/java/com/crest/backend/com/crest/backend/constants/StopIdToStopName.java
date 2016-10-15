package com.crest.backend.com.crest.backend.constants;

import java.util.HashMap;
import java.util.Map;

public class StopIdToStopName {
    public static Map<String, String> stopIdToStopName = new HashMap<>();

    static {
        stopIdToStopName.put("busStop-55", "DE ANZA & STEVENS CREEK");
        stopIdToStopName.put("busStop-60", "OLD IRONSIDES & TASMAN");
        stopIdToStopName.put("busStop-181", "GREAT MALL / MAIN TRANSIT CENTER");
    }

}
