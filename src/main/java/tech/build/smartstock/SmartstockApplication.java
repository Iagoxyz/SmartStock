package tech.build.smartstock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "tech.build.smartstock.client")
@SpringBootApplication
public class SmartstockApplication  {

	public static void main(String[] args) {
		SpringApplication.run(SmartstockApplication.class, args);
	}

}
