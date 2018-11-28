package medic.esy.es.mamyapp.Activites.staff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import medic.esy.es.mamyapp.Activites.model.staff;
import medic.esy.es.mamyapp.R;

public class Signupforstaff extends AppCompatActivity {

    private EditText one,two,three,four,five,six,seven;
    private FirebaseAuth auth;
    private FirebaseDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signupforstaff);

  one=(EditText)findViewById(R.id.editText);
  two=(EditText)findViewById(R.id.editText2);
  three=(EditText)findViewById(R.id.editText3);
  four=(EditText)findViewById(R.id.editText4);
  five=(EditText)findViewById(R.id.editText5);
  six=(EditText)findViewById(R.id.editText6);
  seven=(EditText)findViewById(R.id.editText7);
  auth=FirebaseAuth.getInstance();
  /////////////////////////////////////////////////////////////////
    }

    public void signupforstaff(View view) {
        String email=five.getText().toString().trim();
        String password=seven.getText().toString().trim();
        //////////////////////////////////////////////////
        final String staffname=one.getText().toString().trim();
        final String gender=two.getText().toString().trim();
        final String homePhone=three.getText().toString().trim();
        final String workphone=four.getText().toString().trim();
        final String address=six.getText().toString().trim();


        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
////Hint that cell phone must be upgrade

                    staff staff = new staff(
                            staffname,gender,homePhone,workphone,address,address
                    );
                    FirebaseDatabase.getInstance().getReference("staff").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(staff);
                    Toast.makeText(Signupforstaff.this, "Staff add Successfully", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(Signupforstaff.this, "Faild !!", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }
}
