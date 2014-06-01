package com.shakeSuppression.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.joanzapata.pdfview.PDFView;
import com.shakeSuppression.app.R;
import com.shakeSuppression.app.fullscreen.FullscreenView;
import com.shakeSuppression.app.pdfview.PdfViewController;
import com.shakeSuppression.app.shakedetection.ShakeManager;

public class ShakeSuppressionActivity extends Activity {

    private FullscreenView fullscreen;
    private ShakeManager shakeManager;
    private PdfViewController pdfViewController;
    private String uri ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fullscreen = new FullscreenView(this, findViewById(R.id.fullscreen_content_controls),
                findViewById(R.id.fullscreen_content));
        PDFView pdfView = (PDFView) findViewById(R.id.pdfview);
        shakeManager = new ShakeManager(this.getApplicationContext(), pdfView);
        pdfViewController = new PdfViewController(pdfView);

        final Intent intent = getIntent();
        final String action = intent.getAction();
        if(Intent.ACTION_VIEW.equals(action)){
            uri = intent.getData().getEncodedPath();
            pdfViewController.loadPdfFile(uri, 1);
        }
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
                pdfViewController.loadPdfFile(Environment.getExternalStorageDirectory().getAbsolutePath() + "/czerwony.pdf", 0);
                return true;
            default:
                return false;
        }
    }

    private void toggleSuppression(MenuItem item) {
        if (!item.isChecked()) {
            item.setChecked(true);
            shakeManager.turnOnShakeDetection();
        } else {
            item.setChecked(false);
            shakeManager.turnOffShakeDetection();
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        fullscreen.delayedFullscreenHide(100);
    }
}
