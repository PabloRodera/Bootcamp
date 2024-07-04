package com.prodera.CarRegistry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Número mínimo de hilos concurrentes
        executor.setMaxPoolSize(10); // Número máximo de hilos
        executor.setQueueCapacity(100); // Capacidad de la cola de tareas
        executor.setThreadNamePrefix("Async-"); // Prefijo para los nombres de los hilos
        executor.initialize();
        return executor;
    }
}
