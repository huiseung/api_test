package org.example.client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequiredArgsConstructor
public class ApiController {
    private final WebClient webClient;
    private final RestTemplate restTemplate;
    private final ExecutorService executorService;

    @GetMapping("/resttemplate-virtual")
    public String restTemplateVirtual() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            return restTemplate.getForObject("http://localhost:7000", String.class);
        }, executorService);
        String response = future.get();
        return response;
    }

    @GetMapping("/webclient")
    public String webClient() {
        String response = webClient.get()
                .uri("http://localhost:7000")
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return response;
    }
}
