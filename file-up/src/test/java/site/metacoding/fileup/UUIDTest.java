package site.metacoding.fileup;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class UUIDTest {

    @Test
    public void 유유아이디_연습() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
    }
}
