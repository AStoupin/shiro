package ru.stoupin.shiro;

import org.apache.shiro.spring.boot.autoconfigure.ShiroAutoConfiguration;
import org.apache.shiro.spring.config.web.autoconfigure.ShiroWebAutoConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { ShiroApplication.class,
        ShiroConfiguration.class,
        ShiroWebAutoConfiguration.class,
        ShiroAutoConfiguration.class})

class TestControllerWebTest {

    private MockMvc mockMvc;


    @Autowired
    private Filter appFilter;

    @Autowired
    WebApplicationContext wac;


    @BeforeEach
    public  void beforeClass() {
        mockMvc = MockMvcBuilders
                    .webAppContextSetup(wac)
                    .addFilters(appFilter)
                    .build();
    }



    @Test
    public void testKORequestTest() throws Exception {
        MockHttpSession mockSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());

        this.withoutLogin(mockSession)
            .requestWithDeniedStatus(mockSession);
    }

    @Test
    public void testOKRequestTest() throws Exception {
        MockHttpSession mockSession = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());

        this.afterLogin(mockSession)
            .requestWithOKStatusAndResult(mockSession);
    }


    private TestControllerWebTest withoutLogin(MockHttpSession mockSession){
        return this;
    }

    private TestControllerWebTest afterLogin(MockHttpSession mockSession) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")
                .session(mockSession))
                .andExpect(status().isOk());
        return this;
    }


    private TestControllerWebTest requestWithOKStatusAndResult(MockHttpSession mockSession) throws Exception {
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get("/test")
                        .session(mockSession))
                .andExpect(status().isOk())
                .andReturn();

        String resultText = result.getResponse().getContentAsString();
        assertEquals(resultText, "test");
        return this;
    }


    private TestControllerWebTest requestWithDeniedStatus(MockHttpSession mockSession) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test")
                .session(mockSession))
                .andExpect(status().is3xxRedirection());
        return this;
    }
}