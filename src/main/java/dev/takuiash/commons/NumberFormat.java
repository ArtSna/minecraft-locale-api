package dev.takuiash.commons;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NumberFormat {
	
    private static final NavigableMap<Double, String> suffixes = new TreeMap<>();
    static {
        suffixes.put(1_000D, "k");
        suffixes.put(1_000_000D, "M");
        suffixes.put(1_000_000_000D, "B");
        suffixes.put(1_000_000_000_000D, "T");
        suffixes.put(1_000_000_000_000_000D, "Q");
        suffixes.put(1_000_000_000_000_000_000D, "QQ");
        suffixes.put(1_000_000_000_000_000_000_000D, "S");
        suffixes.put(1_000_000_000_000_000_000_000_000D, "SS");
        suffixes.put(1_000_000_000_000_000_000_000_000_000D, "O");
        suffixes.put(1_000_000_000_000_000_000_000_000_000_000D, "N");
    }

    public static String format(double value) {
        if (value == Long.MIN_VALUE) return format(Double.MIN_VALUE + 1);
        if (value < 1000) return "R$ " + String.format("%,.2f", value);

        Map.Entry<Double, String> e = suffixes.floorEntry(value);
        Double divideBy = e.getKey();

        double truncated = value / (divideBy / 10); 

        String suffix = e.getValue();

        return String.format("%,.2f", (truncated / 10))  + suffix;
    }
}