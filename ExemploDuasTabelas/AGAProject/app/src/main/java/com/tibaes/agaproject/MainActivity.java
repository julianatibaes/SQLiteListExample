package com.tibaes.agaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Juliana Tibães on 08/04/2017.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnTShirts = (Button) findViewById(R.id.btn_t_shirts);
        btnTShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TShirtActivity.class);
                startActivity(intent);
            }
        });

        Button btnNewTShirt = (Button) findViewById(R.id.btn_new_t_shirt);
        btnNewTShirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewTShirtActivity.class);
                startActivity(intent);
            }
        });

        Button btnContacts = (Button) findViewById(R.id.btn_clients);
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactsActivity.class);
                startActivity(intent);
            }
        });

        Button btnNewContact = (Button) findViewById(R.id.btn_new_clients);
        btnNewContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewContactActivity.class);
                startActivity(intent);
            }
        });

        Button btnSale = (Button) findViewById(R.id.btn_new_sale);
        btnSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SalesActivity.class);
                startActivity(intent);
            }
        });

        Button btnShortcuts = (Button) findViewById(R.id.btn_shortcuts);
        btnShortcuts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShortcutsActivity.class);
                startActivity(intent);
            }
        });

    }
}
