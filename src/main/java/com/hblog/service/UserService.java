package com.hblog.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hblog.entity.User;
import com.hblog.shiro.AccountProfile;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv-success
 * @since 2019-02-22
 */
public interface UserService extends IService<User> {
    /**
     * 给关联用户的分页结果添加用户信息
     * @param pageData
     * @param linkfield
     */
    void join(IPage<Map<String,Object>> pageData, String linkfield);

    /**
     * 用于用户登录
     * AccountProfile是有用户基本信息的类，包括私信、通知数量，头像等
     * @param email
     * @param password
     * @return
     */
    AccountProfile login(String email, String password);
    /**
     * 注册
     * @param user
     * @return
     */
    R register(User user);
}
