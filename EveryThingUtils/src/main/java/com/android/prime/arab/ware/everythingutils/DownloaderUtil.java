package com.android.prime.arab.ware.everythingutils;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.prime.arab.ware.everythingutils.DownloaderModel.DownloadFile;
import com.android.prime.arab.ware.everythingutils.DownloaderModel.DownloadService;
import android.content.Intent;
import com.android.prime.arab.ware.everythingutils.DownloaderModel.Utils;

public class DownloaderUtil {

	public void DownloadFornotification(String input, String FileName,Context context) {

		String urlToDownload = input;
		String pathOfFile = FileName;
		Intent intent = new Intent(context, DownloadService.class);
		intent.putExtra("url", urlToDownload);
		intent.putExtra("path", pathOfFile);
		Utils.startService(context, intent);
	}

	public void DownloadForView(ProgressBar progressBar, TextView textView, String input, String output,
			Context context) {
		new DownloadFile(input, output).setListener(new DownloadFile.onDownloadListener() {
			@Override
			public void onProgressListener(int progress, String lengthFiles) {
				textView.setText(lengthFiles);
				progressBar.setProgress((int) progress);
			}

			@Override
			public void onCompleteListener() {

			}

			@Override
			public void onErrorListener(String errorMessage) {
				Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show();
			}
		}).execute(0);

	}

	public void DownloadFileOnView(String input, String output, ProgressBar progressBar, TextView textView) {

		new DownloadFile(input, output).attachProgress(progressBar)
				.attachTextProgress(textView).execute(0);
	}

}
