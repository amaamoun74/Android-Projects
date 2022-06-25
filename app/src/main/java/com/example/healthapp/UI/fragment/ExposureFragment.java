package com.example.healthapp.UI.fragment;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;

public class ExposureFragment extends Fragment {

    Toolbar toolbar;
    SwitchCompat bluetoothSwitch;
    boolean switchBtn = false;
    BluetoothAdapter bluetoothAdapter;
    Context mContext;
    int notification_ID= 1;

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                @SuppressLint("MissingPermission") String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d("tag", deviceHardwareAddress);

            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exposure_system, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        createNotificationChannel();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.inflateMenu(R.menu.exposure_option);
        }

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mContext.registerReceiver(receiver, filter);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        bluetoothSwitch = view.findViewById(R.id.bleSwitch);
        bluetoothSwitch.setOnClickListener(new View.OnClickListener() {
            // logic hena 
            @Override
            public void onClick(View view) {
                if (bluetoothSwitch.isChecked()) {

                    enableBluetooth();
                    if (switchBtn = false ) {
                        discoverability();
                    }

                }
            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.exposure_option, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.moreInfo) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("https://www.google.com/covid19/exposurenotifications/"));
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingPermission")
    void enableBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(mContext.getApplicationContext(), "ble_not_supported", Toast.LENGTH_SHORT).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);

            // Register for broadcasts when a device is discovered.
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            mContext.registerReceiver(receiver, filter);
            switchBtn= true;


        }
        if(bluetoothAdapter.isEnabled()){
            Log.d("TAG", "enableDisableBT: disabling BT.");
            bluetoothAdapter.disable();
            switchBtn= false;
            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            mContext.registerReceiver(receiver, BTIntent);
        }
    }



    @SuppressLint("MissingPermission")
    void discoverability(){
        bluetoothAdapter.startDiscovery();
        int requestCode = 1;
        Intent discoverableIntent =
                new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 24*60*60);//discoverable for 24h
        startActivityForResult(discoverableIntent, requestCode);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        requireActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }


    private void createNotificationChannel() {
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_baseline_coronavirus_24);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "id")
                .setSmallIcon(R.drawable.ic_baseline_coronavirus_24)
                .setLargeIcon(bitmap)
                .setContentTitle("Warning ! . . .")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("3 cases in your area \nPlease Be Careful about yourself"))
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate( new long []{ 1000 , 1000 , 1000 , 1000 , 1000 })
                .setSound(RingtoneManager. getDefaultUri (RingtoneManager.TYPE_NOTIFICATION));

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "name";
            String description = "description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("id", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(mContext);

// notificationId is a unique int for each notification that you must define
            notificationManagerCompat.notify(notification_ID, builder.build());
        }
    }



}

