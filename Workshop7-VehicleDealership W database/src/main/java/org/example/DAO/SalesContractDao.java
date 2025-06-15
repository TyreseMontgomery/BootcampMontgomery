package org.example.DAO;

import org.example.SalesContract;

import java.sql.*;

public class SalesContractDao {
    private String connectionString;
    private String dbUser;
    private String dbPassword;

    public SalesContractDao(String connectionString, String dbUser, String dbPassword) {
        this.connectionString = connectionString;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public boolean addSalesContract(SalesContract contract) {
        String sql = "INSERT INTO salescontracts (vin, dealershipid, sale_date, customer_name, customer_email, " +
                "total_price, monthly_payment, sales_tax, recording_fee, processing_fee, is_financed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, contract.getVehicleSold().getVin());
            stmt.setInt(2,  1);
            stmt.setDate(3, Date.valueOf(contract.getDate())); // assumes format "yyyy-MM-dd"
            stmt.setString(4, contract.getName());
            stmt.setString(5, contract.getEmail());
            stmt.setDouble(6, contract.getTotalPrice());
            stmt.setDouble(7, contract.getMonthlyPayment());
            stmt.setDouble(8, contract.getSalesTax());
            stmt.setInt(9, contract.getRecordingFee());
            stmt.setDouble(10, contract.getProcessingFee());
            stmt.setBoolean(11, contract.getIsFinanced());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
