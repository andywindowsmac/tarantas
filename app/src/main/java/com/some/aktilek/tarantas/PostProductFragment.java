package com.some.aktilek.tarantas;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;

import java.util.List;

public class PostProductFragment extends Fragment {
    private ImageButton pickImageButton;
    private EditText titleEditText;
    private EditText countEditText;
    private EditText priceEditText;
    private EditText descriptionEditText;
    private Button postProductButton;
    Database db = Database.SHARED_INSTANCE;
    Image image;

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
        this.pushToLogin();
        return view;
    }

    private void initViews(View view) {
        pickImageButton = view.findViewById(R.id.pickImageButton);
        titleEditText = view.findViewById(R.id.productTitleEditText);
        countEditText = view.findViewById(R.id.productCountEditText);
        priceEditText = view.findViewById(R.id.productPriceEditText);
        descriptionEditText = view.findViewById(R.id.productDescriptionEditText);
        postProductButton = view.findViewById(R.id.postProductButton);
    }

    private void attachListener() {
        this.pickImageButton.setOnClickListener(this.handlePickImageButtonClick());
        this.postProductButton.setOnClickListener(this.handlePostProductButtonClick());
    }

    private View.OnClickListener handlePostProductButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PostProductFragment", "Handle press");
                postProduct();
            }
        };
    }

    private void postProduct() {
        if (!isInputsValid()) return;
        String title = titleEditText.getText().toString();
        int count = Integer.parseInt(countEditText.getText().toString());
        double price = Double.parseDouble(priceEditText.getText().toString());
        String description = descriptionEditText.getText().toString();
        String imageUrl = image.getPath();
        db.postProduct(
                title,
                description,
                imageUrl,
                price,
                count
        );

        clearInputs();
        ((MainTabbarActivity) getActivity()).moveTo(MainTabbarActivity.SCREENS.Home);
    }

    private void clearInputs() {
        titleEditText.setText("");
        countEditText.setText("");
        priceEditText.setText("");
        descriptionEditText.setText("");
        pickImageButton.setImageResource(R.mipmap.ic_plus_icon);
    }

    private boolean isInputsValid() {
        if (titleEditText.getText().toString().isEmpty() ||
                countEditText.getText().toString().isEmpty() ||
                priceEditText.getText().toString().isEmpty() ||
                descriptionEditText.getText().toString().isEmpty() ||
                image == null
                ) {
            Toast.makeText(getContext(), "Заполните всю форму",Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private View.OnClickListener handlePickImageButtonClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImagePicker();
            }
        };
    }

    private void openImagePicker() {
        ImagePicker.create(this) // Activity or Fragment
                .single()
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {
            // a single image only
            this.image = ImagePicker.getFirstImageOrNull(data);

            if (this.image == null ) return;

            Bitmap myBitmap = BitmapFactory.decodeFile(this.image.getPath());
            this.pickImageButton.setImageBitmap(myBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void pushToLogin() {
        if (AuthUtils.isUserAuthenticated(this.getContext())) return;

        Intent intent = new Intent(this.getContext(), LoginActivity.class);
        startActivity(intent);
    }
}
