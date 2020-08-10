package com.example.camerasurface;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    SurfaceView surfaceView;
    SurfaceHolder holder;
    Camera mCamera;

//    Camera mCamera_1;
//    SurfaceView surfaceView_1;
//    SurfaceHolder holder_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                try {
                    mCamera.setPreviewDisplay(holder);
                    mCamera.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }

        });

//        surfaceView_1 = findViewById(R.id.surfaceView_1);
//        holder_1 = surfaceView_1.getHolder();
//        holder_1.addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder holder) {
//
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//                try {
//                    mCamera_1.setPreviewDisplay(holder);
//                    mCamera_1.startPreview();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder holder) {
//
//            }
//
//        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        int cout = Camera.getNumberOfCameras();
        int CameraIndex = 0;
        Log.i("***************", "Camera Count:" + cout);
        Camera.CameraInfo info = new Camera.CameraInfo();


        for (int nIndex = 0; nIndex < cout; nIndex++) {
            Camera.getCameraInfo(nIndex, info);

            Log.i("***************", "info.orientation:" + info.orientation);
            Log.i("***************", "info.facing" + info.facing);

            CameraIndex = nIndex;
        }

        Log.i("***************", "CameraIndex:" + CameraIndex);

        mCamera = Camera.open(0);
        if (mCamera == null) {
            Log.i("onResume", "mCamera is null ");
        }
        Camera.Parameters param = mCamera.getParameters();
        //param.setPictureSize(640, 480);
        param.setPreviewSize(640, 480);
        //param.setPreviewFpsRange(15, 30);
        mCamera.setParameters(param);

        //mCamera.setDisplayOrientation(90);

//        mCamera_1 = Camera.open(1);
//        if (mCamera_1 == null) {
//            Log.i("onResume", "mCamera_1 is null ");
//        }
//        param.setPreviewSize(640, 480);
//        mCamera_1.setParameters(param);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }

//        if (mCamera_1 != null) {
//            mCamera_1.stopPreview();
//            mCamera_1.release();
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }

//        if (mCamera_1 != null) {
//            mCamera_1.stopPreview();
//            mCamera_1.release();
//        }
    }
}
