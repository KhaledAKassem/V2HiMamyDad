package medic.esy.es.mamyapp.Activites.staff;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import medic.esy.es.mamyapp.R;


public class addMeal extends Fragment {

   private Button addmeal;
   private CheckBox fluid,icecream,yogurt,juice,fruit,vegetable,grains,breads,cheeses;
   private CheckBox meatLunch,chickenLunch,riceLunch,macaroniLunch,soupLunch,pizzaLunch,vegetableLunch,juiceLunch,waterLunch;
   private CheckBox fluidafternoon,icecreamafternoon,yogurtafternoon,juiceafternoon,fruitafternoon,vegetableafternoon,grainsafternoon,breadsafternoon,cheesesafternoon;
   private EditText studentNumberinaddmeal;
   private CheckBox getAllChildern;
   StringBuilder result,resultforlunch,resultforafternoon;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root= inflater.inflate(R.layout.fragment_add_meal, container, false);
        addmeal=(Button)root.findViewById(R.id.addmeal);
        addmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickchecked();

            }
        });

        return root;
    }

    public void onclickchecked(){
        studentNumberinaddmeal=(EditText)getView().findViewById(R.id.studentNumberinaddmeal);
        fluid=(CheckBox)getView().findViewById(R.id.fluidmorning);
        icecream=(CheckBox)getView().findViewById(R.id.icecreammorning);
        yogurt=(CheckBox)getView().findViewById(R.id.yogurtmorning);
        juice=(CheckBox)getView().findViewById(R.id.juicemorning);
        fruit=(CheckBox)getView().findViewById(R.id.fruitmorning);
        vegetable=(CheckBox)getView().findViewById(R.id.vegetablemorning);
        grains=(CheckBox)getView().findViewById(R.id.grainsmorning);
        breads=(CheckBox)getView().findViewById(R.id.breadsmorning);
        cheeses=(CheckBox)getView().findViewById(R.id.cheesesmorning);
//////////////////////////////////////////////////////////
        getAllChildern=(CheckBox)getView().findViewById(R.id.allChilderns) ;
        ////////////////////////////////For Lunching ////////////////////////////////////

        meatLunch=(CheckBox)getView().findViewById(R.id.meatlunch);
        chickenLunch=(CheckBox)getView().findViewById(R.id.chickenlunch);
        riceLunch=(CheckBox)getView().findViewById(R.id.ricelunch);
        macaroniLunch=(CheckBox)getView().findViewById(R.id.macaronilunch);
        soupLunch=(CheckBox)getView().findViewById(R.id.souplunch);
        pizzaLunch=(CheckBox)getView().findViewById(R.id.pizzalunch);
        vegetableLunch=(CheckBox)getView().findViewById(R.id.vegtablelunch);
        juiceLunch=(CheckBox)getView().findViewById(R.id.juicelunch);
        waterLunch=(CheckBox)getView().findViewById(R.id.waterlunch);
        ////////////////////////////////////////for Afternoon//////////////////////////////

        fluidafternoon=(CheckBox)getView().findViewById(R.id.fluidafternoon);
        icecreamafternoon=(CheckBox)getView().findViewById(R.id.icecreamafternoon);
        yogurtafternoon=(CheckBox)getView().findViewById(R.id.yogurtafternoon);
        juiceafternoon=(CheckBox)getView().findViewById(R.id.juiceafternoon);
        fruitafternoon=(CheckBox)getView().findViewById(R.id.fruitafternoon);
        vegetableafternoon=(CheckBox)getView().findViewById(R.id.vegetableafternoon);
        grainsafternoon=(CheckBox)getView().findViewById(R.id.grainsafternoon);
        breadsafternoon=(CheckBox)getView().findViewById(R.id.breadsafternoon);
        cheesesafternoon=(CheckBox)getView().findViewById(R.id.cheesesafternoon);








        ////////////////////////////////////////////////////////////////////////////////////

        resultforlunch=new StringBuilder();
        if(meatLunch.isChecked()){
            resultforlunch.append("Meat ,");
        }
        if(chickenLunch.isChecked()){
            resultforlunch.append("Chicken ,");
        }
        if(riceLunch.isChecked()){
            resultforlunch.append("Rice ,");
        }
        if(macaroniLunch.isChecked()){
            resultforlunch.append("Macaroni ,");
        }
        if(soupLunch.isChecked()){
            resultforlunch.append("Soup ,");
        }
        if(pizzaLunch.isChecked()){
            resultforlunch.append("Pizza ,");
        }
        if(vegetableLunch.isChecked()){
            resultforlunch.append("Vegetable ,");
        }
        if(juiceLunch.isChecked()){
            resultforlunch.append("Juice ,");
        }
        if(waterLunch.isChecked()){
            resultforlunch.append("Water ");
        }

        //////////////////////////////////////////////////////////////////////////////////////

        result=new StringBuilder();

        if(fluid.isChecked()){
            result.append("Fluid ,");
        }


        if(icecream.isChecked()){
            result.append("Ice Cream ,");
        }

        if(yogurt.isChecked()){
            result.append("Yogurt ,");
        }
        if(juice.isChecked()){
            result.append("Juice,");
        }
        if(fruit.isChecked()){
            result.append("Fruit ,");
        }
        if(vegetable.isChecked()){
            result.append("Vegetable , ");
        }
        if(grains.isChecked()){
            result.append("Grains , ");
        }
        if(breads.isChecked()){
            result.append("Breads , ");
        }
        if(cheeses.isChecked()){
            result.append("Cheeses ");
        }


        ////////////////////////////////////////////////////////////////////////////////////

         resultforafternoon=new StringBuilder();

        if(fluidafternoon.isChecked()){
            resultforafternoon.append("Fluid ,");
        }

        if(icecreamafternoon.isChecked()){
            resultforafternoon.append("Ice Cream ,");
        }

        if(yogurtafternoon.isChecked()){
            resultforafternoon.append("Yogurt ,");
        }
        if(juiceafternoon.isChecked()){
            resultforafternoon.append("Juice,");
        }
        if(fruitafternoon.isChecked()){
            resultforafternoon.append("Fruit ,");
        }
        if(vegetableafternoon.isChecked()){
            resultforafternoon.append("Vegetable , ");
        }
        if(grainsafternoon.isChecked()){
            resultforafternoon.append("Grains , ");
        }
        if(breadsafternoon.isChecked()){
            resultforafternoon.append("Breads , ");
        }
        if(cheesesafternoon.isChecked()){
            resultforafternoon.append("Cheeses ");
        }

        //////////////////////////////////////////////////////////////////////////////////

        //Displaying the message on the toast



        String phoneChild=studentNumberinaddmeal.getText().toString().trim();
        if(getAllChildern.isChecked()){
            studentNumberinaddmeal.setFocusable(false);
            DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("childern");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    List<String> Userlist = new ArrayList<>();
                    // Result will be holded Here
                    for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                        Userlist.add(String.valueOf(dsp.getKey())); //add result into array list

                    }

                    for (int i=0;i<Userlist.size();i++){
                        System.out.println("*******************************************"+Userlist.get(i));
                        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("childern").child(Userlist.get(i));
                        mDatabase.child("morningSnack").setValue(result.toString());
                        mDatabase.child("lunchSnack").setValue(resultforlunch.toString());
                        mDatabase.child("afternoonSnack").setValue(resultforafternoon.toString());
                        Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(),resultforlunch,Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(),resultforafternoon,Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

//            studentNumberinaddmeal.setError("Please Enter Code for the student");
        }else if(studentNumberinaddmeal.getText().toString() != null) {
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("childern").child(phoneChild);
            mDatabase.child("morningSnack").setValue(result.toString());
            mDatabase.child("lunchSnack").setValue(resultforlunch.toString());
            mDatabase.child("afternoonSnack").setValue(resultforafternoon.toString());
            Toast.makeText(getActivity(),result,Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),resultforlunch,Toast.LENGTH_SHORT).show();
            Toast.makeText(getActivity(),resultforafternoon,Toast.LENGTH_SHORT).show();

        }
    }

}
