package com.demo.web.servlet;

import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@CrossOrigin(origins = "http://localhost:63342/",maxAge = 3600)


@WebServlet("/ExitServlet")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException{
        response.setHeader("Access-Control-Allow-Origin","*");//星号表示所有
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Access-Control-Allow-Origin","*");//星号表示所有
//1.销毁session
        request.getSession().invalidate();

        //2.跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }
}
