package com.some.aktilek.tarantas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class ProductAdapter extends BaseAdapter {
    private HashMap<String, Product> products;
    private LayoutInflater layoutInflater;
    private Context context;

    public ProductAdapter(Context context, HashMap<String,Product> products) {
        this.products = products;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        Glide.with(context).load(product.imageUrl).into(viewHolder.imageView);

        return view;
    }
}

class ViewHolder {
    ImageView imageView;
    TextView textView;

    ViewHolder(View v) {
        imageView = v.findViewById(R.id.productImageView);
        textView = v.findViewById(R.id.productTitle);
    }
}