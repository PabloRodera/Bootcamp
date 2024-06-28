package com.Threads.ThreadsSample.controller;

import com.Threads.ThreadsSample.repository.entity.DiskEntity;
import com.Threads.ThreadsSample.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
public class ThreadsController {

    @Autowired
    private ThreadService threadService;

    @GetMapping("/disks")
    public ResponseEntity<?> getDisks() {
        try{
            return ResponseEntity.ok(threadService.findAll());
        }
        catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/disks")
    public CompletableFuture<?> addDisk(@RequestBody List<DiskEntity> diskList) {

        return threadService.save(diskList).thenApply(ResponseEntity::ok);

    }
}
