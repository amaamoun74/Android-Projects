package com.example.healthapp.UI.fragment;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.healthapp.R;
import com.example.healthapp.pojo.SessionManagement;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import github.nisrulz.qreader.QRDataListener;
import github.nisrulz.qreader.QREader;


public class QRScanningFragment extends Fragment {
    private SurfaceView surfaceView;
    private QREader qrEader;
    private TextView txt_result;
    private Context mContext;
    SessionManagement sessionManagement;
    RelativeLayout relativeLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_q_r_scanning, container, false);
        txt_result = (TextView) view.findViewById(R.id.code_info);
        surfaceView = (SurfaceView) view.findViewById(R.id.camera_view);
        sessionManagement = new SessionManagement(container.getContext());
        relativeLayout=view.findViewById(R.id.scaning);

        setupQReader();

        //Request permission
        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        setupQReader();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getActivity(), "You must enable this permission", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

        return view;
    }


    private void setupQReader() {
        qrEader = new QREader.Builder(getActivity(), surfaceView, new QRDataListener() {
            @Override
            public void onDetected(String data) {
                txt_result.post(new Runnable() {
                    @Override
                    public void run() {

                        txt_result.setText(data);
                        sessionManagement.saveUserIdFromQR(data);
                        qrEader.stop();
                        loadFragment(new kdaWKdaFragment());


                       /* Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(data));
                        startActivity(intent);
                        */

                    }
                });
            }
        }).facing(QREader.BACK_CAM).enableAutofocus(true)
                .height(surfaceView.getHeight()).width(surfaceView.getWidth()).build();
    }

    @Override
    public void onResume() {
        super.onResume();
        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if (qrEader != null)
                            qrEader.initAndStart(surfaceView);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getActivity(), "You must enable this permission", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                    }
                }).check();
    }

    @Override
    public void onPause() {
        super.onPause();

        Dexter.withActivity(getActivity()).withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if (qrEader != null)
                            qrEader.releaseAndCleanup();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getActivity(), "You must enable this permission", Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext= context;
    }


    private void loadFragment(Fragment fragment) {
        //replace the fragment
        relativeLayout.setVisibility(View.GONE);
        requireActivity().getSupportFragmentManager().beginTransaction().
                replace(R.id.scanFragment, fragment)
                .commit();
    }
}
