package com.example.geomemories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.geomemories.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*binding.bottomNavigationView.setOnMenuItemSelectListener(item ->{


            switch (item.getItemId()) {
                case R.id.person:
                    replaceFragment(new profilefragment());
                    break;
                case R.id.home:
                    replaceFragment(new settingsfragment());
                    break;

            }
            return;
        });*/


        mAuth = FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser() == null){
            Intent intent = new Intent(getApplicationContext(), login.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(getApplicationContext(), mappa.class);
            startActivity(intent);
        }

        /*Button buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(getApplicationContext(), login.class);
                startActivity(intent);
            }
        });*/
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}