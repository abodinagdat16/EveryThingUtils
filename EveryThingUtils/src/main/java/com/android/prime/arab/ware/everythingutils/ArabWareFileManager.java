/*

this class was created by arabware owner (Ameer Ezit)
You Are Free To Use it Or learn coding from it
BUT , IF YOU JUST CHANGE the class name and some small changes and then say
it is made by you !
that will be illegal. 




credits to people or things that helped me

-Ameer Ezit, I hate putting my name at first but I worked hard on more than thousand lines !
imagine how many errors I got !!!
-Sketchub , Storage List project , helped me to get the micro sd card
path in android 7 and up.
-StackOverFlow , for some answers about what DOES THIS DO , and other questions
-Sketchware Offical Developers , to thier FileUtils class , I took delete dir , read and write methods with changes


I HOPE THIS CLASS BE USED TO DO HELPFUL THINGS


TO DO LIST :

-usb management
-root management
-custom functions like rename with rules (like mt manager app) and motor
-converting all uri types to real path






*/


//this is the package name , you need it to access the java class like importing , import arabware.file.ArabWareFileManager;
package arabware.file;

// these are the imports

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.provider.Settings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import arabware.file.listeners.CopyTask;
import arabware.file.listeners.CreateNewTask;
import arabware.file.listeners.DeleteTask;
import arabware.file.listeners.RenameTask;

//this is the definition of the class

public class ArabWareFileManager {

    //fields

    public static boolean android4 = 21 > Build.VERSION.SDK_INT;


    //path of file or folder to do something on
    public static final boolean android5 = 23 > Build.VERSION.SDK_INT;

    //context of an activity or any class that extends it , for fragments it must be FragmentName.this.getActivity , for activities it must be ActivityName.this
    public static final boolean android6 = android.os.Build.VERSION.SDK_INT == 23;

    //array list of Strings , these are the files or folders or both
    public static final boolean android7 = 26 > android.os.Build.VERSION.SDK_INT && android.os.Build.VERSION.SDK_INT > 23;
    public static final boolean android8 = 28 > android.os.Build.VERSION.SDK_INT && android.os.Build.VERSION.SDK_INT > 25;
    public static final boolean android9 = android.os.Build.VERSION.SDK_INT == 28;


    //just temporary strings to add "/" or to not add , or for doing other stuff
    public static final boolean android10 = android.os.Build.VERSION.SDK_INT == 29;
    public static final boolean android11 = android.os.Build.VERSION.SDK_INT == 30;
    public static final boolean android12 = android.os.Build.VERSION.SDK_INT == 31;
    public static final boolean android13 = android.os.Build.VERSION.SDK_INT == 32;


    //just to save errors temporary
    private File file;


    //these fields are the android sdk versions
    private String path;
    private Context context;
    private ArrayList<String> data;
    private ArrayList<String> temp_data;
    private ArrayList<String> temp_data2;
    private String temp1;
    private String temp2;
    private String TEMP;
    private ArrayList<String> errors;


    //this field is temporary saving number value for looping functions like getSize(String) for folders
    private long n;


    //constructors


    //this constructor is for methods that works with no special params

    public ArabWareFileManager() {
        n = 0;
        errors = new ArrayList<>();
        data = new ArrayList<>();
        temp_data = new ArrayList<>();
        temp_data2 = new ArrayList<>();
        TEMP = "";
    }

    //this constructor is for methods that need path

    public ArabWareFileManager(String p) {
        this.path = p;
        n = 0;
        errors = new ArrayList<>();
        data = new ArrayList<>();
        temp_data = new ArrayList<>();
        temp_data2 = new ArrayList<>();
        TEMP = "";
    }

    //this constructor is for methods that needs Context


    public ArabWareFileManager(Context c) {
        this.context = c;
        n = 0;
        errors = new ArrayList<>();
        data = new ArrayList<>();
        temp_data = new ArrayList<>();
        temp_data2 = new ArrayList<>();
        TEMP = "";
    }


    //this constructor is for methods that need path and context

    public ArabWareFileManager(String p, Context c) {
        this.path = p;
        this.context = c;
        n = 0;
        errors = new ArrayList<>();
        data = new ArrayList<>();
        temp_data = new ArrayList<>();
        temp_data2 = new ArrayList<>();
        TEMP = "";
    }


    //methods (functions)


    //this method creates folder with name , in path from constructors that contain String

    public static void chmod(String path, int mode) throws Exception {
        Class<?> libcore = Class.forName("libcore.io.Libcore");
        @SuppressLint("DiscouragedPrivateApi") Field field = libcore.getDeclaredField("os");
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        Object os = field.get(field);
        Method chmod2 = Objects.requireNonNull(os).getClass().getMethod("chmod", String.class, int.class);
        chmod2.invoke(os, path, mode);
    }

    //this method creates file with name , in path from constructors that contain String

    public static <T extends Activity> void checkStoragePermissions(T c) {
        if (isAndroid11()) {
            try {
                Uri uri = Uri.parse("package:" + c.getPackageName());
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION, uri);
                c.startActivity(intent);
            } catch (Exception ex) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                c.startActivity(intent);
            }
        }
        if (isAndroid6()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                c.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3137);
            }
        }
    }


    //this method renames folder or file with new name of path from constructors that contain String

    public static boolean isAndroid11() {
        return (android.os.Build.VERSION.SDK_INT == 30 || android.os.Build.VERSION.SDK_INT > 30);
    }

    //this method moves folder or file to path from constructors that contain String

    public static boolean isAndroid6() {
        return (android.os.Build.VERSION.SDK_INT == 23 || android.os.Build.VERSION.SDK_INT > 23);
    }


    //if it works, don't touch it ! , OK?
    //this method copies folder or file to a folder ...

    public static <T extends Activity> boolean isFullAccessFiles(T c) {
        if (isAndroid11()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                return Environment.isExternalStorageManager();
            }
        }
        if (isAndroid6()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (c.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    return false;
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return c.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED;
            }

        } else {

            return true;

        }
        return false;
    }

    public void createFolder(String name, CreateNewTask cnt) {

        if (path.endsWith("/")) {
            temp1 = path;
        } else {
            temp1 = path + "/";
        }
        temp2 = name.replace("/", "");


        try {

            if (new File(temp1).exists()) {
                new File(temp1 + temp2).mkdirs();

                if (new File(temp1 + temp2).exists()) {
                    if (cnt != null) {
                        cnt.done();
                    }
                } else {
                    if (cnt != null) {
                        cnt.error("failed , try to use correct name or path or give permissions to the app!");
                    }
                }


            } else {
                if (cnt != null) {
                    cnt.error(temp1 + " " + "does not exit , or permission denied");
                }
            }


        } catch (Exception e) {

            if (cnt == null) {
                throw new RuntimeException(e);
            } else {
                cnt.error(e.toString());
            }


        }

    }

    public void createFile(String name, CreateNewTask cnt) {

        if (path.endsWith("/")) {
            temp1 = path;
        } else {
            temp1 = path + "/";
        }
        temp2 = name.replace("/", "");


        try {

            if (new File(temp1).exists()) {
                new File(temp1 + temp2).createNewFile();

                if (new File(temp1 + temp2).exists()) {
                    if (cnt != null) {
                        cnt.done();
                    }
                } else {
                    if (cnt != null) {
                        cnt.error("failed , try to use correct name or path or give permissions to the app!");
                    }
                }


            } else {
                if (cnt != null) {
                    cnt.error(temp1 + " " + "does not exit , or permission denied");
                }
            }


        } catch (Exception e) {

            if (cnt == null) {
                throw new RuntimeException(e);
            } else {
                cnt.error(e.toString());
            }


        }


    }


    //this method deletes folder or file of path from constructors that contain String

    public void rename(String name, RenameTask rt) {
        errors = new ArrayList<>();
        if (!(new File(path).exists())) {
            if (rt != null) {
                errors.add("the path" + " " + path + " does not exist");
                rt.error(errors);
            }
        } else {


            if (isFile()) {

                if (rt != null) {
                    rt.progress(path.replace(Uri.parse(path).getLastPathSegment(), "").concat(name));
                }

                new File(path).renameTo(new File(path.replace(Uri.parse(path).getLastPathSegment(), "").concat(name)));

                if (rt != null) {
                    rt.done();
                }


            } else {

                if (path.endsWith("/")) {
                    temp1 = path;
                } else {
                    temp1 = path + "/";
                }

                if (rt != null) {
                    rt.progress(temp1.replace(Uri.parse(temp1).getLastPathSegment(), "").concat(name));
                }

                new File(path).renameTo(new File(temp1.replace(Uri.parse(temp1).getLastPathSegment(), name)));

                if (rt != null) {
                    rt.done();
                }
            }
        }

    }

    public void move(String where) {


        if (isFile()) {

            temp1 = path;


        }

        if (isFolder()) {

            if (path.endsWith("/")) {
                temp1 = path;
            } else {
                temp1 = path + "/";
            }


        }

        if (new File(temp1).exists()) {

            if (new File(where).exists()) {

                if (new File(where).isDirectory()) {
                    if (where.endsWith("/")) {
                        temp2 = where;
                    } else {
                        temp2 = where + "/";
                    }
                }
                if (new File(where).isFile()) {
                    throw new RuntimeException(new Exception("you are trying to move file or folder into file"));
                }
                String temp3 = Uri.parse(temp1).getLastPathSegment().replace("/", "");
                if (new File(temp1).isDirectory()) {
                    new File(temp1).renameTo(new File(temp2 + temp3 + "/"));
                } else {
                    new File(temp1).renameTo(new File(temp2 + temp3));
                }


            } else {
                throw new RuntimeException(new Exception("no file or folder found " + where));
            }


        } else {
            throw new RuntimeException(new Exception("no file or folder found " + temp1));
        }


    }

    //this method changes Modification date of a file or folder of path from constructors that contain String

    public void copy(String where, CopyTask ct) {

        if (!(new File(path).exists())) {
            throw new RuntimeException(new Exception("the path " + path + " does not exist"));
        }
        if (!(new File(where).exists())) {
            throw new RuntimeException(new Exception("the path " + path + " does not exist"));
        }
        if (new File(where).isFile()) {
            throw new RuntimeException(new Exception("you can't copy files or folders into a file"));
        }

        copy(path, where, ct);


        if (ct != null) {
            ct.done();
        }


    }

    //this method sets the access to a file or folder of path from constructors that contain String , it needs the root , and your app must have root permission

    private void copy(String path1, String path2, CopyTask ct) {

        if (!(path1.endsWith("/"))) {
            path1 = path1 + "/";
        }

        if (!(path2.endsWith("/"))) {
            path2 = path2 + "/";
        }


        if (TEMP.equals("")) {
            TEMP = path1;
        }


        if (isFile(path1)) {


            if (ct != null) {
                ct.progress(path1);
            }


            copyFile(path1, path2, false, path1, ct);


        } else {


            File[] list = file.listFiles();

            for (File f : Objects.requireNonNull(list)) {


                if (f.isFile()) {

                    if (TEMP.equals(path1)) {

                        if (ct != null) {
                            ct.progress(f.getAbsolutePath());
                        }

                        copyFile(f.getAbsolutePath(), path2, true, path1, ct);

                    } else {

                        if (ct != null) {
                            ct.progress(f.getAbsolutePath());
                        }


                        copyFile(f.getAbsolutePath(), path2 + getName(TEMP) + "/", true, path1, ct);

                    }

                } else if (f.isDirectory()) {

                    copy(f.getAbsolutePath(), path2, ct);
                }


            }


        }


    }

    private void copyFile(String path1, String path2, boolean fromFolder, String org, CopyTask ct) {


        if (fromFolder) {


            if (path2.endsWith("/")) {

                if (new File(path2 + getParent(path1).replace(getParent(org), "")).exists()) {

                } else {


                    new File(path2 + getParent(path1).replace(getParent(org), "")).mkdirs();

                }


                try {
                    int count;
                    InputStream input = new FileInputStream(path1);
                    OutputStream output = new FileOutputStream(path2 + getParent(path1).replace(getParent(org), "") + getName(path1));
                    byte[] data = new byte[1024];
                    while ((count = input.read(data)) > 0) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                } catch (Exception e) {

                    if (ct != null) {
                        errors.add(e.toString());
                        ct.error(errors);
                    }


                }


            } else {


                if (new File(path2 + "/" + getParent(path1).replace(getParent(org), "")).exists()) {

                } else {


                    new File(path2 + "/" + getParent(path1).replace(getParent(org), "")).mkdirs();

                }


                try {
                    int count;
                    InputStream input = new FileInputStream(path1);
                    OutputStream output = new FileOutputStream(path2 + "/" + getParent(path1).replace(getParent(org), "") + getName(path1));
                    byte[] data = new byte[1024];
                    while ((count = input.read(data)) > 0) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                } catch (Exception e) {
                    if (ct != null) {
                        errors.add(e.toString());
                        ct.error(errors);
                    }
                }


            }


        } else {


            if (path2.endsWith("/")) {

                try {
                    int count;
                    InputStream input = new FileInputStream(path1);
                    OutputStream output = new FileOutputStream(path2 + getName(path1));
                    byte[] data = new byte[1024];
                    while ((count = input.read(data)) > 0) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                } catch (Exception e) {
                    if (ct != null) {
                        errors.add(e.toString());
                        ct.error(errors);
                    }
                }


            } else {

                try {
                    int count;
                    InputStream input = new FileInputStream(path1);
                    OutputStream output = new FileOutputStream(path2 + "/" + getName(path1));
                    byte[] data = new byte[1024];
                    while ((count = input.read(data)) > 0) {
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                } catch (Exception e) {
                    if (ct != null) {
                        errors.add(e.toString());
                        ct.error(errors);
                    }
                }


            }


        }


    }
    
    
    
    
    /*
    //this method renames Files or Folders with one-rules , like file1.txt , file2.txt etc... this method does not need String constructors since it works by ArrayList of Strings of paths
    
    public void rename(ArrayList<String> files , String rules , RenameTask rt) {
        
    }
    */


    //this method shares file of path from constructors that contain String

    public void delete(DeleteTask dt) {


        deleteTask(path, dt);
        if (dt != null) {
            dt.done();
        }


    }

    //this method edits the file and deletes the old text and writes new text , of path from constructors that contain String

    private void deleteTask(String what, DeleteTask dt) {


        File file = new File(what);

        if (!file.exists()) {
            throw new RuntimeException(new FileNotFoundException("Unable to delete Non Existing File Or Folder"));
        }

        if (file.isFile()) {
            try {


                file.delete();

            } catch (Exception e) {

                if (dt != null) {
                    errors.add(e.toString());
                    dt.error(errors);
                }

            }

            if (dt != null) {
                dt.progress(file.getAbsolutePath());
            }


        } else {


            File[] files = file.listFiles();

            if (files != null) {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        deleteTask(file2.getAbsolutePath(), dt);
                    }

                    if (file2.isFile()) {


                        try {


                            file2.delete();

                        } catch (Exception e) {

                            if (dt != null) {
                                errors.add(e.toString());
                                dt.error(errors);
                            }

                        }


                        if (dt != null) {
                            dt.progress(file2.getAbsolutePath());
                        }


                    }
                }
            }

            file.delete();


        }


    }

    //as the previous method , but it adds the new text to old text without replacing or deleting

    public void changeModifyDate(int year, int month, int day, int hour, int minute, int second) {

        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month - 1);
        c.set(Calendar.DAY_OF_MONTH, day - 1);
        c.set(Calendar.HOUR, hour);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, second);

        if (!(new File(path).exists())) {
            throw new RuntimeException(new Exception("unable to find the file or folder to change the Modification Date!"));
        }

        if (isFolder()) {
            if (!path.endsWith("/")) {
                path = path + "/";
            }
        }

        new File(path).setLastModified(c.getTimeInMillis());


    }
    
    /*
    //this method deletes the hidden files or folders , of path from constructors that contain String
    
    public void deleteHidden(DeleteTask dt) {
        
    }
    
    //this method deletes files or Folders with custom rules like hidden = true to delete hidden or type = jpg to delete jpg files , of path from constructors that contain String 
    
    
    public void deleteCustom(boolean hidden , String type, DeleteTask dt) {
        
    }
    
    */


    //this method gets size of a file or folder in bytes , of path from constructors that contain String 

    public void setAccess(int mode) throws Exception {
        if (new File(path).exists()) {
            if (isFile()) {
                temp1 = path;
            }
            if (isFolder()) {
                if (path.endsWith("/")) {
                    temp1 = path;
                } else {
                    temp1 = path + "/";
                }
            }
        } else {
            throw new Exception("file not found");
        }
        chmod(path, mode);


    }


    /*this method gets the used space for a folder or file*/

    public <T extends Context> void share(T c, String type, String message) {
        android.os.StrictMode.VmPolicy.Builder builder = new android.os.StrictMode.VmPolicy.Builder();
        android.os.StrictMode.setVmPolicy(builder.build());
        android.content.Intent sharingIntent = new android.content.Intent(android.content.Intent.ACTION_SEND);
        android.net.Uri screenshotUri = android.net.Uri.parse(path);
        if (type == null || type.equals("")) {
            sharingIntent.setType("*/*");
        } else {
            sharingIntent.setType(type);
        }
        sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, screenshotUri);
        c.startActivity(android.content.Intent.createChooser(sharingIntent, message));

    }



    /*this method gets the available space for a folder or file*/

    public void rewrite(String str) {


        FileWriter fw = null;

        try {
            fw = new FileWriter(path, false);
            fw.write(str);
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(new IOException("error because " + path + " the reason is : " + e.getMessage()));
        } finally {
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e2) {
                throw new RuntimeException(new IOException("error because " + path + " the reason is : " + e2.getMessage()));
            }
        }


    }


    /*this method gets the total space for a folder or file*/

    public void write(String str) {

        FileWriter fw = null;

        try {
            fw = new FileWriter(path, false);
            fw.write(getText() + str);
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(new IOException("error because " + path + " the reason is : " + e.getMessage()));
        } finally {
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e2) {
                throw new RuntimeException(new IOException("error because " + path + " the reason is : " + e2.getMessage()));
            }
        }


    }


    //this method gets size of a file or folder in kilobytes , of path from constructors that contain String 

    public long size() {
        if (new File(path).exists()) {


            if (new File(path).isFile()) {

                return new File(path).length();
            }
            if (new File(path).isDirectory()) {

                if (path.endsWith("/")) {
                    temp1 = path;
                } else {
                    temp1 = path + "/";
                }


                File[] list = new File(temp1).listFiles();

                for (File f : Objects.requireNonNull(list)) {
                    if (f.isFile()) {
                        n = n + f.length();
                    } else if (f.isDirectory()) {
                        path = f.getAbsolutePath();
                        size();
                    }
                }
                return n;


            }


        } else {

            throw new RuntimeException(new FileNotFoundException("this file or folder does not exist or the system of this device refused to give us permission to read it , are you sure you gave us the necessary permissions ?"));

        }

        return n;
    }

    //this method gets size of a file or folder in megabytes , of path from constructors that contain String 

    @SuppressLint("UsableSpace")
    public long getUsedSpace() {

        if (exists()) {
            if (isFolder()) {
                if (!path.endsWith("/")) {
                    path = path + "/";
                }
            }
        } else {
            throw new RuntimeException(new Exception("unable to find the file or folder to get usable space"));
        }


        return (new File(path).getTotalSpace()) - (new File(path).getUsableSpace());
    }

    //this method gets size of a file or folder in gigabytes , of path from constructors that contain String 

    @SuppressLint("UsableSpace")
    public long getAvaiableSpace() {

        if (exists()) {
            if (isFolder()) {
                if (!path.endsWith("/")) {
                    path = path + "/";
                }
            }
        } else {
            throw new RuntimeException(new Exception("unable to find the file or folder to get available space"));
        }


        return new File(path).getUsableSpace();

    }

    public long getTotalSpace() {

        if (exists()) {
            if (isFolder()) {
                if (!path.endsWith("/")) {
                    path = path + "/";
                }
            }
        } else {
            throw new RuntimeException(new Exception("unable to find the file or folder to get total space"));
        }


        return new File(path).getTotalSpace();


    }


    //this method gets size of a file or folder in kilobytes , of path from constructors that contain String 

    public long sizeInKB() {
        return size() / 1024;
    }

    //this method gets size of a file or folder in megabytes , of path from constructors that contain String 

    public long sizeInMB() {
        return sizeInKB() / 1024;
    }

    //this method gets size of a file or folder in gigabytes , of path from constructors that contain String 

    public long sizeInGB() {
        return sizeInMB() / 1024;
    }


    //this method gets the count of files into a folder , of path from constructors that contain String 

    public long sizeCustom(String type) {
        if (new File(path).exists()) {


            if (new File(path).isFile() && path.endsWith("." + type)) {

                return new File(path).length();
            }
            if (new File(path).isDirectory()) {

                if (path.endsWith("/")) {
                    temp1 = path;
                } else {
                    temp1 = path + "/";
                }


                File[] list = new File(temp1).listFiles();

                for (File f : Objects.requireNonNull(list)) {
                    if (f.isFile()) {
                        if (f.getAbsolutePath().endsWith("." + type)) {
                            n = n + f.length();
                        }
                    } else if (f.isDirectory()) {
                        path = f.getAbsolutePath();
                        sizeCustom(type);
                    }
                }
                return n;


            }


        } else {

            throw new RuntimeException(new FileNotFoundException("this file or folder does not exist or the system of this device refused to give us permission to read it , are you sure you gave us the necessary permissions ?"));

        }

        return n;
    }

    //this method gets the count of files into a folder and it's subfolders , of path from constructors that contain String 

    public long sizeInKBCustom(String type) {
        return sizeCustom(type) / 1024;
    }


    //this method gets the count of folders into a folder , of path from constructors that contain String 

    public long sizeInMBCustom(String type) {
        return sizeInKBCustom(type) / 1024;
    }

    //this method gets the count of folders into a folder and its subfolders , of path from constructors that contain String 

    public long sizeInGBCustom(String type) {
        return sizeInMBCustom(type) / 1024;
    }


    //this method gets the count of files and folders into a folder , of path from constructors that contain String 

    public int files() {
        return files_list(false, "").size();
    }

    //this method gets the count of files and folders into a folder and it's subfolders , of path from constructors that contain String 

    public int full_files() {
        return full_files_list(false, "").size();
    }


    //this method checks if the path is a file

    public int folders() {
        return folders_list(false).size();
    }

    //this method checks if the path is a folder

    public int full_folders() {
        return full_folders_list(false).size();
    }

    //this method checks if the path is readable

    public int content() {
        return content_list(false, "").size();
    }

    //this method checks if the path is writable

    public int full_content() {
        return full_content_list(false, "").size();
    }

    //this method checks if the path is executable

    public boolean isFile() {

        if (new File(path).exists()) {
            return new File(path).isFile();
        } else {
            return false;
        }


    }

    //this method checks if the path is empty

    public boolean isFolder() {
        if (new File(path).exists()) {
            return new File(path).isDirectory();
        } else {
            return false;
        }
    }

    //this method checks if the path is hidden

    public boolean isReadable() {
        if (new File(path).exists()) {
            return new File(path).canRead();
        } else {
            throw new RuntimeException(new FileNotFoundException("Unable to find the file or folder to check if it is readable"));
        }

    }

    //this method checks if the path is a system path

    public boolean isWriteable() {
        if (new File(path).exists()) {
            return new File(path).canWrite();
        } else {
            throw new RuntimeException(new FileNotFoundException("Unable to find the file or folder to check if it is writable"));
        }
    }

    //this method checks if the path is an internal device storage path
    //don't trust this , I am still working on a better way !

    public boolean isExecutable() {
        if (new File(path).exists()) {
            return new File(path).canExecute();
        } else {
            throw new RuntimeException(new FileNotFoundException("Unable to find the file or folder to check if it is executable"));
        }
    }

    //this method checks if the path is a sdcard removal storage (memory card) path

    public boolean isEmpty() {
        if (new File(path).exists()) {


            return size() == 0;


        } else {
            throw new RuntimeException(new FileNotFoundException("Unable to find the file or folder to check if it is empty"));
        }
    }

    //this method checks if the path is a connected USB storage

    public boolean isHidden() {
        if (new File(path).exists()) {


            return getName().startsWith(".");


        } else {
            throw new RuntimeException(new FileNotFoundException("Unable to find the file or folder to check if it is readable"));
        }
    }


    //this methods gets the name of file or folder of path

    public boolean isSystem() {
        try {
            return !new File(path).delete();
        } catch (Exception e) {
            return true;
        }
    }

    //this method gets the parent of file or folder of path

    public boolean isPrimary() {
        return new File(path).getAbsolutePath().contains(getPrimaryStorage());
    }

    //this method gets the folder that contains the folder or file of path

    public <T extends Activity> boolean isRemovalSDCard(T c) {
        context = c;
        return path.contains(getRemovalSDCardStorage());
    }


    //this method get the text of file of folder

    public boolean isRemovalUSB() {
        throw new RuntimeException(new Exception("I am still working on this , this method is not working Currently!"));
    }
    
    
    /*
    
    //this method get the group of file or folder of path
    
    public String getGroup() {
        
    }
    
    //this method gets the owner of file or folder of path
    
    public String getOwner() {
        
    }
    
    //this method gets the owner access info like readable or writable or executable of path
    
    public String getOwnerAccess() {
        
    }
    
    //this method gets the group access info like readable or writable or executable of path
    
    public String getGroupAccess() {
        
    }
    
    //this method gets the other access info like readable or writable or executable of path
    
    public String getOtherAccess() {
        
    }
    
    */


    //this methods gets the system storage path , no need to String constructors

    public String getName() {

        if (new File(path).exists()) {

            return Uri.parse(path).getLastPathSegment().replace("/", "");


        } else {

            throw new RuntimeException(new Exception("the file or folder does not exist."));

        }

    }


    //this method gets the internal device storage path , no need to String constructors

    public String getParent() {


        if (new File(path).exists()) {

            if (isFile()) {
                return path.replace(Uri.parse(path).getLastPathSegment(), "");
            }
            if (isFolder()) {

                if (path.endsWith("/")) {
                } else {
                    path = path + "/";
                }

                return path.replace(Uri.parse(path).getLastPathSegment() + "/", "");

            }


        } else {
            throw new RuntimeException(new Exception("the file or folder does not exist."));
        }

        throw new RuntimeException(new Exception("something went wrong when trying to get parent"));
    }


    //this method gets the removal sdcard storage (memory card) path , it needs Context constructor ArabWareFileManager

    public String getFolder() {
        if (new File(path).exists()) {
            path = getParent();
            return getName();
        } else {
            throw new RuntimeException(new Exception("the file or folder does not exist."));
        }
    }
    
    /*
    
    //this method gets the connected USB storage path , no need to String constructors 
    
    public String getRemovalUSBStorage() {
        
    }
    
    */


    //this method gets the file name Without the the file type like .mp3 etc....

    public String getText() {


        StringBuilder sb = new StringBuilder();
        FileReader fr = null;
        try {
            fr = new FileReader(path);

            char[] buff = new char[1024];
            int length;

            while ((length = fr.read(buff)) > 0) {
                sb.append(new String(buff, 0, length));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return sb.toString();
    }


    //this methods lists files in a folder of path

    public String getSystemStorage() {
        return "system/";
    }


    //this methods lists files in a folder and it's subfolders of path

    public String getPrimaryStorage() {


        if (!(Environment.getExternalStorageDirectory().getAbsolutePath().equals("") || Environment.getExternalStorageDirectory() == null)) {
            temp1 = Environment.getExternalStorageDirectory().getAbsolutePath();
            if (!temp1.endsWith("/")) {
                temp1 = temp1 + "/";
            }
            return temp1;
        }


        if (context == null) {
            throw new RuntimeException(new Exception("you didnot set a Context , you must set context to ArabWareFileManager constructors"));
        }


        /*here we will check the android version , because every one has a way , also if it crash or something goes wrong then we will use another way*/


        //Android 5 to 6 method , it should work , or the app will throw an error

        if (android5 || android6) {

            //here we get the storages of device , it can be internal and external (memory card and usb) , at most devices , the first removal storage is sdcard memory card but what is usb is inserted at first ? I don't know what would happen

            File[] externalCacheDirs = context.getExternalCacheDirs();
            if (externalCacheDirs == null) {
                throw new RuntimeException(new Exception("no access to storages , maybe permissions , I don't know why"));
            }


            // here we will check every item to know if it is memory card then we will take the path

            for (File file : externalCacheDirs) {

                //is it memory sdcard ? is it removal ?
                //this will check if it is readable or readable and writable at same time


                if (Environment.isExternalStorageEmulated(file)) {

                    // It's a removable storage

                    temp1 = (file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf("Android/")));

                    if (temp1.endsWith("/")) {
                        return temp1;
                    } else {
                        return temp1 + "/";
                    }


                } else {
                    throw new RuntimeException(new Exception("an error happened , no access to internal storage for unknown reason"));


                }
            }
        }


        //if Android 7 and up , if this fail then we will use the android 6 and android 5 method


        if (android7 || android8 || android9 || android10 || android11 | android12 | android13) {
            try {
                StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
                String[] volumes = (String[]) storageManager.getClass().getMethod("getVolumePaths").invoke(storageManager, new Object[0]);
                List<StorageVolume> storageVolumeList = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    storageVolumeList = storageManager.getStorageVolumes();
                }
                for (int i = 0; i < Objects.requireNonNull(volumes).length; i++) {
                    String v = volumes[i];
                    StorageVolume svl = storageVolumeList.get(i);
                    if (svl != null) {
                        if (new File(v).exists()) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                if (svl.isPrimary()) {
                                    temp1 = v;
                                    if (temp1.endsWith("/")) {
                                        return temp1;
                                    } else {
                                        return temp1 + "/";
                                    }
                                }
                            }


                        }
                    }
                }
            } catch (Exception e) {


                File[] externalCacheDirs = context.getExternalCacheDirs();

                if (externalCacheDirs == null) {
                    throw new RuntimeException(new Exception("an error occurred , your device refused all ways to access internal storage , are you sure your device gave permissions"));


                }


                // here we will check every item to know if it is memory card then we will take the path

                for (File file : externalCacheDirs) {

                    //is it memory sdcard ? is it removal ?
                    //this will check if it is readable or readable and writable at same time


                    if (Environment.isExternalStorageEmulated(file)) {

                        // It's a removable storage

                        temp1 = (file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf("Android/")));

                        if (temp1.endsWith("/")) {
                            return temp1;
                        } else {
                            return temp1 + "/";
                        }

                    } else {
                        throw new RuntimeException(new Exception("an error happened , no access to internal storage for unknown reason"));


                    }
                }


            }
        }

        throw new RuntimeException(new Exception("all ways failed because of permissions or something else happened , be sure everything is fine"));


    }


    //this methods lists folders in a folder of path

    public String getRemovalSDCardStorage() {

        if (context == null) {
            throw new RuntimeException(new Exception("you didnot set a Context , you must set context to ArabWareFileManager constructors"));
        }


        /*here we will check the android version , because every one has a way , also if it crash or something goes wrong then we will use another way*/
        try {
            String state = Environment.getExternalStorageState();


            if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
                /*do something*/
            } else {
                throw new RuntimeException(new Exception("no memory card sdcard found"));


            }


        } catch (Exception e) {
            throw new RuntimeException(new Exception("device refused to give the app access to check if sdcard removal memory card is available or not"));


        }


        //Android 5 to 6 method , it should work , or the app will throw an error

        if (android5 || android6) {

            //here we get the storages of device , it can be internal and external (memory card and usb) , at most devices , the first removal storage is sdcard memory card but what is usb is inserted at first ? I don't know what would happen

            File[] externalCacheDirs = context.getExternalCacheDirs();
            if (externalCacheDirs == null) {
                throw new RuntimeException(new Exception("no access to storages , maybe permissions , I don't know why"));
            }


            // here we will check every item to know if it is memory card then we will take the path

            for (File file : externalCacheDirs) {

                //is it memory sdcard ? is it removal ?
                //this will check if it is readable or readable and writable at same time


                if (Environment.isExternalStorageRemovable(file) || Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED) || Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

                    // It's a removable storage

                    temp1 = (file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf("Android/")));

                    if (temp1.endsWith("/")) {
                        return temp1;
                    } else {
                        return temp1 + "/";
                    }


                } else {
                    throw new RuntimeException(new Exception("an error happened , no access to memory card for unknown reason"));


                }
            }
        }


        //if Android 7 and up , if this fail then we will use the android 6 and android 5 method


        if (android7 || android8 || android9 || android10 || android11 | android12 | android13) {
            try {
                StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
                String[] volumes = (String[]) storageManager.getClass().getMethod("getVolumePaths").invoke(storageManager, new Object[0]);
                List<StorageVolume> storageVolumeList = null;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    storageVolumeList = storageManager.getStorageVolumes();
                }
                for (int i = 0; i < Objects.requireNonNull(volumes).length; i++) {
                    String v = volumes[i];
                    StorageVolume svl = storageVolumeList.get(i);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        if (Environment.MEDIA_MOUNTED.equals(svl.getState()) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(svl.getState())) {
                            if (new File(v).exists()) {
                                if (svl.isRemovable()) {
                                    temp1 = v;
                                    if (temp1.endsWith("/")) {
                                        return temp1;
                                    } else {
                                        return temp1 + "/";
                                    }
                                }


                            }
                        }
                    }
                }
            } catch (Exception e) {


                File[] externalCacheDirs = context.getExternalCacheDirs();

                if (externalCacheDirs == null) {
                    throw new RuntimeException(new Exception("an error occurred , your device refused all ways to access sdcard memory card , are you sure your device has inserted one or are sure you gave permissions"));


                }


                // here we will check every item to know if it is memory card then we will take the path

                for (File file : externalCacheDirs) {

                    //is it memory sdcard ? is it removal ?
                    //this will check if it is readable or readable and writable at same time


                    if (Environment.isExternalStorageRemovable(file) || Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED) || Environment.getExternalStorageState(file).equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

                        // It's a removable storage

                        temp1 = (file.getAbsolutePath().substring(0, file.getAbsolutePath().indexOf("Android/")));

                        if (temp1.endsWith("/")) {
                            return temp1;
                        } else {
                            return temp1 + "/";
                        }

                    } else {
                        throw new RuntimeException(new Exception("an error happened , no access to memory card for unknown reason"));


                    }
                }


            }
        }

        throw new RuntimeException(new Exception("your device doesn't support memory card or something else happened , be sure everything is fine like inserting one before testing"));


    }

    //this methods lists folders in a folder and it's subfolders of path

    public String getNameOnlyOfFile() {

        if (!getName().contains(".")) {
            return getName();
        }

        return getName().substring(0, getName().lastIndexOf("."));
    }


    //this methods lists files and folders in a folder of path

    public ArrayList<String> files_list(boolean hidden, String type) {


        if (isFile()) {
            throw new RuntimeException(new Exception("you can not list files of a file"));
        }

        if (isFolder()) {
            if (path.endsWith("/")) {
            } else {
                path = path + "/";
            }
        }


        File[] list = new File(path).listFiles();

        for (File f : Objects.requireNonNull(list)) {

            if (f.isFile()) {

                if (hidden) {

                    if (type.equals("") || type == null) {


                        if (getName(f.getAbsolutePath()).startsWith(".")) {
                            data.add(f.getAbsolutePath());
                        }


                    } else {

                        if (f.getAbsolutePath().endsWith("." + type)) {
                            if (getName(f.getAbsolutePath()).startsWith(".")) {
                                data.add(f.getAbsolutePath());
                            }
                        }

                    }

                } else {

                    if (type == null || type.equals("")) {
                        data.add(f.getAbsolutePath());
                    } else {

                        if (f.getAbsolutePath().endsWith("." + type)) {
                            data.add(f.getAbsolutePath());
                        }


                    }


                }

            } else if (f.isDirectory() && f.canRead() && !f.getAbsolutePath().contains("/Android")) {

                //do nothing , it is not full list !

            }
        }

        return data;


    }

    //this methods lists files and files in a folder and it's subfolders of path

    public ArrayList<String> full_files_list(boolean hidden, String type) {


        if (isFile()) {
            throw new RuntimeException(new Exception("you can not list files of a file"));
        }

        if (isFolder()) {
            if (path.endsWith("/")) {
            } else {
                path = path + "/";
            }

        }


        File[] list = new File(path).listFiles();

        for (File f : Objects.requireNonNull(list)) {

            if (f.isFile()) {

                if (hidden) {

                    if (type.equals("") || type == null) {


                        if (getName(f.getAbsolutePath()).startsWith(".")) {
                            data.add(f.getAbsolutePath());
                        }


                    } else {

                        if (f.getAbsolutePath().endsWith("." + type)) {
                            if (getName(f.getAbsolutePath()).startsWith(".")) {
                                data.add(f.getAbsolutePath());
                            }
                        }

                    }

                } else {

                    if (type == null || type.equals("")) {
                        data.add(f.getAbsolutePath());
                    } else {

                        if (f.getAbsolutePath().endsWith("." + type)) {
                            data.add(f.getAbsolutePath());
                        }


                    }


                }

            } else if (f.isDirectory() && f.canRead() && !f.getAbsolutePath().contains("/Android")) {
                path = f.getAbsolutePath();
                full_files_list(hidden, type);

            }
        }

        return data;

    }

    public ArrayList<String> folders_list(boolean hidden) {


        if (isFile()) {
            throw new RuntimeException(new Exception("you can not list files of a file"));
        }

        if (isFolder()) {
            if (path.endsWith("/")) {
            } else {
                path = path + "/";
            }
        }

        if (new File(path).getAbsolutePath().contains("/Android")) {
            return new ArrayList<>();
        }


        File[] list = new File(path).listFiles();

        for (File f : Objects.requireNonNull(list)) {

            if (f.isDirectory() && f.canRead() && !file.getAbsolutePath().contains("/Android")) {

                if (hidden) {


                    if (getName(f.getAbsolutePath()).startsWith(".")) {
                        data.add(f.getAbsolutePath());
                    }


                } else {

                    data.add(f.getAbsolutePath());


                }


            }
        }

        return data;


    }


    //android 11 things


    //this method uses the ArabWareFileManager constructor that needs nothing

    public ArrayList<String> full_folders_list(boolean hidden) {


        if (isFile()) {
            throw new RuntimeException(new Exception("you can not list files of a file"));
        }

        if (isFolder()) {
            if (path.endsWith("/")) {
            } else {
                path = path + "/";
            }
        }


        File[] list = new File(path).listFiles();

        for (File f : Objects.requireNonNull(list)) {

            if (f.isDirectory() && f.canRead() && !f.getAbsolutePath().contains("/Android")) {

                if (hidden) {


                    if (getName(f.getAbsolutePath()).startsWith(".")) {
                        data.add(f.getAbsolutePath());
                    }


                } else {

                    data.add(f.getAbsolutePath());


                }

                path = f.getAbsolutePath();
                full_folders_list(hidden);

            }
        }

        return data;


    }

    //this method checks if the device is Android 11 or up , it does not need anything since it is static

    public ArrayList<String> content_list(boolean hidden, String type) {

        ArrayList<String> temporary = new ArrayList<>();
        temporary.addAll(files_list(hidden, type));
        temporary.addAll(folders_list(hidden));
        return temporary;

    }

    //to check if Android 6 or up

    public ArrayList<String> full_content_list(boolean hidden, String type) {

        ArrayList<String> temporary = new ArrayList<>();
        temporary.addAll(full_files_list(hidden, type));
        temporary.addAll(full_folders_list(hidden));
        return temporary;

    }

    //this method checks if the device is Android 11 and the user granted the manage all files permission .

    public boolean exists() {
        return new File(path).exists();
    }
    
    
    /*
    //this method is used to request the user to give your app access to a folder on android 11
    
    public static <T extends Activity> void requestAccessToFolder(T c , String folder) {
        
        if(isAndroid11()) {
            
            //I will code this later
            
            
            
        } else {
            
            
            
            //nothing , lol , why you want to allow the access to a folder on less than android 11
            
            
            
        }
        
        
    }
    */
    
    /*
    
    
    //this method can return the real file path of an Uri
    //we here try many ways ... if all do nothing then it will throw RuntimeException, sorry
    
    public static <T extends Activity> String getRealFilePathFromUri(Uri u , T c) {
        
        
        //I will code this later ....
        
        
        
        throw new RuntimeException(new Exception("an error occurred, that is not real Uri or storage access not given or something we don't know about"));
        
        
        
                
    }
    
    
    */

    private boolean isFolder(String path) {

        file = new File(path);
        if (file.exists()) {
            return file.isDirectory();
        } else {
            return false;
        }
    }

    private boolean isFile(String path) {
        file = new File(path);
        if (file.exists()) {
            return file.isFile();
        } else {
            return false;
        }

    }


    private String getName(String path) {
        return Uri.parse(path).getLastPathSegment();
    }

    private String getParent(String path) {


        if (isFile(path)) {

            return path.replace(Uri.parse(path).getLastPathSegment(), "");

        } else {

            if (path.endsWith("/")) {

                return path.replace("/" + Uri.parse(path).getLastPathSegment(), "");

            } else {
                return path.replace(Uri.parse(path).getLastPathSegment(), "");

            }


        }

    }

}
