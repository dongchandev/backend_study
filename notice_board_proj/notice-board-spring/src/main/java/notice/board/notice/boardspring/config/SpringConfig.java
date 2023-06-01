package notice.board.notice.boardspring.config;

import jakarta.persistence.EntityManager;
import notice.board.notice.boardspring.repository.PostRepository;
import notice.board.notice.boardspring.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public PostService jpaPostService(PostRepository postRepository) {
        return new PostService(postRepository);
    }
}