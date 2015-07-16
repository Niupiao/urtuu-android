package ohjoseph.com.urtuu.Shop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ohjoseph.com.urtuu.Data.DataSource;
import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/7/15.
 */
public class ItemActivity extends AppCompatActivity {

    Toolbar mToolbar;
    Item curItem;
    TextView mPriceView;
    TextView mShippingInfo;
    TextView mBuyButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_viewpager);

        // Get the Item extras
        Bundle extras = getIntent().getExtras();
        String itemName = extras.getString(ItemFragment.EXTRA_NAME);
        curItem = DataSource.get(this).getItem(itemName);

        // Inflate the ItemFragment
        ItemFragment frag = ItemFragment.newInstance(curItem);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.item_container, frag).commit();

        // Initialize and set up the toolbar
        mToolbar = (Toolbar) findViewById(R.id.action_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Initialize the price
        mPriceView = (TextView) findViewById(R.id.item_price);
        mPriceView.setText("$" + curItem.getPrice());

        mShippingInfo = (TextView) findViewById(R.id.shipping_info);

        // Set the buy button
        mBuyButton = (TextView) findViewById(R.id.buy_button);
        mBuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open bought dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(ItemActivity.this);
                builder.setTitle(R.string.add_cart)
                        .setMessage(curItem.getName() + " has been added to your cart.")
                        .setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Close the dialog window
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton(R.string.show_cart, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Dismiss dialog and open cart
                                dialog.dismiss();
                                CartFragment cartFrag = new CartFragment();
                                cartFrag.show(getSupportFragmentManager(), "My Cart");
                            }
                        }).create().show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.cart) { // Show the cart
            CartFragment cartFrag = new CartFragment();
            cartFrag.show(getSupportFragmentManager(), "My Cart");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
