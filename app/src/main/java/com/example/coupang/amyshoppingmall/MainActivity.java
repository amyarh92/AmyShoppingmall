package com.example.coupang.amyshoppingmall;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by Amy on 2015-08-17.
 */

public class MainActivity extends AppCompatActivity {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FragmentTransaction fragmentTransaction;
    private LoginFragment fragment_login = new LoginFragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MainPLPlist
        ContentFragment fragment_main_plp = new ContentFragment();
        setFragment(fragment_main_plp, "Welcome!");

        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();


                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //select Category
                    case R.id.ct_top:
                        ContentFragment fragment_top_plp = new ContentFragment();
                        setFragment(fragment_top_plp, "Top");
                        return true;

                    case R.id.ct_bottom:
                        ContentFragment fragment_bottom_plp = new ContentFragment();
                        setFragment(fragment_bottom_plp, "Bottom");
                        return true;

                    case R.id.ct_onepiece:
                        ContentFragment fragment_op_plp = new ContentFragment();
                        setFragment(fragment_op_plp, "One-Piece");
                        return true;

                    case R.id.ct_bs:
                        ContentFragment fragment_bs_plp = new ContentFragment();
                        setFragment(fragment_bs_plp, "Bag/Shoes");
                        return true;

                    case R.id.ct_acs:
                        ContentFragment fragment_acs_plp = new ContentFragment();
                        setFragment(fragment_acs_plp, "Accessory");
                        return true;

                    //Move to WebView
                    case R.id.pg_login:
//                        fragment_login = new LoginFragment();
                        setFragment(fragment_login, "Login");
                        return true;

                    case R.id.pg_cart:
                        CartFragment fragment_cart = new CartFragment();
                        setFragment(fragment_cart, "Cart");
                        return true;

                    case R.id.pg_myinfo:
                        Toast.makeText(getApplicationContext(), "Comming Soon~", Toast.LENGTH_LONG).show();
                        return true;

                    case R.id.pg_joinus:
                        JoinUsFragment fragment_joinus = new JoinUsFragment();
                        setFragment(fragment_joinus, "Join Us");
                        return true;

                    case R.id.pg_logout:
                        LogoutFragment fragment_logout = new LogoutFragment();
                        setFragment(fragment_logout, "Logout");
                        return true;

                    default:
                        Toast.makeText(getApplicationContext(), "selected wrong", Toast.LENGTH_LONG).show();
                        return true;
                }
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    //Setting Fragment
    public void setFragment(Fragment fragment, String category){
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);

        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        fragment.setArguments(bundle);

        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView)menu.findItem(R.id.menu_search).getActionView();
//        searchView.setOnQueryTextListener((SearchView.OnQueryTextListener) new TextQueryListner());
        int searchImgId = android.support.v7.appcompat.R.id.search_button;
        ImageView imageView = (ImageView)searchView.findViewById(searchImgId);
        imageView.setImageResource(R.drawable.ic_action_ic_search_w);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.menu_search:
                Toast.makeText(getApplicationContext(), "selected search",Toast.LENGTH_LONG).show();
                return true;
            case R.id.menu_cart:
                CartFragment fragment_cart = new CartFragment();
                setFragment(fragment_cart, "Cart");
                return true;
            case R.id.menu_home:
                // MainPLPlist
                ContentFragment fragment_main_plp = new ContentFragment();
                setFragment(fragment_main_plp, "Welcome!");
                return true;


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(LoginFragment.canGoBack()){
            LoginFragment.goBack();
        }else{
            super.onBackPressed();
        }

    }

//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//            fragment_login.myOnKeyDown(keyCode);
//
//            //and so on...
//        }
//        return super.onKeyDown(keyCode, event);
//    }

}