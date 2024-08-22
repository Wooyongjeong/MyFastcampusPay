package org.example.myfastcampuspay;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @GetMapping(path = "/test")
    public void test() {
        System.out.println("Hello world!");
    }
}
