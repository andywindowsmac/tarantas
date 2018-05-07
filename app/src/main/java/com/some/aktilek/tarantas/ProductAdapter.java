package com.some.aktilek.tarantas;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.HashMap;

public class ProductAdapter extends BaseAdapter {
    private HashMap<String, Product> products;
    private LayoutInflater layoutInflater;
    private Context context;
    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;

    public ProductAdapter(Context context, HashMap<String,Product> products) {
        this.products = products;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mRequestQueue = Volley.newRequestQueue(context);
        this.imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
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

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = this.layoutInflater.inflate(R.layout.row_product, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else  {
            viewHolder = (ViewHolder) view.getTag();
        }

        Product product = (Product ) this.products.values().toArray()[i];

        viewHolder.textView.setText(product.title);
        viewHolder.priceTextView.setText(product.price + " tg");

        if (product.id.compareTo("-1") != 0) {
            viewHolder.productTestImageView.setImageURI(Uri.parse(product.imageUrl));
            ViewGroup.LayoutParams params = viewHolder.imageView.getLayoutParams();
            params.height = 0;
        } else  {
            viewHolder.imageView.setImageUrl(product.imageUrl, imageLoader);
            ViewGroup.LayoutParams params = viewHolder.productTestImageView.getLayoutParams();
            params.height = 0;
        }

        return view;
    }
}

class ViewHolder {
    NetworkImageView imageView;
    TextView textView;
    TextView priceTextView;
    ImageView productTestImageView;

    ViewHolder(View v) {
        imageView = v.findViewById(R.id.productImageView);
        textView = v.findViewById(R.id.productTitle);
        priceTextView = v.findViewById(R.id.priceTextView);
        productTestImageView = v.findViewById(R.id.testProductView);
    }
}