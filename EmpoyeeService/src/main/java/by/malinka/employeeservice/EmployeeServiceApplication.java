package by.malinka.employeeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeServiceApplication {
    static final Logger log = LoggerFactory.getLogger(EmployeeServiceApplication.class);

    public static void main(String[] args) {
        log.info("Starting application");
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }
}
