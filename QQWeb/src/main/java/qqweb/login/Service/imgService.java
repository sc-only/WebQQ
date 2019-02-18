package qqweb.login.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qqweb.login.domain.imgTest;
import qqweb.login.repository.ImgTestReposrtory;


public interface imgService {
    public imgTest add(imgTest imgTest, String path, String username);
}
