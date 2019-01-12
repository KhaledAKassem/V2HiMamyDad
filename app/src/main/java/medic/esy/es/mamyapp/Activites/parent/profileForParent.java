package medic.esy.es.mamyapp.Activites.parent;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
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
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;

import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.Activites.model.upload;
import medic.esy.es.mamyapp.R;

public class profileForParent extends Fragment {

    private TextView name,email,address,age,phone;
    private ImageView imageView;
    String imageFilePath;
    public static final int PICK_IMAGE = 100;
    public static final int REQUEST_PERMISSION=200;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private Uri image_uri;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Button upload_profile_image;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View root=inflater.inflate(R.layout.fragment_profile_for_parent, container, false);

        databaseReference=FirebaseDatabase.getInstance().getReference("parent_profile_photo").child(commonParent.currentuser.getPhone()).child("mImageUrl");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() !=null) {
                    Picasso.with(getContext()).load(dataSnapshot.getValue().toString()).fit().centerCrop().into(imageView);

                }else{
                    Toast.makeText(getActivity(),"please update your profile pic !",Toast.LENGTH_LONG).show();

                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        ///////////////////////////////////////////////////////////////////////////////////////
        storageReference = FirebaseStorage.getInstance().getReference("profile_parent");
        databaseReference = FirebaseDatabase.getInstance().getReference("parent_profile_photo").child(commonParent.currentuser.getPhone());
        imageView=(ImageView)root.findViewById(R.id.parent_profile_image) ;
        upload_profile_image=(Button)root.findViewById(R.id.upload_profile_parent_pic);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();




        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_PERMISSION);
                }
                openCameraIntent();
            }
        });
        upload_profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAnonymously();
                Toast.makeText(getActivity(),"Upload start now",Toast.LENGTH_LONG).show();

            }
        });
        name=(TextView)root.findViewById(R.id.forusername);
        email=(TextView)root.findViewById(R.id.foremail);
        address=(TextView)root.findViewById(R.id.foraddress);
        age=(TextView)root.findViewById(R.id.forage);
        phone=(TextView)root.findViewById(R.id.phone);
        /////////////////////////////////////////////////////
        String Sname =  commonParent.currentuser.getName();
        String Semail=  commonParent.currentuser.getEmail();
        String Saddress=commonParent.currentuser.getAddress();
        String Sage=    commonParent.currentuser.getAge();
        String Sphone=  commonParent.currentuser.getPhone();
        //////////////////////////////////////////////////////
        name.setText(Sname);
        email.setText(Semail);
        address.setText(Saddress);
        age.setText(Sage);
        phone.setText(Sphone);
        //////////////////////////////////////////////////////
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
               image_uri = FileProvider.getUriForFile(getActivity(),getActivity().getPackageName()+".provider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        image_uri);
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
            Glide.with(this).load(imageFilePath).into(imageView);
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

        if (image_uri != null) {
            final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(image_uri));
            fileRef.putFile(image_uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
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
                            databaseReference.setValue(upload);
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
    private void signInAnonymously(){
        mAuth.signInAnonymously().addOnSuccessListener(getActivity(), new OnSuccessListener<AuthResult>() {
            @Override public void onSuccess(AuthResult authResult) {
            uploadFile();
            }
        }) .addOnFailureListener(getActivity(), new OnFailureListener() {
            @Override public void onFailure(@NonNull Exception exception) {
                Log.e("TAG", "signInAnonymously:FAILURE", exception);
            }
        });
    }

}
