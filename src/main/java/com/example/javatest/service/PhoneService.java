package com.example.javatest.service;

import com.example.javatest.model.Phone;
import com.example.javatest.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public Phone createPhone(Phone phone) {
        return phoneRepository.save(phone);
    }
}
