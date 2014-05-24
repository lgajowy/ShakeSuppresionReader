package com.shakeSuppression.app;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;

import com.joanzapata.pdfview.PDFView;
import com.shakeSuppression.app.fullscreen.FullscreenView;
import com.shakeSuppression.app.shakedetection.ShakeEventListener;

import java.io.File;

public class ShakeSuppressionActivity extends Activity {

    private FullscreenView fullscreen;
    private SensorManager sensorManager;
    private ShakeEventListener shakeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullscreen = new FullscreenView(this, findViewById(R.id.fullscreen_content_controls),
                findViewById(R.id.fullscreen_content));


        File downloads = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        File dir = new File(downloads.getAbsolutePath() + "/SR_czesc_1.pdf");

        com.joanzapata.pdfview.PDFView pdfView = (PDFView) findViewById(R.id.pdfview);


        pdfView.fromFile(dir)
                .pages(0, 2, 1, 3, 3, 3)
                .defaultPage(1)
                .showMinimap(false)
                .enableSwipe(true)
                .load();

        shakeListener = new ShakeEventListener(new AnimationController(findViewById(R.id.pdfview)));
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(shakeListener, sensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL); // (2)
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        fullscreen.delayedFullscreenHide(100);
    }
}
