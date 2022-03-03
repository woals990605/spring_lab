package site.metacoding.second.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostApiController {

    // SELECT * FROM post WHERE id=?
    // 구체적으로 뭘 달라고 요청!! - body X
    @GetMapping("/post/{id}")
    public String test1(@PathVariable int id) {
        return "주세요 id : " + id;
    }

    // SELECT * FROM post WHERE title=?
    // 구체적으로 뭘 달라고 요청!! - body X
    // http://localhost:8000/post?title=?
    @GetMapping("/post")
    public String search(String title) {
        return "주세요 title : " + title;
    }

    // http://localhost:8000/post
    // body:title=제목1&content=내용1
    // header:Content-Type:application/x-www-form-urlencoded
    // 뭘 줘야함 - body O
    // request.getParameter() 메서드가 스프링 기본 파싱 전략
    @PostMapping("/post")
    public String test2(String title, String content) {
        return "줄께요 : title : " + title + ", content : " + content;
    }

    // UPDATE post SET title = ?, content = ?, WHERE id =?
    // title, content (primary key : id)
    // 뭘 줘야함 -body O
    // API문서
    @PutMapping("/post/{id}")
    public String test3(String title, String content, @PathVariable int id) {
        return "수정해주세요 : title : " + title + ",content : " + content + ",id : " + id;
    }

    // http://localhost:8000/post?title=제목1
    // DELETE form post WHERE id=?

    // http://localhost:8000/post/1
    // DELETE FROM post WHERE id = ?
    // 구체적으로 삭제해주세요 - body X
    @DeleteMapping("/post/{id}")
    public String test4(@PathVariable int id) {
        return "삭제해주세요 : " + id;
    }
}