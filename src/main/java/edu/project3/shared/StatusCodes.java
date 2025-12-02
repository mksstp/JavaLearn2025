package edu.project3.shared;

import java.util.HashMap;

public final class StatusCodes {

    private StatusCodes() {
    }

    static HashMap<Integer, String> codes = new HashMap<>();

    @SuppressWarnings("MagicNumber")
    private static void fillCodes() {
        codes.put(200, "OK");
        codes.put(304, "Not Modified");
        codes.put(404, "Not found");
    }

    public static String getCode(Integer code) {
        if (codes.isEmpty()) {
            fillCodes();
        }

        if (codes.get(code) != null) {
            return codes.get(code);
        } else {
            return "-";
        }
    }

}
