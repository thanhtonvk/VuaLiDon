package com.quangdz.vualidon.App;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.LichSu;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.Model.YeuThich;
import com.quangdz.vualidon.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ThongTinPhimActivity extends AppCompatActivity {


    List<SlideModel> listPhoto;
    ImageSlider imageSlider;
    TextView tv_tenphim, tv_theloai, tv_quocgia, tv_dienvien, tv_gioithieu;
    Button btn_xemphim, btn_yeuthich;
    DatabaseDBContext dbContext;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_phim);
        anhXa();
        random = new Random();
        dbContext = new DatabaseDBContext();
        listPhoto = getListPhoto();
        imageSlider.setImageList(listPhoto);
        Phim phim = Common.phim;
        tv_tenphim.setText(phim.getTenphimtv());
        tv_theloai.setText(phim.getTheloai());
        tv_quocgia.setText(phim.getQuocgia());
        tv_dienvien.setText(phim.getDienvien());
        tv_gioithieu.setText(phim.getGioithieu());
        btn_xemphim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phim.setLuotxem(phim.getLuotxem() + 1);
                dbContext.getPhimDB().capNhatPhim(phim, ThongTinPhimActivity.this);
                LichSu lichSu = new LichSu();
                lichSu.setId(random.nextInt() + "");
                lichSu.setIdphim(phim.getId());
                lichSu.setIdtaikhoan(Common.taiKhoan.getId());
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                lichSu.setNgay(formatter.format(date));
                dbContext.getLichSuDB().themLichSu(lichSu);
                startActivity(new Intent(getApplicationContext(), XemPhimActivity.class));
            }
        });
        btn_yeuthich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YeuThich yeuThich = new YeuThich();
                yeuThich.setId(random.nextInt() + "");
                yeuThich.setIdphim(Common.phim.getId());
                yeuThich.setIdtaikhoan(Common.taiKhoan.getId());
                dbContext.getYeuThichDB().themYeuThich(yeuThich, ThongTinPhimActivity.this);
            }
        });
    }

    private void anhXa() {
        imageSlider = findViewById(R.id.slider);
        tv_tenphim = findViewById(R.id.tv_tenphim);
        tv_theloai = findViewById(R.id.tv_theloai);
        tv_quocgia = findViewById(R.id.tv_quocgia);
        tv_dienvien = findViewById(R.id.tv_dienvien);
        tv_gioithieu = findViewById(R.id.tv_mota);
        btn_xemphim = findViewById(R.id.btn_xemphim);
        btn_yeuthich = findViewById(R.id.btn_yeuthich);
    }

    private List<SlideModel> getListPhoto() {
        List<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(String.format("https://img.youtube.com/vi/%s/0.jpg", Common.phim.getId()), ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(String.format("https://img.youtube.com/vi/%s/1.jpg", Common.phim.getId()), ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(String.format("https://img.youtube.com/vi/%s/2.jpg", Common.phim.getId()), ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(String.format("https://img.youtube.com/vi/%s/3.jpg", Common.phim.getId()), ScaleTypes.CENTER_CROP));
        return imageList;
    }
}