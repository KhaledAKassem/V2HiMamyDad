package medic.esy.es.mamyapp.Activites.staff;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import medic.esy.es.mamyapp.Activites.MainActivity;
import medic.esy.es.mamyapp.R;

public class HomeForStaff extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeforstaff);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        FloatingActionButton myFab = (FloatingActionButton)  findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                chattingStaff ss= new chattingStaff();
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_main,ss).commit();           }
        });

        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();

        profileforstaff pro = new profileforstaff();
//        pro.setArguments(bundle);

        ft.replace(R.id.content_main,pro).commitAllowingStateLoss();

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeForStaff/Up button, so long
        // as you specify a parenticon activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.signoutstaff) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(HomeForStaff.this,MainActivity.class));
            finish();
            return  true;
        }


        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            setTitle("Profile");
            profileforstaff ss= new profileforstaff();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,ss).commit();
        } else if (id == R.id.nav_addStudent) {
            setTitle("Add Student");
            addStudnt addStudnt= new addStudnt();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,addStudnt).commit();
        }
//        else if (id == R.id.nav_select) {
//            setTitle("Select Student");
//            selectStudent selectStudent= new selectStudent();
//            FragmentManager fragmentManager=getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.content_main,selectStudent).commit();
//        }
        else if (id == R.id.nav_removeActivites) {
            setTitle("Add Class Activity");
            addClassActivity addClassActivity= new addClassActivity();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,addClassActivity).commit();
        }
//        else if (id == R.id.nav_activity) {
//            setTitle("Remove Class Activity");
//            removeClassActivity removeClassActivity= new removeClassActivity();
//            FragmentManager fragmentManager=getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.content_main,removeClassActivity).commit();
//        }
        else if (id == R.id.nav_meal) {
            setTitle("Add Meal");
            addMeal addMeal= new addMeal();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,addMeal).commit();
        }
//        else if (id == R.id.nav_mealRemove) {
//            setTitle("Remove Meal");
//            removeMeal removeMeal= new removeMeal();
//            FragmentManager fragmentManager=getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.content_main,removeMeal).commit();
//        }
        else if (id == R.id.nav_pic) {
            setTitle("Take Pictures");
            takePictures takePictures= new takePictures();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,takePictures).commit();
        }
        else if (id == R.id.addVideo) {
            setTitle("Add Video");
            recordVideo recordVideo= new recordVideo();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,recordVideo).commit();
        }
        else if (id == R.id.chatting) {
            setTitle("Chatting");
            chattingStaff chattingStaff= new chattingStaff();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,chattingStaff).commit();
        }
        else if (id == R.id.protofilo) {
            setTitle("Protofilo & Assesment");
            portfolioAndAssessments portfolioAndAssessments= new portfolioAndAssessments();
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main,portfolioAndAssessments).commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
