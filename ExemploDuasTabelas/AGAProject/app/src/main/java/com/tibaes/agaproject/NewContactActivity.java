package com.tibaes.agaproject;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tibaes.agaproject.dao.ContactDAO;
import com.tibaes.agaproject.helper.ContactHelper;
import com.tibaes.agaproject.model.Client;

import java.io.File;

/**
 * Created by Juliana Tibães on 08/04/2017.
 */
public class NewContactActivity extends AppCompatActivity {

    private ContactHelper helper;
    private String pathPhoto;
    LinearLayout linearLayoutDate ;
    LinearLayout linearLayoutForm;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        helper = new ContactHelper(NewContactActivity.this);
        linearLayoutDate = (LinearLayout) findViewById(R.id.ll_date);
        linearLayoutForm = (LinearLayout) findViewById(R.id.ll_form);
        datePicker = (DatePicker) findViewById(R.id.dp_b_day);

        Intent intent = getIntent();
        final Client client = (Client) intent.getSerializableExtra("goClient");

        if (client != null) {
            helper.setClient(client);

            Button btnDelete = (Button) findViewById(R.id.btn_delete_contact);
            btnDelete.setVisibility(View.VISIBLE);

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ContactDAO dao = new ContactDAO(NewContactActivity.this);
                    dao.deleteContact(client);
                    dao.close();
                    finish();
                }
            });
        }

        Button btnSave = (Button) findViewById(R.id.btn_save_contact);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Client newShirt = helper.getClient();
                ContactDAO dao = new ContactDAO(NewContactActivity.this);

                if(newShirt.getId() != null){
                    dao.updateContact(newShirt);
                }
                else {
                    dao.insertContact(newShirt);
                }
                dao.close();
                finish();
            }
        });

        Button btnGetImage = (Button) findViewById(R.id.btn_get_image_contact);
        btnGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                pathPhoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(pathPhoto);

                // essa parte muda no Android 7, estamos construindo uma URI
                // para acessar a foto utilizando o FileProvider
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        FileProvider.getUriForFile(NewContactActivity.this,
                                BuildConfig.APPLICATION_ID + ".provider", arquivoFoto));

                startActivityForResult(intent, 123);
            }
        });

        Button btnDate = (Button) findViewById(R.id.btn_choose_date);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // pegar os dados dos DataPicker para obter o dia, mês e ano da forma que precisamos
                // para jogar a data no banco da forma com que queremos manipular, embora o banco
                // só aceite TEXT, nossa classe está trabalhando com o formato Data, e portanto, precisamos
                // converter as informações de forma a não dar erro.
                int mes = datePicker.getMonth();
                int dia = datePicker.getDayOfMonth();
                int ano = datePicker.getYear();

                String data = dia + "/"+mes+"/"+ano;

                helper.etBDay.setText(data);
                linearLayoutDate.setVisibility(View.GONE);
                linearLayoutForm.setVisibility(View.VISIBLE);
            }
        });


        helper.etBDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutDate.setVisibility(View.VISIBLE);
                linearLayoutForm.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == NewTShirtActivity.RESULT_OK) {
            helper.uploadImage(pathPhoto);
        }
    }
}
