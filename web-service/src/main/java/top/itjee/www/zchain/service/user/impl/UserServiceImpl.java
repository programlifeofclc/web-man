package top.itjee.www.zchain.service.user.impl;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.itjee.www.zchain.dao.UserDao;
import top.itjee.www.zchain.domain.User;
import top.itjee.www.zchain.service.annotation.ReadWrite;
import top.itjee.www.zchain.service.user.UserService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @ReadWrite(ReadWrite.option.WRITE)
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,Error.class})
    public List<User> findUser() throws IOException {
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBC");
        ((UserService)AopContext.currentProxy()).findUserById();
        System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBBC");
       return userDao.getAll();
    }

    @ReadWrite(ReadWrite.option.READ)
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<User> findUserById(){
        System.out.println("BBCCCCCCCCCCCCCCCCCCCCCCCCCCCC");
        return userDao.getAll();
    }

    @Override
    public int insertUser(User user) {
        int i = userDao.insertUser(user);
        System.out.println("执行结果" +i);
        return i;
    }

    @Override
    public int updateUser(User user) {
        int i = userDao.updateUser(user);
        System.out.println("执行结果u" +i);
        return i;
    }
}
