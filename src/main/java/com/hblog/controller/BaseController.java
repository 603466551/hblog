package com.hblog.controller;

import com.hblog.service.CategoryService;
import com.hblog.service.PostService;
import com.hblog.service.UserService;
import com.hblog.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 60346
 */
public class BaseController {
    @Autowired
    HttpServletRequest req;

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;
    protected AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }

    protected Long getProfileId() {
        return getProfile().getId();
    }
}
