package cn.kgc.ssm.service;

import cn.kgc.ssm.entity.User;

import java.util.List;

public interface UserService {
    /**
     * 不存在输入的邮箱和姓名则users为空,返回true 表示可以注册,否则注册失败
     *
     * @param userss
     * @return
     */
    public User register(User userss);
    /**
     * 登陆用户,不存在返回空对象
     *
     * @param users
     * @return
     */
    public User login(User users);

    /**
     * 有选择的插入用户信息
     *
     * @param users
     * @return
     */
    User insertUsersSelected(User users);

    /**
     * 通过名字查询用户
     * @param user
     * @return
     */
    int selectUserByName(User user);

    /**
     * 通过电话查询用户
     * @param user
     * @return
     */
    int selectUserByPhone(User user);
    int selectByNameAndPhone(User user, User user1);
    /**
     * 通过名字和电话模糊查询得到用户分页集合
     * @param pno
     * @param psize
     * @return
     */
    List<User>selectUserPagesLikeByNameAndPhone(String name, String phone, Integer pno, Integer psize);

    /**
     * 如果user有id,则修改user,否则添加user
     * @param user
     * @return
     */
    int updateOrInsertUser(User user);
    /**
     * 在老状态为一的前提下禁用用户,即把其状态设为零
     *
     * @param id
     * @return
     */
    int deleteUser(Integer id);

    /**
     * 在老状态为0的前提下恢复成普通用户,即把其状态设为一
     *
     * @param id
     * @return
     */
    int recoverUser(Integer id);

    /**
     * 通过id得到用户
     * @param id
     * @return
     */
    User selectUser(Integer id);

    /**
     * 测试事务
     */
    void testTrancational();

}
