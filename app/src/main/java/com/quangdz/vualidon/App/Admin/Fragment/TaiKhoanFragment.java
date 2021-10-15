package com.quangdz.vualidon.App.Admin.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.TaiKhoan;
import com.quangdz.vualidon.R;

import java.util.ArrayList;
import java.util.List;


public class TaiKhoanFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tai_khoan, container, false);
    }


    AutoCompleteTextView edt_timkiem;
    ListView lv_taikhoan;
    ArrayAdapter<TaiKhoan>arrayAdapter;
    DatabaseDBContext dbContext;
    List<TaiKhoan> taiKhoanList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        loadDSTaiKhoan();
        lv_taikhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialog(i);
            }
        });
    }

    private void showDialog(int i) {
        TaiKhoan taiKhoan = taiKhoanList.get(i);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Tùy chọn");
        builder.setNegativeButton("Xóa tài khoản", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbContext.getTaiKhoanDB().xoaTaiKhoan(taiKhoan.getId(), getContext());
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    private void anhXa(View view) {
        lv_taikhoan = view.findViewById(R.id.lv_dstaikhoan);
        edt_timkiem = view.findViewById(R.id.edt_timkiem);
        dbContext = new DatabaseDBContext();
        taiKhoanList = new ArrayList<>();
    }

    private void loadDSTaiKhoan() {
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Đang lấy danh sách tài khoản");
        progressDialog.show();
        taiKhoanList.clear();
        dbContext.getTaiKhoanDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    taiKhoanList.add(dataSnapshot.getValue(TaiKhoan.class));
                }
                arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,taiKhoanList);
                lv_taikhoan.setAdapter(arrayAdapter);
                edt_timkiem.setAdapter(arrayAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}