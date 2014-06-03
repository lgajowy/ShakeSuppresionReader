package com.shakeSuppression.app.pdfview;

import com.joanzapata.pdfview.PDFView;

import java.io.File;

public class PdfViewController {

    private PDFView pdfView;

    public PdfViewController(PDFView pdfView) {
        this.pdfView = pdfView;
    }

    public void loadPdfFile(String path, int onPage) {
        pdfView.fromFile(new File(path))
                .defaultPage(onPage)
                .showMinimap(false)
                .enableSwipe(true)
                .load();
    }
}
