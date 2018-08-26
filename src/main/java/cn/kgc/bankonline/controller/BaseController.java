package cn.kgc.bankonline.controller;

import cn.kgc.bankonline.entity.Account;
import cn.kgc.bankonline.entity.TransactionRecord;
import cn.kgc.bankonline.service.AccountService;
import cn.kgc.bankonline.service.IAccountService;
import cn.kgc.bankonline.util.PageUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class BaseController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url =  request.getRequestURI();
        System.out.println(url);
        String className = url.substring(1,url.lastIndexOf("/"));
        className = className.substring(0,1).toUpperCase()+className.substring(1)+"Controller";
        System.out.println(className);
        try {
            Class<?> clazz = Class.forName("cn.kgc.bankonline.controller."+className);
            Object obj = clazz.newInstance();
            String methodName = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf("."));
            System.out.println(methodName);
            Method method = clazz.getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(obj,request,response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("404");
        }
    }
    /**
     * 利用反射
     *      [类名简写]/方法名.do  访问方法
     */
}
