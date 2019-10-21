package it.tndigit.iot.config;


import it.tndigit.iot.schedule.ScheduledTasks;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ScheduleConfig {
    @Bean
    @ConditionalOnProperty(value = "iot.cron.enable", matchIfMissing = false, havingValue = "true")
    public ScheduledTasks scheduledTasks() {
        return new ScheduledTasks();
    }


}
