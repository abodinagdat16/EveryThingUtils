/* ArabWare 2022 Copyright.The Open Source Project ApkUtils java class
this class created to help android developers create an apps manager or
apps info app with all information and functions

you can modify or use or share this class in your projects
but you can not modify the copy right


the class is so explained , every code line there is something
can explain why I made this .....


Happy coding everyone ... I hope this class was helpful!

*/


//package arabware.file;


package com.android.prime.arab.ware.everythingutils;

//imports of classes

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

//definition of the whole class

/*this just for ignoring warnings since this class is using if then else codes to work on all android versions.*/
public class ApkUtils {


    //fields

    //two important fields

    /*context of your app*/
    public final Context cntx;
    /*drawable of the app icon from file*/
    public Drawable drawable;
    /*name of the app from file or package*/
    public String name = "null";
    /*version name of the app from file or package*/
    public String versionName = "null";
    /*version code of the app from file or package*/
    public int versionCode = 0;
    /*package of the app from file or package*/
    public String pkg = "null";
    /*path of the app from file or package*/
    public String path = "null";
    /*target sdk version of the app fron file*/
    public int targetSdkVersion;
    /*min sdk version of the app from file or package*/
    public int minSdkVersion;
    /*data dir of the app from file or package*/
    public String dataDir = "null";
    /*ManageSpaceActivityName of the app from file or package , it can be null
    if the developer of the app didnot add it in the manifest! OK?*/
    public String manageSpaceActivityName = "null";
    /*Application Class Name of the app from file or package , it can be null
    if the developer didnot add it in the manifest! OK?*/
    public String className = "null";
    /*uid of the app from file or package*/
    public int uid;
    /*source dir of the app from file or package*/
    public String sourceDir = "null";
    /*public source dir of the app from file or package*/
    public String publicSourceDir = "null";
    /*spilt names of the app from file or package if found*/
    public ArrayList<String> names = new ArrayList<>();
    /*spilt source dirs of the app from file or package if found*/
    public ArrayList<String> sources = new ArrayList<>();
    /*spilt public source dirs of the app from file or package if found*/
    public ArrayList<String> publicSources = new ArrayList<>();
    /*name of installed app of same package of app from file*/
    public String installedName = "null";
    /*version name of installed app of same package of app from file*/
    public String installedVerName = "null";
    /*version code of installed app of same package of app from file*/
    public int installedVerCode;
    /*min sdk version of installed app of same package of app from file*/
    public int installedMinSdk;
    /*target sdk version of installed app of same package of app from file*/
    public int installedTargetSdk;
    /*checks if the apk file is installed and with SAME SIGNATURE*/
    public boolean isInstalledWithSameSignature = false;
    /*sha1 of the app from file or package*/
    public String SHA1 = "null";
    /*sha256 of the app from file or package*/
    public String SHA256 = "null";
    /*permissions of the app from file or package*/
    public ArrayList<String> permissions = new ArrayList<>();
    /*activities of the app from file or package*/
    public ArrayList<String> activities = new ArrayList<>();
    /*services of the app from file or package*/
    public ArrayList<String> services = new ArrayList<>();
    /*receivers of the app from file or package*/
    public ArrayList<String> receivers = new ArrayList<>();
    /*providers of the app from file or package*/
    public ArrayList<String> providers = new ArrayList<>();
    private ApplicationInfo ai;
    private PackageInfo pckgInfo;
    /*just for signing info*/
    private PackageInfo packageInfo;
    private Signature[] signatures;
    /*if the developer (YOU) wanted to get info of an app from package and not file path*/
    private boolean fromPackage;
    private boolean temp = false;


    //constructors


    public ApkUtils(Context c) {


        cntx = c;

    }


    //methods (functions)
	
    
    /*
    you have to YES YOU HAVE TO , add the MANAGE_EXTERNAL_STORAGE permission
    in order to make your app run this method on android 11 and later .... 
    */

    @SuppressLint("QueryPermissionsNeeded")
    public static ArrayList<String> getInstalledUserAppPackages(Context c) {

        List<PackageInfo> listn;

        ArrayList<String> tempList = new ArrayList<>();

        try {

            /*Android 13 and up*/

            if (Build.VERSION.SDK_INT >= 33) {

                listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);


                /*Android 12 and below*/

            } else {

                listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);

            }

            for (PackageInfo packageInfo : listn) {

                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    tempList.add(packageInfo.packageName);
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(new Exception("system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
        }

        return tempList;


    }
    
    /*
    you have to YES YOU HAVE TO , add the QUERY_ALL_PACKAGES permission
    in order to make your app run this method on android 11 and later
    */

    @SuppressLint("QueryPermissionsNeeded")
    public static ArrayList<String> getInstalledSystemAppPackages(Context c) {


        List<PackageInfo> listn;

        ArrayList<String> tempList = new ArrayList<>();

        try {

            /*Android 13 and up*/

            if (Build.VERSION.SDK_INT >= 33) {

                listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);


                /*Android 12 and below*/

            } else {

                listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);

            }

            for (PackageInfo packageInfo : listn) {

                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

                } else {
                    tempList.add(packageInfo.packageName);
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(new Exception("system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
        }

        return tempList;

    }


    /* with this you can get an icon drawable value . and put it in ImageView (for example)*/

    @SuppressLint("QueryPermissionsNeeded")
    public static ArrayList<String> getInstalledAppPackages(Context c) {


        List<PackageInfo> listn;

        ArrayList<String> tempList = new ArrayList<>();

        try {

            /*Android 13 and up*/

            if (Build.VERSION.SDK_INT >= 33) {

                listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);


                /*Android 12 and below*/

            } else {

                listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);

            }

            for (PackageInfo packageInfo : listn) {

                tempList.add(packageInfo.packageName);


            }


        } catch (Exception e) {
            throw new RuntimeException(new Exception("system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
        }

        return tempList;

    }

    /*name of the app from file or package*/

    public static ArrayList<String> getApkPaths(String where) {

        return new ArabWareFileManager(where).full_files_list(false, "apk");

    }

    /*version name of the app from file or package*/

    public void setApkPath(String p) {
        path = p;
        fromPackage = false;
        doSomething();
    }

    /*version code of the app from file or package*/

    public void setPackageName(String pkgName) {
        pkg = pkgName;
        fromPackage = true;
        doSomething();
    }

    /*package of the app from file or package*/

    public Drawable getIcon() {
        return drawable;
    }

    /*target sdk version of the app from file or package*/

    public String getName() {
        return name;
    }

    /*min sdk version of the app from file or package*/

    public String getVersionName() {
        return versionName;
    }

    /*manage space activity name if found of the app from file or package*/

    public String getVersionCode() {
        return String.valueOf(versionCode);
    }

    /*Application class name if found of the app from file or package*/

    public String getPackage() {
        return pkg;
    }

    /*uid of the app from file or package*/

    public String getTargetSdkVersion() {
        return String.valueOf(targetSdkVersion);
    }

    /* data dir of the app from file or package , if installed */

    public String getMinSdkVersion() {
        return String.valueOf(minSdkVersion);
    }

    /* source dir of the app from file or package if installed */

    public String getManageSpaceActivityName() {
        return Objects.requireNonNullElse(manageSpaceActivityName, "");
    }

    /*public source dir of the app from file or package if installed*/

    public String getClassName() {
        if (className == null) {
            return "";
        }
        return className;
    }

    /* name of installed app that has same package name of app from file*/

    public String getUID() {
        return String.valueOf(uid);
    }

    /* version name of installed app that has same package name of app from file*/

    public String getDataDir() {
        return dataDir;
    }

    /* version code of installed app that has same package name of app from file*/

    public String getSourceDir() {
        if (sourceDir == null) {
            return "";
        }
        return sourceDir;
    }

    /* target sdk version of installed app that has same package name of app from file*/

    public String getPublicSourceDir() {
        if (publicSourceDir == null) {
            return "";
        }
        return publicSourceDir;
    }

    /* min sdk version of installed app that has same package name of app from file*/

    public String getInstalledName() {
        if (installedName == null) {
            return "";
        }
        return installedName;
    }


    /* names list of split files of the app if found and installed*/

    public String getInstalledVerName() {
        if (installedVerName == null) {
            return "";
        }
        return installedVerName;
    }

    /* source dirs list of split files of the app if found and installed*/

    public String getInstalledVerCode() {
        return String.valueOf(installedVerCode);
    }

    /* public source dirs list of split files of the app if found and installed*/

    public String getInstalledTargetSdk() {
        return String.valueOf(installedTargetSdk);
    }
    
    /* if the user installed the apk file and it is same SIGNATURE ,
    then it will return TRUE !*/

    public String getInstalledMinSdk() {
        return String.valueOf(installedMinSdk);
    }

    /*you can get the sha1 of the app from file or package*/

    public ArrayList<String> getSplitNamesIfFound() {
        if (names == null) {
            names = new ArrayList<>();
            return names;
        }
        return names;
    }

    /*you can get the sha256 of the app from file or package*/

    public ArrayList<String> getSplitSourcesIfFound() {
        if (sources == null) {
            sources = new ArrayList<>();
            return sources;
        }
        return sources;
    }

    /*you can get the permissions list of the app from file or package*/

    public ArrayList<String> getSplitPublicSourcesIfFound() {
        if (publicSources == null) {
            publicSources = new ArrayList<>();
            return publicSources;
        }
        return publicSources;
    }

    /*you can get the activities list of the app from file or package*/

    public boolean installedWithSameSign() {
        return isInstalledWithSameSignature;
    }

    /*you can get the services list of the app from file or package*/

    public String getSHA1() {
        if (SHA1 == null) {
            return "";
        }
        return SHA1;
    }

    /*you can get the receivers list of the app from file or package*/

    public String getSHA256() {
        if (SHA256 == null) {
            return "";
        }
        return SHA256;
    }

    /*you can get the providers list of the app from file or package*/

    public ArrayList<String> getPermissions() {
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        return permissions;
    }

    /*to get information about the time of first install and last update*/

    public ArrayList<String> getActivities() {
        if (activities == null) {
            activities = new ArrayList<>();
        }
        return activities;
    }

    public ArrayList<String> getServices() {
        if (services == null) {
            services = new ArrayList<>();
        }
        return services;
    }

    public ArrayList<String> getReceivers() {
        if (receivers == null) {
            receivers = new ArrayList<>();
        }
        return receivers;
    }

    public ArrayList<String> getProviders() {
        if (providers == null) {
            providers = new ArrayList<>();
        }
        return providers;
    }

    /*this is the MAIN METHOD OF THIS CLASS , 90% OF DATA ARE BEING GOT HERE*/

    @SuppressLint("SimpleDateFormat")
    public String getInstallTime(String format) {
        return new SimpleDateFormat(format).format(new Date(pckgInfo.firstInstallTime));
    }


    //don't care about these, they are available at ArabWareFileManager class but I took them for a reason

    @SuppressLint("SimpleDateFormat")
    public String getUpdateTime(String format) {
        return new SimpleDateFormat(format).format(new Date(pckgInfo.lastUpdateTime));
    }

    public long firstInstallTime() {
        return pckgInfo.firstInstallTime;
    }

    public long lastUpdateTime() {
        return pckgInfo.lastUpdateTime;
    }

    //credits of signatures methods (StackOverFlow)

    private void doSomething() {

        init(0);

        try {

            if (!fromPackage) {

                ai.sourceDir = path;

            }

        } catch (Exception ignored) {

        }

        try {

            if (!fromPackage) {

                ai.publicSourceDir = path;

            }

        } catch (Exception ignored) {

        }

        try {

            drawable = ai.loadIcon(cntx.getPackageManager());

        } catch (Exception ignored) {

        }

        try {

            name = "" + ai.loadLabel(cntx.getPackageManager());

        } catch (Exception ignored) {

        }

        try {

            versionName = pckgInfo.versionName;

        } catch (Exception ignored) {

        }

        try {

            if (Build.VERSION.SDK_INT >= 28) {

                versionCode = (int) pckgInfo.getLongVersionCode();

            } else {

                versionCode = pckgInfo.versionCode;

            }


        } catch (Exception e) {

            versionCode = pckgInfo.versionCode;

        }

        try {


            pkg = pckgInfo.packageName;

        } catch (Exception e) {
            throw new RuntimeException(new Exception(e));
        }

        try {

            targetSdkVersion = ai.targetSdkVersion;

        } catch (Exception ignored) {

        }

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                minSdkVersion = ai.minSdkVersion;
            }

        } catch (Exception ignored) {

        }

        try {

            dataDir = ai.dataDir;

        } catch (Exception ignored) {

        }

        try {

            manageSpaceActivityName = ai.manageSpaceActivityName;

        } catch (Exception ignored) {

        }

        try {

            className = ai.className;

        } catch (Exception ignored) {

        }

        try {

            uid = ai.uid;

        } catch (Exception ignored) {

        }

        /*here we are checking if the app is installed or no and get some info*/

        if (!fromPackage) {
            fromPackage = true;
            temp = true;
        }

        try {

            init(0);

        } catch (Exception ignored) {


        }


        try {

            publicSourceDir = ai.publicSourceDir;


        } catch (Exception ignored) {

        }

        try {

            sourceDir = ai.sourceDir;

        } catch (Exception ignored) {

        }

        try {

            installedVerName = pckgInfo.versionName;

        } catch (Exception ignored) {

        }

        try {

            if (Build.VERSION.SDK_INT >= 28) {

                versionCode = (int) pckgInfo.getLongVersionCode();

            } else {

                versionCode = pckgInfo.versionCode;

            }


        } catch (Exception e) {
            versionCode = pckgInfo.versionCode;
        }

        try {

            installedTargetSdk = ai.targetSdkVersion;

        } catch (Exception ignored) {

        }

        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                installedMinSdk = ai.minSdkVersion;
            }

        } catch (Exception ignored) {


        }

        try {

            installedName = "" + ai.loadLabel(cntx.getPackageManager());

        } catch (Exception ignored) {

        }

        try {

            names = new ArrayList<>();

            String[] list = new String[0];
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                list = ai.splitNames;
            }


            names.addAll(Arrays.asList(list));

        } catch (Exception ignored) {

        }

        try {

            sources = new ArrayList<>();

            String[] list2 = ai.splitSourceDirs;

            sources.addAll(Arrays.asList(list2));

        } catch (Exception ignored) {

        }

        try {

            publicSources = new ArrayList<>();


            String[] list3 = ai.splitPublicSourceDirs;

            publicSources.addAll(Arrays.asList(list3));

        } catch (Exception ignored) {

        }

/*
these four lines of code (if statement) , are very important
because we did some checking for case the app is from file and
we wanted to check if it installed also , so after we finished
we must set the value back as it was
*/

        if (temp) {
            temp = false;
            fromPackage = false;
        }


        try {
            if (fromPackage) {
                SHA1 = getSignture("SHA1", pkg);
                SHA256 = getSignture("SHA256", pkg);
            } else {
                SHA1 = getSignture("SHA1");
                SHA256 = getSignture("SHA256");
            }


        } catch (Exception ignored) {

        }

        try {


            if (fromPackage) {

                isInstalledWithSameSignature = true;

            } else {

                String s = getSignture("SHA1");
                String s2 = getSignture("SHA1", pkg);
                String s3 = getSignture("SHA256");
                String s4 = getSignture("SHA256", pkg);

                isInstalledWithSameSignature = (s.equals(s2) || s3.equals(s4));

            }


        } catch (Exception ignored) {

        }


        try {

            init(PackageManager.GET_PERMISSIONS);


            permissions = new ArrayList<>();

            if (pckgInfo.requestedPermissions != null) {


                permissions.addAll(Arrays.asList(pckgInfo.requestedPermissions));


            }


        } catch (Exception ignored) {


        }


        try {

            init(PackageManager.GET_ACTIVITIES);

            activities = new ArrayList<>();

            if (pckgInfo.activities != null) {


                for (ActivityInfo a : pckgInfo.activities) {

                    activities.add(a.name);


                }


            }


        } catch (Exception ignored) {


        }


        try {

            init(PackageManager.GET_SERVICES);

            services = new ArrayList<>();

            if (pckgInfo.services != null) {


                for (ServiceInfo a : pckgInfo.services) {

                    services.add(a.name);


                }


            }


        } catch (Exception ignored) {


        }


        try {

            init(PackageManager.GET_RECEIVERS);

            receivers = new ArrayList<>();

            if (pckgInfo.receivers != null) {


                for (ActivityInfo a : pckgInfo.receivers) {

                    receivers.add(a.name);


                }


            }


        } catch (Exception ignored) {


        }


        try {

            init(PackageManager.GET_PROVIDERS);

            providers = new ArrayList<>();

            if (pckgInfo.providers != null) {


                for (ProviderInfo a : pckgInfo.providers) {

                    providers.add(a.name);


                }


            }


        } catch (Exception ignored) {


        }

        /*here , we are just doing this to make the first install time and last update time functions work*/

        init(0);


    }

    private boolean isAndroid11() {
        return (Build.VERSION.SDK_INT == 30 || Build.VERSION.SDK_INT > 30);
    }

    private boolean isAndroid6() {
        return (Build.VERSION.SDK_INT == 23 || Build.VERSION.SDK_INT > 23);
    }

    private boolean isFullAccessFiles(Context c) {
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

        return true;
    }


    //Legendary streamer class calling , all credits go to his help to me.

    public String getSignture(String type) {

        try {

            if (Build.VERSION.SDK_INT < 28) {

                packageInfo = cntx.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_SIGNATURES);

            } else {

                if (Build.VERSION.SDK_INT >= 33) {


                    packageInfo = cntx.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_SIGNING_CERTIFICATES);

                } else {


                    packageInfo = cntx.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_SIGNING_CERTIFICATES);

                }

            }

            try {

                if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        signatures = packageInfo.signingInfo.getApkContentsSigners();
                    }

                } else {

                    signatures = packageInfo.signatures;

                }

            } catch (Exception e) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    signatures = packageInfo.signingInfo.getApkContentsSigners();
                }

            }


            for (Signature signature : signatures) {

                // check is matches hardcoded value
                return getSHAOfType_(signature.toByteArray(), type);
            }
        } catch (Exception e) {
            throw new RuntimeException(new Exception(e));
        }

        return "";
    }

    @SuppressLint("PackageManagerGetSignatures")
    public String getSignture(String type, String pkgName) {

        try {

            if (Build.VERSION.SDK_INT < 28) {

                packageInfo = cntx.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);

            } else {

                if (Build.VERSION.SDK_INT >= 33) {


                    packageInfo = cntx.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNING_CERTIFICATES);

                } else {


                    packageInfo = cntx.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNING_CERTIFICATES);

                }

            }

            try {

                if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                        signatures = packageInfo.signingInfo.getApkContentsSigners();
                    }

                } else {

                    signatures = packageInfo.signatures;

                }

            } catch (Exception e) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    signatures = packageInfo.signingInfo.getApkContentsSigners();
                }

            }


            for (Signature signature : signatures) {

                // check is matches hardcoded value
                return getSHAOfType_(signature.toByteArray(), type);
            }
        } catch (Exception e) {
            throw new RuntimeException(new Exception(e));
        }

        return "";
    }


    /*get both of user & system apps list*/

    //computed the sha1 hash of the signature
    public String getSHAOfType_(byte[] sig, String type) {
        try {
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance(type);
            digest.update(sig);
            byte[] hashtext = digest.digest();
            return bytes_To_Hex_(hashtext);
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException(new java.security.NoSuchAlgorithmException(e));
        }

    }



    /*you can list the apk files using this , where is the full path of folder String value*/

    //util method to convert byte array to hex string
    public String bytes_To_Hex_(byte[] bytes) {
        final char[] hexArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    
    /*
    just to create data from package or file path.
    mode is the data that should be collected like ,
    0 to all modes (maybe)
    PackageManager.GET_ACTIVITIES , for activities list
    PackageManager.GET_RECEIVERS , for receivers list
    PackageManager.GET_PROVIDERS , for providers list
    PackageManager.GET_SERVICESB , for services lost
    PackageManager.GET_PERMISSIONS , for permissions list
    PackageManager.GET_MATCH_ALL , maybe for all data at one
    */

    private void init(int mode) {


        try {


            if (fromPackage) {


                if (Build.VERSION.SDK_INT < 28) {

                    pckgInfo = cntx.getPackageManager().getPackageInfo(pkg, mode);

                } else {

                    if (Build.VERSION.SDK_INT >= 33) {


                        pckgInfo = cntx.getPackageManager().getPackageInfo(pkg, mode);

                    } else {


                        pckgInfo = cntx.getPackageManager().getPackageInfo(pkg, mode);

                    }

                }


            } else {


                if (Build.VERSION.SDK_INT < 28) {

                    pckgInfo = cntx.getPackageManager().getPackageArchiveInfo(path, mode);

                } else {

                    if (Build.VERSION.SDK_INT >= 33) {


                        pckgInfo = cntx.getPackageManager().getPackageArchiveInfo(path, mode);

                    } else {


                        pckgInfo = cntx.getPackageManager().getPackageArchiveInfo(path, mode);

                    }

                }


            }

            ai = pckgInfo.applicationInfo;

        } catch (Exception e) {

            throw new RuntimeException(e);

        }

    }

}
