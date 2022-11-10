package com.android.prime.arab.ware.everythingutils;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.regex.*;
import org.json.*;
import android.os.storage.StorageManager;
import androidx.activity.result.ActivityResultLauncher; 
import androidx.activity.result.ActivityResultRegistry; 
import androidx.activity.result.ActivityResultCallback; 
import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.documentfile.provider.DocumentFile;

public class SafUtils {
    AppCompatActivity act;
    StorageManager sm; 
    Intent inte;

    public SafUtils(AppCompatActivity act){
        this.act=act;
        } 
        
        
        public void askToAccessDataFolder(OnPermissionListener opl){
        if(checkPermission( "/storage/emulated/0/Android/data/" )){
            opl.onHavePermission();
        }else{
                ActivityResultLauncher<Intent> arl = act.registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == Activity.RESULT_OK) {
                                    Intent data = result.getData();
                                    opl.onPermissionAllowed( result.toString() );
                                     } else {
                                   opl.onPermissionDenied( result.toString() );
                                }
                            }
                        } );
                sm = (StorageManager) act.getSystemService( Context.STORAGE_SERVICE );
                inte = sm.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
                String d = "Android%2F" + "data";
                Uri u = (Uri) inte.getParcelableExtra( "android.provider.extra.INITIAL_URI" );
                String m = u.toString();
                m = m.replace( "/root/", "/document/" );
                m += "%3A" + d;
                Uri u1 = Uri.parse( m );
                inte.putExtra( "android.provider.extra.INITIAL_URI", u1 );
                arl.launch( inte );


            }
            } 
            
             
        public void askToAccessObbFolder(int requestCode){
           sm =(StorageManager) act.getSystemService(Context.STORAGE_SERVICE);
           inte =sm.getPrimaryStorageVolume().createOpenDocumentTreeIntent();
           String d="Android%2F"+"obb";
           Uri u =(Uri) inte.getParcelableExtra("android.provider.extra.INITIAL_URI");
           String m =u.toString();
           m = m.replace("/root/", "/document/");
           m+="%3A" +d;
           Uri u1=Uri.parse(m);
           inte.putExtra("android.provider.extra.INITIAL_URI", u1);
           act.startActivityForResult(inte, requestCode);

            
            } 
            
            public boolean checkPermission(String u){
            Uri uri=Uri.parse( u );
                DocumentFile df = DocumentFile.fromTreeUri( act.getApplicationContext(),uri );
                if(df.canRead()&&df.canWrite()){
                    return true;
                }
                return false;
            }
  /**  private void havePermissionToPath(final String _uri,final int requestCode) {
        ActivityResultLauncher<Intent>  mainly= act.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();

                        }else{

                        }
                    }
                });
	 Intent i = new Intent();
	i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION |  Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
			
	i.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);				    i.putExtra(android.provider.DocumentsContract.EXTRA_INITIAL_URI, Uri.parse(_uri));

    mainly.launch( inte );

	}**/

  public interface OnPermissionListener{
      public void onHavePermission();
      public void onPermissionAllowed(String message);
      public void onPermissionDenied(String error);
  }



}
































