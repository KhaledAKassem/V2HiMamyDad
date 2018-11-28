package medic.esy.es.mamyapp.Activites.parent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.R;

public class profileForParent extends Fragment {

   private TextView name,email,address,age,phone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View root=inflater.inflate(R.layout.fragment_profile_for_parent, container, false);

        name=(TextView)root.findViewById(R.id.forusername);
        email=(TextView)root.findViewById(R.id.foremail);
        address=(TextView)root.findViewById(R.id.foraddress);
        age=(TextView)root.findViewById(R.id.forage);
        /////////////////////////////////////////////////////
        String Sname =  commonParent.currentuser.getName();
        String Semail=  commonParent.currentuser.getEmail();
        String Saddress=commonParent.currentuser.getAddress();
        String Sage=    commonParent.currentuser.getAge();
        String Sphone=  commonParent.currentuser.getActivity();
        //////////////////////////////////////////////////////
        name.setText(Sname);
        email.setText(Semail);
        address.setText(Saddress);
        age.setText(Sage);
        //////////////////////////////////////////////////////
        return root;
    }

}
