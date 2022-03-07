package site.metacoding.dbproject.web;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import site.metacoding.dbproject.domain.user.User;
import site.metacoding.dbproject.domain.user.UserRepository;

@Controller
public class UserController {

    // 컴퍼지션 (의존성 연결)
    private UserRepository userRepository;
    private HttpSession session;

    // DI 받는 코드. // @Autowired와 같음.
    public UserController(UserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
    }

    // 회원가입 페이지 (정적) - 로그인 X
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    // username=ssar&password=1234&email=ssar@nate.com (x-www-form-urlencoded)
    // 회원가입 - 로그인 X
    @PostMapping("/join")
    public String join(User user) {

        // 1.username, password, email 1.null 체크 2.공백체크
        if (user.getUsername() == null || user.getPassword() == null || user.getEmail() == null) {
            return "redirect:/joinForm";
        }
        if (user.getUsername().equals("") || user.getPassword().equals("") || user.getEmail().equals("")) {
            return "redirect:/joinForm";
        }

        // 2.핵심로직
        User userEntity = userRepository.save(user);
        System.out.println("userEntity : " + userEntity);
        // redirect : 매핑주소
        return "redirect:/loginForm"; // 로그인페이지 이동해주는 컨트롤러 메서드를 재활용
    }

    // 로그인 페이지 (정적) - 로그인 X
    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    // SELECT * From user WHERE username=? AND password=?
    // 원래 SELET는 무조건 Get요청
    // 그런데 로그인만 예외 (POST)
    // 이유 : 주소에 패스워드를 남길 수 없으니까!! => 보안을 위해 POST를 사용!!
    // 로그인 - 로그인 X
    @PostMapping("/login")
    public String login(HttpServletRequest request, User user) {
        System.out.println("사용자로 부터 받은 username, password : " + user);
        HttpSession session = request.getSession(); // 자기 번호의 세션 영역에 접근
        User userEntity = userRepository.mLogin(user.getUsername(), user.getPassword());
        if (userEntity == null) {
            System.out.println("아이디 혹은 패스워드가 틀렸습니다.");
        } else {
            System.out.println("로그인 되었습니다.");
            session.setAttribute("principal", userEntity);
        }
        // 1. DB연결해서 username, password 있는지 확인
        // 2. 있으면 session 영역에 인증됨 이라고 메시지 하나 넣어두자.
        return "redirect:/"; // PostController 만들고 수정하자.
    }

    // http://localhost:8080/user/1
    // 유저상세 페이지 (동적) - 로그인 O
    @GetMapping("/user/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        User principal = (User) session.getAttribute("principal");

        // 1. 인증 체크
        if (principal == null) {
            return "error/page1";
        }

        // 2. 권한체크
        if (principal.getId() != id) {
            return "error/page1";
        }

        // 3. 핵심로직
        Optional<User> userOp = userRepository.findById(id);

        if (userOp.isPresent()) {
            User userEntity = userOp.get();
            model.addAttribute("user", userEntity);
            return "user/detail";
        } else {
            return "error/page1";
        }

        // DB에 로그 남기기 (로그인 한 아이디)
    }

    // 유저수정 페이지 (동적) - 로그인 O
    @GetMapping("/user/updateForm")
    public String updateForm() {
        return "user/updateForm";
    }

    // 유저수정 - 로그인 O
    @PutMapping("/user/{id}")
    public String update(@PathVariable Integer id) {
        return "redirect:/user/" + id;
    }

    // 로그아웃 - 로그인 O
    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/loginForm"; // PostController 만들고 수정하자.
    }
}