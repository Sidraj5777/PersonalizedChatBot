package com.IxChatBot.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "tbl_sites")
public class tblSites {
	@Id
	@Column(name = "entry_id")
	private Long siteId;

	@Column(name = "site_name")
	private String siteName;

	@Column(name = "status")
	private Long statusId;

	@Column(name = "host_name_validation")
	private String hostNameValidation;

	@ManyToOne
	@JoinColumn(name = "status", referencedColumnName = "id", insertable = false, updatable = false)
	private TblStatus status;

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

	public String getHostNameValidation() {
		return hostNameValidation;
	}

	public void setHostNameValidation(String hostNameValidation) {
		this.hostNameValidation = hostNameValidation;
	}

	public TblStatus getStatus() {
		return status;
	}

	public void setStatus(TblStatus status) {
		this.status = status;
	}

}
