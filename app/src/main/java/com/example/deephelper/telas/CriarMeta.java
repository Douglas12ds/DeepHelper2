package com.example.deephelper.telas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deephelper.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CriarMeta extends AppCompatActivity {

    TextView txtMetaNome;
    TextView txtMetaPont;
    Button btnAddMeta;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_meta);

        txtMetaNome= findViewById(R.id.txtNome);
        txtMetaPont= findViewById(R.id.txtPont);
        btnAddMeta= findViewById(R.id.btnAddMeta);

        btnAddMeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CriarMeta.this,"Criando meta...",Toast.LENGTH_LONG).show();
                String mMetaNome = txtMetaNome.getText().toString();
                String mMetaPont = txtMetaPont.getText().toString();
                Map<String, Object>novaMeta = new HashMap<>();
                novaMeta.put("Nome", mMetaNome);
                novaMeta.put("Pontuação", mMetaPont);
                db.collection("metas").document(mMetaNome).set(novaMeta)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(CriarMeta.this,"Meta criada!",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });
    }
}
