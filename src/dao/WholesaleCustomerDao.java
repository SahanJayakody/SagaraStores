/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Wholesalecustomer;
import java.util.HashMap;
import javafx.collections.ObservableList;

/**
 *
 * @author SahaN
 */
public class WholesaleCustomerDao {

    public static ObservableList getAll() {
        return CommonDao.select("Wholesalecustomer.findAll");
    }

    public static void add(Wholesalecustomer wholesalecustomer) throws DaoException {
        CommonDao.insert(wholesalecustomer);
    }

    public static Wholesalecustomer getById(Integer id) {
        HashMap hmap = new HashMap();
        hmap.put("id", id);

        return (Wholesalecustomer) CommonDao.select("Wholesalecustomer.findById", hmap).get(0);
    }

    public static void update(Wholesalecustomer wholesalecustomer) {
        CommonDao.update(wholesalecustomer);
    }

    public static void delete(Wholesalecustomer wholesalecustomer) {
        CommonDao.delete(wholesalecustomer);
    }

    public static ObservableList getAllByName(String name) {
        HashMap hmap = new HashMap();

        hmap.put("name", "%" + name + "%");
        return CommonDao.select("Wholesalecustomer.findAllByName", hmap);

    }
}
