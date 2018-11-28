package medic.esy.es.mamyapp.Activites.staff;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import medic.esy.es.mamyapp.R;


public class chattingStaff extends Fragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View root=inflater.inflate(R.layout.fragment_chatting_staff, container, false);
       tabLayout=(TabLayout)root.findViewById(R.id.tabLayout);
       viewPager=(ViewPager) root.findViewById(R.id.viewpager);

        ViewpagerAdapter viewpagerAdapter=new ViewpagerAdapter(getChildFragmentManager());
        viewpagerAdapter.addFragments(new users(),"Users");
        viewpagerAdapter.addFragments(new chattingFragment(),"Chatting");
        viewPager.setAdapter(viewpagerAdapter);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });


       return root;
    }

    class ViewpagerAdapter extends FragmentPagerAdapter{
        private ArrayList<Fragment>fragments;
        private ArrayList<String>titles;

        public ViewpagerAdapter(FragmentManager fm) {
            super(fm);
            this.fragments=new ArrayList<>();
            this.titles=new ArrayList<>();
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragments(Fragment fragment,String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }


}
