
package com.example.sor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
        "jwt.secret=VGhpc0lzQVRlc3RTZWNyZXRLZXlGb3JKV1QxMjM0NTY="
})
class SystemOfRecordApplicationTests {

    @Test
    void contextLoads() {
    }
}
