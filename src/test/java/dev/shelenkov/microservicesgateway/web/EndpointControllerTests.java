package dev.shelenkov.microservicesgateway.web;

import dev.shelenkov.microservicesgateway.adapter.BackendAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestClientException;

import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EndpointController.class)
public class EndpointControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BackendAdapter backendAdapter;

    @Test
    public void getRequest_okResponse() throws Exception {
        when(backendAdapter.getRequests()).thenReturn("1");

        mockMvc.perform(get("/"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.content().string(
                startsWith("Number of requests 1")));
    }

    @Test
    public void getRequest_failedResponse() throws Exception {
        when(backendAdapter.getRequests()).thenThrow(RestClientException.class);

        mockMvc.perform(get("/")).andExpect(status().is5xxServerError());
    }
}
