package com.android.prime.arab.ware.everythingutils.listeners;

import java.util.ArrayList;

public interface CopyTask {
     void done();

     void progress(String file);

     void error(ArrayList<String> e);
}
