package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import com.dao.ICustomerDao;
import com.dao.impl.CustomerMSSQLDaoImpl;

@WebServlet("/DeleteCustomerById")
public class DeleteCustomerById extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteCustomerById() {
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

        JSONArray jsonArray = null;
        String jsonInputString = "";

        Enumeration<String> enumeration = request.getParameterNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String[] values = request.getParameterValues(key);
            System.out.println("key = [" + key + "]");
            System.out.println("values = [" + Arrays.toString(values) + "]");
            jsonInputString = key;
        }

        jsonArray = new JSONArray(jsonInputString);
        System.out.println("jsonArray -> [" + jsonArray + "]");
        System.out.println("Get JSON array data in Back-End.");

        if (jsonArray != null) {
            List<Integer> deleteList = new ArrayList<Integer>();
            for (int i = 0, len = jsonArray.length(); i < len; i++) {
                int unit = jsonArray.getInt(i);
                System.out.println("unit -> [" + unit + "]");
                deleteList.add(unit);
            }
            ICustomerDao customerDao = new CustomerMSSQLDaoImpl();
            int executeNumber = customerDao.deleteCustomerDataById(deleteList);
            System.out.println("executeNumber -> [" + executeNumber + "]");
        }

        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter out = response.getWriter();
        out.print("delete customer data successful");
    }
}
