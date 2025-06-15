package org.example;

public class LeaseContract extends Contract {
    double endingExpectedValue= getVehicleSold().getPrice()/2;
    double leaseFee = getVehicleSold().getPrice() * 0.07;
    public LeaseContract(String date, String name, String email, Vehicle vehicleSold, double totalPrice, double monthlyPayment) {
        super(date, name, email, vehicleSold, totalPrice, monthlyPayment);

    }

    public double getEndingExpectedValue() {
        return endingExpectedValue;
    }

    public void setEndingExpectedValue(double endingExpectedValue) {
        this.endingExpectedValue = endingExpectedValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {
       return getVehicleSold().getPrice()*1.12;
    }


    @Override
    public double getMonthlyPayment() {
        return getTotalPrice()/36;
    }
}
