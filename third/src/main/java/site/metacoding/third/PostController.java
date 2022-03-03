package site.metacoding.third;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // 파일을 리턴하는 컨트롤러이다.
public class PostController {

    @GetMapping("/post/writeForm")
    public String writeForm() {
        // resorces/templates/{리턴값}.mustache 뷰리졸버 설정
        return "post/writeForm";// 파일경로(뷰리졸버 설정이 되어 있음 - mustache 라이브러리)
    }

    // Post메서드로 요청-> http://localhost:8080/post
    // title=제목1&xontext=내용1-> x-www-form-urlencoded
    // 스프링 기본 파싱 전략 -> x-www-form-urlencoded만 파싱함
    @PostMapping("/post")
    public String write(String title, String content, Model model) {
        // 1.title과 content로 DB에 insert 하기
        System.out.println("title : " + title);
        System.out.println("content : " + content);
        // 2.글목록 페이지로 이동
        model.addAttribute("title2", title);
        return "post/list";
    }

}
