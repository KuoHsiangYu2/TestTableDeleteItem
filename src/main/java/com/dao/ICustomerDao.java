package com.dao;

import java.util.List;

import com.model.CustomerBean;

public interface ICustomerDao {

    List<CustomerBean> getCustomerData();

    int deleteCustomerDataById(List<Integer> deleteList);

    void resetCustomerData();

}
