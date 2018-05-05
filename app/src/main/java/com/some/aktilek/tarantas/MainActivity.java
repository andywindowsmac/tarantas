package com.some.aktilek.tarantas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    SearchView searchView;
    ListView productsList;
    HashMap<String, Product> products;
    Database db = new Database();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initViews();
        products = db.products;
        setAdapter();
    }

    private void initViews() {
        productsList = findViewById(R.id.productsList);
        productsList.setOnItemClickListener(this.handleItemClick());
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this.handleTextChange());
    }

    private AdapterView.OnItemClickListener handleItemClick() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("MainActivity", "clicked");
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
}
