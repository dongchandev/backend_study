package notice.board.notice.boardspring.board.repository;
import notice.board.notice.boardspring.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}