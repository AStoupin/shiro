package ru.stoupin.shiro;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {TestController.class})
class ShiroApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() {
		assertEquals("test", "test1");
	}

}
