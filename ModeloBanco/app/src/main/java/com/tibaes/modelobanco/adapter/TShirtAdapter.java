package com.tibaes.modelobanco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tibaes.modelobanco.R;
import com.tibaes.modelobanco.model.TShirt;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by julia on 31/03/2017.
 */

public class TShirtAdapter extends BaseAdapter {

    private final List<TShirt> tshirts;
    private final Context context;

    public TShirtAdapter(List<TShirt> tshirts, Context context) {
        this.tshirts = tshirts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return tshirts.size();
    }

    @Override
    public Object getItem(int position) {
        return tshirts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tshirts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // tShirt recebe o objeto que está na position da lista tshirts
        TShirt tShirt = tshirts.get(position);

        // Faz o layout que criamos na pasta layout "inflar" no componente view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_t_shirt,null);


        TextView itemModel = (TextView) view.findViewById(R.id.item_model);
        itemModel.setText(tShirt.getModelo());

        NumberFormat formatarFloat= new DecimalFormat("0.00");
        TextView itemPrice = (TextView) view.findViewById(R.id.item_price);
        itemPrice.setText("R$ " + formatarFloat.format(tShirt.getValor()));

        ImageView itemPhoto = (ImageView) view.findViewById(R.id.item_photo);
        itemPhoto.setImageResource(tShirt.getIcon());
        return view;
    }
}
