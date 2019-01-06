package top.itjee.www.zchain.webcontroller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import top.itjee.www.zchain.domain.User;
import top.itjee.www.zchain.service.user.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WebControllerApplicationTests {

    @Autowired
    UserService userService;

    @Test
    @Rollback(true)
    public void contextLoads() throws IOException {

        List<User> list = userService.findUser();
        System.out.println(list.size());

        User u = new User();
        u.setId(1236L);
        u.setName("vvv");
        u.setXingHao("D");
        u.setPinPai("ccc");
        u.setCreateTime(new Date());
        u.setUpdateTime("2018-07-27 11:38:34");
        int i = userService.insertUser(u);
        System.out.println(i);
        u.setName("qer");
        i = userService.updateUser(u);
        System.out.println(i);
    }

}

