package com.quangdz.vualidon.App;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.App.Admin.AdminActivity;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.TaiKhoan;
import com.quangdz.vualidon.R;

public class DangNhapActivity extends AppCompatActivity {

    EditText edt_email, edt_matkhau;
    Button btn_dangnhap, btn_dangky;
    DatabaseDBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhXa();
        onClick();
    }

    private void anhXa() {
        edt_email = findViewById(R.id.edt_email);
        edt_matkhau = findViewById(R.id.edt_matkhau);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        btn_dangky = findViewById(R.id.btn_dangky);
        dbContext = new DatabaseDBContext();
    }

    private void onClick() {
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edt_email.getText().toString().equals("") || edt_matkhau.getText().toString().equals("")) {
                    Toast.makeText(DangNhapActivity.this, "Email và mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    dangNhap(edt_email.getText().toString(), edt_matkhau.getText().toString());
                }
            }
        });
    }

    private void dangNhap(String email, String matkhau) {
        ProgressDialog progressDialog = new ProgressDialog(DangNhapActivity.this);
        progressDialog.setTitle("Đang đăng nhập..");
        progressDialog.show();
        dbContext.getTaiKhoanDB().auth.signInWithEmailAndPassword(email, matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
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
                            if (Common.taiKhoan.isAdmin()) {
                                startActivity(new Intent(DangNhapActivity.this, AdminActivity.class));
                                finish();
                            } else {
                                startActivity(new Intent(DangNhapActivity.this, MainAppActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(DangNhapActivity.this, "Email hoặc mất khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}