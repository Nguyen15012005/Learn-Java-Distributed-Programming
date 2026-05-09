package iuh.fit;


import iuh.fit.dao.OrderDao;
import iuh.fit.dao.OrderDetailDao;
import iuh.fit.dao.ProductDao;
import iuh.fit.dao.SupplierDao;
import iuh.fit.model.Order;
import iuh.fit.model.Product;
import iuh.fit.model.Supplier;

import java.util.List;

/**
 * Author : TrungNguyen
 * Date   : 4/2/2026
 * Description:
 *///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

//        List<Product> res = ProductDao.listProductsBySupplier("Exotic Liquids", 1, 2);
//        res.forEach(System.out::println);
//
//        Supplier supplier = new Supplier("S00990", "Nguyen Dep Trai Vai l", "Giam Doc Nguyen", "SinGapo");
//        boolean update = SupplierDao.updateSupplier(supplier);
//        System.out.println(update?"Update thanh cong":"Update that bai");
//
//        double totalPrice = OrderDao.calculateTotalOrder("O008");
//        System.out.println("Tong tien cua don hang O008: " + totalPrice);
//
//        List<Product> res1 = ProductDao.listProductsByName("Ch", 1, 2);
//        res1.forEach(System.out::println);

//        List<Order> res = OrderDao.listOrdersByStatus("COMPLETED");
//        res.forEach(System.out::println);
//        List<Supplier> res = SupplierDao.listSuppliersByCountry("Japan");
//        res.forEach(System.out::println);
//        Product product = new Product("P004454", "Iphone 18", "1kg", 39000000, 32);
//        boolean addProduct = ProductDao.addProduct(product, "S003");
//        System.out.println(addProduct? "Them san pham moi thanh cong": "them san pham that bai");

//        boolean addOrderDetail = OrderDetailDao.addOrderDetail("O009", "P010", 65, 433.2, 65.3);
//        System.out.println(addOrderDetail?"Them thanh cong":"Them that bai");

        boolean update = OrderDao.updateOrderStatus("O001","CANCELLED");
        System.out.println(update?"Update thanh cong":"Update that bai");
    }






}
