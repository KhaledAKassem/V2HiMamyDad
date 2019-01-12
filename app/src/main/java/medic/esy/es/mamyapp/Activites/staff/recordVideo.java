package medic.esy.es.mamyapp.Activites.staff;

import android.content.ContentResolver;
import android.content.Intent;
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
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

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


public class recordVideo extends Fragment {

    private static final int CAMERA_REQUEST_CODE_VEDIO = 1;
    private EditText getVideoCode;
    private Button recordVideo;
    private Button uploadVideo;
    private VideoView videoView;
    private ProgressBar progressBar;
    private Uri VideoUri;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_record_video, container, false);
        getVideoCode = (EditText) root.findViewById(R.id.videoCode);
        recordVideo = (Button) root.findViewById(R.id.btRecordVideo);
        uploadVideo = (Button) root.findViewById(R.id.btUploadVideo);
        videoView = (VideoView) root.findViewById(R.id.videoView);
        progressBar=(ProgressBar)root.findViewById(R.id.progrssforvideo);
        storageReference = FirebaseStorage.getInstance().getReference("uploadsVideo");
        databaseReference = FirebaseDatabase.getInstance().getReference("childernVideo");

        ///////////////////////////////////////////////////////////////
        recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent,
                            CAMERA_REQUEST_CODE_VEDIO);
                }
            }
        });

        ///////////////////////////////////////////////////////////////
        uploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getdata = getVideoCode.getText().toString().trim();
                if (getdata.length() == 0) {
                    getVideoCode.setError("Code is Required");
                    getVideoCode.requestFocus();
                    return;

                } else {
                    Toast.makeText(getActivity(), "start Uploading pls wait ...", Toast.LENGTH_SHORT).show();
                    uploadFile();
                }
            }


        });


        /////////////////////////////////////////////////////////////////
        return root;

    }

    private void uploadFile() {
        if (VideoUri != null) {
            final StorageReference fileRef = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(VideoUri));
            fileRef.putFile(VideoUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    }, 4000);
                    fileRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {

                        @Override
                        public void onComplete(@NonNull Task<Uri> task) {
                            String downloadUrl = task.getResult().toString();
                            upload upload = new upload(downloadUrl,"");
                            databaseReference.child(getVideoCode.getText().toString().trim()).setValue(upload);
                        }
                    });
                    Toast.makeText(getActivity(), "Upload Successfully", Toast.LENGTH_SHORT).show();
                    uploadVideo.setEnabled(true);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int) progress);
                }
            });
        } else

        {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
        }

    }

    private String getFileExtension(Uri uri){
        ContentResolver cr=getActivity().getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Creating MediaController
        MediaController mediaController= new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        VideoUri = data.getData();
        videoView.setVideoURI(VideoUri);
        videoView.start();
        videoView.requestFocus();
    }
}


