package com.itsvickie.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.itsvickie.whatsapp.R;
import com.itsvickie.whatsapp.model.User;
import com.itsvickie.whatsapp.config.ConfigFirebase;

public class CadastroActivity extends AppCompatActivity {
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtSenha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtNome = findViewById(R.id.txtNome);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
    }

    public void cadastrarUsuario(User user){
        auth = ConfigFirebase.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    String exception = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        exception = "Senha Fraca!";
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "E-mail inválido!";
                    } catch (FirebaseAuthUserCollisionException e){
                        exception = "Usuário já cadastrado!";
                    } catch (Exception e) {
                        exception = "Erro ao cadastrar!";
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void validarCadastro(View view) {
        String nome = txtNome.getText().toString();
        String email = txtEmail.getText().toString();
        String senha = txtSenha.getText().toString();

        if (!nome.isEmpty()) {
            if (!email.isEmpty()) {
                if (!senha.isEmpty()) {
                    User user = new User();
                    user.setNome(nome);
                    user.setEmail(email);
                    user.setSenha(senha);

                    cadastrarUsuario(user);
                } else {
                    Toast.makeText(this, "Preencha a senha, por favor!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Preencha o email, por favor!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Preencha o nome, por favor!", Toast.LENGTH_SHORT).show();

        }
    }
}
