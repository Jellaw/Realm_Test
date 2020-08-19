package com.example.realmtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;

import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {
    Button btn1, btnUpdate, btnInsert;
    ListView listView;
    ArrayList list;
    EditText txtcountry;
    EditText txtpop;
    int i = 3;
    int k = 0;
    ArrayAdapter aa;
    Realm realmCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        realmCountry = Realm.getDefaultInstance();

        list = new ArrayList(realmCountry.where(Country.class).findAll());

         aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        setBtn1();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listView.setAdapter(aa);


            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Country country = (Country) o;
                Toast.makeText(MainActivity.this, "Selected :" + " " + country, Toast.LENGTH_LONG).show();
                txtcountry.setText(country.getName());
                txtpop.setText(country.getPopulation());
                k= country.getId();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmCountry.beginTransaction();
                Country c = realmCountry.createObject(Country.class);
                c.setId(i);
                c.setName(txtcountry.getText().toString());
                c.setPopulation(txtpop.getText().toString());
                i=i+1;
                realmCountry.commitTransaction();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realmCountry.beginTransaction();
                final Country c = realmCountry.createObject(Country.class);

                RealmResults<Country> rows = realmCountry.where(Country.class).equalTo("id",k).findAll();
                rows.deleteAllFromRealm();


                c.setId(k);
                c.setName(txtcountry.getText().toString());
                c.setPopulation(txtpop.getText().toString());
                realmCountry.commitTransaction();



            }
        });

    }


    private void setBtn1(){
        btn1 = findViewById(R.id.button2);
        listView = findViewById(R.id.list1);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        txtcountry = findViewById(R.id.editCountry);
        txtpop = findViewById(R.id.editPop);

    }

}
