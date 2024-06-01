package poateto.final4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class Final4jApplication {

	public static void main(String[] args) {
//		System.setProperty("java.awt.headless", "false");
		SpringApplication.run(Final4jApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void openLoginPage() {
//		System.out.println("Welcome to the Login Page");
//		try {
//			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
//				Desktop.getDesktop().browse(new URI("http://localhost:8080/login"));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
