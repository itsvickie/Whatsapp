package com.itsvickie.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.itsvickie.whatsapp.R;
import com.itsvickie.whatsapp.config.ConfigFirebase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        auth = ConfigFirebase.getFirebaseAuth();

        toolbar.setTitle("WhatsApp");
        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuExit:
                deslogarUsuario();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deslogarUsuario(){
        try{
            auth.signOut();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
