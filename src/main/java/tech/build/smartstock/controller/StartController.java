package tech.build.smartstock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.build.smartstock.controller.dto.StartDto;

public class StartController {


    @PostMapping("/start")
    public ResponseEntity<Void> start(@RequestBody StartDto dto) {


        return ResponseEntity.accepted().build();
    }
}
