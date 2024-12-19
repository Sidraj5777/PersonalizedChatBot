package com.IxChatBot.ServiceImpl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.IxChatBot.Constants.SiteStatusConstants;
import com.IxChatBot.Dao.MainDao;
import com.IxChatBot.Model.SiteDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SqlServiceImpl {

	@Autowired
	private MainDao mainRepo;

	// This map will store the data, to be updated every hour
	private Map<String, List<String>> sqlDataMap;

	// Constructor to initialize the map initially, if needed
	public SqlServiceImpl() {
		this.sqlDataMap = new HashMap<>();
	}

	public List<SiteDto> getSiteDetails(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return mainRepo.fetchSiteDetails(pageable);
	}

	// Scheduled task that will run every hour
	@Scheduled(fixedRate = 3600000) // 1 hour in milliseconds
	public void loadDataFromSQL() {
		Map<String, List<String>> siteMap = new HashMap<>();

		try {
			// Fetch the data every hour
			List<SiteDto> sites = getSiteDetails(0, 300);

			
			// Process the data and populate the map
			for (SiteDto site : sites) {
//				System.out.println("status id & and name -- > "+site.getStatusId() +" -  - "+site.getStatusName() +" -- "+site.getSiteName());
			    // Handle statusId mapping
			    String key;
			    int siteId = site.getStatusId().intValue();
			    switch (siteId) {
			        case SiteStatusConstants.APPROVED:
			            key = "approved_sites";
			            break;
			        case SiteStatusConstants.REJECTED:
			            key = "rejected_sites";
			            break;
			        case SiteStatusConstants.UNDER_REVIEW:
			            key = "under_review_sites";
			            break;
			        case SiteStatusConstants.SUSPENDED:
			            key = "suspended_sites";
			            break;
			        case SiteStatusConstants.INVALID:
			            key = "invalid_sites";
			            break;
			        case SiteStatusConstants.COMPLETED:
			            key = "completed_sites";
			            break;
			        case SiteStatusConstants.MAILSENT:
			            key = "mailsent_sites";
			            break;
			        case SiteStatusConstants.ACTIVE:
			            key = "active_sites";
			            break;
			        case SiteStatusConstants.IN_PROCESS:
			            key = "inprocess_sites";
			            break;
			        case SiteStatusConstants.CANCELED:
			            key = "canceled_sites";
			            break;
			        case SiteStatusConstants.PENDING:
			            key = "pending_sites";
			            break;
			        case SiteStatusConstants.INACTIVE:
			            key = "inactive_sites";
			            break;
			        case SiteStatusConstants.HOLD:
			            key = "hold_sites";
			            break;
			        default:
			            key = "unknown_status";
			    }
			    // Add site name to the map for the status
			    siteMap.computeIfAbsent(key, k -> new ArrayList<String>()).add(site.getSiteName());

			    // Handle hostNameValidation mapping
			    String hostValidationKey = "1".equals(site.getHostNameValidation())
			        ? "hostNameValidationOn_sites"
			        : "hostNameValidationOf_sites";
			    siteMap.computeIfAbsent(hostValidationKey, k -> new ArrayList<>()).add(site.getSiteName());
			}

//				map.put("site_" + site.getSiteId(), site.getSiteName());
			

			this.sqlDataMap = siteMap;

			// Print the updated map for verification (optional)
			System.out.println("Data loaded successfully: " + sqlDataMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Map<String, List<String>> getSqlDataMap() {
		return sqlDataMap;
	}
}
