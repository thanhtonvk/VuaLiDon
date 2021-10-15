package com.quangdz.vualidon.App.Admin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.R;

public class NhapPhimActivity extends AppCompatActivity {

    EditText edt_idphim, edt_tenphimtv, edt_tenphimta, edt_theloai, edt_quocgia, edt_dienvien, edt_gioithieu;
    RadioButton rd_decu_co, rd_decu_khong, rd_phimmoi_co, rd_phimmoi_khong, rd_phimhot_co, rd_phimhot_khong;
    Button btn_capnhat, btn_xoa;

    DatabaseDBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_phim);
        anhXa();
        if (Common.phim != null) {
            edt_idphim.setText(Common.phim.getId());
            edt_tenphimta.setText(Common.phim.getTenphimta());
            edt_tenphimtv.setText(Common.phim.getTenphimtv());
            edt_theloai.setText(Common.phim.getTheloai());
            edt_quocgia.setText(Common.phim.getQuocgia());
            edt_dienvien.setText(Common.phim.getDienvien());
            edt_gioithieu.setText(Common.phim.getGioithieu());
            if (Common.phim.isDecu()) {
                rd_decu_co.setChecked(true);
            }
            if (Common.phim.isPhimhot()) {
                rd_phimhot_co.setChecked(true);
            }
            if (Common.phim.isPhimmoi()) {
                rd_phimmoi_co.setChecked(true);
            }
            btn_xoa.setVisibility(View.VISIBLE);
            btn_xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dbContext.getPhimDB().xoaPhim(edt_idphim.getText().toString(), NhapPhimActivity.this);
                    finish();
                    Common.phim = null;
                    btn_xoa.setVisibility(View.GONE);
                    btn_capnhat.setText("Thêm");
                }
            });
            btn_capnhat.setText("Cập nhật");
            btn_capnhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Common.phim.setTenphimta(edt_tenphimta.getText().toString());
                    Common.phim.setTenphimtv(edt_tenphimtv.getText().toString());
                    Common.phim.setTheloai(edt_theloai.getText().toString());
                    Common.phim.setQuocgia(edt_quocgia.getText().toString());
                    Common.phim.setDienvien(edt_dienvien.getText().toString());
                    Common.phim.setGioithieu(edt_gioithieu.getText().toString());
                    if (rd_decu_co.isChecked()) {
                        Common.phim.setDecu(true);
                    } else {
                        Common.phim.setDecu(false);
                    }
                    if (rd_phimhot_co.isChecked()) {
                        Common.phim.setPhimhot(true);
                    } else {
                        Common.phim.setPhimhot(false);
                    }
                    if (rd_phimmoi_co.isChecked()) {
                        Common.phim.setPhimmoi(true);
                    } else {
                        Common.phim.setPhimmoi(false);
                    }
                    btn_xoa.setVisibility(View.GONE);
                    btn_capnhat.setText("Thêm");
                    dbContext.getPhimDB().capNhatPhim(Common.phim, NhapPhimActivity.this);
                    Common.phim = null;
                    finish();
                }
            });
        } else {
            btn_capnhat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Phim phim = new Phim();
                    phim.setId(edt_idphim.getText().toString());
                    phim.setTenphimta(edt_tenphimta.getText().toString());
                    phim.setTenphimtv(edt_tenphimtv.getText().toString());
                    phim.setTheloai(edt_theloai.getText().toString());
                    phim.setQuocgia(edt_quocgia.getText().toString());
                    phim.setDienvien(edt_dienvien.getText().toString());
                    phim.setGioithieu(edt_gioithieu.getText().toString());
                    if (rd_decu_co.isChecked()) {
                        phim.setDecu(true);
                    } else {
                        phim.setDecu(false);
                    }
                    if (rd_phimhot_co.isChecked()) {
                        phim.setPhimhot(true);
                    } else {
                        phim.setPhimhot(false);
                    }
                    if (rd_phimmoi_co.isChecked()) {
                        phim.setPhimmoi(true);
                    } else {
                        phim.setPhimmoi(false);
                    }
                    dbContext.getPhimDB().themPhim(phim, NhapPhimActivity.this);
                    finish();
                }
            });

        }
    }
    private void anhXa() {
        dbContext = new DatabaseDBContext();
        edt_idphim = findViewById(R.id.edt_idphim);
        edt_tenphimtv = findViewById(R.id.edt_tenphimtv);
        edt_tenphimta = findViewById(R.id.edt_tenphimta);
        edt_theloai = findViewById(R.id.edt_theloai);
        edt_quocgia = findViewById(R.id.edt_quocgia);
        edt_dienvien = findViewById(R.id.edt_dienvien);
        edt_gioithieu = findViewById(R.id.edt_gioithieu);
        rd_decu_co = findViewById(R.id.rd_decu_co);
        rd_decu_khong = findViewById(R.id.rd_decu_khong);
        rd_phimmoi_co = findViewById(R.id.rd_phimmoi_co);
        rd_phimmoi_khong = findViewById(R.id.rd_phimmoi_khong);
        rd_phimhot_co = findViewById(R.id.rd_phimhot_co);
        rd_phimhot_khong = findViewById(R.id.rd_phimhot_khong);
        btn_capnhat = findViewById(R.id.btn_capnhat);
        btn_xoa = findViewById(R.id.btn_xoa);
    }
}