package top.itjee.www.zchain.service.user;

import top.itjee.www.zchain.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserService {

    public List<User> findUser() throws IOException;

    public List<User> findUserById();

    public int insertUser(User user);

    public int updateUser(User user);
}
