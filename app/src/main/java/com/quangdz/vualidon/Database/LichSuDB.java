package com.quangdz.vualidon.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quangdz.vualidon.Model.LichSu;
import com.quangdz.vualidon.Model.YeuThich;

public class LichSuDB {
    public DatabaseReference reference;
    public FirebaseDatabase database;
    public LichSuDB(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("LichSu");
    }
    public void themLichSu(LichSu lichSu){
        reference.child(lichSu.getId()).setValue(lichSu);
    }
}
