package medic.esy.es.mamyapp.Activites.parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import medic.esy.es.mamyapp.Activites.Adapter.afternoonAdapter;
import medic.esy.es.mamyapp.Activites.Adapter.lunchAdapter;
import medic.esy.es.mamyapp.Activites.Adapter.morningAdapter;
import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.R;


public class childMealViews extends Fragment {


    private RecyclerView morning,lunch,afternoon;
    private DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root =inflater.inflate(R.layout.fragment_child_meal_views, container, false);   String activity =commonParent.currentuser.getActivity();

        morning=(RecyclerView)root.findViewById(R.id.morningRecycle);
        lunch=(RecyclerView)root.findViewById(R.id.lunchRecycle);
        afternoon=(RecyclerView)root.findViewById(R.id.afternoonRecycle);
        if (commonParent.currentuser.getMorningSnack() !=null && commonParent.currentuser.getLunchSnack() !=null&& commonParent.currentuser.getAfternoonSnack() !=null ) {
            String morningsr = commonParent.currentuser.getMorningSnack();
            String[] morningArray = morningsr.split(",");
            String[] cleanMorning = Arrays.copyOf(morningArray, morningArray.length - 1);


            String lunchsr = commonParent.currentuser.getLunchSnack();
            String[] lunchArray = lunchsr.split(",");
            String[] cleanLunching = Arrays.copyOf(lunchArray, lunchArray.length - 1);


            String afternoonsr = commonParent.currentuser.getAfternoonSnack();
            String[] afternoonArray = afternoonsr.split(",");
            String[] cleanAfternoon = Arrays.copyOf(afternoonArray, afternoonArray.length - 1);

            Toast.makeText(getContext(), afternoonsr, Toast.LENGTH_SHORT).show();


            //for Morning
            morning.setLayoutManager(new LinearLayoutManager(getActivity()));

            //ADAPTER
            morningAdapter adapter = new morningAdapter(getActivity(), morningArray);
            morning.setAdapter(adapter);
/////////////////////////////////////////////////////////////////////////////
            //for Lunching
            lunch.setLayoutManager(new LinearLayoutManager(getActivity()));

            //ADAPTER
            lunchAdapter adapter2 = new lunchAdapter(getActivity(), lunchArray);
            lunch.setAdapter(adapter2);
////////////////////////////////////////////////////////////////////////////////
            //for Afternoon
            afternoon.setLayoutManager(new LinearLayoutManager(getActivity()));

            //ADAPTER
            afternoonAdapter adapter3 = new afternoonAdapter(getActivity(), afternoonArray);
            afternoon.setAdapter(adapter3);

        }
        else {
            Toast.makeText(getActivity(),"No Meals Available now !",Toast.LENGTH_SHORT).show();
        }
        return  root;
    }

    public static String[] clean(final String[] v) {
        List<String> list = new ArrayList<String>(v.length);
        for (String aString : v)
        {
            if (aString.length()>0)
            {
                list.add(aString);
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
