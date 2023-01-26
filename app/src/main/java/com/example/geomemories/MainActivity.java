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
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    ActivityMainBinding binding;
    ChipNavigationBar bar;

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

        bar = findViewById(R.id.chip_app_bar);

        bar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment = null;
                switch(i){
                    case R.id.home:
                        FragmentManager settingsFragment = getSupportFragmentManager();
                        FragmentTransaction fragSettings = settingsFragment.beginTransaction();
                        settingsfragment settings = new settingsfragment();
                        fragSettings.replace(R.id.home, settings).commit();

                        break;
                    case R.id.person:
                        FragmentManager personFragment = getSupportFragmentManager();
                        FragmentTransaction fragPerson = personFragment.beginTransaction();
                        settingsfragment person = new settingsfragment();
                        fragPerson.replace(R.id.person, person).commit();
                        break;
                }
            }
        });







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