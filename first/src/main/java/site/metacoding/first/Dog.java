package site.metacoding.first;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component // 특별한 목적없이 IOC에 등록하고 싶을때
public class Dog {
    private String name = "푸들";

    public Dog() {
        System.out.println("강아지가 IOC 컨테이너에 등록되었어요.");
    }
}