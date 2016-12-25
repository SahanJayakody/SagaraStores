/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import entity.Supplier;
import java.math.BigDecimal;
import java.util.HashMap;
import javafx.collections.ObservableList;

/**
 *
 * @author SahaN
 */
public class SupplierDao {

    public static ObservableList getAll() {
        return CommonDao.select("Supplier.findAll");
    }

    public static void add(Supplier supplier) throws DaoException {
        CommonDao.insert(supplier);
    }

    public static Supplier getById(Integer id) {
        HashMap hmap = new HashMap();
        hmap.put("id", id);

        return (Supplier) CommonDao.select("Supplier.findById", hmap).get(0);
    }

    public static void update(Supplier supplier) {
        CommonDao.update(supplier);
    }

    public static void delete(Supplier supplier) {
        CommonDao.delete(supplier);
    }

  

    public static ObservableList getAllByWithBalance(BigDecimal value) {
        HashMap hmap = new HashMap();
        hmap.put("value", value);

        return CommonDao.select("Supplier.findAllWithBalance", hmap);
    }

  

    public static ObservableList getAllByName(String name) {
        HashMap hmap = new HashMap();

        hmap.put("name", "%" + name + "%");
        return CommonDao.select("Supplier.findAllByName", hmap);

    }
}
