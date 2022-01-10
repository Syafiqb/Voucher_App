package com.example.vouchersteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vouchersteam.dbmanager.VoucherManager;

public class TopUpActivity extends AppCompatActivity {

    private Button addTodoBtn;
    private EditText nameEditText;
    private EditText locEditText;
    private EditText statEditText;
    private VoucherManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        nameEditText = (EditText) findViewById(R.id.inputVName);
        locEditText = (EditText) findViewById(R.id.inputVLoc);
        statEditText = (EditText) findViewById(R.id.inputVStat);
        addTodoBtn = (Button) findViewById(R.id.fabSaveRS);
// Membuat objek dari kelas DBManager
        dbManager = new VoucherManager(this);
        dbManager.open();
    }

    public void save(View v) {
// Mengambil data inputan user
        final String name = nameEditText.getText().toString();
        final String loc = locEditText.getText().toString();
        final String stat = statEditText.getText().toString();
/* Memanggil fungsi insert melalui objek dbManager dengan parameter
nilaikelas dan nama */
        dbManager.insert(name, loc, stat);
// Memindahkan halaman kembali ke daftar siswa
        Intent main = new Intent(TopUpActivity.this,
                HomePageActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(main);
    }
}