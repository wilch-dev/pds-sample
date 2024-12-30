package com.walcfpw.personnel.client;

import com.walcfpw.personnel.dto.DepartmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Component
public class DepartmentClient {

//    https://www.youtube.com/watch?v=0jyKgEz0Yn8&t=518s
//    https://www.youtube.com/watch?v=HFl2dzhVuUo

    private final WebClient webClient;

    public DepartmentClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("http://localhost:8060").build();
    }
//    private String departmentUri = "http://department";


    public Mono<DepartmentDTO> getDepartmentById(Long deptId) {
        return webClient.get()
                .uri("/department/id/{deptId}", deptId)
                .retrieve()
                .bodyToMono(DepartmentDTO.class);
    }

    public Mono<String> getHello() {
        return webClient.get()
                .uri("/department/hello")
                .retrieve()
                .bodyToMono(String.class);
    }

}
