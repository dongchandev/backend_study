package notice.board.notice.boardspring.controller;

import lombok.RequiredArgsConstructor;
import notice.board.notice.boardspring.domain.Post;
import notice.board.notice.boardspring.repository.PostRepository;
import notice.board.notice.boardspring.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/post")
    public String PostWrite() {
        return "post-form";
    }
    @PostMapping("/write-post")
    public String write(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String author
    ) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setAuthor(author);
        postService.savePost(post);
        return "redirect:/posts";
    }
    @GetMapping("/posts")
    public String list(Model model){
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts",posts);
        //"members"키를 통해 members를 리스트형태로 넣어둠
        return "post-list";
    }
    @GetMapping("/posts/{id}")
    public String showPostDetail(@PathVariable("id") Long id, Model model) {
        Optional<Post> optionalPost = postService.findPostById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post-view";
        } else {
            return "error"; // 적절한 에러 페이지로 이동
        }
    }
}