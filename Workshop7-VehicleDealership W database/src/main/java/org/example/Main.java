
package org.example;

        public class Main {
            public static void main(String[] args) {
                String dbUsername= args[0];
                String dbPassword= args[1];
                String connectionString = "jdbc:mysql://localhost:3306/cardealership";

                UserInterface ui = new UserInterface(connectionString,dbUsername,dbPassword);
                ui.display();
            }
        }

