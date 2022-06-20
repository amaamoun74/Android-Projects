package com.example.healthapp;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class modelQues extends Fragment {
    SwitchCompat bluetoothSwitch;
    boolean switchBtn = true;
    BluetoothAdapter bluetoothAdapter;
    ListView scanListView;
    ArrayList<String> arrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                @SuppressLint("MissingPermission") String deviceName = device.getName();
                String deviceHardwareAddress = device.getAddress(); // MAC address
                Log.d("tag",deviceHardwareAddress.toString());
                arrayList.add(deviceHardwareAddress);
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_model_ques, container, false);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        requireActivity().registerReceiver(receiver,filter);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_2,arrayList);
        scanListView.setAdapter(arrayAdapter);

        bluetoothSwitch = view.findViewById(R.id.bleSwitch);
        bluetoothSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (switchBtn= false)
                {
                    switchBtn= true;
                    bluetoothSwitch.setChecked(true);
                    enableBluetooth();
                }
                else if (switchBtn= true){
                    switchBtn= false;
                    bluetoothSwitch.setChecked(false);
                    enableBluetooth();
                }
            }
        });
        return view;
    }
    @SuppressLint("MissingPermission")
    void enableBluetooth() {
        if (bluetoothAdapter == null) {
            Toast.makeText(getActivity(), "ble_not_supported", Toast.LENGTH_SHORT).show();
        }
        if (!bluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);

            // Register for broadcasts when a device is discovered.
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            requireActivity().registerReceiver(receiver, filter);
            discoverability();
        }
        if(bluetoothAdapter.isEnabled()){
            Log.d("TAG", "enableDisableBT: disabling BT.");
            bluetoothAdapter.disable();

            IntentFilter BTIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
            requireActivity().registerReceiver(receiver, BTIntent);
        }
    }

    @SuppressLint("MissingPermission")
    void discoverability(){
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

}