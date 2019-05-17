package com.alication.caloriescounter;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Data.DatabaseHandler;
import Model.Food;

public class FoodItemDetailsActivity extends AppCompatActivity {

    private TextView foodName, calories, dateTaken;
    private Button shareButton;
    private Button deleteButton;
    private int foodId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_details);

        foodName = findViewById(R.id.detsFoodName);
        calories = findViewById(R.id.detsCaloriesValue);
        dateTaken = findViewById(R.id.detsDateText);
        shareButton = findViewById(R.id.detsShareButton);
        deleteButton = findViewById(R.id.deleteButton);

        Food food = (Food) getIntent().getSerializableExtra("userObj");

        foodName.setText(food.getFoodName());
        calories.setText(String.valueOf(food.getCalories()));
        dateTaken.setText(food.getRecordDate());

        foodId = food.getFoddId();

        calories.setTextSize(34.9f);
        calories.setTextColor(Color.RED);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareCals();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(FoodItemDetailsActivity.this);
                alert.setTitle("Delete?");
                alert.setMessage("Are you sure?");
                alert.setNegativeButton("No", null);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler dba = new DatabaseHandler(getApplicationContext());
                        dba.deleteFood(foodId);

                        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(FoodItemDetailsActivity.this, DisplayFoodActivity.class));

                        FoodItemDetailsActivity.this.finish();
                    }
                });
                alert.show();
            }
        });
    }

    public void shareCals () {

        StringBuilder dataString = new StringBuilder();

        String name = foodName.getText().toString();
        String cals = calories.getText().toString();
        String date = dateTaken.getText().toString();

        dataString.append(" Food: " + name + "\n");
        dataString.append(" Calories: " + cals + "\n");
        dataString.append(" Date taken: " + date);

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_SUBJECT, "Mano kaloriju suvartojimas");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"dviraciai@ziguliai.com"});
        i.putExtra(Intent.EXTRA_TEXT, dataString.toString());

        try {

            startActivity(Intent.createChooser(i, "send mail.."));

        }catch (ActivityNotFoundException e) {
            Toast.makeText(getApplicationContext(), "Prasau ivesti adresa", Toast.LENGTH_SHORT).show();
        }
    }
}
