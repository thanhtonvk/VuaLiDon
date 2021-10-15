package com.quangdz.vualidon.App;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.TaiKhoan;
import com.quangdz.vualidon.R;

public class DangKyActivity extends AppCompatActivity {

    EditText edt_email,edt_matkhau,edt_hoten;
    Button btn_dangky;
    DatabaseDBContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        anhXa();
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TaiKhoan taiKhoan = new TaiKhoan(edt_email.getText().toString(),edt_matkhau.getText().toString(),edt_hoten.getText().toString());
                if(taiKhoan.getEmail().equals("")||taiKhoan.getMatkhau().equals("")||taiKhoan.getHoten().equals("")){
                    Toast.makeText(DangKyActivity.this, "Không được bỏ trống trường nhập thông tin", Toast.LENGTH_SHORT).show();
                }else{
                    dbContext.getTaiKhoanDB().dangKyTaiKhoan(taiKhoan,DangKyActivity.this);
                }

            }
        });
    }
    private void anhXa(){
        dbContext = new DatabaseDBContext();
        edt_email = findViewById(R.id.edt_email);
        edt_matkhau = findViewById(R.id.edt_matkhau);
        edt_hoten= findViewById(R.id.edt_hoten);
        btn_dangky = findViewById(R.id.btn_dangky);
    }
}