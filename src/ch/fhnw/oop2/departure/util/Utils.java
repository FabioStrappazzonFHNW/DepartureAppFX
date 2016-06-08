package ch.fhnw.oop2.departure.util;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ernst on 26.04.2016.
 */

public class Utils {
    /**
     * Returns the WHOLE CSV as a List<List<String>>, first entry are headers of the csv
     *
     * @param file
     * @param seperator
     * @return List<List<String>>
     */
    public static List<List<String>> readCSV(File file, String seperator) {
        try {
            return Files.lines(file.toPath(), StandardCharsets.UTF_8)
                    .map(line -> Arrays.asList(line.split(seperator)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Create a csv String with a given seperator
     *
     * @param data
     * @param seperator
     * @return String
     */
    public static String createCSVString(List<List<String>> data, final String seperator) {
        List<String> csv = new ArrayList<>();
        data.forEach(line ->
                csv.add(line.stream().
                        reduce((t, u) -> t + seperator + u).
                        get() + seperator)
        );
        StringBuilder csvString = new StringBuilder();
        for (String line : csv) {
        	csvString.append(line);
        	csvString.append("\n");
        }
        return csvString.toString();
    }
}
