package Practice1.hotelapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity
@SpringBootApplication
public class HotelApp {

	public static void main(String[] args) {

        SpringApplication.run(HotelApp.class, args);
	}

}
