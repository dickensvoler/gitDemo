package com.userLogin.web;

import com.userLogin.mapper.UserMapper;
import com.userLogin.pojo.User;
import com.userLogin.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        String password = request.getParameter("password");

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.SqlSessionFactoryGenerator();

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user1 = userMapper.SelectByUsername(username);

        if (user1==null){
            userMapper.Insert(user);

            sqlSession.commit();

            sqlSession.close();
        }
        else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("用户已存在");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
