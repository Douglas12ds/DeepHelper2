package com.example.deephelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MetaAdapter extends ArrayAdapter{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Context context;
    private ArrayList<String> metasNome;
    private String[] metasPontuacao;

    public MetaAdapter(Context context, ArrayList<String> metasNome, String[] metasPontuacao) {
        super(context, R.layout.adapter_meta, metasNome);
        this.context = context;
        this.metasNome = metasNome;
        this.metasPontuacao = metasPontuacao;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.adapter_meta, parent, false);
        final CheckBox chkConcluida = (CheckBox) itemView.findViewById(R.id.chkConcluida);
        final TextView txtMetaNome = (TextView) itemView.findViewById(R.id.txtMetaNome);
        final TextView txtMetaPontuacao = (TextView) itemView.findViewById(R.id.txtMetaPontuacao);
        final int posicao = position;
        final String mMeta = metasNome.get(position);
        txtMetaNome.setText(mMeta);
        txtMetaPontuacao.setText(metasPontuacao[position]);

        chkConcluida.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (chkConcluida.isChecked() ) {
                    String pontos = txtMetaPontuacao.getText().toString();
                    Map<String, Object> novaPontuacao = new HashMap<>();
                    novaPontuacao.put("pontuacao", pontos);

                    DocumentReference usuario = db.collection("usuario").document("Douglas");
                    usuario.set(novaPontuacao).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    db.collection("metas").document(mMeta).delete();
                    chkConcluida.setEnabled(false);
                }
            }
        });
        return itemView;
    }
}
