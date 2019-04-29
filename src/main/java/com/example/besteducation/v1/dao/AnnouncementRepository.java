package com.example.besteducation.v1.dao;

import com.example.besteducation.v1.domain.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {
    Announcement save(Announcement announcement);
}
