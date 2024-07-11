package com.example.test.service;

import com.example.test.entity.DemoEntity;
import com.example.test.respository.DemoRepository;
import com.example.test.service.impl.DemoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DemoServiceImplTest {
    @InjectMocks
    private DemoServiceImpl demoServiceImpl;

    @Mock
    DemoRepository demoRepository;

    @Test
    void test_hello() {
        DemoEntity entity = new DemoEntity();
        entity.setName("Pablo");
        when(demoRepository.findByName("Pablo")).thenReturn(entity);

        String result = demoServiceImpl.hello();
        assertEquals(result, "Hello Pablo");
    }
}
