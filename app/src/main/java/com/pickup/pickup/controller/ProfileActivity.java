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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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

    // declare UI components
    private ImageView profileImage;
    private Button profileBtn;

    // declare firebase components
    private StorageReference mStorageRef;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // initialize UI components
        profileImage = (ImageView) findViewById(R.id.imageView);
        profileBtn = (Button) findViewById(R.id.buttonProfile);

        // initialize firebase components
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference myRef = database.getReference(firebaseUser.getUid());
        mStorageRef = FirebaseStorage.getInstance().getReference();

        // start off by updating the image based on firebase storage
        updateImg();

        // listener to see if a new profile picture is chosen
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                updateImg();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), "Database error", Toast.LENGTH_SHORT).show();
            }
        });

        // opens gallery if profile image is long clicked
        profileImage.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                imageOnClick();
                return true;
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "But you're already here", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * used for opening pre-defined intent
     */
    private void imageOnClick() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }


    /**
     * allows user to pick an image from the gallery and then stores that in firebase storage
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            Uri targetUri = data.getData();

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                StorageReference userProf = mStorageRef.child(firebaseUser.getUid());
                userProf.putFile(targetUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                Toast.makeText(getBaseContext(), "this worked! :)", Toast.LENGTH_SHORT).show();
                                DatabaseReference myRef = database.getReference(firebaseUser.getUid());
                                @SuppressWarnings("VisibleForTests")
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();
                                myRef.child("profile_img").setValue(downloadUrl.toString());
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
                e.printStackTrace();
            }
        }
    }

    /**
     * updates the profile picture from firebase storage
     */
    private void updateImg() {
        StorageReference userProf = mStorageRef.child(firebaseUser.getUid());
        try {
            final File localFile = File.createTempFile(firebaseUser.getUid().toString(), "png");
            userProf.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                            Toast.makeText(getBaseContext(), "updateImg() success", Toast.LENGTH_SHORT).show();
                            Log.d("firebase_file_path", localFile.getAbsolutePath());
                            Bitmap bmp = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            profileImage.setImageBitmap(bmp);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getBaseContext(), "updateImg() failure", Toast.LENGTH_SHORT).show();
                            profileImage.setImageResource(R.drawable.default_profile);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        StorageReference defaultRef = mStorageRef.child("default/default.png");
        Uri path = Uri.parse("android.resource://com.pickup.pickup/" + R.drawable.default_profile);
        defaultRef.putFile(path)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(getBaseContext(), "this worked! :)", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("FIREBASE_STORAGE", e.getMessage().toString());
                        Toast.makeText(getBaseContext(), "this didn't work", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
