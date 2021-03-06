package com.hblog.controller;

import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.code.kaptcha.Producer;
import com.hblog.entity.Post;
import com.hblog.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 首页控制器
 */
@Slf4j
@Controller
public class IndexController extends BaseController {

    private static final String KAPTCHA_SESSION_KEY = "KAPTCHA_SESSION_KEY";

    //验证码的生成器
    @Autowired
    private Producer producer;

    @GetMapping("/capthca.jpg")
    public void capthca(HttpServletResponse response) throws IOException{
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //把验证码存到shrio的session中
        SecurityUtils.getSubject().getSession().setAttribute(KAPTCHA_SESSION_KEY, text);

        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpg", outputStream);
    }

    @GetMapping("/")
    public String index(){
        log.info("---------这是首页");
        Page<Post> page = new Page<>();
        page.setCurrent(1);
        page.setSize(10);

        IPage pageData = postService.pageMaps(page,null);

        //添加关联的用户信息
        userService.join(pageData,"user_id");
        req.setAttribute("pageData",pageData);

        log.info("--------------->" + pageData.getRecords());
        log.info("-------------------------------" + page.getPages());
        return "index";
    }
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @ResponseBody
    @PostMapping("/login")
    public R doLogin(String email, String password, ModelMap model) {
        if(StringUtils.isEmpty(email)||StringUtils.isEmpty(password)){
            return R.failed("用户名或密码不能为空!");
        }
        AuthenticationToken token = new UsernamePasswordToken(email, SecureUtil.md5(password));

        try {
            //尝试登陆,将会调用realm的认证方法
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            if (e instanceof UnknownAccountException) {
                return R.failed("用户不存在");
            } else if (e instanceof LockedAccountException) {
                return R.failed("用户被禁用");
            } else if (e instanceof IncorrectCredentialsException) {
                return R.failed("密码错误");
            } else {
                return R.failed("用户认证失败");
            }
        }
        return R.ok("登陆成功");
    }

    @GetMapping("/register")
    public String register() {
        return "auth/register";
    }

    @ResponseBody
    @PostMapping("/register")
    public R doRegister(User user,String capthca) {
        String kaptcha = (String)SecurityUtils.getSubject().getSession().getAttribute(KAPTCHA_SESSION_KEY);
        if(!kaptcha.equalsIgnoreCase(capthca)){
            System.out.println(kaptcha+"----"+capthca);
            return  R.failed("验证码不正确");
        }
        R r = userService.register(user);
        return r;
    }
}
