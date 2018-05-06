package com.some.aktilek.tarantas;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView productsList;
    HashMap<String, Product> products;
    Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initViews();
        this.setupToolbar();
        products = db.products;
        setAdapter();
    }

    private void initViews() {
        productsList = findViewById(R.id.productsList);
        productsList.setOnItemClickListener(this.handleItemClick());
    }

    private void setupToolbar() {
        Toolbar myToolbar = findViewById(R.id.feedToolbar);
        setSupportActionBar(myToolbar);
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
        ProductAdapter pointAdapter = new ProductAdapter(this, products);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.feed_toolbar, menu);

        MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(this.handleTextChange());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void pushToDetails (String productKey) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra("SEARCH_DETAILS", productKey);
        startActivity(intent);
    }
}
