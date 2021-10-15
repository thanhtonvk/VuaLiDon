package com.quangdz.vualidon.App.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.quangdz.vualidon.App.DangNhapActivity;
import com.quangdz.vualidon.App.LichSuActivity;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.R;

public class UserFragment extends Fragment {


    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    TextView tv_name;
    Button btn_lichsu, btn_dangxuat;
    DatabaseDBContext dbContext;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_name = view.findViewById(R.id.tv_name_user);
        btn_lichsu = view.findViewById(R.id.btn_lichsu);
        btn_dangxuat = view.findViewById(R.id.btn_dangxuat);
        dbContext  = new DatabaseDBContext();
        tv_name.setText("Xin chào: " + Common.taiKhoan.getHoten());
        btn_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbContext.getTaiKhoanDB().auth.signOut();
                startActivity(new Intent(getContext(), DangNhapActivity.class));
                getActivity().finish();
            }
        });
        btn_lichsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), LichSuActivity.class));
            }
        });
    }
}