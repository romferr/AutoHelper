package com.darkjp.AutoHelper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ImageView tears = (ImageView) findViewById(R.id.tearsToManyMothers);
        final ImageView gwt = (ImageView) findViewById(R.id.greatWesternTrail);

        tears.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), Tears.class);
                startActivity(intent);

            }
        });
         gwt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getBaseContext(), GreatWesternTrail.class);
                 startActivity(intent);
             }
         });
    }
}