package com.quangdz.vualidon.App.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.App.Adapter.ListPhimAdapter;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.R;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    TwoWayView lv_phimmoidecu, lv_phimchieurap, lv_phimlecapnhat, lv_phimhoathinh;
    ListPhimAdapter listPhimAdapter;
    DatabaseDBContext dbContext;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        setListPhimDeCu();
        setPhimChieuRap();
        setPhimHoatHinh();
        setPhimLeCapNhat();
        onClick();
    }

    private void anhXa(View view) {
        lv_phimmoidecu = view.findViewById(R.id.lv_phimmoidecu);
        lv_phimchieurap = view.findViewById(R.id.lv_phimchieurap);
        lv_phimlecapnhat = view.findViewById(R.id.lv_phimlemoicapnhat);
        lv_phimhoathinh = view.findViewById(R.id.lv_phimhoathinh);
        phimMoiDeCuList = new ArrayList<>();
        phimChieuRapList = new ArrayList<>();
        phimLeCapNhatList = new ArrayList<>();
        phimHoatHinhList = new ArrayList<>();
        dbContext = new DatabaseDBContext();
    }

    private void onClick() {

    }

    ArrayList<Phim> phimMoiDeCuList, phimChieuRapList, phimLeCapNhatList, phimHoatHinhList;

    private void setListPhimDeCu() {
        phimMoiDeCuList.clear();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.isDecu()) {
                        phimMoiDeCuList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    listPhimAdapter = new ListPhimAdapter(getContext(), phimMoiDeCuList);
                    lv_phimmoidecu.setAdapter(listPhimAdapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setPhimChieuRap() {
        phimChieuRapList.clear();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.isPhimhot()) {
                        phimChieuRapList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    listPhimAdapter = new ListPhimAdapter(getContext(), phimChieuRapList);
                    lv_phimchieurap.setAdapter(listPhimAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setPhimLeCapNhat() {
        phimLeCapNhatList.clear();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.isPhimmoi()) {
                        phimLeCapNhatList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    listPhimAdapter = new ListPhimAdapter(getContext(), phimLeCapNhatList);
                    lv_phimlecapnhat.setAdapter(listPhimAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setPhimHoatHinh() {
        phimHoatHinhList.clear();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.getTheloai().contains("Hoạt hình")) {
                        phimHoatHinhList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    listPhimAdapter = new ListPhimAdapter(getContext(), phimHoatHinhList);
                    lv_phimhoathinh.setAdapter(listPhimAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}