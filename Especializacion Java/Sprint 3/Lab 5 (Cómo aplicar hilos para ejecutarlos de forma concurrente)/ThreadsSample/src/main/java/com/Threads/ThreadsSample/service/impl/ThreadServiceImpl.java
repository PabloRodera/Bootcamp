package com.Threads.ThreadsSample.service.impl;

import com.Threads.ThreadsSample.repository.DiskRepository;
import com.Threads.ThreadsSample.repository.entity.DiskEntity;
import com.Threads.ThreadsSample.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ThreadServiceImpl implements ThreadService {

    @Autowired
    private DiskRepository diskRepository;
    @Override
    @Async
    public CompletableFuture<List<DiskEntity>> findAll(){
        long startTime = System.currentTimeMillis();
        List<DiskEntity> disks = diskRepository.findAll();


        long endTime = System.currentTimeMillis();
        log.info("Total time: " + (endTime-startTime));
        return CompletableFuture.completedFuture(disks);
    }

    @Override
    @Async
    public CompletableFuture<List<DiskEntity>> save(List<DiskEntity> disks){
        long startTime = System.currentTimeMillis();
        List<DiskEntity> disksList = diskRepository.findAll();
        long endTime = System.currentTimeMillis();
        log.info("Total time: " + (endTime-startTime));
        return CompletableFuture.completedFuture(disksList);
    }
}
