package medic.esy.es.mamyapp.Activites.parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import medic.esy.es.mamyapp.Activites.Adapter.ActivitiesAdapter;
import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.R;

public class childActivitesViews extends Fragment {


    private TextView babynameintroduction,bodydata;
    private RecyclerView recycleForActivities;

  private TextView mychildActivity;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_child_activites_views, container, false);
        babynameintroduction=(TextView)root.findViewById(R.id.babyname);
        recycleForActivities= (RecyclerView) root.findViewById(R.id.recycleForActivities);
        String babyname =commonParent.currentuser.getName();
        babynameintroduction.setText("Your Child "+babyname +" "+"Activities For this Day");
        if(commonParent.currentuser.getActivity() != null) {

            String activity = commonParent.currentuser.getActivity();

            String[] activites = activity.split(",");
            recycleForActivities.setLayoutManager(new LinearLayoutManager(getActivity()));

            //ADAPTER
            ActivitiesAdapter adapter = new ActivitiesAdapter(getActivity(), activites);
            recycleForActivities.setAdapter(adapter);

        }
        else{
            Toast.makeText(getActivity(),"No Activit is Available now !",Toast.LENGTH_SHORT).show();
        }

        return root;
    }


}
