package com.tibaes.agaproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tibaes.agaproject.adapter.TShirtAdapter;
import com.tibaes.agaproject.dao.TShirtDAO;
import com.tibaes.agaproject.model.TShirt;

import java.util.List;

/**
 * Created by Juliana Tibães on 08/04/2017.
 */
public class TShirtActivity extends AppCompatActivity {

    private ListView listTShirts;
    private List<TShirt> tShirts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tshirt);

        listTShirts = (ListView) findViewById(R.id.list_t_shirts);
        carregar();

        listTShirts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TShirt shirt = (TShirt)  listTShirts.getItemAtPosition(position);

                Intent intent = new Intent(TShirtActivity.this,
                        NewTShirtActivity.class);

                // pega os dados do objeto shirt e utiliza o marcador "shirt" para a intent carregar
                // essa informação na hora de 'startar' outra
                intent.putExtra("goShirt", shirt);
                startActivity(intent);
            }
        });
    }

    private void carregar() {
        TShirtDAO dao = new TShirtDAO(this);
        tShirts = dao.searchTShirt();
        dao.close();

        // carregar a lista personalizada
        TShirtAdapter adapter = new TShirtAdapter (tShirts, this);

        // Adicionando o adaptador com a lista de string no listView
        listTShirts.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregar();
    }
}
