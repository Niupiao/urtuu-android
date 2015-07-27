package ohjoseph.com.urtuu.Data;

/**
 * Created by Benjamin on 7/27/2015.
 */
public class Payment {
    public String payment_type;
    public String bank_account;
    public String card_number;
    public String cvv;
    public String holder;
    public int exp_month;
    public int exp_year;

    public Payment() {

    }

    public Payment(String payment_type, String bank_account, String card_number, String cvv, String holder, int exp_month, int exp_year) {
        this.payment_type = payment_type;
        this.bank_account = bank_account;
        this.card_number = card_number;
        this.cvv = cvv;
        this.holder = holder;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
    }
}
