package cn.kgc.ssm.mybatis;

import cn.kgc.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    /**
     * 通过名字或者电话查询user的集合
     * @param users
     * @return
     */
    List<User> selectByNameOrPhone(User users);
    /**
     *在用户没被禁言的情况下,通过(名字,电话)和密码查询用户对象
     * @param users
     * @return
     */
    List<User> selectByNameAndPasswordOrPhone(User users);
    /**
     * 通过姓名和电话模糊查询得到用户集合
     * @param name
     * @param phone
     * @return
     */
    List<User>selectUserListLikeByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

    /**
     * 通过名字或电话得到用户集合
     * @param user
     * @return
     */
    List<User>selectUserListByName(User user);
    /**
     * 通过名字或电话得到用户集合
     * @param user
     * @return
     */
    List<User>selectUserListByPhone(User user);
    /**
     * 改变用户权限
     * @param id
     * @param oldState
     * @param newState
     * @return
     */
    int updateUsersState(@Param("id") Integer id, @Param("oldState") Integer oldState, @Param("newState") Integer newState);
}