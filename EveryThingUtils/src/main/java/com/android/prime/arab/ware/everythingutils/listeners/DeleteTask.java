package com.android.prime.arab.ware.everythingutils.listeners;
import java.util.ArrayList;
public interface DeleteTask {
	void loading();
    void done();
    void progress(String file);
	void error(String error);
    void error(ArrayList<String> message);
}
