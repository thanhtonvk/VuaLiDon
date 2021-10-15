package com.quangdz.vualidon.App.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.quangdz.vualidon.App.Adapter.ListPhimAdapter;
import com.quangdz.vualidon.App.AllPhimActivity;
import com.quangdz.vualidon.Database.DatabaseDBContext;
import com.quangdz.vualidon.Model.Phim;
import com.quangdz.vualidon.R;

import org.lucasr.twowayview.TwoWayView;

import java.util.ArrayList;


public class SearchFragment extends Fragment {

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    TwoWayView lv_kinhdi, lv_hanhdong, lv_hoathinh, lv_tamlitinhcam, lv_vientuong, lv_ngontinh;
    TextView tv_tatca;
    DatabaseDBContext dbContext;
    TwoWayView lv_hai;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        anhXa(view);
        setLv_kinhdi();
        setLv_hanhdong();
        setLv_hoathinh();
        setLv_tamlitinhcam();
        setLv_hai();
        setLv_vientuong();
        setLv_ngontinh();
        tv_tatca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AllPhimActivity.class));
            }
        });
    }

    private void anhXa(View view) {
        lv_kinhdi = view.findViewById(R.id.lv_phimkinhdi);
        lv_hanhdong = view.findViewById(R.id.lv_phimhanhdong);
        lv_hoathinh = view.findViewById(R.id.lv_hoathinh);
        lv_tamlitinhcam = view.findViewById(R.id.lv_phimtamlytinhcam);
        lv_hai = view.findViewById(R.id.lv_phimhai);
        lv_vientuong = view.findViewById(R.id.lv_vientuong);
        lv_ngontinh = view.findViewById(R.id.lv_ngontinh);
        tv_tatca = view.findViewById(R.id.tv_tatcacacphim);
        dbContext = new DatabaseDBContext();
    }

    private void setLv_kinhdi() {
        ArrayList<Phim> phimList = new ArrayList<>();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.getTheloai().toLowerCase().contains("kinh dị")) {
                        phimList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    ListPhimAdapter adapter = new ListPhimAdapter(getContext(), phimList);
                    lv_kinhdi.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setLv_hanhdong() {
        ArrayList<Phim> phimList = new ArrayList<>();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.getTheloai().toLowerCase().contains("hành động")) {
                        phimList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    ListPhimAdapter adapter = new ListPhimAdapter(getContext(), phimList);
                    lv_hanhdong.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setLv_hoathinh() {
        ArrayList<Phim> phimList = new ArrayList<>();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.getTheloai().toLowerCase().contains("hoạt hình")) {
                        phimList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    ListPhimAdapter adapter = new ListPhimAdapter(getContext(), phimList);
                    lv_hoathinh.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setLv_tamlitinhcam() {
        ArrayList<Phim> phimList = new ArrayList<>();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.getTheloai().toLowerCase().contains("tâm lý") || phim.getTheloai().toLowerCase().contains("tình cảm")) {
                        phimList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    ListPhimAdapter adapter = new ListPhimAdapter(getContext(), phimList);
                    lv_tamlitinhcam.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setLv_hai() {
        ArrayList<Phim> phimList = new ArrayList<>();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                phimList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.getTheloai().toLowerCase().contains("hài")) {
                        phimList.add(phim);

                    }
                }
                if (getActivity() != null) {
                    ListPhimAdapter adapter = new ListPhimAdapter(getContext(), phimList);
                    lv_hai.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setLv_vientuong() {
        ArrayList<Phim> phimList = new ArrayList<>();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.getTheloai().toLowerCase().contains("viễn tưởng")) {
                        phimList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    ListPhimAdapter adapter = new ListPhimAdapter(getContext(), phimList);
                    lv_vientuong.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setLv_ngontinh() {
        ArrayList<Phim> phimList = new ArrayList<>();
        dbContext.getPhimDB().reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()
                ) {
                    Phim phim = dataSnapshot.getValue(Phim.class);
                    if (phim.getTheloai().toLowerCase().contains("ngôn tình")) {
                        phimList.add(phim);
                    }
                }
                if (getActivity() != null) {
                    ListPhimAdapter adapter = new ListPhimAdapter(getContext(), phimList);
                    lv_ngontinh.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}