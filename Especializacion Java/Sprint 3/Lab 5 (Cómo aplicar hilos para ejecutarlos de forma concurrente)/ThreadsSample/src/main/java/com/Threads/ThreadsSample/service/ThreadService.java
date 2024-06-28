package com.Threads.ThreadsSample.service;

import com.Threads.ThreadsSample.repository.entity.DiskEntity;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ThreadService {
    CompletableFuture<List<DiskEntity>> findAll();

    CompletableFuture<List<DiskEntity>> save(List<DiskEntity> disks);
}
