      package com.example.deephelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deephelper.telas.CriarMeta;
import com.example.deephelper.telas.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

      public class MainActivity extends AppCompatActivity {

          FirebaseFirestore db = FirebaseFirestore.getInstance();
          Button btnAbrirUsuario, btnAbrirCriarMeta;
          ListView lstMetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> metasNome = new ArrayList<String>();
        metasNome.add("Terminar Projeto");
        metasNome.add("Preparar o almoço");
        metasNome.add("Levar o cachorro para passear");
        metasNome.add("Ajeitar a torneira");
        metasNome.add("Levar o carro ao mecânico");

        String[] metasPontuacao = new String[]{"100", "40", "30", "80", "120"};

        btnAbrirUsuario = findViewById(R.id.btnAbrirUsuario);
        btnAbrirCriarMeta = findViewById(R.id.btnAbriCriarMeta);
        lstMetas = findViewById(R.id.lstMetas);

        final MetaAdapter adapter = new MetaAdapter(this, metasNome, metasPontuacao);
        lstMetas.setAdapter(adapter);
        lstMetas.requestLayout();

        btnAbrirCriarMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CriarMeta.class);
                MainActivity.this.startActivity(intent);
            }
        });

        btnAbrirUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Usuario.class);
                MainActivity.this.startActivity(intent);
            }
        });

        lstMetas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

    }
}
