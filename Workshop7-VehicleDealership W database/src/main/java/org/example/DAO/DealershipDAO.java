package org.example.DAO;

import org.example.Dealership;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {
    private final String connectionString;
    private final String userName;
    private final String password;

    public DealershipDAO(String connectionString, String userName, String password) {
        this.connectionString = connectionString;
        this.userName = userName;
        this.password = password;
    }
//    public List<Dealership> getAll() {
//        List<Dealership> list = new ArrayList<>();
//        String query = " SELECT * FROM Products";
//
//        try (Connection conn = DriverManager.getConnection(connectionString, userName, password);
//             PreparedStatement stmt = conn.prepareStatement(query);
//             ResultSet rs = stmt.executeQuery()) {
//            while (rs.next()) {
//                Product newProduct = new Product();
//                newProduct.setProductID(rs.getInt("ProductID"));
//                newProduct.setProductName(rs.getString("ProductName"));
//                newProduct.setSupplierId(rs.getInt("SupplierID"));
//                newProduct.setCategoryId(rs.getInt("CategoryID"));
//                newProduct.setQuantityPerUnit(rs.getString("QuantityPerUnit"));
//                list.add(newProduct);
//            }
}
