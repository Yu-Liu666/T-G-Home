package com.example.besteducation.v1.Service;

import com.example.besteducation.v1.dao.AnnouncementRepository;
import com.example.besteducation.v1.domain.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;
    @Override
    public Announcement save(Announcement announcement) {
        return announcementRepository.save(announcement);
    }
}
