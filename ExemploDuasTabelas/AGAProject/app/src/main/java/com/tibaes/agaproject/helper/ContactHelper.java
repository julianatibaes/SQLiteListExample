package com.tibaes.agaproject.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tibaes.agaproject.NewContactActivity;
import com.tibaes.agaproject.R;
import com.tibaes.agaproject.model.Client;
import com.tibaes.agaproject.util.ConvertData;

/**
 * Created by Juliana Tibães on 07/04/2017.
 */

public class ContactHelper {

    private final EditText etName, etEmail, etPhone;
    public TextView etBDay;
    private final ImageView imgFoto;

    private Client client;

    public ContactHelper(NewContactActivity activity) {
        this.etName = (EditText) activity.findViewById(R.id.et_new_name);
        this.etEmail = (EditText) activity.findViewById(R.id.et_new_email);
        this.etBDay = (TextView) activity.findViewById(R.id.et_new_b_day);
        this.etPhone = (EditText) activity.findViewById(R.id.et_new_phone);
        this.imgFoto = (ImageView) activity.findViewById(R.id.img_new_photo_contact);
        this.client = new Client();
    }

    public Client getClient(){
        client.setNome(etName.getText().toString());
        client.setEmail(etEmail.getText().toString());
        client.setNascimento(ConvertData.stringToDate(etBDay.getText().toString()));
        client.setCelular(etPhone.getText().toString());
        client.setCaminhoFoto((String) imgFoto.getTag());
        return client;
    }

    public void setClient(Client client){
        etName.setText(client.getNome());
        etEmail.setText(client.getEmail());
        etPhone.setText(client.getCelular());
        etBDay.setText(ConvertData.dateToString(client.getNascimento()));
        uploadImage(client.getCaminhoFoto());
        this.client = client;
    }

    public void uploadImage(String caminhoFoto){
        if (caminhoFoto != null){
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            imgFoto.setImageBitmap(bitmapReduzido);
            imgFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            imgFoto.setTag(caminhoFoto);
        }
    }
}
