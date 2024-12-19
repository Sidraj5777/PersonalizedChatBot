package com.IxChatBot.Model;

public class SiteDto {

	
	private Long siteId;
    private String siteName;
    private Long statusId;
    private String statusName;
    private String hostNameValidation;

    // Constructor
    public SiteDto(Long siteId, String siteName, Long statusId, String statusName, String hostNameValidation) {
        this.siteId = siteId;
        this.siteName = siteName;
        this.statusId = statusId;
        this.statusName = statusName;
        this.hostNameValidation = hostNameValidation;
    }

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getHostNameValidation() {
		return hostNameValidation;
	}

	public void setHostNameValidation(String hostNameValidation) {
		this.hostNameValidation = hostNameValidation;
	}

	@Override
	public String toString() {
		return "SiteDto [siteId=" + siteId + ", siteName=" + siteName + ", statusId=" + statusId + ", statusName="
				+ statusName + ", hostNameValidation=" + hostNameValidation + "]";
	}
	
    
    
}
