package top.itjee.www.zchain.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import top.itjee.www.zchain.domain.User;
import java.util.List;

@Repository
@Mapper
public interface UserDao {


    @Select("SELECT * FROM t_user")
    @Results({
            @Result(property = "id",  column = "id", javaType = Long.class),
            @Result(property = "name", column = "name"),
            @Result(property = "xingHao", column = "xing_hao"),
            @Result(property = "pinPai", column = "pin_pai"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "updateTime", column = "update_time")
    })
    @ResultType(User.class)
    List<User> getAll();

    @Insert("insert into t_user(id,name,xing_hao,pin_pai,create_time,update_time) " +
            "values(#{id},#{name},#{xingHao},#{pinPai},#{createTime},#{updateTime})")
    public int insertUser(User user);

    @Update("update t_user set name=#{name} where id=#{id}")
    public int updateUser(User user);

}
