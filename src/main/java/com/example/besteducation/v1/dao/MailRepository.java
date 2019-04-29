package com.example.besteducation.v1.dao;

import com.example.besteducation.v1.domain.MailMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailRepository extends JpaRepository<MailMessage,Long> {
    MailMessage save(MailMessage mailMessage);
}
