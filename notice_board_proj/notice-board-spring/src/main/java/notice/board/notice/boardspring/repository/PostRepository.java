package notice.board.notice.boardspring.repository;
import notice.board.notice.boardspring.domain.Member;
import notice.board.notice.boardspring.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}