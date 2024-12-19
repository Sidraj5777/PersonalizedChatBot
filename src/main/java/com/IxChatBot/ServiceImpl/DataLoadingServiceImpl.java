package com.IxChatBot.ServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DataLoadingServiceImpl {
	private static final String FILE_PATH = "/home2/config/ixChatBotData.txt";
	public Map<String, String> dataMap;

	public DataLoadingServiceImpl() {
		this.dataMap = loadDataFromFile();
	}

	private Map<String, String> loadDataFromFile() {

		Map<String, String> map = new HashMap<>();
		try {
			String content = Files.readString(Paths.get(FILE_PATH));
			String[] sections = content.split("# ");

			for (String section : sections) {
				if (section.startsWith("Active Sites")) {
					map.put("active_sites", parseSection(section, "active_sites"));
				} else if (section.startsWith("Daily Revenue")) {
					map.put("revenue_today", parseValue(section, "revenue_today"));
				} else if (section.startsWith("Inactive Sites")) {
					map.put("inactive_sites", parseSection(section, "inactive_sites"));
				} else if (section.startsWith("Impressions Data")) {
					String[] lines = section.split("\n");
					for (String line : lines) {
						if (line.contains(":")) {
							String[] parts = line.split(":");
							if (parts.length == 2) { // Check if split produced two parts
								String site = parts[0].trim().toLowerCase().replace("- ", "");
								String impressions = parts[1].trim();
								map.put("impressions_" + site, impressions);
							}
						}
					}
				}
			}

			System.out.println("map after reading -- > " + map.toString());

		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	private String parseSection(String section, String key) {
		String[] lines = section.split("\n");
		StringBuilder value = new StringBuilder();
		boolean isKey = false;
		for (String line : lines) {
			if (line.contains(key + ":"))
				isKey = true;
			else if (isKey && line.startsWith("-"))
				value.append(line.trim().substring(2)).append(", ");
		}
		return value.toString().replaceAll(", $", ""); // Remove last comma
	}

	private String parseValue(String section, String key) {
		for (String line : section.split("\n")) {
			if (line.contains(key + ":")) {
				return line.split(":")[1].trim();
			}
		}
		return "";
	}
}
