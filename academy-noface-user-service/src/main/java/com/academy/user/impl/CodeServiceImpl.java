package com.academy.user.impl;

import com.academy.core.pojo.Code;
import com.academy.core.pojo.User;
import com.academy.core.service.CodeService;
import com.academy.user.mapper.CodeMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CodeServiceImpl implements CodeService {

    @Resource
    private CodeMapper codeMapper;

    @Override
    public Long insertCode(@RequestBody Code code) {
        code.setCreateAt(System.currentTimeMillis());
        code.setUpdateAt(System.currentTimeMillis());
        codeMapper.insert(code);
        return code.getId();
    }

    @Override
    public Code findByNumber(String number) {
        return codeMapper.findByNumber(number);
    }

    @Override
    public void update(Code code) {
        code.setUpdateAt(System.currentTimeMillis());
        codeMapper.update(code);
    }
}
