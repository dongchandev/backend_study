package notice.board.notice.boardspring.board.controller;

import notice.board.notice.boardspring.board.domain.Board;
import notice.board.notice.boardspring.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("api/board-write")
    public Board BoardWrite(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String author
    ) {
        Board board = new Board();
        board.setTitle(title);
        board.setContent(content);
        board.setAuthor(author);
        boardService.savePost(board);
        return board;
    }
}