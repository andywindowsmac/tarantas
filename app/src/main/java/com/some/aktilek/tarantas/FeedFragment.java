package com.some.aktilek.tarantas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;

public class FeedFragment extends Fragment {
    ListView productsList;
    HashMap<String, Product> products;
    Database db = Database.SHARED_INSTANCE;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        this.initViews(view);
        this.setupToolbar(view);
        products = db.products;
        setAdapter();
        setHasOptionsMenu(true);
        return view;
    }

    private void initViews(View view) {
        productsList = view.findViewById(R.id.productsList);
        productsList.setOnItemClickListener(this.handleItemClick());
    }

    private void setupToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.feedToolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
    }

    private AdapterView.OnItemClickListener handleItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MainActivity", "clicked");
                String key = ((Product) products.values().toArray()[i]).title;
                pushToDetails(key);
            }
        };
    }

    private void setAdapter() {
        ProductAdapter pointAdapter = new ProductAdapter(this.getContext(), products);
        this.productsList.setAdapter(pointAdapter);
    }

    private SearchView.OnQueryTextListener handleTextChange() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                products = db.search(query);
                setAdapter();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.feed_toolbar, menu);
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(this.handleTextChange());
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void pushToDetails (String productKey) {
        Intent intent = new Intent(this.getContext(), ProductDetailsActivity.class);
        intent.putExtra("SEARCH_DETAILS", productKey);
        startActivity(intent);
    }
}
