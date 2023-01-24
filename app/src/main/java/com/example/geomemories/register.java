package com.example.geomemories;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    boolean registred = false;
    private FirebaseDatabase database;

    public void goToLogin() {
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
    }

    public void register(){

        String email = ((EditText) findViewById(R.id.inputEmail)).getText().toString();
        String password = ((EditText) findViewById(R.id.inputPass)).getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            registred = true;
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(register.this, "Registration success.",
                                    Toast.LENGTH_SHORT).show();
                            registred = true;
                            String uid = user.getUid();
                            database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("message");
                            myRef.setValue("Hello, World!");

                            /*User userToDb = new User(((EditText) findViewById(R.id.inputName)).getText().toString(), ((EditText) findViewById(R.id.inputSurname)).getText().toString());
                            mDatabase.child("users").child(uid).setValue(userToDb).addOnSuccessListener(aVoid -> {
                                Log.d("TAG", "onSuccess: user Profile is created for " + uid);
                            });*/
                            goToLogin();
                        } else {
                            Toast.makeText(register.this, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView linkLogin = (TextView) findViewById(R.id.linkLogin);
        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLogin();
            }
        });

        Button btnRegister = (Button) findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

}