public class MortgageReport {
    final static byte MONTHS = 12;
    final static byte DECIMAL_CONVERSION = 100;
    private double principal;
    private double APR;
    private int mortgageTerm;
    private String prompt;
    private double monthlyPayment;
    private double totalInterest;
    int lastPayment;
    double extraPayment;


    MortgageReport() {
        principal = Console.readInput("Principle ($1K - $10M): ", 1000, 10000000);
        APR = Console.readInput("Interest (0-30%): ", 0, 30);
        mortgageTerm = (int) Console.readInput("Term of loan in years(1-30): ", 1, 30);
        extraPayment = Console.readInput("Extra payment every month: ", 0, 100000);
        monthlyPayment = (principal * (getMonthlyInterest() * Math.pow((1 + getMonthlyInterest()), getMonthlyTerm()))) /
                ((Math.pow((1 + getMonthlyInterest()), getMonthlyTerm())) - 1);
    }

    private int getMonthlyTerm() {
        return mortgageTerm * MONTHS;
    }

    private double getMonthlyInterest() {
        return ((APR / DECIMAL_CONVERSION) / MONTHS);
    }


    private double calculateBalance(double currentBalance, double extraPayment){
        double monthlyPrincipal = monthlyPayment - calculateInterest(currentBalance) + extraPayment;
        return currentBalance - monthlyPrincipal;
    }

    private double calculateInterest(double currentBalance){
        return ((currentBalance * (APR/DECIMAL_CONVERSION / 12)));
    }

    public void printMonthlyPayment(){
        System.out.println("Monthly Payment: " + Console.convertToCurrency(monthlyPayment));
    }

    public void printAmortization(){
        System.out.println("Here is your amortization schedule: ");
        double currentBalance = principal;
        for (int paymentNum = 1; paymentNum <= getMonthlyTerm(); paymentNum++) {
            currentBalance = calculateBalance(currentBalance, extraPayment);
            System.out.println("Payment #" + paymentNum + " " + Console.convertToCurrency(currentBalance) +
                    " Interest Paid: " + Console.convertToCurrency(calculateInterest(currentBalance)) +
                    " Principal Paid: " + Console.convertToCurrency(monthlyPayment - (calculateInterest(currentBalance))));

            totalInterest += calculateInterest(currentBalance);
            lastPayment = paymentNum;
            if(currentBalance < 0)
                break;
        }

        int savedMonths = getMonthlyTerm() - lastPayment;
        System.out.println("Total Interest Paid: " + Console.convertToCurrency(totalInterest));
        System.out.println("You saved " + savedMonths + " months on your mortgage!");
    }

}
