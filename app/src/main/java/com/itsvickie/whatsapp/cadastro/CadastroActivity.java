package com.itsvickie.whatsapp.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itsvickie.whatsapp.R;
import com.itsvickie.whatsapp.database.inicializarDatabase;

import java.util.UUID;

public class CadastroActivity extends AppCompatActivity {
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtTelefone;
    private EditText txtSenha;
    private Button btnCadastrar;
    private User user;
    private inicializarDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtSenha = findViewById(R.id.txtSenha);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        database = new inicializarDatabase();
        database.inicializarDatabase(CadastroActivity.this);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = new User();
                user.setId(UUID.randomUUID().toString());
                user.setNome(txtNome.getText().toString());
                user.setTelefone(txtTelefone.getText().toString());
                user.setSenha(txtSenha.getText().toString());

                database.insertUser("user", user.getId(), user);
                Toast.makeText(CadastroActivity.this, "Cadastro Realizado!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
