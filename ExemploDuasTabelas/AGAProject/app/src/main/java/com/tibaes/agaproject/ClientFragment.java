package com.tibaes.agaproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.tibaes.agaproject.adapter.ClientAdapter;
import com.tibaes.agaproject.dao.ContactDAO;
import com.tibaes.agaproject.model.Client;
import com.tibaes.agaproject.my_interface.RecyclerViewOnClickListener;

import java.util.List;


public class ClientFragment extends Fragment implements RecyclerViewOnClickListener{


    private RecyclerView recyclerView;
    private List<Client> clientList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_client, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);


        carregar();

        return view;
    }

    private void carregar() {
        ContactDAO dao = new ContactDAO(getContext());
        clientList = dao.searchContact();
        dao.close();

        ClientAdapter adapter = new ClientAdapter(getActivity(), clientList);
        adapter.setRecyclerViewOnClickListener(this);
        recyclerView.setAdapter( adapter );
    }


    @Override
    public void onClickListener(View view, int position) {
        Toast.makeText(getActivity(), "Position: "+position, Toast.LENGTH_SHORT).show();

        ClientAdapter adapter = (ClientAdapter) recyclerView.getAdapter();

        Client contact = adapter.getClient(position);
        Intent intent = new Intent(getContext(),
                NewContactActivity.class);

        // pega os dados do objeto shirt e utiliza o marcador "shirt" para a intent carregar essa informação na hora de 'startar' outra
        intent.putExtra("goClient", contact);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        carregar();
    }


}
