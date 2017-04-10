package com.tibaes.agaproject;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tibaes.agaproject.dao.TShirtDAO;
import com.tibaes.agaproject.helper.TShirtHelper;
import com.tibaes.agaproject.model.TShirt;

import java.io.File;

/**
 * Created by Juliana Tibães on 08/04/2017.
 */
public class NewTShirtActivity extends AppCompatActivity {

    private TShirtHelper helper;
    private String caminhoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tshirt);

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
                TShirt newShirt = helper.getTShirt();
                TShirtDAO dao = new TShirtDAO(NewTShirtActivity.this);

                if(newShirt.getId() != null){
                    dao.updateTShirt(newShirt);
                    Toast.makeText(NewTShirtActivity.this, "Camiseta "+newShirt.getModelo()+ "alterada " +
                            "com sucesso.", Toast.LENGTH_LONG).show();
                }
                else {
                    dao.insertTShirt(newShirt);
                    Toast.makeText(NewTShirtActivity.this, "Camiseta "+newShirt.getModelo()+ "cadastrada" +
                            "com sucesso.", Toast.LENGTH_LONG).show();
                }
                dao.close();
                finish();
            }
        });

        Button btnGetImage = (Button) findViewById(R.id.btn_get_image);
        btnGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);

                // essa parte muda no Android 7, estamos construindo uma URI
                // para acessar a foto utilizando o FileProvider
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(NewTShirtActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider", arquivoFoto));

                startActivityForResult(intent, 123);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == NewTShirtActivity.RESULT_OK) {
            helper.uploadImage(caminhoFoto);
        }
    }
}
