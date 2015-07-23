package ohjoseph.com.urtuu.MyAccount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

public class OrderItemActivity extends AppCompatActivity {
    public final static String ITEM_NAME = "ItemName";
    Item mItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item);

        Bundle extras = getIntent().getExtras();
        String itemName = extras.getString(ITEM_NAME);
        mItem = DataSource.get(this).getOrder(itemName);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView picture = (ImageView) findViewById(R.id.item_picture);
        TextView name = (TextView) findViewById(R.id.item_name);
        name.setText(mItem.getName());
        TextView price = (TextView) findViewById(R.id.item_price);
        price.setText("$" + mItem.getPrice());
    }
}
