package com.quangdz.vualidon.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quangdz.vualidon.Model.YeuThich;

public class YeuThichDB {
    public DatabaseReference reference;
    public FirebaseDatabase database;
    public YeuThichDB(){
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("YeuThich");
    }
    public void themYeuThich(YeuThich yeuThich, Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Đang thêm..");
        progressDialog.show();
        reference.child(yeuThich.getId()).setValue(yeuThich).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void xoaYeuThich(String id,Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Đang xóa...");
        progressDialog.show();
        reference.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
