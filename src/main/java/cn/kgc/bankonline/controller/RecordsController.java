package cn.kgc.bankonline.controller;

import cn.kgc.bankonline.entity.Account;
import cn.kgc.bankonline.entity.TransactionRecord;
import cn.kgc.bankonline.service.AccountService;
import cn.kgc.bankonline.service.IAccountService;
import cn.kgc.bankonline.util.PageUtil;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RecordsController {
    public void records(HttpServletRequest request, HttpServletResponse response) throws IOException {
        IAccountService accountService = new AccountService();
        String cardno = request.getParameter("cardno");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String indexStr = request.getParameter("index");
        Integer index = 1;
        if(indexStr!=null&&!"".equals(indexStr)){
            index = Integer.valueOf(indexStr);
        }
        PageUtil<TransactionRecord> pageUtil = accountService.getRecord(cardno,startTime,endTime,index,4);
        response.getWriter().write(JSON.toJSONString(pageUtil));
    }
}
