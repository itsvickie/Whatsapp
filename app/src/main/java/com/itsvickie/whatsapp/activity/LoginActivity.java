package com.itsvickie.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.itsvickie.whatsapp.R;
import com.itsvickie.whatsapp.config.ConfigFirebase;
import com.itsvickie.whatsapp.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText userEmail;
    private EditText userSenha;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.txtEmail);
        userSenha = findViewById(R.id.txtSenha);
        auth = ConfigFirebase.getFirebaseAuth();
    }

    public void logarUsuario(User user){
        auth.signInWithEmailAndPassword(user.getEmail(), user.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    telaPrincipal();
                } else {
                    String exception = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        Toast.makeText(LoginActivity.this, "Usuário não cadastrado!", Toast.LENGTH_SHORT).show();
                    } catch (FirebaseAuthInvalidCredentialsException e){
                        Toast.makeText(LoginActivity.this, "E-mail ou senha inválidos!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e){
                        exception = "Erro ao realizar login! " + e.getMessage();
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    protected void onStart(){
        super.onStart();
        FirebaseUser usuarioAtual = auth.getCurrentUser();
        if (usuarioAtual != null){
            telaPrincipal();
        }
    }

    public void validarUsuario(View view){
        String email = userEmail.getText().toString();
        String senha = userSenha.getText().toString();

        if (!email.isEmpty()){
            if (!senha.isEmpty()){
                User usuario = new User();
                usuario.setEmail(email);
                usuario.setSenha(senha);

                logarUsuario(usuario);
            } else {
                Toast.makeText(this, "Digite sua senha!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Digite seu Email!", Toast.LENGTH_SHORT).show();
        }
    }

    public void telaPrincipal(){
        Intent telaPrincipal = new Intent (LoginActivity.this, MainActivity.class);
        startActivity(telaPrincipal);
    }

    public void telaCadastro(View view){
        Intent telaCadastro = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(telaCadastro);
    }
}
