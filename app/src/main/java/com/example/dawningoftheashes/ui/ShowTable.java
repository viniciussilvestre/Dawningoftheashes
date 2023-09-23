package com.example.dawningoftheashes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.databinding.ActivityShowTableBinding;
import com.example.dawningoftheashes.model.IdViewModel;
import com.example.dawningoftheashes.model.TableViewModel;
import com.example.dawningoftheashes.util.Util;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.elevation.SurfaceColors;

public class ShowTable extends AppCompatActivity
{
    private ActivityShowTableBinding binding;
    private TableViewModel tableViewModel;
    private IdViewModel idViewModel;
    int id;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(SurfaceColors.SURFACE_2.getColor(this));
        getWindow().setNavigationBarColor(SurfaceColors.SURFACE_2.getColor(this));
        Bundle data = getIntent().getExtras();
        if (data != null)
        {
            id = data.getInt(Util.TABLE_ID);
        }
        idViewModel = new ViewModelProvider(this).get(IdViewModel.class);
        idViewModel.setId(id);

        binding = ActivityShowTableBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.bottomNavigationView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.table_info, R.id.campaign_notes, R.id.table_info).build();
        NavController navController = getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);
    }

    @NonNull
    private NavController getNavController()
    {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        if (!(fragment instanceof NavHostFragment))
        {
            throw new IllegalStateException("Activity " + this + " does not have a NavHostFragment");
        }
        return ((NavHostFragment) fragment).getNavController();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        getMenuInflater().inflate(R.menu.menu_sheet_view, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item)
//    {
//        int id = item.getItemId();
//        if (id == R.id.action_delete)
//        {
//            SheetViewModel.deleteDnd(sheetDAndD);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}