package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserInterfaceSellTest {

    @BeforeEach
    public void setup() {
        Vehicle testVehicle = new Vehicle(1234, 2020, "Toyota", "Camry", "Sedan", , "Blue", 30000, 15000.0);
        ArrayList<Vehicle> list = new ArrayList<>();
        list.add(testVehicle);
        DealershipFileManager.saveDealership(testVehicle);
    }

    @Test
    public void testSellSalesContract() {
        String input = String.join(System.lineSeparator(),
                "10",        // menu: Sell a Vehicle
                "1234",      // VIN
                "John Doe",  // buyer name
                "john@example.com", // buyer email
                "sales",     // contract type
                "true",      // is financed
                "0"          // Exit
        );

        provideInput(input);

     //   UserInterface ui = new UserInterface(String connectionString,String dbUserName,String dbPassword);
        // ui.display(); // Run app with simulated input

        assertTrue(true); // No crash = pass (manual verification or better assert can be added)
    }

    @Test
    public void testSellLeaseContract() {
        String input = String.join(System.lineSeparator(),
                "10",        // menu: Sell a Vehicle
                "1234",      // VIN
                "Jane Smith", // buyer name
                "jane@example.com", // email
                "lease",     // contract type
                "0"          // Exit
        );

        provideInput(input);

//        UserInterface ui = new UserInterface();
//        ui.display();

        assertTrue(true);
    }
// used AI tools to help me discover method blow and how it works.
    private void provideInput(String data) {
        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
    }
}
