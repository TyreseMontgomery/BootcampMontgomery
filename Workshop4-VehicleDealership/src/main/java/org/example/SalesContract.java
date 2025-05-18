package org.example;

public class SalesContract extends Contract {
    private double salesTax;
    private int recordingFee;
    private double processingFee;
    private boolean isFinanced;
    private double monthlyPayment;


    public SalesContract(String date, String name, String email, Vehicle vehicleSold,
                         double totalPrice, double monthlyPayment,
                         double salesTax, int recordingFee,
                         double processingFee, boolean isFinanced,
                         double monthlyPayment1) {
        super(date, name, email, vehicleSold, totalPrice, monthlyPayment);
        this.salesTax = salesTax;
        this.recordingFee = recordingFee;
        this.processingFee = processingFee;
        this.isFinanced = isFinanced;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public int getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean getIsFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {

        double vehiclePrice = getVehicleSold().getPrice();
        double salesTax= vehiclePrice * 0.05;
        recordingFee = 100;
        if (vehiclePrice < 10000) {
            processingFee = 295;
        } else {
            processingFee = 495;
        }

        return vehiclePrice + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) {
            return 0;
        }

        double totalPrice = getTotalPrice();

        int loanTerm;
        double interestRate;


        if (getVehicleSold().getPrice() >= 10000) {
            loanTerm = 48;
            interestRate = 0.0425;
        } else {
            loanTerm = 24;
            interestRate = 0.0525;
        }
        double monthlyRate = interestRate / 12.0;
        double loanAmount = totalPrice;
        double monthlyPayment = (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -loanTerm));
        return monthlyPayment;
    }
}
