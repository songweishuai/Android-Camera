package com.example.camerasurface;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Surface Camera Demo";

    private String path;
    MediaRecorder mRecorder;

    SurfaceView surfaceView;
    SurfaceHolder holder;
    Camera mCamera;

    SurfaceView surfaceView_2;
    SurfaceHolder holder_2;
    Camera mCamera_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();

        surfaceView_2 = findViewById(R.id.surfaceView_2);
        holder_2 = surfaceView_2.getHolder();

        /*openCamera_1();
        mRecorder = new MediaRecorder();
        mRecorder.setCamera(mCamera);

        try {
            // 这两项需要放在setOutputFormat之前
            mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
            mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);

            // Set output file format
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);

            // 这两项需要放在setOutputFormat之后
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);

            mRecorder.setVideoSize(640, 480);
            mRecorder.setVideoFrameRate(30);
            mRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
            mRecorder.setOrientationHint(90);
            //设置记录会话的最大持续时间（毫秒）
            mRecorder.setMaxDuration(30 * 1000);
            mRecorder.setPreviewDisplay(holder.getSurface());

            path = getSDPath();
            Log.i("*********","path:"+path);
            if (path != null) {
//                File dir = new File(path + "/recordtest");
                File dir = new File("/data"+"/recordtest");

                if (!dir.exists()) {
                    Log.i(TAG,"1111111111115:"+dir.mkdir());
                }
                path = dir + "/" + getDate() + ".mp4";
                Log.i(TAG,"path:"+path);
                mRecorder.setOutputFile(path);
                mRecorder.prepare();
                mRecorder.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/

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

        holder_2.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                try {
                    mCamera_2.setPreviewDisplay(holder);
                    mCamera_2.startPreview();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }

        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        int cout = Camera.getNumberOfCameras();
        Log.i(TAG, "Camera Count:" + cout);
        Camera.CameraInfo info = new Camera.CameraInfo();


        for (int nIndex = 0; nIndex < cout; nIndex++) {
            Camera.getCameraInfo(nIndex, info);
            Log.i(TAG, "info.orientation:" + info.orientation);
            Log.i(TAG, "info.facing" + info.facing);
        }

        openCamera_1();

        openCamera_2();
    }

    private void openCamera_1() {
        int width = 320;
        int height = 240;

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
        param.setPreviewSize(width, height);

        //自动对焦
        List<String> focusMode = param.getSupportedFocusModes();
        Log.i(TAG, "focusMode:" + focusMode);
        if (!focusMode.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            Log.i(TAG, "focusMode 不支持自动对焦");
        }

        //param.getPictureFormat();
        List<Integer> suppFormat = param.getSupportedPictureFormats();
        for (int form : suppFormat) {
            Log.i(TAG, "support Format: " + form);
        }
        //PixelFormat.RGB_565


        //设置参数
        mCamera.setParameters(param);

        Log.i(TAG, "onResume: " + mCamera.toString());

        //设置旋转
        mCamera.setDisplayOrientation(270);
    }

    private void openCamera_2() {
        int width = 320;
        int height = 240;

        mCamera_2 = Camera.open(0);
        if (mCamera_2 == null) {
            Log.i(TAG, "mCamera_2 is null ");
        }

        //相机参数设置
        Camera.Parameters param = mCamera_2.getParameters();

        //获取支持分辨率
        List<Camera.Size> supportSize = param.getSupportedPreviewSizes();
        for (Camera.Size size : supportSize) {
            Log.i(TAG, "Support Size w:" + size.width + "  h:" + size.height);
        }

        //设置视频分辨率
        param.setPreviewSize(width, height);

        //自动对焦
        List<String> focusMode = param.getSupportedFocusModes();
        Log.i(TAG, "focusMode:" + focusMode);
        if (!focusMode.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
            Log.i(TAG, "focusMode 不支持自动对焦");
        }

        //param.getPictureFormat();
        List<Integer> suppFormat = param.getSupportedPictureFormats();
        for (int form : suppFormat) {
            Log.i(TAG, "support Format: " + form);
        }
        //PixelFormat.RGB_565


        //设置参数
        mCamera_2.setParameters(param);

        Log.i(TAG, "onResume: " + mCamera_2.toString());

        //设置旋转
        mCamera_2.setDisplayOrientation(270);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }

        if (mCamera_2 != null) {
            mCamera_2.stopPreview();
            mCamera_2.release();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.release();
        }

        if (mCamera_2 != null) {
            mCamera_2.stopPreview();
            mCamera_2.release();
        }
    }

    /**
     * 获取SD path
     *
     * @return
     */
    public String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
            return sdDir.toString();
        }

        return null;
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    public static String getDate() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);           // 获取年份
        int month = ca.get(Calendar.MONTH);         // 获取月份
        int day = ca.get(Calendar.DATE);            // 获取日
        int minute = ca.get(Calendar.MINUTE);       // 分
        int hour = ca.get(Calendar.HOUR);           // 小时
        int second = ca.get(Calendar.SECOND);       // 秒

        String date = "" + year + (month + 1) + day + hour + minute + second;
        Log.d("getDate:", "date:" + date);

        return date;
    }
}
