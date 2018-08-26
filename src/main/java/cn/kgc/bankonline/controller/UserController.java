package cn.kgc.bankonline.controller;

import cn.kgc.bankonline.entity.Account;
import cn.kgc.bankonline.service.AccountService;
import cn.kgc.bankonline.service.IAccountService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController {

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cardno = request.getParameter("cardno");
        String password = request.getParameter("password");
        IAccountService accountService = new AccountService();
        try {
            Account account = accountService.login(cardno,password);
            request.getSession().setAttribute("account",account);
            String jsonStr = JSON.toJSONString(account);
            response.getWriter().write(jsonStr);
        } catch (Exception e) {
            System.err.print(e.getMessage());
            response.getWriter().write(JSON.toJSONString(e.getMessage()));
        }

    }
    public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("account");
        request.getRequestDispatcher("/login.jsp").forward(request,response);
    }

    public void modified(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IAccountService accountService = new AccountService();
        String cardno = request.getParameter("cardno");
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");

        try {
            int result = accountService.modifiedPass(cardno,oldPass,newPass);
            response.getWriter().write(result+"");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.getWriter().write(e.getMessage());
        }
    }
    public void tranfer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IAccountService accountService = new AccountService();
        String cardno = request.getParameter("cardno");
        String toCardno = request.getParameter("toCardno");
        String money = request.getParameter("money");
        try {
            int result = accountService.tranferAccount(cardno,Double.valueOf(money),toCardno);
            response.getWriter().write(result+"");
        } catch (Exception e) {
            System.err.print(e.getMessage());
            response.getWriter().write(e.getMessage());
        }
    }
    public void getBalance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IAccountService accountService = new AccountService();
        String cardno = request.getParameter("cardno");
        Double d  = accountService.getBalance(cardno);
        response.getWriter().write(d+"");
    }

}
