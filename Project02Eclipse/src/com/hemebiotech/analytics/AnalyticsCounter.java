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

/**
 * read the list of symptoms and write a sorted list of symptoms with the
 * curencys of curencys
 * 
 * @author jc
 *
 */
public class AnalyticsCounter {
	/**
	 * create a file reader
	 */
	private ISymptomReader reader = new ReadSymptomDataFromFile("symptoms.txt");

	/**
	 * read the symtoms.txt file and send it on a list of strings
	 * 
	 * @return List String
	 */
	private List<String> read() {
		return reader.getSymptoms();

	}

	/**
	 * get the list of strings and returns it on a treeMap ordered by letter and
	 * with the number of each symptoms
	 * 
	 * @param symptoms list of the symptoms
	 * @return Treemap <String, Integer>
	 */

	private TreeMap<String, Integer> comput(List<String> symptoms) {
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

	/**
	 * write the treemap ( with all the symptoms ordered and with the numbers of
	 * each symptom )on a file ( result.txt)
	 * 
	 * @param list     list of sorted symptoms
	 * @param fileName name of the target file
	 */
	private void writeSymptoms(TreeMap<String, Integer> list, String fileName) {
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

				bw.write(mentry.getKey() + "=" + mentry.getValue());
				bw.newLine();
			}

			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) throws Exception {

		ReadSymptomDataFromFile reader = new ReadSymptomDataFromFile("symptoms.txt");
		AnalyticsCounter analyticsCounter = new AnalyticsCounter();
		/*
		 * send the result.txt fil into a list of String, send the list into a treemap
		 * to order it, write the treemap into a file result.out.txt
		 */
		analyticsCounter.writeSymptoms(analyticsCounter.comput(reader.getSymptoms()), "results.out.txt");

	}
}
