package com.example.vouchersteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.vouchersteam.dbhelper.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    DatabaseHelper db;

    private static final String TAG = LoginActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        inputEmail = (EditText) findViewById(R.id.et_email);
        inputPassword = (EditText) findViewById(R.id.et_password);
    }

    public void login(View v) {
        String email = inputEmail.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();
        Boolean res = db.checkuser(email, password);
        if (res == true) {
            Intent homepage = new Intent(LoginActivity.this, HomePageActivity.class);
            startActivity(homepage);
            Toast.makeText(LoginActivity.this, "Login Berhasil ",
                    Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(LoginActivity.this, "Login Gagal ",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void signup(View view) {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


}
