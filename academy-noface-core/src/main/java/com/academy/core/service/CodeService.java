package com.academy.core.service;

import com.academy.core.pojo.Code;
import com.academy.core.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "academy-noface-user-service")
public interface CodeService {

    @PostMapping("/feign/code")
    Long insertCode(@RequestBody Code code);

    @GetMapping("/feign/code")
    Code findByNumber(@RequestParam(value = "info") String info);

    @PutMapping("/feign/coed")
    void update(@RequestBody Code code);

}
