package site.metacoding.dbproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class PostController {

    // 글쓰기 페이지 /post/writeForm
    @GetMapping("/post/writeForm")
    public String writeForm() {
        return "post/writeForm";
    }

    // 메인 페이지
    // 글 목록 페이지 /post/list/
    @GetMapping({ "/", "post/list" }) // 두 가지 방법으로 들어올 수 있음
    public String list() {
        return "post/list";
    }

    // 글 상세보기 페이지 /post/{id} (삭제버튼 만들어두면 되니까 삭제페이지 필요 X)
    @GetMapping("/post/{id}")
    public String detail(@PathVariable Integer id) { // int는 null이 없음, 초기값이 0
                                                     // Integer는 초기값이 null
        return "post/" + id;
    }

    // 글 수정 페이지 /post/{id}/updateForm
    @GetMapping("/post/{id}/updateForm")
    public String updateForm(@PathVariable Integer id) {
        return "post/updateForm";// ViewResolver 도움 받음.
    }

    // DELETE 글 삭제 /post/{id} -> 글 목록으로 가기
    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable Integer id) {
        return "redirect:/";
    }

    // UPDATE 글 수정 /post/{id} -> 글 상세보기 페이지 가기
    @PutMapping("/post/{id}")
    public String update(@PathVariable Integer id) {
        return "redirect:/post/" + id;
    }

    // POST 글 쓰기 /post -> 글 목록으로 가기
    @PostMapping("/post")
    public String post() {
        return "redirect:/";
    }
}