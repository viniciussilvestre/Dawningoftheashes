package com.example.dawningoftheashes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.dawningoftheashes.R;
import com.example.dawningoftheashes.databinding.ActivityShowTableBinding;
import com.example.dawningoftheashes.model.IdViewModel;
import com.example.dawningoftheashes.model.Table;
import com.example.dawningoftheashes.model.TableViewModel;
import com.example.dawningoftheashes.util.Util;
import com.example.dawningoftheashes.util.WiFiDirectBroadcastReceiver;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.elevation.SurfaceColors;

public class ShowTable extends AppCompatActivity
{
    private ActivityShowTableBinding binding;
    private TableViewModel tableViewModel;
    private IdViewModel idViewModel;
    private Table currentTable;
    WifiP2pManager manager;
    WifiP2pManager.Channel channel;
    BroadcastReceiver receiver;
    IntentFilter intentFilter;
    int id;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);
        receiver = new WiFiDirectBroadcastReceiver(manager, channel, this);
        intentFilter = new IntentFilter();
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.NEARBY_WIFI_DEVICES) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.discoverPeers(channel, new WifiP2pManager.ActionListener()
        {
            @Override
            public void onSuccess()
            {

            }

            @Override
            public void onFailure(int i)
            {

            }
        });

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
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.table_info, R.id.campaign_notes, R.id.monsters_used).build();
        NavController navController = getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController);

        tableViewModel = new ViewModelProvider.AndroidViewModelFactory(ShowTable.this.getApplication()).create(TableViewModel.class);
        tableViewModel.getTable(id).observe(this, table ->
        {
            if (table != null)
            {
                currentTable = table;
            }
            else
            {
                finish();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver(receiver, intentFilter);

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        unregisterReceiver(receiver);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_sheet_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == R.id.action_delete)
        {
            TableViewModel.deleteTable(currentTable);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}