package notice.board.notice.boardspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class NoticeBoardSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoticeBoardSpringApplication.class, args);
	}
}
