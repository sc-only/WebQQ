package qqweb.login.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqweb.login.Service.imgService;
import qqweb.login.domain.imgTest;
import qqweb.login.repository.ImgTestReposrtory;

@Service
public class imgServiceImpl implements imgService {

    @Autowired
    private ImgTestReposrtory imgTestReposrtory;

    @Override
    public imgTest add(imgTest imgTest,String path,String username) {
        imgTest.setUrl(path);
        imgTest.setUsername(username);
        return imgTestReposrtory.save(imgTest);
    }
}
