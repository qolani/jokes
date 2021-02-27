package com.example.jokes.ui.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.jokes.R;
public class MainActivity extends AppCompatActivity {

    /****
     * Property declaration
     */
    private AppBarConfiguration appBarConfiguration;
    private final static int REQUEST_ID = 23;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OnInitialize();
    }

    /****
     * Initialize view or any other component required by the view
     */
    private void OnInitialize(){
        OnsetUpNav();
    }

    /****
     * Setup Navigation component
     */
    private void OnsetUpNav(){
        navController = Navigation.findNavController(this, R.id.main_id);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    /****
     * Setup the back button for fragments during navigation
     * @return
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }

    /****
     * Create top right menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}