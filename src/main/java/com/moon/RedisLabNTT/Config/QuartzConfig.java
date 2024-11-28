package com.moon.RedisLabNTT.Config;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.moon.RedisLabNTT.Job.RedisSyncJob;

@Configuration
public class QuartzConfig {
    
    @Value("${scheduler.redisSyncJobInterval}")
    private long jobIntervalInMilliseconds;
    
    @Bean
    JobDetail redisSyncJobDetail() {
        return JobBuilder.newJob(RedisSyncJob.class)
                .withIdentity("redisSyncJob")
                .storeDurably()
                .build();
    }

    @Bean
    Trigger redisSyncJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(redisSyncJobDetail())
                .withIdentity("redisSyncTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInMilliseconds(jobIntervalInMilliseconds)
                        .repeatForever())
                .build();
    }
}