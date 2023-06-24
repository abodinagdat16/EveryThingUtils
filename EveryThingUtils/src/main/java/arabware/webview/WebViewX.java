package arabware.webview;

import android.webkit.*;





public class WebViewX {

private WebView wv;

public WebViewX(WebView w) {

w.getSettings().setJavaScriptEnabled(true);

w.setWebChromeClient(new android.webkit.WebChromeClient());

wv = w;

}

public void runJavaScript(String js) {

if (android.os.Build.VERSION.SDK_INT >= 19) {

wv.evaluateJavascript(js,null);

} else {

wv.loadUrl("javascript:" + js);

}

}

public void runHtml(String html) {

wv.loadData(html,"text/html","UTF-8");

}


public void setColor(String id , String color) {
    String js = "document.getElementById('" + id + "').style.color = '" + color + "';";
    runJavaScript(js);
}

public void setColor(String id , int r , int g , int b , int a) {
    String js = "document.getElementById('" + id + "').style.color = '" + "rgba(" + r + "," + g + "," + b + "," + a + ")" + "';";
    runJavaScript(js);
}

public void setBackgroundColor(String id , String color) {
    String js = "document.getElementById('" + id + "').style.background = '" + color + "';";
    runJavaScript(js);
}

public void setBackgroundColor(String id , int r , int g , int b , int a) {
    String js = "document.getElementById('" + id + "').style.background = '" + "rgba(" + r + "," + g + "," + b + "," + a + ")" + "';";
    runJavaScript(js);
}

public void changeInputType(String id, String type) {
    String js = "var input = document.getElementById('" + id + "'); " +
            "input.type = '" + type + "';";
    runJavaScript(js);
}


public void changeTextContent(String id , String text) {

    String js = "document.getElementById('" + id + "').textContent = '" + text + "';";

runJavaScript(js);

}

public void changeFontSize(String id , int size) {

    String js = "document.getElementById('" + id + "').style.fontSize = '" + size + "px" + "';";

runJavaScript(js);

}

public void changeInput(String id , String text) {

    String js = "document.getElementById('" + id + "').value = '" + text + "';";

runJavaScript(js);

}


public void hideElement(String id) {

String js = "document.getElementById('" + id + "').style.display = 'none';";

runJavaScript(js);

}

public void unhideElement(String id) {

String js = "document.getElementById('" + id + "').style.display = 'block';";

runJavaScript(js);

}

public void onClick(String id , String js2) {

String js = "javascript:document.getElementById('"+id+"').addEventListener('click', function() {" + js2 + "});";
wv.loadUrl(js);

}


public void playVideo(String id) {
    String js = "var video = document.getElementById('" + id + "'); video.play();";
    runJavaScript(js);
}

public void pauseVideo(String id) {
    String js = "var video = document.getElementById('" + id + "'); video.pause();";
    runJavaScript(js);
}

public void stopVideo(String id) {
    String js = "var video = document.getElementById('" + id + "'); video.pause(); video.currentTime = 0;";
    runJavaScript(js);
}

public void changeVideoSource(String id, String newVideoUrl) {
    String js = "var video = document.getElementById('" + id + "'); video.src = '" + newVideoUrl + "';";
    runJavaScript(js);
}

public void seekVideo(String id, int seconds) {
    String js = "var video = document.getElementById('" + id + "'); video.currentTime = " + seconds + ";";
    runJavaScript(js);
}


public void muteVideo(String id) {
    String js = "var video = document.getElementById('" + id + "'); video.muted = true;";
    runJavaScript(js);
}

public void unmuteVideo(String id) {
    String js = "var video = document.getElementById('" + id + "'); video.muted = false;";
    runJavaScript(js);
}

public void editVideoController(String id, boolean show) {
    String js = "var video = document.getElementById('" + id + "'); video.controls = " + show + ";";
    runJavaScript(js);
}

public void setVideoPlaybackSpeed(String id, float speed) {
String js = "var video = document.getElementById('" + id + "'); video.playbackRate = " + speed + ";";
runJavaScript(js);
}



public void playAudio(String id) {
    String js = "var audio = document.getElementById('" + id + "'); audio.play();";
    runJavaScript(js);
}

public void pauseAudio(String id) {
    String js = "var audio = document.getElementById('" + id + "'); audio.pause();";
    runJavaScript(js);
}

public void stopAudio(String id) {
    String js = "var audio = document.getElementById('" + id + "'); audio.pause(); audio.currentTime = 0;";
    runJavaScript(js);
}

public void changeAudioSource(String id, String newAudioUrl) {
    String js = "var audio = document.getElementById('" + id + "'); audio.src = '" + newAudioUrl + "';";
    runJavaScript(js);
}

public void seekAudio(String id, int seconds) {
    String js = "var audio = document.getElementById('" + id + "'); audio.currentTime = " + seconds + ";";
    runJavaScript(js);
}


public void muteAudio(String id) {
    String js = "var audio = document.getElementById('" + id + "'); audio.muted = true;";
    runJavaScript(js);
}

public void unmuteAudio(String id) {
    String js = "var audio = document.getElementById('" + id + "'); audio.muted = false;";
    runJavaScript(js);
}

public void editAudioController(String id, boolean show) {
    String js = "var audio = document.getElementById('" + id + "'); audio.controls = " + show + ";";
    runJavaScript(js);
}

public void setAudioPlaybackSpeed(String id, float speed) {
String js = "var audio = document.getElementById('" + id + "'); audio.playbackRate = " + speed + ";";
runJavaScript(js);
}


public void getCurrentDuration(String id, final ValueCallback<String> callback) {
    String js = "var media = document.getElementById('" + id + "'); " +
            "var currentDuration = media.currentTime; " +
            "currentDuration.toString();";
    runJavaScriptNewer(js, new ValueCallback<String>() {
    @Override
    public void onReceiveValue(String value) {
callback.onReceiveValue(value);
}});
}

public void getMaxDuration(String id, final ValueCallback<String> callback) {
    String js = "var media = document.getElementById('" + id + "'); " +
            "var maxDuration = media.duration; " +
            "maxDuration.toString();";
    runJavaScriptNewer(js, new ValueCallback<String>() {
    @Override
    public void onReceiveValue(String value) {
callback.onReceiveValue(value);
}});
}

private void runJavaScriptNewer(String js, ValueCallback<String> callback) {
    if (android.os.Build.VERSION.SDK_INT >= 19) {
        wv.evaluateJavascript(js, callback);
    } else {
        //android api 19 and up only 
    }
}

public void addTableRow(String id , String rowName, int position) {
    String javascriptCode = "var table = document.getElementById('" + id + "'); " +
                            "var row = table.insertRow(" + position + "); " +
                            "var cell = row.insertCell(0); " +
                            "cell.innerHTML = '" + rowName + "';";
    runJavaScript(javascriptCode);
}

public void deleteTableRow(String id , int position) {
    String javascriptCode = "var table = document.getElementById('" + id + "'); " +
                            "table.deleteRow(" + position + ");";
    runJavaScript(javascriptCode);
}

public void changeTableCellContent(String id , int rowIndex , int cellIndex , String newContent) {
    String javascriptCode = "var table = document.getElementById('" + id + "'); " +
                            "var row = table.rows[" + rowIndex + "]; " +
                            "var cell = row.cells[" + cellIndex + "]; " +
                            "cell.innerHTML = '" + newContent + "';";
    runJavaScript(javascriptCode);
}

public void addTableCell(String id , int rowIndex, String cellContent) {
    String javascriptCode = "var table = document.getElementById('" + id + "'); " +
                            "var row = table.rows[" + rowIndex + "]; " +
                            "var cell = row.insertCell(-1); " +
                            "cell.innerHTML = '" + cellContent + "';";
    runJavaScript(javascriptCode);
}

public void deleteTableCell(String id , int rowIndex, int cellIndex) {
    String javascriptCode = "var table = document.getElementById('" + id + "'); " +
                            "var row = table.rows[" + rowIndex + "]; " +
                            "row.deleteCell(" + cellIndex + ");";
    runJavaScript(javascriptCode);
}





}

