package com.tibaes.agaproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tibaes.agaproject.R;
import com.tibaes.agaproject.model.Client;
import com.tibaes.agaproject.my_interface.RecyclerViewOnClickListener;
import com.tibaes.agaproject.util.CircleImageView;

import java.util.List;

/**
 * Created by julia on 10/04/2017.
 * https://developer.android.com/guide/components/fragments.html?hl=pt-br
 * http://www.thiengo.com.br/recyclerview-material-design-android-parte-2
 * https://developer.android.com/reference/android/support/v7/widget/RecyclerView.html
 * https://developer.android.com/guide/topics/ui/layout/recyclerview.html
 * https://www.youtube.com/watch?v=PMX7zRD3dhg
 */

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.MyViewHolder> {

    private List<Client> clientList;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

    public ClientAdapter(Context context, List<Client> clientList) {
        this.clientList = clientList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("LOG", "onCreateViewHolder()");
        View view = layoutInflater.inflate(R.layout.list_item_client, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.i("LOG", "onBindViewHolder()");

        String pathPhoto = clientList.get(position).getCaminhoFoto();
        if (pathPhoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            holder.ivClient.setImageBitmap(bitmapReduzido);
            holder.ivClient.setTag(pathPhoto);
        }

        holder.tvClienName.setText(clientList.get(position).getNome());
        holder.tvClientPhone.setText(clientList.get(position).getCelular());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener recyclerViewOnClickListener){
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    public Client getClient(int position){
        return clientList.get(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public CircleImageView ivClient;
        public TextView tvClienName;
        public TextView tvClientPhone;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivClient = (CircleImageView) itemView.findViewById(R.id.item_list_photo_client);
            tvClienName = (TextView) itemView.findViewById(R.id.item_list_name);
            tvClientPhone = (TextView) itemView.findViewById(R.id.item_list_phone);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(recyclerViewOnClickListener != null){
                recyclerViewOnClickListener.onClickListener(view, getPosition());
            }
        }
    }
}
