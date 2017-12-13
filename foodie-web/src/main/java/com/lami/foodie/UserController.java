package com.lami.foodie;

import mx4j.tools.adaptor.http.HttpUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xujiankang on 2017/12/6.
 */
public class UserController extends MultiActionController {

    public ModelAndView add(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","add");
        mv.setViewName("add");
        return mv;
    }
    public ModelAndView delete(HttpServletRequest request,HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message","delete");
        mv.setViewName("delete");
        return mv;
    }
}

