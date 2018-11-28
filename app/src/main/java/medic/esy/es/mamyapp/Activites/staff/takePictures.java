package medic.esy.es.mamyapp.Activites.staff;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import medic.esy.es.mamyapp.Activites.model.upload;
import medic.esy.es.mamyapp.R;


public class takePictures extends Fragment {



    private static final int PicImageRequest=1;
    private static final int RESULTOK = -1;
    private Button buttonChoose;
    private Button buttonUpload;
    private EditText getcode;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Uri ImageUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View root=inflater.inflate(R.layout.fragment_take_pictures, container, false);
        buttonChoose=(Button)root.findViewById(R.id.opencamera);
        buttonUpload=(Button)root.findViewById(R.id.uploadImage);
        getcode=(EditText)root.findViewById(R.id.etchildcode);
        imageView=(ImageView)root.findViewById(R.id.mychildphoto);
        progressBar=(ProgressBar)root.findViewById(R.id.progressbar);
        storageReference=FirebaseStorage.getInstance().getReference("uploads");
        databaseReference=FirebaseDatabase.getInstance().getReference("childernPhoto");

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfilechooser();
            }
        });

        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getdata=getcode.getText().toString().trim();
                if (getdata.length() == 0) {
                    getcode.setError("Code is Required");
                    getcode.requestFocus();
                    return;

                }else{
                    Toast.makeText(getActivity(),"start Uploading pls wait ...",Toast.LENGTH_SHORT).show();
                        uploadFile();
                }
            }


        });

        return root;
    }

    private void uploadFile() {

        if(ImageUri !=null){
            final StorageReference fileRef=storageReference.child(System.currentTimeMillis()+"."+getFileExtension(ImageUri));
           fileRef.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                   Handler handler=new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                 progressBar.setProgress(0);
                       }
                   },4000);
                   fileRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {

                       @Override
                       public void onComplete(@NonNull Task<Uri> task) {
                           String downloadUrl = task.getResult().toString();
                           upload upload =new upload(downloadUrl);
                           databaseReference.child(getcode.getText().toString().trim()).setValue(upload);
                       }
                   });
                   Toast.makeText(getActivity(),"Upload Successfully",Toast.LENGTH_SHORT).show();
                   buttonUpload.setEnabled(true);
               }
           }).addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
               Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
               }
           }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
               @Override
               public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                   double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                   progressBar.setProgress((int) progress);
               }
           });
        }else{
            Toast.makeText(getActivity(),"No file selected",Toast.LENGTH_SHORT).show();
        }

    }

    private String getFileExtension(Uri uri){
        ContentResolver cr=getActivity().getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }


    private void openfilechooser() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 1);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            ImageUri = data.getData();
            if (ImageUri != null) {
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }

    }





}

