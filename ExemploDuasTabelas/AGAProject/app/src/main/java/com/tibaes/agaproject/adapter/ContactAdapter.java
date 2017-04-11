package com.tibaes.agaproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tibaes.agaproject.R;
import com.tibaes.agaproject.model.Client;
import com.tibaes.agaproject.util.CircleImageView;


import java.util.List;

/**
 * Created by Juliana Tibães on 07/04/2017.
 */

public class ContactAdapter extends BaseAdapter {

    private final List<Client> clientList;
    private final Context context;

    public ContactAdapter(List<Client> clientList, Context context) {
        this.clientList = clientList;
        this.context = context;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return clientList.size();
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
        return clientList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return clientList.get(position).getId();
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
        Client client = clientList.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_client,null);

        TextView itemModel = (TextView) view.findViewById(R.id.item_list_name);
        itemModel.setText(client.getNome());

        TextView itemPrice = (TextView) view.findViewById(R.id.item_list_phone);
        itemPrice.setText(client.getCelular());

        CircleImageView itemPhoto = (CircleImageView) view.findViewById(R.id.item_list_photo_client);

        String pathPhoto = client.getCaminhoFoto();
        if (pathPhoto != null) {
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
                Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
                itemPhoto.setImageBitmap(bitmapReduzido);
                itemPhoto.setScaleType(ImageView.ScaleType.FIT_XY);
                itemPhoto.setTag(pathPhoto);
            } catch (Exception e){
                Log.d("DEBUGGER: ", e.getMessage());
            }
        }

        return view;
    }
}
