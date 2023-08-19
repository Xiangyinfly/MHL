package com.service;

import com.dao.BillDao;
import com.domain.Bill;
import com.domain.Food;
import com.domain.Table;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class BillService {
    private BillDao billDao = new BillDao();
    private FoodService foodService = new FoodService();
    private TableService tableService = new TableService();

    public boolean order(int tableId,int foodId,int num) throws Exception {
        String billId = UUID.randomUUID().toString();
        double money = foodService.getFoodPrice(foodId) * num;
        int rows = billDao.executeUpdate("insert into bill value(null,?,?,?,?,now(),?,'not pay')", billId, foodId, num, money, tableId);

        return rows > 0;
    }

    public List<Bill> billList(int tableId) throws Exception {
        return billDao.executeQuery(Bill.class,"select * from bill where tableId=?",tableId);
    }

    public boolean checkout(int tableId) throws SQLException {
        int rows = billDao.executeUpdate("update bill set state='paid' where tableId=?", tableId);
        return rows > 0;
    }

    public boolean isPaid(int tableId) throws Exception {
        return billDao.executeQuery(Bill.class,"select state from bill where tableId=?",tableId).get(0).getState().equals("paid");
    }
}
