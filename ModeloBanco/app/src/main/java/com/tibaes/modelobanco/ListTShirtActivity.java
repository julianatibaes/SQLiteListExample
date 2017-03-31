package com.tibaes.modelobanco;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tibaes.modelobanco.model.TShirt;

import java.util.ArrayList;
import java.util.List;

public class ListTShirtActivity extends AppCompatActivity {

    private ListView listTShirts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tshirt);

        listTShirts = (ListView) findViewById(R.id.list_t_shirt);

        // Lista de objetos que irão mostrar na tela
        List<TShirt> tShirts = new ArrayList<>();

        tShirts.add(new TShirt("Camiseta AGA Basebool", (float) 85.00, R.drawable.basebool_f));
        tShirts.add(new TShirt("Camiseta AGA Raglan Tag", (float) 59.00, R.drawable.bolso_cinza));
        tShirts.add(new TShirt("Camisa AGA Camuflada Especial+", (float) 110.00, R.drawable.camuflada_especial));
        tShirts.add(new TShirt("Camiseta AGA Nuvem", (float) 59.00, R.drawable.nuvem_f));

        // Preparando o modelo da lista
        ArrayAdapter<TShirt> adapter = new ArrayAdapter<TShirt>(this, android.R.layout.simple_list_item_1, tShirts);

        // Adicionando o adaptador com a lista de string no listView
        listTShirts.setAdapter(adapter);
    }
}
