package com.academy.uaa.service;

import com.academy.uaa.dao.ManagerDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManagerServiceDetail implements UserDetailsService {
    @Autowired
    private ManagerDao managerDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("uaa-service业务层：ManagerServiceDetail.loadUserByUsername:"+s);
        return managerDao.findByName(s);
    }
}
