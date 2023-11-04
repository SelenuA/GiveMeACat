package com.selenu.givemeacat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    ImageView catImage;
    String tagStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        catImage = findViewById(R.id.cat_image);
        Button giveMeACat = findViewById(R.id.button);
        Spinner tag = findViewById(R.id.tag_select);
        EditText say = findViewById(R.id.say_something);
        Button reset = findViewById(R.id.reset);

        giveMeACat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int r = (int) (Math.random() * 10000);
                String link = "https://cataas.com/cat";
                if (!tagStr.isEmpty()) {
                    link += ("/" + tagStr);
                }
                String sayStr = say.getText().toString();
                if (!sayStr.isEmpty()) {
                    link += ("/says/" + sayStr);
                }
                Log.d("meow", link);
                Glide.with(getApplicationContext())
                        .load(link + "?wi=600&?a=" + r)
                        .into(catImage);
                Log.d("meow", "meow");
                say.clearFocus();
                tag.clearFocus();
            }
        });

        String tagList[] = {"None", "#Cute", "#Funny", "#Fluffy", "#Sleepy", "#Orange", "#White", "#Black"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, tagList);
        tag.setAdapter(adapter);
        tag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    tagStr = "";
                    Log.d("meow", "tag reset");
                } else {
                    tagStr = tagList[i].substring(1).toLowerCase();
                    Log.d("meow", "tag: " + tagStr);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tagStr = "";
                tag.setSelection(0);
                say.setText("");
            }
        });
    }
}