package cn.kgc.ssm.service.impl;

import cn.kgc.ssm.entity.User;
import cn.kgc.ssm.mybatis.UserMapper;
import cn.kgc.ssm.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper udao;

    /**
     * 不存在输入的邮箱和姓名则users为空,返回true 表示可以注册,否则注册失败
     *
     * @param userss
     * @return
     */
    @Transactional
    @Override
    public User register(User userss) {
//        if (userss.getPassword() == null) {
//            throw new RuntimeException("用户密码不能为空");
//        }
        User user = new User();
        user.setName(userss.getName());
        List<User> list = udao.selectByNameOrPhone(user);
        if (list.size() != 0) {
            throw new RuntimeException("用户名已经存在");
        }
        user = new User();
        user.setPhone(userss.getPhone());
        List<User> list1 = udao.selectByNameOrPhone(user);
        System.out.println("list 1为 :" + list1.size());
        if (list1.size() != 0) {
            throw new RuntimeException("电话已经存在");
        }
        try {
            userss.setState(1);
            int rr = udao.insertSelective(userss);
            if (rr==0) {
                throw new RuntimeException("插入用户失败");
            }
            return userss;
        } catch (Exception e) {
            throw new RuntimeException("插入用户失败,异常为 " + e.toString());
        }
    }


    /**
     * 登陆用户,不存在返回空对象
     *
     * @param users
     * @return
     */
    @Transactional
    @Override
    public User login(User users) {
        try {

            List<User> list = udao.selectByNameAndPasswordOrPhone(users);
            if (list.size() == 0) {
                return null;
            } else {
                return list.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 有选择的插入用户信息
     *
     * @param users
     * @return
     */
    @Override
    public User insertUsersSelected(User users) {
        users.setState(1);
        udao.insertSelective(users);
        return users;
    }

    /**
     * 通过名字查询用户
     *
     * @param user
     * @return
     */
    @Override
    public int selectUserByName(User user) {
        System.out.println("UserServiceImpl selectUserByName: "+user);
        if(user==null)
        {
            throw new RuntimeException("请传入user对象");
        }
        if(user.getName()==null||"".equals(user.getName().trim()))
        {
            throw new RuntimeException("用户名不能为空");
        }
        return udao.selectUserListByName(user).size();
    }

    /**
     * 通过电话查询用户
     *
     * @param user
     * @return
     */
    @Override
    public int selectUserByPhone(User user) {
        if(user==null)
        {
            throw new RuntimeException(" 请传入user对象");
        }
        if(user.getPhone()==null||"".equals(user.getPhone().trim()))
        {
            throw new RuntimeException("电话不能为空");
        }
        return udao.selectUserListByPhone(user).size();
    }

    @Override
    @Transactional
    public int selectByNameAndPhone(User user, User user1) {
        int i=selectUserByName(user);
        int j=selectUserByPhone(user1);
        if(i==0&&j==0)
        {
            return 1;
        }
        return 0;
    }

    /**
     * 通过名字和电话模糊查询得到用户分页集合
     *
     * @param pno
     * @param psize
     * @return
     */
    @Override
    public List<User> selectUserPagesLikeByNameAndPhone(String name, String phone, Integer pno, Integer psize) {
        if(name!=null)
        {
            if("".equals(name.trim()))
            {
                name=null;
            }
        }
        if(phone!=null)
        {
            if("".equals(phone.trim()))
            {
                phone=null;
            }
        }
        System.out.println("UserServiceImpl name"+name+","+phone);
        PageHelper.startPage(pno,psize);
        List<User>list=udao.selectUserListLikeByNameAndPhone(name,phone);
        return list;
    }

    /**
     * 如果user有id,则修改user,否则添加user
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public int updateOrInsertUser(User user) {
        System.out.println("UserServiceImpl user"+  user);
        int ret=0;
        List<User>list=new ArrayList<>();
        if(user!=null&&user.getName()!=null)
        {
            list=udao.selectUserListByName(user);
        }
        List<User>list1=new ArrayList<>();
        if(user!=null&&user.getPhone()!=null)
        {
            list1=udao.selectUserListByPhone(user);
        }
        if(list.size()>0||list1.size()>0)
        {
            throw new RuntimeException("用户名或者电话已经存在");
        }
        if(user.getId()==null)
        {
            user.setState(1);
            ret=udao.insertSelective(user);
        }else {
            ret=udao.updateByPrimaryKeySelective(user);
        }
        System.out.println(ret);
        return ret;
    }
    /**
     * 在老状态为一的前提下禁用用户,即把其状态设为零
     * @param id
     * @return
     */
    @Override
    public int deleteUser(Integer id) {

        int i=udao.updateUsersState(id,1,0);
        return i;
    }
    /**
     * 在老状态为0的前提下恢复成普通用户,即把其状态设为一
     * @param id
     * @return
     */
    @Override
    public int recoverUser(Integer id) {

        int i=udao.updateUsersState(id,0,1);
        return i;
    }

    /**
     * 通过id得到用户
     *
     * @param id
     * @return
     */
    @Override
    public User selectUser(Integer id) {
        User user=udao.selectByPrimaryKey(id);
        return user;
    }

    /**
     * 测试事务
     */
    @Override
    @Transactional
    public void testTrancational() {
        User user=udao.selectByPrimaryKey(1);
        user.setPassword(user.getPassword()+1);
        udao.updateByPrimaryKeySelective(user);
        throw new  RuntimeException("hello");
    }
}
