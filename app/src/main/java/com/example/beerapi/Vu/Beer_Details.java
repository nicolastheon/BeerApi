package com.example.beerapi.Vu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.beerapi.R;
import com.squareup.picasso.Picasso;

public class Beer_Details extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beer_activity);

        getScdIntent();
    }

    private void getScdIntent(){
        if(getIntent().hasExtra("name")){
            String nameString = getIntent().getStringExtra("name");
            String priceString = getIntent().getStringExtra("price");
            String imageString = getIntent().getStringExtra("image");
            String categorieString = getIntent().getStringExtra("categorie");
            String styleString = getIntent().getStringExtra("style");
            String attributesString = getIntent().getStringExtra("attributes");
            String typeString = getIntent().getStringExtra("type");
            String countryString = getIntent().getStringExtra("country");

            initTxt(nameString,priceString,imageString,categorieString,styleString,attributesString,typeString,countryString);



        }
    }

    private void initTxt(String name, String price, String image, String categorie, String style, String attributes, String type, String country){
        TextView nameTxtV=findViewById(R.id.beer_name);
        nameTxtV.setText(name);

        TextView priceTxtV=findViewById(R.id.beer_price);
        priceTxtV.setText(price + " $");

        ImageView pictureBeer= findViewById(R.id.beer_picture);
        Glide.with(pictureBeer)
                .load(image)
                .centerCrop()
                .into(pictureBeer);

        TextView categorieTxtV=findViewById(R.id.beer_categorie);
        categorieTxtV.setText(categorie);

        TextView styleTxtV=findViewById(R.id.beer_style);
        styleTxtV.setText(style);

        TextView attributesTxtV=findViewById(R.id.beer_attribute);
        attributesTxtV.setText(attributes);

        TextView typeTxtV=findViewById(R.id.beer_type);
        typeTxtV.setText(type);

        TextView countryTxtV=findViewById(R.id.beer_country);
        countryTxtV.setText(country);
    }
}
