package com.IxChatBot.Dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.IxChatBot.Model.SiteDto;
import com.IxChatBot.Model.tblSites;

@Repository
public interface MainDao extends JpaRepository<tblSites, Long> {

    // Pagination handled by Spring Data JPA, no need for manual Pageable in @Query
    @Query("SELECT new com.IxChatBot.Model.SiteDto(s.siteId, s.siteName, s.statusId, st.statusName, s.hostNameValidation) "
            + "FROM tblSites s "
            + "JOIN s.status st "
            + "ORDER BY s.siteId")
    List<SiteDto> fetchSiteDetails(Pageable pageable);
}
