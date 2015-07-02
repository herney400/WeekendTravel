package com.example.n550j.weekendtravel;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;

import fragments.Lugares;
import fragments.Mapa;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import tabs.SlidingTabLayout;


public class MainActivity extends ActionBarActivity implements MaterialTabListener, View.OnClickListener,OnMapReadyCallback{
    private String[] drawerListViewItems;
    private ListView drawerListView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CharSequence mTitle;
    private DrawerLayout drawerLayout;
    private CharSequence mDrawerTitle;
    private SlidingTabLayout mTabs;


    private Toolbar toolbar;
    private MaterialTabHost tabHost;
    private ViewPager viewPager;

    public static final int TAB_MAPA = 0;
    public static final int TAB_AMIGOS = 1;
    private ViewPagerAdapter adapter;

    private GoogleApiClient mGoogleApiClient;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gui();

    }


  public void Gui(){
      toolbar = (Toolbar)findViewById(R.id.app_bar);
      setSupportActionBar(toolbar);
      getSupportActionBar().setDisplayShowHomeEnabled(true);

      tabHost   = (MaterialTabHost) findViewById(R.id.materialTabHost);
      viewPager = (ViewPager)       findViewById(R.id.pager   );

      adapter=new ViewPagerAdapter(getSupportFragmentManager());
      viewPager.setAdapter(adapter);
      // viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
      viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
          @Override
          public void onPageSelected(int position) {
              tabHost.setSelectedNavigationItem(position);
          }
      });

      for (int i = 0; i < adapter.getCount(); i++) {
          tabHost.addTab(
                  tabHost.newTab()
                          .setText(adapter.getPageTitle(i))
                          .setTabListener(this));
      }

  }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
   //     MenuItem itembuscar=menu.findItem(R.id.action_buscar);
//        SearchView searchView=(SearchView) MenuItemCompat.getActionView(itembuscar);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){


        switch (item.getItemId()){
            case R.id.action_nuevo:
               startActivity(new Intent(this, ActivityLugar.class));
              return true;
            case R.id.action_buscar:
                Toast.makeText(this, "Mas"+item.getTitle(),Toast.LENGTH_SHORT).show();
              return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings"+item.getTitle(),Toast.LENGTH_SHORT).show();
              return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    @Override
    public void onMapReady(GoogleMap map) {
     /*   LatLng sydney=new LatLng(3.410834, -76.535828);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
        map.addMarker(new MarkerOptions().title("sydney").snippet("fasdfasdfasdf").position(sydney)
        );
        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);*/
    }






    @Override
    public void onTabSelected(MaterialTab materialTab) {
        viewPager.setCurrentItem(materialTab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    @Override
    public void onClick(View v) {


        Fragment fragment= (Fragment)adapter.instantiateItem(viewPager,viewPager.getCurrentItem());

    }

    class  ViewPagerAdapter extends FragmentStatePagerAdapter {
         FragmentManager fragmentManager;
       String[] tabs;

     public ViewPagerAdapter(FragmentManager fm) {
         super(fm);
         fragmentManager=fm;
         tabs=getResources().getStringArray(R.array.tabs);
     }

     @Override
     public Fragment getItem(int position) {


      if(position==0)
      {
             Mapa fragmeMapa= Mapa.newInstance(" "," ");
          return  fragmeMapa;
      } else{
             Lugares fragmLugares= Lugares.newInstance("", "");

             return  fragmLugares ;
      }


     }

     @Override
     public CharSequence getPageTitle(int position) {
         return getResources().getStringArray(R.array.tabs)[position];
     }

     @Override
     public int getCount() {
         return 3;
     }


 }

    public static class MyFragment extends  Fragment{


        public static MyFragment getInstance(int position ){
             MyFragment myFragment=new MyFragment();

            Bundle args=new Bundle();
            args.putInt("position", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
            View layout =inflater.inflate(R.layout.fragment_mapa, container, false);
            Bundle bundle =getArguments();
           // if(bundle!){}

            return layout;
        }
    }






}
