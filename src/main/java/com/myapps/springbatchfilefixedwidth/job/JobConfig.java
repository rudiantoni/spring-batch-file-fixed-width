package com.myapps.springbatchfilefixedwidth.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class JobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    public JobConfig(JobBuilderFactory jobBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public Job fileFixedWidthJob(Step fileFixedWidthStep) {
        return jobBuilderFactory
            .get("fileFixedWidthJob")
            .start(fileFixedWidthStep)
            /*
            The .incrementer(new RunIdIncrementer()) line allows the same job to run multiple times when using the same
            application execution parameters. But there's no execution failure management like restart, stop and
            re-run.
            */
            //.incrementer(new RunIdIncrementer())
            .build();
    }

}
