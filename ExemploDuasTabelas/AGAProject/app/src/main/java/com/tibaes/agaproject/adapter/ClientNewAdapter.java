package com.tibaes.agaproject.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tibaes.agaproject.R;
import com.tibaes.agaproject.model.Client;
import com.tibaes.agaproject.my_interface.RecyclerViewOnClickListener;
import com.tibaes.agaproject.util.CircleImageView;

import java.util.List;

/**
 * Created by julia on 10/04/2017.
 */

public class ClientNewAdapter extends RecyclerView.Adapter<ClientNewAdapter.MyViewHolder> {

    private List<Client> clientList;
    private LayoutInflater layoutInflater;
    private RecyclerViewOnClickListener recyclerViewOnClickListener;

    public ClientNewAdapter(Context context, List<Client> clientList) {
        this.clientList = clientList;
        this.layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setRecyclerViewOnClickListener(RecyclerViewOnClickListener
                                                       recyclerViewOnClickListener){
        this.recyclerViewOnClickListener = recyclerViewOnClickListener;
    }

    public Client getClient(int position){
        return clientList.get(position);
    }

    @Override
    public ClientNewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.list_item_client, parent, false);
        ClientNewAdapter.MyViewHolder myViewHolder = new ClientNewAdapter.MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(ClientNewAdapter.MyViewHolder myViewHolder, int position) {

        Client client = clientList.get(position);

        String pathPhoto = client.getCaminhoFoto();
        if (pathPhoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(pathPhoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
            myViewHolder.ivClient.setImageBitmap(bitmapReduzido);
            myViewHolder.ivClient.setTag(pathPhoto);
        }

        myViewHolder.tvClienName.setText(client.getNome());
        myViewHolder.tvClientPhone.setText(client.getCelular());
    }

    @Override
    public int getItemCount() {
        return clientList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        public CircleImageView ivClient;
        public TextView tvClienName;
        public TextView tvClientPhone;

        public MyViewHolder(View itemView) {
            super(itemView);

            this.ivClient = (CircleImageView) itemView.findViewById(R.id.item_list_photo_client);
            this.tvClienName = (TextView) itemView.findViewById(R.id.item_list_name);
            this.tvClientPhone = (TextView) itemView.findViewById(R.id.item_list_phone);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(recyclerViewOnClickListener != null){
                recyclerViewOnClickListener.onClickListener(v, getPosition());
            }
        }
    }
}
