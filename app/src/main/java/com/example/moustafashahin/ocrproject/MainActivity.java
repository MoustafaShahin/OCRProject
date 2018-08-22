package com.example.moustafashahin.ocrproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    SurfaceView cameraView;
    //detected text
    TextView text;
    //
    String detected_text = "";
    CameraSource camera;
    final int Request = 1001;
// giving permission for camera
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case  Request:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                        return;
                    }
                    try {
                        camera.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraView = findViewById(R.id.surface);
        text= findViewById(R.id.text);

        TextRecognizer textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        if (!textRecognizer.isOperational()) {
            Log.w("test", "failed");
        } else {
            camera = new CameraSource.Builder(getApplicationContext(), textRecognizer).setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280, 1024).setAutoFocusEnabled(true).build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                          ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA},Request);
                            return;
                        }
                        camera.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {
                    camera.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
    @Override
    public void release() {

    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
final SparseArray<TextBlock> items = detections.getDetectedItems();
if(items.size() !=0){
    text.post(new Runnable() {
        @Override
        public void run() {
            StringBuilder s = new StringBuilder();
            for (int i = 0;i<items.size();i++) {
                TextBlock item = items.valueAt(i);
                s.append(item.getValue());
                s.append(" ");

            }
            text.setText(s);
            detected_text = text.getText().toString();
        }
    });
}
    }
});
        }
    }

    public void find(View view) {
        Intent i = new Intent(MainActivity.this,Main2Activity.class);
        i.putExtra("q",detected_text);
        startActivity(i);
    }
}
