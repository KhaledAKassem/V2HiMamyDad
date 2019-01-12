package medic.esy.es.mamyapp.Activites.parent;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.R;

public class childPhotos extends Fragment {

    private ImageView getIMageView;
    private Uri uri;
    private DatabaseReference mDatabaseReference;
    private ProgressBar progressBar;
    private TextView timePic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root=inflater.inflate(R.layout.fragment_child_photos, container, false);
        getIMageView = (ImageView)root.findViewById(R.id.getimageview);
        progressBar=(ProgressBar)root.findViewById(R.id.progressforPhoto);
        timePic=(TextView) root.findViewById(R.id.timePic);
        mDatabaseReference=FirebaseDatabase.getInstance().getReference("childernPhoto").child(commonParent.currentuser.getPhone()).child("mImageUrl");

//        if(getIMageView.getDrawable() != null) {

            mDatabaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if(dataSnapshot.getValue() !=null) {
                        Picasso.with(getActivity()).load(dataSnapshot.getValue().toString()).fit().centerCrop().into(getIMageView);
                        progressBar.setVisibility(View.INVISIBLE);
                    }else{
                        Toast.makeText(getActivity(),"No Pic Uploaded yet !",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.INVISIBLE);

                    }
//                    timePic.setText(upload.getDate());
                }



                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

        mDatabaseReference=FirebaseDatabase.getInstance().getReference("childernPhoto").child(commonParent.currentuser.getPhone()).child("date");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() !=null){
                    timePic.setText(dataSnapshot.getValue().toString());
            }
            else{

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.INVISIBLE);
            }
        });



//        }
//        else
//
//        {
//            Toast.makeText(getActivity(), "No Photo uploaded from staff", Toast.LENGTH_SHORT).show();
//            progressBar.setVisibility(View.INVISIBLE);
//
//        }

        return root;
    }

}
