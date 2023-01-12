package com.myapps.springbatchfilefixedwidth.step;

import com.myapps.springbatchfilefixedwidth.domain.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public StepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step fileFixedWidthStep(
        ItemReader<Customer> fileFixedWidthReader,
        ItemWriter<Customer> fileFixedWidthWriter
    ) {
        /*
        Here both the reader and writer are referencing via Spring Beans that are
        being injected into this function parameters, but it could be a normal function,
        just need to return the same type (or any that extends it) requested in the build chain
         */
        return stepBuilderFactory
            .get("fileFixedWidthStep") // Step method name
            .<Customer, Customer>chunk(4) // Reader type, writer type, and chunk size (registers being processed at once)
            .reader(fileFixedWidthReader) // Identify the reader
            .writer(fileFixedWidthWriter) // Identify the writer
            .build();
    }


}
