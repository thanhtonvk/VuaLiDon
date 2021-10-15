package com.quangdz.vualidon.Database;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quangdz.vualidon.Model.TaiKhoan;

public class TaiKhoanDB {
    public FirebaseAuth auth;
    public FirebaseUser user;
    public FirebaseDatabase database;
    public DatabaseReference reference;

    public TaiKhoanDB() {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("TaiKhoan");
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
    }

    public void dangKyTaiKhoan(TaiKhoan taiKhoan, Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Đang đăng ký");
        progressDialog.show();
        auth.createUserWithEmailAndPassword(taiKhoan.getEmail(), taiKhoan.getMatkhau()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();
                    taiKhoan.setId(user.getUid());
                    reference.child(taiKhoan.getId()).setValue(taiKhoan).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Không thể đăng ký tài khoản, kiếm tra lại email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(context, "Không thể đăng ký tài khoản, kiếm tra lại email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void xoaTaiKhoan(String id,Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Đang xóa tài khoản");
        progressDialog.show();
        reference.child(id).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    Toast.makeText(context, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
    public void capNhatTaiKhoan(TaiKhoan taiKhoan,Context context){
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Đang cập nhật tài khoản");
        progressDialog.show();
        reference.child(taiKhoan.getId()).setValue(taiKhoan).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }else{
                    Toast.makeText(context, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }
}
