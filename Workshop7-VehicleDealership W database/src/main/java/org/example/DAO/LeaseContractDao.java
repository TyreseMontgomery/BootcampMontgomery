package org.example.DAO;

import org.example.LeaseContract;

import java.sql.*;

public class LeaseContractDao {
    private String connectionString;
    private String dbUser;
    private String dbPassword;

    public LeaseContractDao(String connectionString, String dbUser, String dbPassword) {
        this.connectionString = connectionString;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public boolean addLeaseContract(LeaseContract contract) {
        String sql = "INSERT INTO leasecontract (vin, dealershipid, lease_date, customer_name, customer_email, " +
                "total_price, monthly_payment, expected_end_value, lease_fee) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(connectionString, dbUser, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contract.getVehicleSold().getVin());
            stmt.setInt(2, 1);
            stmt.setDate(3, Date.valueOf(contract.getDate())); // assumes "yyyy-MM-dd"
            stmt.setString(4, contract.getName());
            stmt.setString(5, contract.getEmail());
            stmt.setDouble(6, contract.getTotalPrice());
            stmt.setDouble(7, contract.getMonthlyPayment());
            stmt.setDouble(8, contract.getEndingExpectedValue());
            stmt.setDouble(9, contract.getLeaseFee());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
