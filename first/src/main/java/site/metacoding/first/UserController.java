package site.metacoding.first;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Data를 리턴하는 컨트롤러 (PrintWriter로 리턴함)
@RestController
public class UserController {

    // DI 라고 함. IOC컨테이너에 있으니까 주입해 줄 수 있음.
    // Dependency Injection 의존성 주입
    public UserController(Dog d, HttpServletRequest request) {
        System.out.println("UserController 생성자 실행됨");
    }

    @GetMapping("/home")
    public void home() {
        System.out.println("home~~~~~~~~~~~~~~~~~");
    }

    @GetMapping("/bye")
    public void bye() {
        System.out.println("bye~~~~~~~~~~~~~~~~~~");
    }

    @GetMapping("/data")
    public String data() {
        return "<h1>data</h1>";
    }
}