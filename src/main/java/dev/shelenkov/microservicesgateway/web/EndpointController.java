package dev.shelenkov.microservicesgateway.web;

import dev.shelenkov.microservicesgateway.adapter.BackendAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EndpointController {

    private final BackendAdapter backendAdapter;

    @Value("${instance.id}")
    private int instanceId;

    @Value("${secret}")
    private String secret;

    @GetMapping("/")
    public String getRequestsCount() {
        String result = String.format("Number of requests %s (gateway %d, secret %s)",
            backendAdapter.getRequests(), instanceId, secret);
        log.info("Handling count request. Result {}", result);
        return result;
    }
}
