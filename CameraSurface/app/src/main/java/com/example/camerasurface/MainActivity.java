package com.example.camerasurface;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    SurfaceView surfaceView;
    SurfaceHolder holder;
    Camera mCamera;
    private String TAG = "Surface Camera Demo";

    //Button mButton;
    //Camera mCamera_1;
    //SurfaceView surfaceView_1;
    //SurfaceHolder holder_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mButton = findViewById(R.id.button);
        //mButton.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        Toast.makeText(MainActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
        //
        //        if (mCamera != null) {
        //            mCamera.stopPreview();
        //            mCamera.release();
        //        }
        //
        //        mCamera = Camera.open(1);
        //        if (mCamera == null) {
        //            Log.i("onResume", "mCamera is null ");
        //        }
        //
        //        //相机参数设置
        //        Camera.Parameters param = mCamera.getParameters();
        //        param.setPreviewSize(640, 480);
        //        mCamera.setParameters(param);
        //
        //        mCamera.startPreview();
        //    }
        //});

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

        //surfaceView_1 = findViewById(R.id.surfaceView_1);
        //holder_1 = surfaceView_1.getHolder();
        //holder_1.addCallback(new SurfaceHolder.Callback() {
        //    @Override
        //    public void surfaceCreated(SurfaceHolder holder) {
        //
        //    }
        //
        //    @Override
        //    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //        try {
        //            mCamera_1.setPreviewDisplay(holder);
        //            mCamera_1.startPreview();
        //        } catch (IOException e) {
        //            e.printStackTrace();
        //        }
        //    }
        //
        //    @Override
        //    public void surfaceDestroyed(SurfaceHolder holder) {
        //
        //    }
        //
        //});

    }


    @Override
    protected void onResume() {
        super.onResume();

        int cout = Camera.getNumberOfCameras();
        int CameraIndex = 0;
        Log.i(TAG, "Camera Count:" + cout);
        Camera.CameraInfo info = new Camera.CameraInfo();


        for (int nIndex = 0; nIndex < cout; nIndex++) {
            Camera.getCameraInfo(nIndex, info);

            Log.i(TAG, "info.orientation:" + info.orientation);
            Log.i(TAG, "info.facing" + info.facing);

            CameraIndex = nIndex;
        }

        Log.i(TAG, "CameraIndex:" + CameraIndex);

        mCamera = Camera.open(1);
        if (mCamera == null) {
            Log.i(TAG, "mCamera is null ");
        }

        //相机参数设置
        Camera.Parameters param = mCamera.getParameters();

        //获取支持分辨率
        List<Camera.Size> supportSize = param.getSupportedPreviewSizes();
        for (Camera.Size size : supportSize) {
            Log.i(TAG, "Support Size w:" + size.width + "  h:" + size.height);
        }

        //设置视频分辨率
        param.setPreviewSize(640, 480);

        //自动对焦
        List<String> focusMode = param.getSupportedFocusModes();
        Log.i(TAG, "focusMode:" + focusMode);
        if(!focusMode.contains(Camera.Parameters.FOCUS_MODE_AUTO)){
            Log.i(TAG, "focusMode 不支持自动对焦");
        }

        //设置参数
        mCamera.setParameters(param);

        //设置旋转
        mCamera.setDisplayOrientation(270);

        //mCamera_1 = Camera.open(1);
        //if (mCamera_1 == null) {
        //  Log.i("onResume", "mCamera_1 is null ");
        //}
        //param.setPreviewSize(640, 480);
        //mCamera_1.setParameters(param);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }

        //if (mCamera_1 != null) {
        //  mCamera_1.stopPreview();
        //  mCamera_1.release();
        //}
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }

        //if (mCamera_1 != null) {
        //mCamera_1.stopPreview();
        //mCamera_1.release();
        //}
    }
}
