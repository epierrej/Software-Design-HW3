import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.cli.*;
import org.apache.commons.cli.Option.Builder;
import java.util.Collections;

public class MyList {
	public MyList() {

	}

	public static void main(String[] args) {
		Comparable key;
		Comparable aList[];
		ArrayList<String> stringList = new ArrayList<String>();
		ArrayList<Integer> intList = new ArrayList<Integer>();
		Options options = new Options();
		Option opt1 = new Option("t", "type", true, "input type");
		Option opt2 = new Option("k", "key", true, "search value");
		Option opt3 = new Option("l", "list", true, "array list");

		opt3.setArgs(Option.UNLIMITED_VALUES);

		options.addOption(opt1);
		options.addOption(opt2);
		options.addOption(opt3);

		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);
			// If the list exists
			if (cmd.hasOption("list")) {

				aList = cmd.getOptionValues("list");

				// If the datatype is Integer
				if (cmd.getOptionValue("type").equals("i")) {
					// Fill out integer array
					for (int i = 0; i < aList.length; i++) {
						intList.add(Integer.parseInt((String) aList[i]));
						// System.out.println(intList.get(i));
					}
					// Assign Key
					key = cmd.getOptionValue("key");
					Collections.sort(intList);

					// do binary search with integers
					System.out.println(binSearch(null, intList, key));
				}

				// If the datatype is String
				if (cmd.getOptionValue("type").equals("s")) {

					// Fill out String array
					for (int i = 0; i < aList.length; i++) {
						stringList.add((String) aList[i]);
					}
					key = cmd.getOptionValue("key");
					Collections.sort(stringList, String.CASE_INSENSITIVE_ORDER);

					// Do binary search with strings
					System.out.println(binSearch(stringList, null, key));

				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	static int binSearch(ArrayList<String> stringList, ArrayList<Integer> intList, Comparable key) {
		if (intList == null) {
			String stringKey = (String) key;

			int min = 0;
			int max = stringList.size() - 1;
			int mid;
			while (min <= max) {
				mid = (min + max) / 2;
				if (stringList.get(mid).compareTo(stringKey) < 0) {
					min = mid + 1;
				} else if (stringList.get(mid).compareTo(stringKey) > 0) {
					max = mid - 1;
				} else if (stringList.get(mid).compareTo(stringKey) == 0) {
					return 1;
				}
			}
		}
		if (stringList == null) {

			int intKey = Integer.parseInt((String) key);

			int min = 0;
			int max = intList.size() - 1;
			int mid;
			while (min <= max) {
				mid = (min + max) / 2;
				if (intList.get(mid).compareTo(intKey) < 0) {
					min = mid + 1;
				} else if (intList.get(mid).compareTo(intKey) > 0) {
					max = mid - 1;
				} else if (intList.get(mid).compareTo(intKey) == 0) {
					return 1;
				}
			}
		}

		return 0;
	}
}
