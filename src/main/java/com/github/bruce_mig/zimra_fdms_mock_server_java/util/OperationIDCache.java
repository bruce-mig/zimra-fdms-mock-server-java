package com.github.bruce_mig.zimra_fdms_mock_server_java.util;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * Class  that uses a BlockingQueue to cache a batch of operation IDs
 * */
@Component
public class OperationIDCache {

    private final BlockingQueue<String> idPool;
    private final int poolSize;
    private final ScheduledExecutorService scheduler;

    public OperationIDCache() {

        this.poolSize = 100;
        this.idPool = new ArrayBlockingQueue<>(poolSize);
        this.scheduler = Executors.newSingleThreadScheduledExecutor();

        // Pre-generate the initial pool
        generateBatch(poolSize);

        // Schedule periodic replenishment of the pool
        scheduler.scheduleAtFixedRate(()->{
            int currentSize = idPool.size();
            if (currentSize < poolSize / 2) {
                generateBatch(poolSize - currentSize);
            }
        },5,5, TimeUnit.SECONDS);
    }

    private void generateBatch(int count) {
        for (int i = 0; i < count; i++) {
            idPool.offer(OperationIDGenerator.generateOperationID());
        }
    }

    public String getOperationID() {
        String id = idPool.poll();
        if (id == null) {
            id = OperationIDGenerator.generateOperationID();
        }
        return id;
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ex) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    @PreDestroy
    public void cleanup() {
        // This method will be called when the Spring context is shutting down.
        shutdown();
    }
}
