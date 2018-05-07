package com.some.aktilek.tarantas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.LruCache;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product product;
    Database db = Database.SHARED_INSTANCE;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private ImageView productTestImageView;
    private NetworkImageView productImageView;
    private TextView titleTextView;
    private TextView priceTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent intent = this.getIntent();
        String productKey = intent.getStringExtra("SEARCH_DETAILS");
        this.product = db.getPointByKey(productKey);
        initViews();
        initRequest();
        bindView();
    }

    private void initRequest() {
        requestQueue = Volley.newRequestQueue(this);
        this.imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    private void bindView() {
        productImageView.setImageUrl(product.imageUrl, imageLoader);
        if (product.id.compareTo("-1") != 0) {
            productTestImageView.setImageURI(Uri.parse(product.imageUrl));
            ViewGroup.LayoutParams params = productImageView.getLayoutParams();
            params.height = 0;
        } else  {
            productImageView.setImageUrl(product.imageUrl, imageLoader);
            ViewGroup.LayoutParams params = productTestImageView.getLayoutParams();
            params.height = 0;
        }
        titleTextView.setText(product.title);
        priceTextView.setText(product.price + " tg");
        descriptionTextView.setText(product.description);
    }

    private void initViews() {
        productTestImageView = findViewById(R.id.testProductDetailsImageView);
        productImageView = findViewById(R.id.productDetailsImageView);
        titleTextView = findViewById(R.id.productDescriptionTitle);
        priceTextView = findViewById(R.id.productDescriptionPrice);
        descriptionTextView = findViewById(R.id.productDescription);
    }
}
