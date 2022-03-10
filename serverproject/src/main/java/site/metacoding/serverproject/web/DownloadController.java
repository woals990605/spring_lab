package site.metacoding.serverproject.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import site.metacoding.serverproject.domain.Hospital;
import site.metacoding.serverproject.domain.HospitalRepository;

@Controller
public class DownloadController {

    private HospitalRepository hospitalRepository;
    private HttpSession session;

    public DownloadController(HospitalRepository hospitalRepository, HttpSession session) {
        this.hospitalRepository = hospitalRepository;
        this.session = session;
    }

    @GetMapping("/download")
    public String download(Hospital hospital) {
        // 1. URI로 다운로드
        RestTemplate rt = new RestTemplate();
        Hospital[] response = rt.getForObject("http://3.38.254.72:5000/api/hospital?sidoCdNm=부산&sgguCdNm=부산사하구",
                Hospital[].class);

        List<Hospital> hosList = Arrays.asList(response);

        // 2. DB에 saveAll() + model에 담기

        // 3. 리턴
        return "list";
    }
}