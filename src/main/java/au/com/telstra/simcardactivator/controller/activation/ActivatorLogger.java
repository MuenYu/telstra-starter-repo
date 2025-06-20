package au.com.telstra.simcardactivator.controller.activation;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class ActivatorLogger {

    private final WebClient webClient = WebClient.create("http://localhost:8444");

    @PostMapping("/")
    public Mono<ActualActivationResp> activateSim(@RequestBody ActivationReq req) {
        // send to existing service
        ActualActivationReq activationBody = new ActualActivationReq(req.iccid);
        return webClient.post().uri("/actuate")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(activationBody)
                .retrieve()
                .bodyToMono(ActualActivationResp.class);
    }
}
