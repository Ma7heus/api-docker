package com.matheusbiasi.api_docker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/teste")
public class TesteController {

    @GetMapping()
    public ResponseEntity<String> getTeste() {
        return ResponseEntity.ok("Teste OK");
    }

}
