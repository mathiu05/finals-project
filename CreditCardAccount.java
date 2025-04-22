package MidtermrepresentativeSerrano2;

public class CreditCardAccount extends BankAccounts {

    private double creditLimit;
    private double charges;

    public CreditCardAccount() {
        creditLimit = 10000;
        charges = 0;
    }

    public CreditCardAccount(int accountNo, String accountName, double creditLimit, double charges) {
        super(String.valueOf(accountNo), accountName);
        this.creditLimit = creditLimit;
        this.charges = charges;
    }
    public double getCreditLimit(){return creditLimit;}
    public double getCharges(){return charges;}

    public void payCard(double amount){
        if(amount<charges){
            System.out.println("You transaction was successful");
            this.charges -= amount;
        }else{
            System.out.println("You are exceeding your credit limit");
        }
    }



    public void inquireAvailableCredit(){
        double availableCreditAmount;
        availableCreditAmount = creditLimit-charges;
        if(availableCreditAmount<creditLimit){
            System.out.println("You still have "+ availableCreditAmount +" left.");
        }else{
            System.out.println("You have exceeded your credit limit.");
        }
    }


    public void chargeToCard(double amount){
        double availableCreditAmount;
        availableCreditAmount = creditLimit-charges;
        if(availableCreditAmount>amount){
            System.out.println("Your transaction was successful");
            this.charges+=amount;
        }else{
            System.out.println("Transaction exceeded your credit limit.");
        }
    }
    public void cashAdvance(double amount){
        double availableCreditAmount;
        availableCreditAmount = creditLimit-charges;
        double fiftyPercent = availableCreditAmount * 0.5;
        if(fiftyPercent>amount){
            System.out.println("Cash advanced was approved");
            this.charges += amount;
        }else{
            System.out.println("Cash advance was denied");
        }
    }
}