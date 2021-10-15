package com.quangdz.vualidon.App;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.App.Adapter.ListPhimAdapter;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.R;

import java.util.ArrayList;
import java.util.List;

public class AllPhimActivity extends AppCompatActivity {

    AutoCompleteTextView edt_timkiem;
    ListView lv_listphim;
    ListPhimAdapter phimAdapter;
    DatabaseDBContext dbContext;
    List<Phim> phimArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_phim);
        edt_timkiem = findViewById(R.id.edt_timkiemphim);
        lv_listphim = findViewById(R.id.lv_listphim);
        dbContext = new DatabaseDBContext();
        phimArrayList = new ArrayList<>();
        loadDSPhim();
    }

    private void loadDSPhim() {
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    phimArrayList.add(phim);
                }
                phimAdapter = new ListPhimAdapter(AllPhimActivity.this, phimArrayList);
                lv_listphim.setAdapter(phimAdapter);
                edt_timkiem.setAdapter(phimAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}