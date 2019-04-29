package com.example.besteducation.v1.Service;

import com.example.besteducation.v1.dao.MailRepository;
import com.example.besteducation.v1.domain.MailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService{

    @Autowired
    private MailRepository mailRepository;

    @Override
    public MailMessage save(MailMessage mailMessage) {
        MailMessage m = mailRepository.save(mailMessage);
        return m;
    }
}
