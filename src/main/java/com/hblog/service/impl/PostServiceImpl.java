package com.hblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hblog.entity.Post;
import com.hblog.mapper.PostMapper;
import com.hblog.service.PostService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv-success
 * @since 2019-02-22
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

}
