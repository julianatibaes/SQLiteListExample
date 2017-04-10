package com.tibaes.agaproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShortcutsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortcuts);

        TextView site = (TextView) findViewById(R.id.tv_site_aga);
        final TextView address = (TextView) findViewById(R.id.tv_address_aga);
        final TextView phone = (TextView) findViewById(R.id.tv_phone_aga_one);

        site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.up.edu.br/"));
                startActivity(intent);
            }
        });

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + address.getText().toString()));
                startActivity(intent);
            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + phone.getText().toString()));
                if (ActivityCompat.checkSelfPermission(ShortcutsActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                   ActivityCompat.requestPermissions(ShortcutsActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 124);
                } else{
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: " + phone.getText().toString()));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
