package com.lami.foodie;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xujiankang on 2017/12/6.
 */
public interface BaseInterface {
    /**
     * 展示信息列表视图
     */
    public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception;
    /**
     * 增加对象视图
     */
    public ModelAndView add(HttpServletRequest request,HttpServletResponse response) throws Exception;
    /**
     * 编辑对象视图
     */
    public ModelAndView edit(HttpServletRequest request,HttpServletResponse response) throws Exception;
    /**
     * 只读对象视图
     */
    public ModelAndView read(HttpServletRequest request,HttpServletResponse response) throws Exception;
    /**
     * 保存对象功能视图
     */
    public void save(HttpServletRequest request,HttpServletResponse response) throws Exception;
    /**
     * 更新对象功能视图
     */
    public void update(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
