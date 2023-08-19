package com.service;

import com.dao.FoodDao;
import com.domain.Food;

import java.util.List;

public class FoodService {
    private FoodDao foodDao = new FoodDao();

    public List<Food> foodList() throws Exception {
        return foodDao.executeQuery(Food.class,"select * from food");
    }

    public boolean isFoodExist(int id) throws Exception {
        List<Food> data = foodDao.executeQuery(Food.class, "select * from food where id=?",id);
        if (!data.isEmpty()) {
            return true;
        }
        return false;
    }

    public double getFoodPrice(int id) throws Exception {
        return foodDao.executeQuery(Food.class, "select price from food where id=?", id).get(0).getPrice();
    }

    public String getFoodName(int id) throws Exception {
        return foodDao.executeQuery(Food.class, "select name from food where id=?", id).get(0).getName();
    }
}
