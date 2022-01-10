package com.example.vouchersteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vouchersteam.dbhelper.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText inEmail, inPassword, inPassword2;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);

        inEmail=(EditText) findViewById(R.id.et_email);
        inPassword=(EditText) findViewById(R.id.et_password);
        inPassword2=(EditText) findViewById(R.id.et_password2);


    }

    public void submit(View view) {

        final String email = inEmail.getText().toString().trim();
        final String password = inPassword.getText().toString().trim();
        final String password2 = inPassword2.getText().toString().trim();
        if(password.equals(password2)) {
            long val = db.addUser(email, password);
            if(val > 0) {
                Toast.makeText(RegisterActivity.this, "Anda Telah Terdafar",
                        Toast.LENGTH_SHORT).show();
                Intent moveTologin = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(moveTologin);
            }else {
                Toast.makeText(RegisterActivity.this, "Pendaftaran Gagal",
                        Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(RegisterActivity.this, "Password Tidak Sesuai",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void Pindah(View view) {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
