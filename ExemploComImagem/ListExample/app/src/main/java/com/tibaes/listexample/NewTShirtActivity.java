package com.tibaes.listexample;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tibaes.listexample.dao.TShirtDAO;
import com.tibaes.listexample.helper.TShirtHelper;
import com.tibaes.listexample.model.TShirt;

import java.io.File;

public class NewTShirtActivity extends AppCompatActivity {

    TShirtHelper helper;

    private String caminhoFoto;//novo
    //private ImageView imgFoto; //novo - remover depois

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_tshirt);

        helper = new TShirtHelper(NewTShirtActivity.this);

        //imgFoto = (ImageView) findViewById(R.id.img_new_photo); //novo - remover depois

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
                Toast.makeText(NewTShirtActivity.this, "Camiseta salva!", Toast.LENGTH_LONG).show();

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
            /*Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = bitmap.createScaledBitmap(bitmap, 150, 150, true);
            imgFoto.setImageBitmap(bitmapReduzido);
            imgFoto.setTag(caminhoFoto);
            */
        }
    }
}
