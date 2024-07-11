package com.example.test.service.impl;

import com.example.test.entity.DemoEntity;
import com.example.test.respository.DemoRepository;
import com.example.test.service.DemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {

    private final DemoRepository demoRepository;

    @Override
    public String hello() {
        log.info("Hello from service");
        DemoEntity entity = demoRepository.findByName("Pablo");
        return "Hello" + entity.getName();
    }
}
