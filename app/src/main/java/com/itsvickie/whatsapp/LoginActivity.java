package com.itsvickie.whatsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itsvickie.whatsapp.cadastro.CadastroActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userSenha;
    private Button btnLogin;
    private TextView txtCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.txtEmail);
        userSenha = findViewById(R.id.txtSenha);
        btnLogin = findViewById(R.id.btnEntrar);
        txtCadastro = findViewById(R.id.txtCadastro);


        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastro = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(telaCadastro);
            }
        });
    }
}
