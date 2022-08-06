package com.api.produc.sup;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;




@SpringBootApplication
public class ApiRestMicroFrontEndProductosSupApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(ApiRestMicroFrontEndProductosSupApplication.class);
		
		app.setBanner(new Banner() {

			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				 out.print("\n\n\t\n"
				 		+ "           _____ _____   __  __ _____ _____ _____   ____    ______ _____   ____  _   _ _______   _____  _____   ____  _____  _    _  _____ _______ ____   _____    _____ _    _ _____  ______ _____  __  __ ______ _____   _____          _____   ____  \n"
				 		+ "     /\\   |  __ \\_   _| |  \\/  |_   _/ ____|  __ \\ / __ \\  |  ____|  __ \\ / __ \\| \\ | |__   __| |  __ \\|  __ \\ / __ \\|  __ \\| |  | |/ ____|__   __/ __ \\ / ____|  / ____| |  | |  __ \\|  ____|  __ \\|  \\/  |  ____|  __ \\ / ____|   /\\   |  __ \\ / __ \\ \n"
				 		+ "    /  \\  | |__) || |   | \\  / | | || |    | |__) | |  | | | |__  | |__) | |  | |  \\| |  | |    | |__) | |__) | |  | | |  | | |  | | |       | | | |  | | (___   | (___ | |  | | |__) | |__  | |__) | \\  / | |__  | |__) | |       /  \\  | |  | | |  | |\n"
				 		+ "   / /\\ \\ |  ___/ | |   | |\\/| | | || |    |  _  /| |  | | |  __| |  _  /| |  | | . ` |  | |    |  ___/|  _  /| |  | | |  | | |  | | |       | | | |  | |\\___ \\   \\___ \\| |  | |  ___/|  __| |  _  /| |\\/| |  __| |  _  /| |      / /\\ \\ | |  | | |  | |\n"
				 		+ "  / ____ \\| |    _| |_  | |  | |_| || |____| | \\ \\| |__| | | |    | | \\ \\| |__| | |\\  |  | |    | |    | | \\ \\| |__| | |__| | |__| | |____   | | | |__| |____) |  ____) | |__| | |    | |____| | \\ \\| |  | | |____| | \\ \\| |____ / ____ \\| |__| | |__| |\n"
				 		+ " /_/    \\_\\_|   |_____| |_|  |_|_____\\_____|_|  \\_\\\\____/  |_|    |_|  \\_\\\\____/|_| \\_|  |_|    |_|    |_|  \\_\\\\____/|_____/ \\____/ \\_____|  |_|  \\____/|_____/  |_____/ \\____/|_|    |______|_|  \\_\\_|  |_|______|_|  \\_\\\\_____/_/    \\_\\_____/ \\____/ \n"
				 		+ "                                                                                                                                                                                                                                                        \n"
				 		+ "                                                                                                                                                                                                                                                        \n"
				 		+ "\n\n".toUpperCase());
			 }
		});
		
		app.run( args);
		
		

		
		
		
	}



}
