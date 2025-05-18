package yys.SoundAlley;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SoundAlleyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SoundAlleyApplication.class, args);
	}

}
