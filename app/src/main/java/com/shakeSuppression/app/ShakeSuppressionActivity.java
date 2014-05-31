package com.shakeSuppression.app;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.shakeSuppression.app.fullscreen.FullscreenView;
import com.shakeSuppression.app.shakedetection.ShakeEventListener;

import java.io.File;

public class ShakeSuppressionActivity extends Activity {

    private FullscreenView fullscreen;
    private SensorManager sensorManager;
    private ShakeEventListener shakeListener;
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shakeListener = new ShakeEventListener(new AnimationController(findViewById(R.id.pdfview)));
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        fullscreen = new FullscreenView(this, findViewById(R.id.fullscreen_content_controls),
                findViewById(R.id.fullscreen_content));
        pdfView = (PDFView) findViewById(R.id.pdfview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shake_suppression_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toggleSuppression:
                toggleSuppression(item);
                return true;
            case R.id.openFile:
                loadPdfFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/czerwony.pdf", 0);
                Toast.makeText(getApplicationContext(), getString(R.string.notSupportedMsg), Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    private void toggleSuppression(MenuItem item) {
        if (!item.isChecked()) {
            item.setChecked(true);
            sensorManager.registerListener(shakeListener, sensorManager
                    .getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
                    SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            item.setChecked(false);
            sensorManager.unregisterListener(shakeListener);
        }
    }

    private void loadPdfFile(String path, int onPage) {
        pdfView.fromFile(new File(path))
                .defaultPage(onPage)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        fullscreen.delayedFullscreenHide(100);
    }
}
