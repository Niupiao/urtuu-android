package ohjoseph.com.urtuu.Data;

import java.util.ArrayList;

/**
 * Created by Benjamin on 7/25/2015.
 */
public class Receipt {
    public int buyer_id;
    public int seller_id;
    public String item_name;
    public int item_quantity;
    public ArrayList<String> item_tags;
    public String item_type;
    public int charge;
    public String status;
    public String checkin_code;
    public Receipt() {

    }

    public Receipt(int buyer_id, int seller_id, String item_name, int item_quantity, ArrayList<String> item_tags, String item_type, int charge, String status, String checkin_code){
        this.buyer_id = buyer_id;
        this.seller_id = seller_id;
        this.item_name = item_name;
        this.item_quantity = item_quantity;
        this.item_tags = item_tags;
        this.item_type = item_type;
        this.charge = charge;
        this.status = status;
        this.checkin_code = checkin_code;
    }
}
