package org.example.store.app;

import org.example.store.config.ApplicationConfig;
import org.example.store.processor.IOProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Class with main method, point of entry
 */
public class Store {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        IOProcessor processor = context.getBean(IOProcessor.class);
        processor.run();
    }
}
