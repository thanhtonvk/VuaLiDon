package com.quangdz.vualidon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.App.Admin.AdminActivity;
import com.quangdz.vualidon.App.DangNhapActivity;
import com.quangdz.vualidon.App.MainAppActivity;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.TaiKhoan;

public class MainActivity extends AppCompatActivity {

    DatabaseDBContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbContext = new DatabaseDBContext();
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Đang tải dữ liệu...");
        progressDialog.show();
        if(dbContext.getTaiKhoanDB().auth.getCurrentUser().getUid()!=null){
            dbContext.getTaiKhoanDB().reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()
                    ) {
                        if (dbContext.getTaiKhoanDB().auth.getCurrentUser().getUid().equals(dataSnapshot.getValue(TaiKhoan.class).getId())) {
                            Common.taiKhoan = dataSnapshot.getValue(TaiKhoan.class);
                        }
                    }
                    progressDialog.dismiss();
                    if(Common.taiKhoan.isAdmin()){
                        startActivity(new Intent(MainActivity.this, DangNhapActivity.class));
                    }else{
                        startActivity(new Intent(MainActivity.this, MainAppActivity.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });
        }else{
            startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
        }
    }
}