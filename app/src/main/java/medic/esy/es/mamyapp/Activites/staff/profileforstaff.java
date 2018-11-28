package medic.esy.es.mamyapp.Activites.staff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import medic.esy.es.mamyapp.Activites.model.staff;
import medic.esy.es.mamyapp.R;


public class profileforstaff extends Fragment {

    FirebaseAuth myAuth;
    TextView staffAddress,staffName,staffHome,staffWork,staffCell,staffGender,staffEmail;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_profileforstaff, container, false);
        staffAddress = (TextView) root.findViewById(R.id.tv_address);
        staffName = (TextView) root.findViewById(R.id.tv_name);
        staffHome = (TextView) root.findViewById(R.id.tvHomePhone);
        staffWork = (TextView) root.findViewById(R.id.tvWorkPhone);
        staffCell = (TextView) root.findViewById(R.id.tvCellPhone);
        staffGender = (TextView) root.findViewById(R.id.tvGender);
        staffEmail = (TextView) root.findViewById(R.id.staffEmail);


        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        // get reference to 'staff' node
        mFirebaseDatabase = mFirebaseInstance.getReference("staff").child(uid);
        mFirebaseDatabase.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        staff user = dataSnapshot.getValue(staff.class);
                        String address = user.getStaffAddress();
                        String name = user.getStaffName();
                        String homePhone = user.getStaffHomePhone();
                        String workPhone = user.getStaffWorkPhone();
                        String cellPhone = user.getStaffCellPhone();
                        String gender = user.getStaffGender();
                        staffAddress.setText(address);
                        staffName.setText(name);
                        staffHome.setText(homePhone);
                        staffWork.setText(workPhone);
                        staffCell.setText(cellPhone);
                        staffGender.setText(gender);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            staffEmail.setText(email);


        }
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
//        if(myAuth.getCurrentUser()==null) {
//            myAuth = FirebaseAuth.getInstance();
//            startActivity(new Intent(getActivity(), MainActivity.class));
//        }
    }

}
