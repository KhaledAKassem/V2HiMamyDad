package medic.esy.es.mamyapp.Activites.parent;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.google.firebase.database.DatabaseReference;

import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.R;


public class childVideos extends Fragment {

    private VideoView getVideo;
    private Uri videoUri;
    private DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View root=inflater.inflate(R.layout.fragment_child_videos, container, false);
       getVideo=(VideoView)root.findViewById(R.id.getVideo);
       String babyphone =commonParent.currentuser.getPhone();
//       databaseReference = FirebaseDatabase.getInstance().getReference("");
//       databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//               videoUri=Uri.parse( dataSnapshot.getValue(String.class));
//               getVideo.setVideoURI(videoUri);
//               getVideo.start();
//               getVideo.requestFocus();
//           }
//
//           @Override
//           public void onCancelled(@NonNull DatabaseError databaseError) {
//
//           }
//       });
               videoUri=Uri.parse("https://mamyapp-5a6e0.firebaseio.com/childernVideo/"+babyphone+"/mImageUrl");
               getVideo.setVideoURI(videoUri);
               getVideo.start();
               getVideo.requestFocus();


        return root;
    }

}
