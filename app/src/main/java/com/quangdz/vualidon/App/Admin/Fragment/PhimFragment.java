package com.quangdz.vualidon.App.Admin.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.App.Adapter.ListPhimAdapter;
import com.quangdz.vualidon.App.Admin.NhapPhimActivity;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.R;

import java.util.ArrayList;


public class PhimFragment extends Fragment {


    public PhimFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_phim, container, false);
    }

    Button btn_them;
    AutoCompleteTextView edt_timkiem;
    ListView lv_dsphim;
    DatabaseDBContext dbContext;
    ListPhimAdapter listPhimAdapter;
    ArrayList<Phim> phimList;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        layDSPhim();
        onClick();
    }
    private void anhXa(View view){
        btn_them = view.findViewById(R.id.btn_them);
        edt_timkiem= view.findViewById(R.id.edt_timkiem);
        lv_dsphim = view.findViewById(R.id.lv_dsphim);
        dbContext = new DatabaseDBContext();
        phimList = new ArrayList<>();
    }
    private void onClick(){
        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NhapPhimActivity.class));
            }
        });
        lv_dsphim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Common.phim = phimList.get(i);
            }
        });
        lv_dsphim.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
    }
    private void layDSPhim(){
        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Đang lấy danh sách phim");
        progressDialog.show();
        phimList.clear();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot:snapshot.getChildren()
                     ) {
                    phimList.add(dataSnapshot.getValue(Phim.class));
                }
                listPhimAdapter = new ListPhimAdapter(getContext(),phimList);
                lv_dsphim.setAdapter(listPhimAdapter);
                edt_timkiem.setAdapter(listPhimAdapter);
                progressDialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}