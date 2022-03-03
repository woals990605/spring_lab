package site.metacoding.first;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//파일을 리턴함 (RequestDispatcher dis = request.getRequestDispatcher("파일명");
//dis.forward(request, response);
@Controller
public class PostController {

    @GetMapping("/writeForm")
    public String writeForm() {
        return "writeForm";
    }

    @GetMapping("/updateForm")
    public String updateForm() {
        return "updateForm";
    }
}