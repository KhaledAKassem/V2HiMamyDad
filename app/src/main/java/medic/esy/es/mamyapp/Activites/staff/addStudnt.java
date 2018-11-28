package medic.esy.es.mamyapp.Activites.staff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import medic.esy.es.mamyapp.Activites.model.parent;
import medic.esy.es.mamyapp.R;

public class addStudnt extends Fragment {

    private EditText userName,email,address,age,phone,gender,password;
    private Button signUp;
    FirebaseAuth myAuth;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View RootView = inflater.inflate(R.layout.fragment_add_studnt, container, false);
        userName = (EditText) RootView.findViewById(R.id.inputforusername);
        email = (EditText) RootView.findViewById(R.id.inputforemail);
        address = (EditText) RootView.findViewById(R.id.inputforaddress);
        age = (EditText) RootView.findViewById(R.id.inputforage);
        phone = (EditText) RootView.findViewById(R.id.inputforphone);
        gender = (EditText) RootView.findViewById(R.id.inputforgender);
        password = (EditText) RootView.findViewById(R.id.inputforpassword);
        signUp = (Button) RootView.findViewById(R.id.Signup);
        myAuth = FirebaseAuth.getInstance();
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailtx = email.getText().toString().trim();
                final String passwordtx = password.getText().toString().trim();
                final String userNametx = userName.getText().toString().trim();
                final String addresstx = address.getText().toString().trim();
                final String agetx = age.getText().toString().trim();
                final String phonetx = phone.getText().toString().trim();
                final String gendertx = gender.getText().toString().trim();
                if (userName.length() == 0) {
                    userName.setError("UserName is Required");
                    userName.requestFocus();
                    return;
                } else if (email.length() == 0) {
                    userName.setError("Email is Required");
                    userName.requestFocus();
                    return;
                } else if (address.length() == 0) {
                    address.setError("Address is Required");
                    address.requestFocus();
                    return;
                } else if (age.length() == 0) {
                    age.setError("Age is Required");
                    age.requestFocus();
                    return;
                } else if (phone.length() == 0) {
                    phone.setError("phone is Required");
                    phone.requestFocus();
                    return;
                } else if (gender.length() == 0) {
                    gender.setError("Gender is Required");
                    gender.requestFocus();
                    return;
                } else if (password.length() == 0) {
                    password.setError("Password is Required");
                    password.requestFocus();
                    return;
                }
                FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
                String id=currentFirebaseUser.getUid();
     parent parent=new parent(
            id ,passwordtx,userNametx,gendertx,addresstx,agetx,emailtx,phonetx,"","","","",""
     );

     FirebaseDatabase.getInstance().getReference().child("childern").child(phonetx).setValue(parent).addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull Task<Void> task) {
             if(task.isSuccessful()){
                 Toast.makeText(getActivity(), "Student added Successfully", Toast.LENGTH_SHORT).show();
             }
         }
     });
            }
        });
        return RootView;
    }




    @Override
    public void onStart() {
        super.onStart();

        if(myAuth.getCurrentUser()!=null){

        }
    }
}