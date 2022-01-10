package com.example.vouchersteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.vouchersteam.dbmanager.VoucherManager;

public class UpdateProfileSuccessActivity extends AppCompatActivity {

    private EditText nameText,locText, statText, idText;
    private Button updateBtn, deleteBtn;
    private long _id;
    private VoucherManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_success);

        dbManager = new VoucherManager(this);
        dbManager.open();

        nameText = (EditText) findViewById(R.id.inputRsNameEdit);
        locText = (EditText) findViewById(R.id.inputRsLocEdit);
        statText = (EditText) findViewById(R.id.inputStatEdit);
        updateBtn = (Button) findViewById(R.id.fabUpdate);
        deleteBtn = (Button) findViewById(R.id.fabDelete);
/* Membuat objek Intent dengan nilai yang dikirim objek Intent
yang telah memanggil kelas ini sebelumnya */

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("name");
        String b = intent.getStringExtra("br");
        String p = intent.getStringExtra("pr");

        _id = Long.parseLong(id);
        nameText.setText(name);
        locText.setText(b);
        statText.setText(p);
    }

    public void update(View v) {
// Menyimpan nilai kelas dan nama baru ke variabel

        String name = nameText.getText().toString();
        String loc = locText.getText().toString();
        String stat = statText.getText().toString();
/* Memanggil fungsi update melalui objek dbManager
fungsi ini membawa tiga parameter yakni _id, kelas, nama */
        dbManager.update(_id, name, loc, stat);
        this.returnHome();
    }
    public void delete(View v) {
// Memanggil fungsi delete dengan parameter _id
        dbManager.delete(_id);
        this.returnHome();
    }

    // Fungsi untuk kembali ke halaman awal
    public void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(),
                HomePageActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }
}
