package com.skropotov.crm;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.skropotov.crm.models.Role;
import com.skropotov.crm.models.User;
import com.skropotov.crm.repositories.RoleRepository;
import com.skropotov.crm.repositories.UserRepository;


@SpringBootApplication
@ComponentScan(basePackages = "com.skropotov.crm")
@EnableJpaRepositories(basePackages = "com.skropotov.crm.repositories")
@EntityScan(basePackages = "com.skropotov.crm.models")

public class CrmApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 

    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**");
			}
		};
	}
    
	public static void main(String[] args) {
		SpringApplication.run(CrmApplication.class, args);
	}
}

/*public class CrmApplication implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setUsername("admin");
		user.setPassword(bCryptPasswordEncoder().encode("123"));
		user.setFirstName("Sergey");
		user.setLastName("Kropotov");
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findById(1L).get());
		roles.add(roleRepository.findById(2L).get());
		user.setRoles(roles);
		userRepository.save(user);
	}
	
	public static void main(String[] args) {
        SpringApplication.run(CrmApplication.class, args);
    }
	
}*/
