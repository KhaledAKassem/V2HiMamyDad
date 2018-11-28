package medic.esy.es.mamyapp.Activites.parent;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.R;

public class childPhotos extends Fragment {

    private ImageView getIMageView;
    private Uri uri;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root=inflater.inflate(R.layout.fragment_child_photos, container, false);
        getIMageView = (ImageView)root.findViewById(R.id.getimageview);
        Picasso.with(getActivity()).load(Uri.parse("https://mamyapp-5a6e0.firebaseio.com/childernPhoto/"+commonParent.currentuser.getPhone()+"/mImageUrl")).into(getIMageView);

       return root;
    }

}
