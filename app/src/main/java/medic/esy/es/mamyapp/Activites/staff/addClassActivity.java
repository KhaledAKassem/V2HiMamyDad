package medic.esy.es.mamyapp.Activites.staff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import medic.esy.es.mamyapp.R;


public class addClassActivity extends Fragment {

    private CheckBox goingOutside,GoingPark,Reading,ListeningMusic,Dancing,Drawing,Learning_Stories;
    private CheckBox calm,angry,happy,cooperative,listening;
    private Button done;
    private EditText getphoneNumber;
    StringBuilder result;
    StringBuilder resultForMode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_add_class, container, false);
        done=(Button)root.findViewById(R.id.activity);


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickchecked();

            }
        });


        return root;
    }

public void onclickchecked(){
    getphoneNumber=(EditText)getView().findViewById(R.id.getphoneNumber);
    goingOutside=(CheckBox)getView().findViewById(R.id.goingoutside);
    GoingPark=(CheckBox)getView().findViewById(R.id.goingpark);
    Reading=(CheckBox)getView().findViewById(R.id.reading);
    ListeningMusic=(CheckBox)getView().findViewById(R.id.listeningmusic);
    Dancing=(CheckBox)getView().findViewById(R.id.dancing);
    Drawing=(CheckBox)getView().findViewById(R.id.drawing);
    Learning_Stories=(CheckBox)getView().findViewById(R.id.learningStories);


    ////////////////////////////////////////////////////////////////
    calm=(CheckBox)getView().findViewById(R.id.calm);
    angry=(CheckBox)getView().findViewById(R.id.angry);
    happy=(CheckBox)getView().findViewById(R.id.happy);
    cooperative=(CheckBox)getView().findViewById(R.id.cooperative);
    listening=(CheckBox)getView().findViewById(R.id.listening);
//////////////////////////////////////////////////////////
    result=new StringBuilder();

    if(GoingPark.isChecked()){
        result.append("Going Park ,");
    }


    if(goingOutside.isChecked()){
        result.append("Going Outside ,");
    }

    if(Reading.isChecked()){
        result.append("Reading ,");
    }
    if(ListeningMusic.isChecked()){
        result.append("Listening Music ,");
    }
    if(Dancing.isChecked()){
        result.append("Dancing ,");
    }
    if(Drawing.isChecked()){
        result.append("Drawing ");
    }


    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////

   resultForMode=new StringBuilder();
    if(calm.isChecked()){
        resultForMode.append("Calm");
    }
    if(angry.isChecked()){
        resultForMode.append("Angry");
    }
    if(happy.isChecked()){
        resultForMode.append("Happy");
    }
    if(cooperative.isChecked()){
        resultForMode.append("Cooperative");
    }
    if(listening.isChecked()){
        resultForMode.append("Listening");
    }


    //Displaying the message on the toast
    String phoneChild=getphoneNumber.getText().toString().trim();
    if(phoneChild.isEmpty()){
        getphoneNumber.setError("Please Enter number of the student");
    }else {
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("childern").child(phoneChild);
        mDatabase.child("Activity").setValue(result.toString());
        mDatabase.child("Mode").setValue(resultForMode.toString());
        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
        Toast.makeText(getActivity(),resultForMode,Toast.LENGTH_SHORT).show();


    }
}



}
