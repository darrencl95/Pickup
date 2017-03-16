package com.pickup.pickup.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.pickup.pickup.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zachschlesinger on 3/13/17.
 */

public class ProfileActivity extends AppCompatActivity {

    private ImageView profileImage;
    private StorageReference mStorageRef;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        profileImage = (ImageView) findViewById(R.id.imageView);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        StorageReference userProf = mStorageRef.child(firebaseUser.getUid());
        try {
            final File localFile = File.createTempFile(firebaseUser.getUid().toString(), "png");
            userProf.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.d("firebase_file_path", localFile.getAbsolutePath());
                            Bitmap bmp = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            profileImage.setImageBitmap(bmp);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            profileImage.setImageResource(R.drawable.default_profile);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

//        StorageReference defaultRef = mStorageRef.child("default/default.png");
//        Uri path = Uri.parse("android.resource://com.pickup.pickup/" + R.drawable.default_profile);
//        defaultRef.putFile(path)
//                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(getBaseContext(), "this worked! :)", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.e("FIREBASE_STORAGE", e.getMessage().toString());
//                        Toast.makeText(getBaseContext(), "this didn't work", Toast.LENGTH_SHORT).show();
//                    }
//                });

        profileImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageOnClick();
                return true;
            }
        });
    }

    private void imageOnClick() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();
//            textTargetUri.setText(targetUri.toString());
//            StorageReference darrenProf = mStorageRef.child("darrenProfilePicture/profile.jpg");

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
//                profileImage.setImageBitmap(bitmap);
                StorageReference userProf = mStorageRef.child(firebaseUser.getUid());
                userProf.putFile(targetUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getBaseContext(), "this worked! :)", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("FIREBASE_STORAGE", e.getMessage().toString());
                                Toast.makeText(getBaseContext(), "this didn't work", Toast.LENGTH_SHORT).show();
                            }
                        });
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
