package com.hemebiotech.analytics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AnalyticsCounter {
	/*
	 * private static int headacheCount = 0; private static int rashCount = 0;
	 * private static int pupilCount = 0;
	 */
	private ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");

	// read the symtoms.txt file and send it on a list of strings
	private List<String> read() {
		return reader.getSymptoms();

	}

// get the list of strings and returns it on a treeMap ordered by letter and with the  number of each symptoms

	private static TreeMap<String, Integer> comput(List<String> symptoms) {
		TreeMap<String, Integer> result = new TreeMap<String, Integer>();
		for (String symptom : symptoms) {
			if (result.containsKey(symptom)) {
				int i = result.get(symptom) + 1;
				result.put(symptom, i);
			} else {
				result.put(symptom, 1);
			}
		}

		return result;
	}

// write the treemap  ( with all the symtoms ordered and with the numbers of each symptom )on a file ( result.txt)
	private static void writeSymptoms(TreeMap<String, Integer> list, String fileName) {
		File file = new File(fileName);
		try {
			FileWriter writer = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(writer);
			// get the whole entrys
			Set set = list.entrySet();

			// create an iterator to browse the treemap
			Iterator it = set.iterator();

			// write into the result.txt file what is into the treemap
			while (it.hasNext()) {

				Map.Entry mentry = (Map.Entry) it.next();
				System.out.print(mentry.getKey());
				System.out.println("," + mentry.getValue());
				bw.write(mentry.getKey() + "," + mentry.getValue());
				bw.newLine();
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws Exception {
		// first get input
		// instance of ReadSymtomDataFromFIle

		ReadSymptomDataFromFile reader = new ReadSymptomDataFromFile("symptoms.txt");
		/*
		 * send the result.txt fil into a list of String, send the list into a treemap
		 * to order it, write the treemap into a file result.txt
		 */
		writeSymptoms(comput(reader.getSymptoms()), "result.txt");

		/*
		 * BufferedReader reader = new BufferedReader(new FileReader("symptoms.txt"));
		 * String line = reader.readLine();
		 * 
		 * int i = 0; // set i to 0 int headCount = 0; // counts headaches while (line
		 * != null) { i++; // increment i System.out.println("symptom from file: " +
		 * line); if (line.equals("headache")) { headacheCount++;
		 * System.out.println("number of headaches: " + headCount); } else if
		 * (line.equals("rush")) { rashCount++; } else if (line.contains("pupils")) {
		 * pupilCount++; }
		 * 
		 * line = reader.readLine(); // get another symptom }
		 * 
		 * // next generate output FileWriter writer = new FileWriter("result.out");
		 * writer.write("headache: " + headacheCount + "\n"); writer.write("rash: " +
		 * rashCount + "\n"); writer.write("dialated pupils: " + pupilCount + "\n");
		 * writer.close();
		 */
	}
}
