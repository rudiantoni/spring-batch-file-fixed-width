package com.myapps.springbatchfilefixedwidth.reader;

import com.myapps.springbatchfilefixedwidth.domain.Customer;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ReaderConfig {

    @StepScope
    @Bean
    public FlatFileItemReader<Customer> fileFixedWidthReader(
        @Value("#{jobParameters['customerFile']}") Resource customerFile
    ) {
        return new FlatFileItemReaderBuilder<Customer>()
            .name("fileFixedWidthReader") // Reader method name
            .resource(customerFile) // Used resource (local filesystem file path)
            .fixedLength() // Resource type: fixed width
            .columns(new Range[]{ // File columns start and end index, based in index 1
                new Range(1, 10), // firstName
                new Range(11, 20), // lastName
                new Range(21, 23), // age
                new Range(24, 43), // email
            })
            /*
            The names() instruction needs the property names from the domain class (Customer)
            Note that in order to match the column range, they need to follow the same order as defined in columns().
            You can use varargs rather than String[].
             */
            .names(new String[]{"firstName", "lastName", "age", "email"})
            .targetType(Customer.class) // Domain class, the read data will be converted to properties from this class
            .build();
    }
}
