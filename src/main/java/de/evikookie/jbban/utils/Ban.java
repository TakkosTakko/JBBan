package de.evikookie.jbban.utils;

import java.util.ArrayList;
import java.util.List;

public enum Ban {
    SECOND("Sekunde(n)", 1, "sec"),
    MINUTE("Minute(n)", 60, "min"),
    HOUR("Stunde(n)", 3600, "hour"),
    DAY("Tag(e)", 86400, "day"),
    WEEK("Woche(n)", 604800, "week");

    private String name;

    private int toSecond;

    private String shortcut;

    Ban(String name, int toSecond, String shortcut) {
        this.name = name;
        this.toSecond = toSecond;
        this.shortcut = shortcut;
    }

    public int getToSecond() {
        return this.toSecond;
    }

    public String getName() {
        return this.name;
    }

    public String getShortcut() {
        return this.shortcut;
    }

    public static List<String> getUnitsAsString() {
        List<String> units = new ArrayList<>();
        byte b;
        int i;
        Ban[] arrayOfBanUnit;
        for (i = (arrayOfBanUnit = values()).length, b = 0; b < i; ) {
            Ban unit = arrayOfBanUnit[b];
            units.add(unit.getShortcut().toLowerCase());
            b++;
        }
        return units;
    }

    public static Ban getUnit(String unit) {
        byte b;
        int i;
        Ban[] arrayOfBanUnit;
        for (i = (arrayOfBanUnit = values()).length, b = 0; b < i; ) {
            Ban units = arrayOfBanUnit[b];
            if (units.getShortcut().toLowerCase().equals(unit.toLowerCase()))
                return units;
            b++;
        }
        return null;
    }
}
