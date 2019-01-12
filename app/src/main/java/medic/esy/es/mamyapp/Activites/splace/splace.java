package medic.esy.es.mamyapp.Activites.splace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import medic.esy.es.mamyapp.Activites.MainActivity;
import medic.esy.es.mamyapp.R;

public class splace extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_TIME = 1;
    private ViewPager viewPager;
    private LinearLayout dotslayout;
    private sliderAdapter sliderAdapter;
    private TextView mDots[];
    private Button btnPrevious,btnNext;
    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splace);

        checkFirstOpen();
        viewPager=(ViewPager)findViewById(R.id.Viewpagerlayout);
        dotslayout=(LinearLayout)findViewById(R.id.dotsLayout);
        btnPrevious=(Button)findViewById(R.id.btnPrevious);
        btnNext=(Button)findViewById(R.id.btnnext);

        sliderAdapter=new sliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(btnNext.getText()=="Finish"){
                    startActivity(new Intent(splace.this,MainActivity.class));
                }else{
                    viewPager.setCurrentItem(currentPage+1);

                }
            }
        });
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(currentPage-1);
            }
        });



        ////////////////////////////////New Handler///////////////////

//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//
//                /* Create an intent that will start the main activity. */
//                Intent mainIntent = new Intent(splace.this,
//                        HomeForManager.class);
//                mainIntent.putExtra("id", "1");
//
//                //SplashScreen.this.startActivity(mainIntent);
//                startActivity(mainIntent);
//                /* Finish splash activity so user cant go back to it. */
//                splace.this.finish();
//
//                     /* Apply our splash exit (fade out) and main
//                        entry (fade in) animation transitions. */
//                overridePendingTransition(R.anim.mainfadein,R.anim.splashfadeout);
//            }
//        }, SPLASH_DISPLAY_TIME);

        //////////////////////////////Ending Handler/////////////////
    }


    public void addDotsIndicator(int positon){
        mDots=new TextView[4];
        dotslayout.removeAllViews();

        for (int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.transparentWhite));
            dotslayout.addView(mDots[i]);


        }

        if(mDots.length>0){
               mDots[positon].setTextColor(getResources().getColor(R.color.white));
        }
    }
    ViewPager.OnPageChangeListener onPageChangeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
            currentPage=i;
            if(i==0){
                btnNext.setEnabled(true);
                btnPrevious.setEnabled(false);
                btnPrevious.setVisibility(View.INVISIBLE);
                btnNext.setText("Next");
                btnPrevious.setText("");
            }else if(i == mDots.length -1){

                btnNext.setEnabled(true);
                btnPrevious.setEnabled(true);
                btnPrevious.setVisibility(View.VISIBLE);
                btnNext.setText("Finish");
                btnPrevious.setText("Back");

            }else {
                btnNext.setEnabled(true);
                btnPrevious.setEnabled(true);
                btnPrevious.setVisibility(View.VISIBLE);
                btnNext.setText("Next");
                btnPrevious.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
    private void checkFirstOpen(){
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);

        if (!isFirstRun) {
            Intent intent = new Intent(splace.this, MainActivity.class);
            startActivity(intent);
            finish();

        }

        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().putBoolean("isFirstRun",
                false).apply();
    }
}
