package com.some.aktilek.tarantas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import java.util.List;

public class PostProductFragment extends Fragment {
    private Button pickImageButton;
    private EditText titleEditText;
    private EditText countEditText;
    private EditText priceEditText;
    private EditText descriptionEditText;

    public PostProductFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_product, container, false);

        this.initViews(view);
        this.attachListener();
        return view;
    }

    private void initViews(View view) {
        pickImageButton = view.findViewById(R.id.pickImageButton);
        titleEditText = view.findViewById(R.id.productTitleEditText);
        countEditText = view.findViewById(R.id.productCountEditText);
        priceEditText = view.findViewById(R.id.productPriceEditText);
        descriptionEditText = view.findViewById(R.id.productDescriptionEditText);
    }

    private void attachListener() {
        this.pickImageButton.setOnClickListener(this.handlePickImageButtonClick());
    }

    private View.OnClickListener handlePickImageButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.create(getActivity()) // Activity or Fragment
                        .single()
                        .start();
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // Get a list of picked images
            List<Image> images = ImagePicker.getImages(data);
            // or get a single image only
            Image image = ImagePicker.getFirstImageOrNull(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
