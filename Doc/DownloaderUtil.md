### Downloader Util 

- This is a download model that is very summarized and useful. This feature includes download with notification and view. Even you can get the download length of the file with the amount. Let's get started.


### GetStart 

### If you want to receive a notification, use this code, an input path and an output path, then the current activity
``` java 
  DownloaderUtil downloaderUtil = new DownloaderUtil();
		downloaderUtil.DownloadFornotification("input","output",MainActivity.this);

```

### If you want to watch the downloaded value, use this code. bar is the progress of the bar. TextView also shows the value like Bar.

``` java 

DownloaderUtil downloaderUtil = new DownloaderUtil();
	
		downloaderUtil.DownloadForView(bar,textView,"input","output",this);
```

### This feature is the same as the code above, the difference between this feature is that it is not very advanced and it does not inform you if an error occurs during the download process.

``` java 

DownloaderUtil downloaderUtil = new DownloaderUtil();
		downloaderUtil.DownloadFileOnView("input","output",bar,textView);
```
