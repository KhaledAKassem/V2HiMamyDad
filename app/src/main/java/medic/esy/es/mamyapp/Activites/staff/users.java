package medic.esy.es.mamyapp.Activites.staff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import medic.esy.es.mamyapp.Activites.Adapter.UsersAdapter;
import medic.esy.es.mamyapp.Activites.model.parent;
import medic.esy.es.mamyapp.R;


public class users extends Fragment {

    private RecyclerView recyclerView;
    private UsersAdapter usersAdapter;
    private List<parent> mUsers;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_users, container, false);
        recyclerView=(RecyclerView)root.findViewById(R.id.recycleViewForUsers);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mUsers= new ArrayList<>();

        readUsers();

        return root;
    }

    private void readUsers() {
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference("childern");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mUsers.clear();
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    parent parent=new parent();
//                    Map<String, parent> td = (HashMap<String,parent>) dataSnapshot.getValue();
//                    int size =td.size();
//                    Collection<parent> values =new ArrayList<>();
//                    values = td.values();
//                    Log.v("asdasdasd", String.valueOf(values));
                    parent users=dataSnapshot.getValue(parent.class);
                    Log.v("asdasdasd", String.valueOf(users.getName()));

                    mUsers.add(users);
                }
                usersAdapter=new UsersAdapter(getContext(),mUsers);
                recyclerView.setAdapter(usersAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
