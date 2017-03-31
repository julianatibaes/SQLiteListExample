package com.tibaes.listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTShirts = (Button) findViewById(R.id.btn_t_shirts);
        btnTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TShirtsActivity.class);
                startActivity(intent);
            }
        });

        Button btnNewTShirt = (Button) findViewById(R.id.btn_new_t_shirts);
        btnNewTShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTShirtActivity.class);
                startActivity(intent);
            }
        });
    }
}
