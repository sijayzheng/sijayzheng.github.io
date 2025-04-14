package cn.sijay.biu;

import cn.sijay.biu.generate.service.GenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * GenTest
 *
 * @author Sijay
 * @since 2025-04-07
 */
@SpringBootTest(classes = BiuApplication.class)
public class GenTest {
    @Autowired
    private GenService genService;

    @Test
    public void test() {
        genService.generatorCode(5L);
    }
}
