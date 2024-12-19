package com.IxChatBot.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_status")
public class TblStatus {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "status")
    private String statusName;

    public TblStatus() {}

    public TblStatus(Long statusId, String statusName) {
        this.id = statusId;
        this.statusName = statusName;
    }

    public Long getStatusId() {
        return id;
    }

    public void setStatusId(Long statusId) {
        this.id = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "TblStatusDto{" +
                "statusId=" + id +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
