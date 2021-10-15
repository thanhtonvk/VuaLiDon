package com.quangdz.vualidon.App.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.App.Adapter.ListPhimAdapter;
import com.quangdz.vualidon.Common;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.Model.YeuThich;
import com.quangdz.vualidon.R;

import java.util.ArrayList;


public class FavouriteFragment extends Fragment {


    public FavouriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    ListView listView;
    ListPhimAdapter adapter;
    DatabaseDBContext dbContext;
    ArrayList<Phim> phimList;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.lv_yeuthich);
        dbContext = new DatabaseDBContext();
        phimList = new ArrayList<>();
        loadDSYeuThich();
    }

    private void loadDSYeuThich() {

        dbContext.getYeuThichDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phimList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    YeuThich yeuThich = dataSnapshot.getValue(YeuThich.class);
                    if (yeuThich.getIdtaikhoan().equals(Common.taiKhoan.getId())) {
                        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()
                                ) {
                                    Phim phim = dataSnapshot1.getValue(Phim.class);
                                    if (phim.getId().equals(yeuThich.getIdphim())) {
                                        phimList.add(phim);
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }
                }
                if(getActivity()!=null){
                    adapter = new ListPhimAdapter(getContext(), phimList);
                    listView.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}