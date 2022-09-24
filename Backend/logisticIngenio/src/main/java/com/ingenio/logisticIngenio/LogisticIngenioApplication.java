package com.ingenio.logisticIngenio;

import com.ingenio.logisticIngenio.Exceptions.CustomException;
import com.ingenio.logisticIngenio.models.AppUser;
import com.ingenio.logisticIngenio.models.AppUserRole;
import com.ingenio.logisticIngenio.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class LogisticIngenioApplication  implements CommandLineRunner {


	final UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(LogisticIngenioApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... params)  {
//		try{
//			AppUser admin = new AppUser();
//			admin.setUsername("admin");
//			admin.setPassword("admin");
//			admin.setEmail("admin@email.com");
//			admin.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_ADMIN)));
//
//			userService.signup(admin);
//
//			AppUser client = new AppUser();
//			client.setUsername("client");
//			client.setPassword("client");
//			client.setEmail("client@email.com");
//			client.setAppUserRoles(new ArrayList<AppUserRole>(Arrays.asList(AppUserRole.ROLE_CLIENT)));
//
//			userService.signup(client);
//		}catch (CustomException e){
//			System.out.println(e.getMessage());
//		}
	}
}
