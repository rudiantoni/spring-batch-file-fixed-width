package com.myapps.springbatchfilefixedwidth.writer;

import com.myapps.springbatchfilefixedwidth.domain.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WriterConfig {

    @Bean
    public ItemWriter<Customer> fileFixedWidthWriter() {
        // Working properly
        return items -> items.forEach(
            System.out::println // Action at each item read and already processed from the data source
        );
        // Deliberate error for job restart testing
        /*return items -> {
            for(Customer customer: items) {
                if (customer.getFirstName().equals("Maria")) {
                    throw new Exception();
                }
                else {
                    System.out.println(customer.toString());
                }
            }
        };*/

    }
}
