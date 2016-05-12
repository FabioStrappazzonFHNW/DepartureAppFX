package ch.fhnw.oop2.departure.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ernst on 26.04.2016.
 */
public class Utils {
	public static List<List<String>> readCSV(File file, String seperator) {
		try {
			return Files.lines(file.toPath(), StandardCharsets.UTF_8)
					.skip(1)
					.map(line -> Arrays.asList(line.split(seperator)))
					.collect(Collectors.toList());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
