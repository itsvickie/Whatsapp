package com.itsvickie.whatsapp.database;

import android.app.Activity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.itsvickie.whatsapp.cadastro.User;

public class inicializarDatabase {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    public void inicializarDatabase(Activity activity){
        FirebaseApp.initializeApp(activity);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void insertUser(String child, String id, User user){
        databaseReference.child(child).child(id).setValue(user);
    }
}
