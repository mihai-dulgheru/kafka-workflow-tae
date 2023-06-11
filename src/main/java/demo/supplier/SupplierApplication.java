package demo.supplier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "demo", "demo.supplier" })
public class SupplierApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "supplier");
		SpringApplication.run(SupplierApplication.class, args);
	}

}
