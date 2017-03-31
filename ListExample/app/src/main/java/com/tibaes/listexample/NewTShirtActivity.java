package com.tibaes.listexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tibaes.listexample.dao.TShirtDAO;
import com.tibaes.listexample.helper.TShirtHelper;
import com.tibaes.listexample.model.TShirt;

public class NewTShirtActivity extends AppCompatActivity {

    TShirtHelper helper;
    EditText etDescription, etModelTShirt, etPrice, etAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tshirt);

        etDescription = (EditText) findViewById(R.id.et_new_description);
        etModelTShirt = (EditText) findViewById(R.id.et_new_model);
        etPrice = (EditText) findViewById(R.id.et_new_price);
        etAmount = (EditText) findViewById(R.id.et_new_amount);


        helper = new TShirtHelper(NewTShirtActivity.this);

        Intent intent = getIntent();
        final TShirt shirt = (TShirt) intent.getSerializableExtra("goShirt");
        if (shirt != null){
            helper.setTShirt(shirt);

            Button btnDelete = (Button) findViewById(R.id.btn_delete_t_shirt);
            btnDelete.setVisibility(View.VISIBLE);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TShirtDAO dao = new TShirtDAO(NewTShirtActivity.this);
                    dao.deleteTShirt(shirt);

                    dao.close();
                    finish();
                }
            });
        }

        Button btnSave = (Button) findViewById(R.id.btn_save_t_shirt);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NewTShirtActivity.this, "Camiseta salva!", Toast.LENGTH_LONG).show();

                TShirt newShirt = helper.getTShirt();
                TShirtDAO dao = new TShirtDAO(NewTShirtActivity.this);

                if(newShirt.getId() != null){
                    //Toast.makeText(NewTShirtActivity.this, newShirt.getId().toString(), Toast.LENGTH_LONG).show();
                    dao.updateTShirt(newShirt);
                }
                else {
                    dao.insertTShirt(newShirt);
                }

                dao.close();

                finish();
            }
        });
    }
}
