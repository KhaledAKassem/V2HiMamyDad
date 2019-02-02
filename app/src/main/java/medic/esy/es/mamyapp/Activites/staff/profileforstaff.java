package medic.esy.es.mamyapp.Activites.staff;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.Activites.model.staff;
import medic.esy.es.mamyapp.Activites.model.upload;
import medic.esy.es.mamyapp.R;


public class profileforstaff extends Fragment {

    FirebaseAuth myAuth;
    TextView staffAddress,staffName,staffHome,staffGender,staffEmail;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private ImageView profile_imagestaff;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private Uri mImageUri;
    String imageFilePath;
    public static final int PICK_IMAGE = 100;
    public static final int REQUEST_PERMISSION=200;
    private Button Update_Profile_Pic_staff;
    Uri photoURI;
    String uid_profile;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profileforstaff, container, false);
        myAuth = FirebaseAuth.getInstance();
        staffAddress = (TextView) root.findViewById(R.id.tv_address);
        Update_Profile_Pic_staff=(Button)root.findViewById(R.id.Update_Profile_Pic_staff);
        staffName = (TextView) root.findViewById(R.id.tv_name);
        staffHome = (TextView) root.findViewById(R.id.tvHomePhone);
        staffGender = (TextView) root.findViewById(R.id.tvGender);
        staffEmail = (TextView) root.findViewById(R.id.staffEmail);
        profile_imagestaff = (ImageView) root.findViewById(R.id.profile_imagestaff);


        ///////////////////////////////////////////////////////////////////////
        uid_profile= FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference("StaffProfilePhoto").child(uid_profile).child("mImageUrl");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() !=null) {
                    Picasso.with(getActivity()).load(dataSnapshot.getValue().toString()).fit().centerCrop().into(profile_imagestaff);
                }else{
                    Toast.makeText(getActivity(),"please update your profile pic !",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        /////////////////////////////////////////////////////////////////////////////

        storageReference=FirebaseStorage.getInstance().getReference("profile_staff");
        databaseReference=FirebaseDatabase.getInstance().getReference("StaffProfilePhoto");
        profile_imagestaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION);
                }
                openCameraIntent();
            }
        });
        Update_Profile_Pic_staff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 uploadFile();
            }
        });
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'staff' node
        mFirebaseDatabase = mFirebaseInstance.getReference("staff").child(uid);
        mFirebaseDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value

                        staff user = dataSnapshot.getValue(staff.class);
                            String address = user.getStaffAddress();
                            String name = user.getStaffName();
                            String homePhone = user.getStaffHomePhone();
                            String gender = user.getStaffGender();
                            staffAddress.setText(address);
                            staffName.setText(name);
                            staffHome.setText(homePhone);
                            staffGender.setText(gender);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            staffEmail.setText(email);


        }
        return root;
    }
    private void openCameraIntent() {
        Intent pictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(getActivity(),getActivity().getPackageName()+".provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        photoURI);
                startActivityForResult(pictureIntent,
                        PICK_IMAGE);
            }
        }
    }


    private File createImageFile() throws IOException {
        String timeStamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss",
                        Locale.getDefault()).format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir =
                getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent data) {
        if (requestCode == PICK_IMAGE) {
            //don't compare the data to null, it will always come as  null because we are providing a file URI, so load with the imageFilePath we obtained before opening the cameraIntent
            Glide.with(this).load(imageFilePath).into(profile_imagestaff);
            // If you are using Glide.
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_PERMISSION&&grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getActivity(),"Permission ganted",Toast.LENGTH_SHORT);
            }
        }
    }
    private void uploadFile() {

        if (photoURI != null) {
            final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(photoURI));
            fileRef.putFile(photoURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        }
                    }, 4000);
                    fileRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {

                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String downloadUrl = task.getResult().toString();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date date = new Date();
                            String day = date.toString();
                            upload upload = new upload(downloadUrl, day);
                            databaseReference.child(uid_profile).setValue(upload);
                            Toast.makeText(getActivity(),"Uploaded Done !",Toast.LENGTH_LONG).show();
                        }
                    });
                    Toast.makeText(getActivity(), "Upload Successfully", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
        }

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }
}
