package com.tibaes.listexample.helper;

import android.widget.EditText;

import com.tibaes.listexample.NewTShirtActivity;
import com.tibaes.listexample.R;
import com.tibaes.listexample.model.TShirt;

/**
 * Created by julia on 26/03/2017.
 */

public class TShirtHelper {

    private final EditText etDescription, etModelTShirt, etPrice, etAmount;
    private TShirt shirt;

    public TShirtHelper(NewTShirtActivity activity) {
        etDescription = (EditText) activity.findViewById(R.id.et_new_description);
        etModelTShirt = (EditText) activity.findViewById(R.id.et_new_model);
        etPrice = (EditText) activity.findViewById(R.id.et_new_price);
        etAmount = (EditText) activity.findViewById(R.id.et_new_amount);
        shirt = new TShirt();
    }

    public TShirtHelper(EditText etDescription,EditText etModelTShirt,EditText etPrice, EditText etAmount) {
        this.etDescription = etDescription;
        this.etModelTShirt = etModelTShirt;
        this.etPrice = etPrice;
        this.etAmount = etAmount;
    }
    public TShirt getTShirt(){
        //TShirt shirt = new TShirt();
        shirt.setDescricao(etDescription.getText().toString());
        shirt.setQuantidade(Integer.parseInt(etAmount.getText().toString()));
        shirt.setModelo(etModelTShirt.getText().toString());
        shirt.setValor(Float.parseFloat(etPrice.getText().toString()));

        return shirt;
    }

    public void setTShirt(TShirt shirt){
        etDescription.setText(shirt.getDescricao());
        etModelTShirt.setText(shirt.getModelo());
        etPrice.setText(shirt.getValor().toString());
        etAmount.setText(shirt.getQuantidade().toString());
        this.shirt = shirt;
    }
}
