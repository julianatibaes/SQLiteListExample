package com.tibaes.agaproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tibaes.agaproject.R;
import com.tibaes.agaproject.model.TShirt;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by Juliana Tibães on 26/03/2017.
 */

public class TShirtAdapter extends BaseAdapter {

    private final List<TShirt> tshirts;
    private final Context context;

    public TShirtAdapter(List<TShirt> tshirts, Context context) {
        this.tshirts = tshirts;
        this.context = context;
    }


    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {

        return tshirts.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return tshirts.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return tshirts.get(position).getId();
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
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

        String caminhoFoto = tShirt.getCaminhoFoto();
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            itemPhoto.setImageBitmap(bitmapReduzido);
            itemPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
            itemPhoto.setTag(caminhoFoto);
        }

        return view;
    }
}
