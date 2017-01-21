package com.developers.laboursewa;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.developers.laboursewa.adapters.TabAdapter;

public class TabActivity extends AppCompatActivity {
    private ViewPager pager;
    private TabLayout tab;
    private TabAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        pager= (ViewPager) findViewById(R.id.pager);
        tab= (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("Home"));
        tab.addTab(tab.newTab().setText("Job Listings"));
        tab.addTab(tab.newTab().setText("Tutorials"));
        adapter=new TabAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
