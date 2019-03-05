package com.hblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hblog.entity.Category;
import com.hblog.mapper.CategoryMapper;
import com.hblog.service.CategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
