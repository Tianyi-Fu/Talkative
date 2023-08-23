package com.demo.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.demo.model.ResultInfo;
import com.demo.model.User;
import com.demo.serivce.UserService;
import com.demo.serivce.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        //1.获取用户名和密码
        Map<String, String[]> map = request.getParameterMap();
        //2.封装user对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);//用BeanUtils工具类封装，注意不要导错了包，是org.apache.commons.beanutils.BeanUtils;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用service查询
        HttpSession session = request.getSession();
        UserService service = new UserServiceImpl();
        ResultInfo info = new ResultInfo();//给出提示信息，这里用方法封装了一下,方便使用
        User loginUser = service.login(user);
        //4.判断用户登录是否成功
        if(user!=null){
            //登录成功
            //存储用户，做个24小时免登录
            Cookie cookie = new Cookie("JSESSIONID", session.getId());
            cookie.setMaxAge(60*60*24);
            response.addCookie(cookie);
            session.setAttribute("user",loginUser);//登录成功标记
            info.setFlag(true);
        }else{
            //如果用户登录失败，给出提示信息
            info.setErrorMsg("wrong username or password");
            info.setFlag(false);
        }

        //响应数据
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(),info);

    }
}
