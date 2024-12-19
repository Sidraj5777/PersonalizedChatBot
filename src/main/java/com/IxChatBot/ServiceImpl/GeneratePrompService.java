package com.IxChatBot.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneratePrompService extends DataLoadingServiceImpl {

	@Autowired
	SqlServiceImpl sqlMapGetter;

	public String generatePrompt(String userQuery) {

		System.out.println("map with value -- = > " + sqlMapGetter.getSqlDataMap());


		if (userQuery.toLowerCase().contains("active sites") || userQuery.toLowerCase().contains("sites are active")) {

			return "The following sites are active: " + sqlMapGetter.getSqlDataMap().get("active_sites") + ".";

		} else if (userQuery.toLowerCase().contains("revenue today")
				|| userQuery.toLowerCase().contains("today's revenue")) {
			return "The total revenue for today is " + dataMap.get("revenue_today") + ".";

		} else if (userQuery.toLowerCase().contains("impressions for")
				|| userQuery.toLowerCase().contains("impression for")) {
			String siteName = extractSiteName(userQuery);
			String impressions = dataMap.get("impressions_" + siteName.toLowerCase());
			return impressions != null ? "The total impressions for " + siteName + " are " + impressions + "."
					: "Impression data for " + siteName + " is not available.";

		} else if (userQuery.toLowerCase().contains("approved sites")
				|| userQuery.toLowerCase().contains("sites are approved")) {
			return "The following sites are approved: " + sqlMapGetter.getSqlDataMap().get("approved_sites") + ".";

		}else if (userQuery.toLowerCase().contains("rejected sites")
				|| userQuery.toLowerCase().contains("sites are rejected")) {
			return "The following sites are rejected: " + sqlMapGetter.getSqlDataMap().get("rejected_sites") + ".";

		} else if (userQuery.toLowerCase().contains("inactive sites")
				|| userQuery.toLowerCase().contains("sites are inactive")) {
			return "The following sites are Inactive: " + sqlMapGetter.getSqlDataMap().get("inactive_sites") + ".";

		}else if (userQuery.toLowerCase().contains("under review sites")
				|| userQuery.toLowerCase().contains("sites are under review")) {
			return "The following sites are under review: " + sqlMapGetter.getSqlDataMap().get("under_review_sites") + ".";

		}else if (userQuery.toLowerCase().contains("suspended sites")
				|| userQuery.toLowerCase().contains("sites are suspended")) {
			return "The following sites are suspended: " + sqlMapGetter.getSqlDataMap().get("suspended_sites") + ".";

		} else if (userQuery.toLowerCase().contains("completed sites")
				|| userQuery.toLowerCase().contains("sites are completed")) {
			return "The following sites are completed: " + sqlMapGetter.getSqlDataMap().get("completed_sites") + ".";

		}else if (userQuery.toLowerCase().contains("mailsent sites")
				|| userQuery.toLowerCase().contains("sites mailsent")) {
			return "The following sites are mailsent: " + sqlMapGetter.getSqlDataMap().get("mailsent_sites") + ".";

		}else if (userQuery.toLowerCase().contains("inprocess sites")
				|| userQuery.toLowerCase().contains("sites are inprocess")) {
			return "The following sites are inprocess: " + sqlMapGetter.getSqlDataMap().get("inprocess_sites") + ".";

		}else if (userQuery.toLowerCase().contains("canceled sites")
				|| userQuery.toLowerCase().contains("sites are canceled")) {
			return "The following sites are canceled: " + sqlMapGetter.getSqlDataMap().get("canceled_sites") + ".";

		}else if (userQuery.toLowerCase().contains("pending sites")
				|| userQuery.toLowerCase().contains("sites are pending")) {
			return "The following sites are pending: " + sqlMapGetter.getSqlDataMap().get("pending_sites") + ".";

		}else if (userQuery.toLowerCase().contains("sites are on hold")
				|| userQuery.toLowerCase().contains("sites on hold")) {
			return "The following are the sites on hold: " + sqlMapGetter.getSqlDataMap().get("hold_sites") + ".";

		}else {

			return "I'm sorry, I couldn't understand your question.";
		}
	}

	private String extractSiteName(String query) {
		return query.split("for")[1].trim();
	}
}
