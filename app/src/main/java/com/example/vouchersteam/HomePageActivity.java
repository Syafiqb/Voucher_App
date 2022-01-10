package com.example.vouchersteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.example.vouchersteam.dbhelper.DbVoucher;
import com.example.vouchersteam.dbmanager.VoucherManager;

public class HomePageActivity extends AppCompatActivity {

    private VoucherManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    final String[] from = new String[] {DbVoucher.COL_1 ,DbVoucher.COL_2,
            DbVoucher.COL_3, DbVoucher.COL_4 };
    final int[] to = new int[] {R.id.tvIdV, R.id.tvNameV, R.id.tvLocV, R.id.tvStatusV };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        dbManager = new VoucherManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();
        listView = (ListView) findViewById(R.id.rvListVoucher);
        listView.setEmptyView(findViewById(R.id.pbLoading));

        /* Adapter untuk menunjuk data di database
untuk di tampilkan dalam list siswa yang mana data tersebut
menunjuk ke fragment dari ListView */
        adapter = new SimpleCursorAdapter(this, R.layout.list_item_main,
                cursor, from, to, 0);
        adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);
// OnCLickListiner untuk Data Siswa yang telah ada di database
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long viewId) {
// Mengambil nilai list yang dipilih
                TextView idTextView = (TextView) view.findViewById(R.id.tvIdV);
                TextView nameTextView = (TextView) view.findViewById(R.id.tvNameV);
                TextView locTextView = (TextView) view.findViewById(R.id.tvLocV);
                TextView statTextView = (TextView) view.findViewById(R.id.tvStatusV);
// Menyimpan nilai list yang di pilih ke variabel
                String id = idTextView.getText().toString();
                String nama = nameTextView.getText().toString();
                String b = locTextView.getText().toString();
                String p = statTextView.getText().toString();
// Proses Intent untuk mengirim data ke halaman Edit
                Intent modify_intent = new Intent(getApplicationContext(),
                        UpdateProfileSuccessActivity.class);
                modify_intent.putExtra("id", id);
                modify_intent.putExtra("name", nama);
                modify_intent.putExtra("br", b);
                modify_intent.putExtra("pr", p);
                startActivity(modify_intent);
            }
        });
    }

    public void tambah(View v) {
        Intent intent = new Intent(HomePageActivity.this, TopUpActivity.class);
        startActivity(intent);
    }
}