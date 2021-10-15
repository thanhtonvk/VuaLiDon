package com.quangdz.vualidon.App;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.App.Adapter.ListPhimAdapter;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.LichSu;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.R;

import java.util.ArrayList;

public class LichSuActivity extends AppCompatActivity {

    ListView lv_lichsu;
    ArrayList<Phim> phimArrayList;
    DatabaseDBContext dbContext;
    ListPhimAdapter phimAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);
        lv_lichsu = findViewById(R.id.lv_lichsu);
        phimArrayList = new ArrayList<>();
        dbContext = new DatabaseDBContext();
        loadLichSu();
    }

    private void loadLichSu() {

        dbContext.getLichSuDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    LichSu lichSu = dataSnapshot.getValue(LichSu.class);
                    if (lichSu.getIdtaikhoan().equals(Common.taiKhoan.getId())) {
                        phimArrayList.clear();
                        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dSnapshot : snapshot.getChildren()
                                ) {
                                    Phim phim = dSnapshot.getValue(Phim.class);
                                    if (lichSu.getIdphim().equals(phim.getId())) {
                                        phimArrayList.add(phim);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
                phimAdapter = new ListPhimAdapter(LichSuActivity.this, phimArrayList);
                lv_lichsu.setAdapter(phimAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}