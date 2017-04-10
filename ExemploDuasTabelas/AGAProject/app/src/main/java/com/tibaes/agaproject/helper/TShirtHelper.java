package com.tibaes.agaproject.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;

import com.tibaes.agaproject.NewTShirtActivity;
import com.tibaes.agaproject.R;
import com.tibaes.agaproject.model.TShirt;

/**
 * Created by Juliana Tibães on 26/03/2017.
 */

public class TShirtHelper {

    /* variáveis que estão na minha interface gráfica para inserir ou alterar
    os objetos
    */
    private final EditText etDescription, etModelTShirt, etPrice, etAmount;
    private final ImageView imgFoto;

    private TShirt shirt;

    public TShirtHelper(NewTShirtActivity activity) {
        etDescription = (EditText) activity.findViewById(R.id.et_new_description);
        etModelTShirt = (EditText) activity.findViewById(R.id.et_new_model);
        etPrice = (EditText) activity.findViewById(R.id.et_new_price);
        etAmount = (EditText) activity.findViewById(R.id.et_new_amount);
        imgFoto = (ImageView) activity.findViewById(R.id.img_new_photo);
        shirt = new TShirt();
    }

    public TShirt getTShirt(){
        shirt.setDescricao(etDescription.getText().toString());
        shirt.setQuantidade(Integer.parseInt(etAmount.getText().toString()));
        shirt.setModelo(etModelTShirt.getText().toString());
        shirt.setValor(Float.parseFloat(etPrice.getText().toString()));
        shirt.setCaminhoFoto((String) imgFoto.getTag());
        return shirt;
    }

    public void setTShirt(TShirt shirt){
        etDescription.setText(shirt.getDescricao());
        etModelTShirt.setText(shirt.getModelo());
        etPrice.setText(shirt.getValor().toString());
        etAmount.setText(shirt.getQuantidade().toString());
        uploadImage(shirt.getCaminhoFoto());
        this.shirt = shirt;
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
