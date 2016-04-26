package ch.fhnw.oop2.departure.util;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ernst on 26.04.2016.
 */
public class Utils {
    public static List<List<String>> readCSV(File f, String seperator) {
        try (BufferedReader reader = new BufferedReader(new FileReader(f.getPath()))) {
            return reader.lines().skip(1)
                    .map(line -> Arrays.asList(line.split(seperator)))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
