package dev.shelenkov.microservicesgateway.web;

import dev.shelenkov.microservicesgateway.adapter.BackendAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EndpointController {

    private final BackendAdapter backendAdapter;

    @GetMapping("/")
    public String getRequestsCount() {
        return "Number of requests " + backendAdapter.getRequests();
    }
}
