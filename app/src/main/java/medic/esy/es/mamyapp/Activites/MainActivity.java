package medic.esy.es.mamyapp.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import medic.esy.es.mamyapp.Activites.model.commonParent;
import medic.esy.es.mamyapp.Activites.model.parent;
import medic.esy.es.mamyapp.Activites.parent.HomeForParent;
import medic.esy.es.mamyapp.Activites.staff.HomeForStaff;
import medic.esy.es.mamyapp.R;

public class MainActivity extends AppCompatActivity {

    private EditText emaill,userpassword;
    private Button signin;
    private RadioGroup rg;
    private  String txRadio;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emaill=(EditText)findViewById(R.id.input_email);
        userpassword=(EditText)findViewById(R.id.input_password);
        signin=(Button)findViewById(R.id.signin);
        rg= (RadioGroup) findViewById(R.id.rb);
        txRadio="parent";
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                txRadio = radioButton.getText().toString();
                Toast.makeText(MainActivity.this,txRadio,Toast.LENGTH_LONG).show();
             }
        });
        FirebaseDatabase database =FirebaseDatabase.getInstance();
        final DatabaseReference tableUser=database.getReference("childern");

        signin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 auth=FirebaseAuth.getInstance();
                 final String emailtx=emaill.getText().toString().trim();
                 final String passwordtx=userpassword.getText().toString().trim();
                 if (emaill.length()==0){
                     emaill.setError("UserName is Required");
                     emaill.requestFocus();
                     return;
                 }else if(userpassword.length()==0){
                     userpassword.setError("Password is Reqired");
                     userpassword.requestFocus();
                     return;
                 }

                     if (txRadio.toLowerCase().trim().equals("staff")) {

                         auth.signInWithEmailAndPassword(emailtx, passwordtx)
                                 .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                     @Override
                                     public void onComplete(@NonNull Task<AuthResult> task) {
                                         if (task.isSuccessful()) {
                                             Toast.makeText(MainActivity.this,"Authentication success",Toast.LENGTH_SHORT).show();
                                             Intent i = new Intent(MainActivity.this, HomeForStaff.class);
                                             startActivity(i);
                                         } else {
                                             // If sign in fails, display a message to the user.
                                             Toast.makeText(MainActivity.this, "Authentication failed.",
                                                     Toast.LENGTH_SHORT).show();

                                         }

                                         // ...
                                     }
                                 });
                     }

                     ///////////////////////////////////////////////////////////////
                     /////////////////////////Parent Authentication////////////////
                     //////////////////////////////////////////////////////////////

                     else if (txRadio.toLowerCase().trim().equals("parent")){


                      tableUser.addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                              if (dataSnapshot.child(emailtx).exists()) {
                                  parent parent = dataSnapshot.child(emailtx).getValue(parent.class);
                                  if (parent.getPassword().equals(passwordtx)) {


                                      Intent i=new Intent(MainActivity.this, HomeForParent.class);
                                      commonParent.currentuser=parent;
                                      startActivity(i);
                                      Toast.makeText(MainActivity.this, "Welcome " + parent.getName(), Toast.LENGTH_SHORT).show();

                                  } else {
                                      Toast.makeText(MainActivity.this, "Authentication faild !!", Toast.LENGTH_SHORT).show();
                                  }
                              }else{
                                  Toast.makeText(MainActivity.this, "User isnt exists in our database", Toast.LENGTH_SHORT).show();
                              }
                          }
                          @Override
                          public void onCancelled(@NonNull DatabaseError databaseError) {

                          }
                      });

                     }
                     ////////////////////////////////////////////////////
             }
         });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            startActivity(new Intent(MainActivity.this,HomeForStaff.class));
            finish();
        }
    }
}
