package com.academy.user.impl;

import com.academy.core.pojo.Code;
import com.academy.core.pojo.User;
import com.academy.core.service.CodeService;
import com.academy.user.mapper.CodeMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class CodeServiceImpl implements CodeService {

    @Resource
    private CodeMapper codeMapper;

    @Override
    public void insertCode(@RequestBody Code code) {
        Code check = findByInfo(code.getInfo());
        if(check==null){
            code.setCreateAt(System.currentTimeMillis());
            code.setUpdateAt(System.currentTimeMillis());
            codeMapper.insert(code);
        }else {
            check.setInfo(code.getInfo());
            check.setNumber(code.getNumber());
            codeMapper.update(check);
        }
    }

    @Override
    public Code findByInfo(@RequestParam(value = "info") String info) {
        return codeMapper.findByInfo(info);
    }
}
