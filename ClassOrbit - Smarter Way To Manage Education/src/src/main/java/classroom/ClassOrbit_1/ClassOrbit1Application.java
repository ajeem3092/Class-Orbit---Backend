package classroom.ClassOrbit_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "classroom.entities")
//@ComponentScan(basePackages = {"classroom.Service", "classroom.controller"})
@ComponentScan(basePackages = "classroom")
@EnableJpaRepositories("classroom.repository")
public class ClassOrbit1Application {

	
	public static void main(String[] args) {
		SpringApplication.run(ClassOrbit1Application.class, args);
		String s1 = null;
		if ("email".equals(s1)) {
			System.out.println("Hello");
		} else {
			System.out.println("end");
		}
	}
}
