package notice.board.notice.boardspring.board.service;
import notice.board.notice.boardspring.board.domain.Board;
import notice.board.notice.boardspring.board.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Board savePost(Board post) {
        boardRepository.save(post);
        return post;
    }

    public Optional<Board> findPostById(Long id) {
        return boardRepository.findById(id);
    }

    public List<Board> getAllPosts() {
        return boardRepository.findAll();
    }

    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
