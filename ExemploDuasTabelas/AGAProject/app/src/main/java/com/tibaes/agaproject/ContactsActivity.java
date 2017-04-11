package com.tibaes.agaproject;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tibaes.agaproject.adapter.ContactAdapter;
import com.tibaes.agaproject.dao.ContactDAO;
import com.tibaes.agaproject.model.Client;

import java.util.List;

/**
 * Created by Juliana Tibães on 08/04/2017.
 */

public class ContactsActivity extends AppCompatActivity {

    //private ListView listViewContact;
    //private List<Client> clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // FRAGMENT
        ClientFragment frag = (ClientFragment)
                getSupportFragmentManager().findFragmentByTag("mainFrag");

        if(frag == null) {
            frag = new ClientFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.rl_fragment_container, frag, "mainFrag");
            ft.commit();
        }

       /* listViewContact = (ListView) findViewById(R.id.list_contact);
        carregar();

        listViewContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Client contact = (Client)  listViewContact.getItemAtPosition(position);
                Intent intent = new Intent(ContactsActivity.this,
                        NewContactActivity.class);

                // pega os dados do objeto shirt e utiliza o marcador "shirt" para a intent carregar essa informação na hora de 'startar' outra
                intent.putExtra("goClient", contact);
                startActivity(intent);

            }
        });*/

    }

  /*  private void carregar() {
        ContactDAO dao = new ContactDAO(this);
        clientList = dao.searchContact();
        dao.close();

        //ArrayAdapter<Client> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, clientList);
        // carregar a lista personalizada
        ContactAdapter adapter = new ContactAdapter (clientList, this);

        // Adicionando o adaptador com a lista de string no listView
        listViewContact.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregar();
    }
*/
}
