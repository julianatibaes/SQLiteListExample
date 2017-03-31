package com.tibaes.modelobanco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NewTShirtActivity extends AppCompatActivity {

    EditText etDescription, etModelTShirt, etPrice, etAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tshirt);

        etDescription = (EditText) findViewById(R.id.et_new_description);
        etModelTShirt = (EditText) findViewById(R.id.et_new_model);
        etPrice = (EditText) findViewById(R.id.et_new_price);
        etAmount = (EditText) findViewById(R.id.et_new_amount);

        Button btnSave = (Button) findViewById(R.id.btn_save_t_shirt);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NewTShirtActivity.this, "Camiseta salva!", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
