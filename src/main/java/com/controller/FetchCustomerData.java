package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.dao.ICustomerDao;
import com.dao.impl.CustomerMSSQLDaoImpl;
import com.model.CustomerBean;

@WebServlet("/FetchCustomerData")
public class FetchCustomerData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public FetchCustomerData() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        ICustomerDao customerDao = new CustomerMSSQLDaoImpl();
        List<CustomerBean> customerBeanList = customerDao.getCustomerData();

        /* 把 ArrayList裡的資料轉換成 JSON物件，接著把一個一個 JSON物件 塞入 JSON陣列 裡面去，最後把 JSON格式資料 打到前端去。 */
        Map<String, Object> customerBeanMap = null;
        JSONArray jsonArray = new JSONArray();
        System.out.println("fetch customer data size -> " + customerBeanList.size());
        for (int i = 0, len = customerBeanList.size(); i < len; i++) {
            CustomerBean unit = customerBeanList.get(i);
            customerBeanMap = new HashMap<String, Object>();
            customerBeanMap.put("pk", unit.getPk());// 編號
            customerBeanMap.put("cName", unit.getcName());// 姓名
            customerBeanMap.put("cAge", unit.getcAge());// 年齡
            customerBeanMap.put("cType", unit.getcType());// 類型, 分類
            customerBeanMap.put("cShow", unit.getcShow());// 用來控制是否顯示的flag
            jsonArray.put(customerBeanMap);
            customerBeanMap = null;
            unit = null;
        }

        response.setStatus(HttpServletResponse.SC_OK);

        /* 把資料包裝成JSON格式打出到前端 */
        PrintWriter out = response.getWriter();
        out.print(jsonArray.toString());
    }
}
