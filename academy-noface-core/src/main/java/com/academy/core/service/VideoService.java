package com.academy.core.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "academy-noface-user-service")
public interface VideoService {

}
