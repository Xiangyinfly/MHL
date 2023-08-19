package com.service;

import com.dao.TableDao;
import com.domain.Employee;
import com.domain.Table;

import java.sql.SQLException;
import java.util.List;

public class TableService {
    private TableDao tableDao = new TableDao();

    public List<Table> tableList() throws Exception {
        return tableDao.executeQuery(Table.class,"select id,state from dtable");
    }

    public Table getTable(int id) throws Exception {
        List<Table> data = tableDao.executeQuery(Table.class, "select * from dtable where id=?",id);
        if (!data.isEmpty()) {
            return data.get(0);
        }
        return null;
    }

    public boolean registerTable(int id,String name,String tel) throws SQLException {
        int rows = tableDao.executeUpdate("update dtable set state='yes',orderName=?,orderTel=? where id=?", name, tel, id);
        return rows > 0;
    }

    public boolean isRegisteredTable(int id) throws Exception {
        List<Table> rTables = tableDao.executeQuery(Table.class, "select * from dtable where state='yes'");
        for (Table table :rTables) {
            if (table.getId() == id) {
                return true;
            }
        }
        return false;
    }
}
