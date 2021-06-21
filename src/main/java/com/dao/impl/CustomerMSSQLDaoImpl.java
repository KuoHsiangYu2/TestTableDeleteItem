package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dao.ICustomerDao;
import com.model.CustomerBean;

public class CustomerMSSQLDaoImpl implements ICustomerDao {
    private DataSource dataSource = null;

    public CustomerMSSQLDaoImpl() {
        super();
        try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TestTableDeleteItem");
        } catch (NamingException e) {
            System.out.println("Connection pool creation failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void resetCustomerData() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String newLine = System.getProperty("line.separator");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("UPDATE DB08..Customer " + newLine);
            stringBuffer.append("SET c_show = ? " + newLine);
            String updateStatementSQL = stringBuffer.toString();
            preparedStatement = connection.prepareStatement(updateStatementSQL);
            preparedStatement.setString(1, "Y");
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int deleteCustomerDataById(List<Integer> deleteList) {
        int executeNumber = 0;

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        if (deleteList == null) {
            return executeNumber;
        } else if (deleteList.size() == 0) {
            return executeNumber;
        }

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            String newLine = System.getProperty("line.separator");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("UPDATE DB08..Customer " + newLine);
            stringBuffer.append("SET c_show = ? " + newLine);
            stringBuffer.append("WHERE pk = ?; " + newLine);
            String updateStatementSQL = stringBuffer.toString();

            preparedStatement = connection.prepareStatement(updateStatementSQL);
            int length = deleteList.size();
            for (int i = 0; i < length; i++) {
                /* 批次更新資料。把使用者勾選過要刪除的資料設定為[不顯示] */
                executeNumber++;
                preparedStatement.setString(1, "N");
                preparedStatement.setInt(2, deleteList.get(i));
                preparedStatement.addBatch();
            }
            preparedStatement.executeLargeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return executeNumber;
    }

    @Override
    public List<CustomerBean> getCustomerData() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        /* 把 許多個 JavaBean 封裝進 List裡面。 */
        List<CustomerBean> resultList = new ArrayList<CustomerBean>();

        try {
            connection = dataSource.getConnection();

            /* 關閉 auto commit，開啟交易模式 */
            connection.setAutoCommit(false);

            String newLine = System.getProperty("line.separator");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SELECT pk, c_name, c_age, c_type, c_show " + newLine);
            stringBuffer.append("FROM DB08..Customer " + newLine);
            stringBuffer.append("WHERE c_show = ?; " + newLine);
            String queryStatementSQL = stringBuffer.toString();
            preparedStatement = connection.prepareStatement(queryStatementSQL);
            preparedStatement.setString(1, "Y");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CustomerBean customerBean = new CustomerBean();
                customerBean.setPk(resultSet.getInt("pk"));
                customerBean.setcName(resultSet.getString("c_name"));
                customerBean.setcAge(resultSet.getInt("c_age"));
                customerBean.setcType(resultSet.getString("c_type"));
                customerBean.setcShow(resultSet.getString("c_show"));
                resultList.add(customerBean);
                customerBean = null;
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    preparedStatement = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                    connection = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultList;
    }
}
