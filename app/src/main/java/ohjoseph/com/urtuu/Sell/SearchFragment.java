package ohjoseph.com.urtuu.Sell;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ohjoseph.com.urtuu.Data.Item;
import ohjoseph.com.urtuu.R;

/**
 * Created by Joseph on 7/22/15.
 */
public class SearchFragment extends Fragment {

    ArrayList<Item> mItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mItems = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mItems.add(new Item("Search Item"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, parent, false);

        ListView lv = (ListView) v.findViewById(android.R.id.list);
        ItemSearchAdapter adapter = new ItemSearchAdapter(mItems);
        lv.setAdapter(adapter);

        return v;
    }

    public class ItemSearchAdapter extends ArrayAdapter<Item> {

        public ItemSearchAdapter(ArrayList<Item> items) {
            super(getActivity(), 0, items);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.list_item_search_sell, parent, false);
            }

            ImageView image = (ImageView) convertView.findViewById(R.id.image);
            TextView name = (TextView) convertView.findViewById(R.id.name);
            TextView price = (TextView) convertView.findViewById(R.id.price);

            return convertView;
        }
    }
}
