package com.skyobserver.schedulers;

import com.skyobserver.repository.BaggageRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.skyobserver.config.ServerConfiguration.BAGGAGE_SCHEDULE_CRON_EXPRESSION;

@Component
public class BaggageUpdateScheduler {

    private BaggageRepository baggageRepository = new BaggageRepository();

    @Scheduled(cron = BAGGAGE_SCHEDULE_CRON_EXPRESSION)
    public void scheduleUpdating() throws IOException {
        baggageRepository.initializeRepository();
    }
}
