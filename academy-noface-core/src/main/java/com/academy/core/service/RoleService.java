package com.academy.core.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Role业务层接口
 */
@FeignClient(value = "admin-service")
public class RoleService {

}
