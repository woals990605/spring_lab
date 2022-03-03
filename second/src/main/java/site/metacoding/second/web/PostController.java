package site.metacoding.second.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import site.metacoding.second.domain.Post;

//View (글쓰기 페이지, 글 목록 페이지, 글 상세보기 페이지)
@Controller
public class PostController {

    @GetMapping("/post/writeForm")
    public String writeForm() {
        return "post/writeForm";
    }

    @GetMapping("/post/list")
    public String list(Model model) {

        Post post1 = new Post(1, "제목1", "내용1");
        Post post2 = new Post(2, "제목2", "내용2");
        Post post3 = new Post(3, "제목3", "내용3");
        List<Post> posts = new ArrayList<>();
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);

        model.addAttribute("posts", posts);

        return "post/list";
    }

    @GetMapping("/post/detail")
    public String detail(Model model) {

        // 1. DB에 연결해서 SELECT 해야함.
        // 2. ResultSet을 JavaObject으로 변경
        Post post = new Post(1, "제목1", "내용1");

        // 3. request에 담기
        model.addAttribute("post", post);
        return "post/detail";// RequestDispatcher로 forward함 = request가 복제됨.
    }
}