package com.tibaes.listexample;

import android.content.Intent;
import android.content.res.Resources;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tibaes.listexample.adapter.TShirtAdapter;
import com.tibaes.listexample.dao.TShirtDAO;
import com.tibaes.listexample.model.TShirt;

import java.util.ArrayList;
import java.util.List;

public class TShirtsActivity extends AppCompatActivity {

    private ListView listTShirts;
    private List<TShirt> tShirts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tshirts);

        listTShirts = (ListView) findViewById(R.id.list_t_shirt);

        //======================== início array string ===================
        // Array de string com os itens que irão popular a listView
       /* String [] tShirts = {"Camiseta AGA Basebool", "Camiseta AGA Raglan Tag", "Camisa AGA " +
                            "Camuflada Especial+", "Camiseta AGA Nuvem", "Camiseta AGA Nuvem Branca",
                            "Camiseta AGA Camuflada Especial Tag", "Camiseta AGA Imagine All The " +
                            "People - Primitive", "Camiseta AGA Selo - círculo", "Camiseta AGA Selo " +
                             "- círculo", "Camiseta AGA YHVH", "Camiseta AGA Raglan Botão - Arte " +
                            "Gera Arte Primitive", "Camiseta AGA Raglan Original", "Camiseta AGA " +
                            "Raglan Botão - Arte Gera Arte " + "Primitive", "Camiseta AGA Raglan Tag"};


        /* Adapter de array de string, utilizando um layout de string
        * disponibilizado pelo Android (simple_list_item_1) e contendo
        * os valores do array de string tShits
        */
        //ArrayAdapter<String> adapter = new ArrayAdapter <String> (this, android.R.layout.simple_list_item_1, tShits);

        //======================== fim array string ===================

        //======================== início lista de objetos ===================

        // lista de array de TShirt com os itens que irão popular a listView
        /*ArrayList <TShirt> listArrayTShirts = new ArrayList<>();


        listArrayTShirts.add(new TShirt("Camiseta AGA Basebool", (float) 85.00, R.drawable.basebool_b));
        listArrayTShirts.add(new TShirt("Camiseta AGA Raglan Tag", (float) 59.00, R.drawable.raglan_tag_f));
        listArrayTShirts.add(new TShirt("Camisa AGA Camuflada Especial+", (float) 110.00, R.drawable.camuflada_especial));
        listArrayTShirts.add(new TShirt("Camiseta AGA Nuvem", (float) 59.00, R.drawable.nuvem_f));
        listArrayTShirts.add(new TShirt("Camiseta AGA Nuvem Branca", (float) 59.00, R.drawable.nuvem__branca_f));
        listArrayTShirts.add(new TShirt("Camiseta AGA Camuflada Especial Tag", (float) 59.00, R.drawable.raglan_tag_f));
        listArrayTShirts.add(new TShirt("Camiseta AGA Imagine All The People - Primitive", (float) 59.00, R.drawable.imagine_all_the_people));
        listArrayTShirts.add(new TShirt("Camiseta AGA Selo - círculo", (float) 59.00, R.drawable.selo_circulo));
        listArrayTShirts.add(new TShirt("Camiseta AGA YHVH", (float) 59.00, R.drawable.yhvh));
        listArrayTShirts.add(new TShirt("Camiseta AGA Raglan Botão", (float) 75.00, R.drawable.botao_selo_branca));
        listArrayTShirts.add(new TShirt("Camiseta AGA com Bolso", (float) 70.00, R.drawable.bolso_cinza));
        listArrayTShirts.add(new TShirt("Camiseta AGA Letit Rain", (float) 55.00, R.drawable.bolso_cinza));

        */
        /* Adapter de array de Tshirt, utilizando um layout de string
        * disponibilizado pelo Android (simple_list_item_1) e contendo
        * os valores do array de objetos tShits
        * Nesse caso, sempre será exibido o toString() do objeto
        */
        //ArrayAdapter<TShirt> adapter = new ArrayAdapter <> (this, android.R.layout.simple_list_item_1, listArrayTShirts);

        /* Adapter de array de Tshirt, utilizando um layout personalizado
        * contendo os valores do array de objetos tShits
        */
        //TShirtAdapter adapter = new TShirtAdapter (this, listArrayTShirts);
        carregar();


        // ==========================
        listTShirts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TShirt shirt = (TShirt)  listTShirts.getItemAtPosition(position);
                Toast.makeText(TShirtsActivity.this, "Camiseta " +
                        shirt.getModelo() + " clicada!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TShirtsActivity.this,
                        NewTShirtActivity.class);

                // pega os dados do objeto shirt e utiliza o marcador "shirt" para a intent carregar essa informação na hora de 'startar' outra
                intent.putExtra("goShirt", shirt);

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        carregar();
    }

    private void carregar() {
        // ==================== início lista com banco de dados ===========
        TShirtDAO dao = new TShirtDAO(this);
        tShirts = dao.searchTShirt();
        dao.close();

        //ArrayAdapter<TShirt> adapter = new ArrayAdapter<TShirt> (this, android.R.layout.simple_list_item_1, tShirts);

        // carregar a lista personalizada
        TShirtAdapter adapter = new TShirtAdapter (tShirts, this);

        //======================== fim lista de objetos ===================

        // Adicionando o adaptador com a lista de string no listView
        listTShirts.setAdapter(adapter);
    }

}
