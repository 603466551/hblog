package com.hblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hblog.entity.Comment;
import com.hblog.mapper.CommentMapper;
import com.hblog.service.CommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
