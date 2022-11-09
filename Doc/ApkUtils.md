## Full Guide To ApkUtils Class
### ApkUtils is a class that can get info about apps (file path or package name) without any hard codes + it works for most (all) android versions from 5 to 13
#### c is the context like MainActivity.this or Fragment.this.getActivity(); , path is the string path of apk file , pkg is the package name of the app , apk is the name of ApkUtils definition here

## Add This Permission in Order to Get all info about an app by package on android 11 and later .
## android.permission.QUERY_ALL_PACKAGES
## ONLY IF YOU GET LIST OF APPS OR DANGEROUS INFO THEN YOU NEED IT , SOME WORK WITHOUT IT

> first you have to create the ApkUtils then initialize it with a valid Context , I suggest an activity but it's OK to use another things

``` java

ApkUtils apk = new ApkUtils(MainActivity.this); //MainActivity.this, MainActivity is the activity name , if it is a fragment then MainFragment.this.getActivity()
```


> how to create from file


``` java

apk.setApkPath(path);

```

> how to create from input stream (if you don't want the MANAGE_EXTERNAL_STORAGE permission in order to work on android 11)

``` java

try {

apk.setInputStreamAsApk(YourInputStream);

} catch(Exception e) {
//error
}

```

> how to create from package name


``` java
apk.setPackageName(pkg);
```


> how to get app name


``` java

YourString = apk.getName();

```

> how to get app package name


``` java

YourString = apk.getPackage();

```

> how to get the version name of the app


``` java

YourString = apk.getVersionName();
```

> how to get the version code of the app


``` java

YourString = apk.getVersionCode();

```

> how to get icon of the app


``` java

Drawable drawable = apk.getIcon();
imageview1.setImageDrawable(drawable);



//or just use it directly

imageview1.setImageDrawable(apk.getIcon());

```

> how to get min sdk version of the app


``` java

YourString = apk.getMinSdkVersion();

```

> how to get target sdk version of the app


``` java

YourString = apk.getTargetSdkVersion();

```

> how to get class name (the main application class that extends android.app.Application or its copies)


``` java

YourString = apk.getClassName();

```


> how to get manage space activity of the app which user must open to delete the storage of app (he/she can't do it in settings!)


``` java

YourString = apk.getManageSpaceActivityName();

```

> how to get the UID of the app


``` java

YourString = apk.getUID();

```

> how to get the data directory of the app


``` java

YourString = apk.getDataDir();

```

> how to get the source directory of the app


``` java

YourString = apk.getSourceDir();

```

> how to get the public source directory (mostly it is the path of apk file which can be read and copied )


``` java

YourString = apk.getPublicSourceDir();

```

> if you loaded the app from file and you want to check if it is installed or a copy of it installed then these methods will help


``` java

//to get the installed name

YourString = apk.getInstalledName();

//to get the installed version name

YourString = apk.getInstalledVerName();

//to get the installed version code

YourString = apk.getInstalledVerCode();

//to get the installed min sdk

YourString = apk.getInstalledMinSdk();

//to get the installed target sdk

YourString = apk.getInstalledTargetSdk();

```

> to get split names list of an installed app if found


``` java

YourArrayListString = apk.getSplitNamesIfFound();

```

> to get split sources directories list of installed app if found


``` java

YourArrayListString = apk.getSplitSourcesIfFound();

```

> to get split public sources directories list of installed app if found


``` java

YourArrayListString = apk.getSplitPublicSourcesIfFound();
```


> check if the app file is installed with same SIGNATURE or NO


``` java

YourBoolean = apk.installedWithSameSign();

```


> get the sha1 AND sha256 signature as String of any app (file or installed)


``` java

YourString = apk.getSHA1();
YourString2 = apk.getSHA256();
```


> get the permissions list of the installed or file app


``` java

YourArrayListString = apk.getPermissios();
```


> get the activities list of the installed or file app


``` java

YourArrayListString = apk.getActivities();

```
> get the services list of the installed or file app


``` java

YourArrayListString = apk.getServices();
```

> get the receivers list of the installed or file app


``` java

YourArrayListString = apk.getReceivers();
```

> get the providers list of the installed or file app


``` java

YourArrayListString = apk.getProviders();

```

> get the first install time as String


``` java

YourString = apk.getInstallTime("yyyy/MM/dd hh:mm:ss");
```


> get the last update time as String


``` java

YourString = apk.getUpdateTime("yyyy/MM/dd hh:mm:ss");

```

> get the first install time or last update time as long which is time in milliseconds , you can use it in calendar or something


``` java

YourLong = apk.firstInstallTime();
YourLong2 = apk.lastUpdateTime();
```


> how to get installed user apps list or system apps list or both , YOUR APP MUST HAVE THE QUERY_ALL_PACKAGES PERMISSION IN ORDER TO ACCESS IT ON ANDROID 11 AND LATER .....


``` java

YourArrayListString = ApkUtils.getInstalledUserAppPackages(MainActivity.this);
YourArrayListString2 = ApkUtils.getInstalledSystemAppPackages(MainActivity.this);
YourArrayListString2 = ApkUtils.getInstalledAppPackages(MainActivity.this);

```

> how to sort apps by name or size or first install time or last update time ?
``` java
//there are 8 types of sorting.
/*
SORT_BY_INSTALL_TIME_UP_TO_DOWN
SORT_BY_INSTALL_TIME_DOWN_TO_UP
SORT_BY_NAME_UP_TO_DOWN
SORT_BY_NAME_DOWN_TO_UP
SORT_BY_SIZE_UP_TO_DOWN
SORT_BY_SIZE_DOWN_TO_UP
SORT_BY_LAST_UPDATE_UP_TO_DOWN
SORT_BY_LAST_UPDATE_DOWN_TO_UP
*/
//example :

YourArrayListString = ApkUtils.getInstalledUserAppPackages(MainActivity.this);

ApkUtils.sort(MainActivity.this,YourArrayListString,ApkUtils.SORT_BY_INSTALL_TIME_UP_TO_DOWN);

//MainActivity.this ==> Your Context
//YourArrayListString ==> Your Array List String ( or List String ) Name
//SORT_BY_INSTALL_TIME_UP_TO_DOWN ==> sorting rule

```


> HOW TO LIST THE APK FILES IN A SPECIFIC FOLDER (AND ITS SUBFOLDERS) , IF THE TARGET ANDROID IS ANDROID 11 AND LATER THEN YOUR APP MUST HAVE MANAGE_EXTERNAL_STORAGE + GRANTED IT FROM USER

``` java

YourArrayListString = ApkUtils.getApkPaths(YourFolderString);

```
> open an app using package name

``` java

ApkUtils.openApp(MainActivity.this,PackageNameAsString);

```

> open an app's settings using package name

``` java
ApkUtils.openAppSettings(MainActivity.this,PackageNameAsString);

```

> open an app's activity of an app using package name of the app and package name of activity + . + its activity name

``` java

ApkUtils.openApp(MainActivity.this,AppPackageName,ActivityPackageAndName); // like com.hello and com.activityPackage.ActivityName , if your Activity package name is same as app then no problem...same thing , com.hello and com.hello.ActivityName

```

> request uninstalling an app using package name

``` java

ApkUtils.uninstallApp(MainActivity.this,AppPackageName);

```

> share an app using package name

``` java

ApkUtils.share(MainActivity.this,PackageName,"*/*","your message ....");

```

> save an app using package name

``` java

ApkUtils.saveApp(MainActivity.this,PackageName,WhereToSave,new com.android.prime.arab.ware.everythingutils.listeners.CopyTask() {
@Override
public void done() {
//done
}
@Override
public void loading() {
//loading
}
@Override
public void progress(String file) {
//progress
}
@Override
public void error(String error) {
//error
}
@Override
public void error(ArrayList<String> errors) {
//errors
}
});
//WhereToSave ==> like storage/emulated/0/Download

```

> checking if the package is installed

``` java
if(ApkUtils.isInstalled(MainActivity.this,PackageName)) {
//installed
} else {
//not installed
}

```

> how to check if the apk file is signed

``` java

if(ApkUtils.isSigned(MainActivity.this,new java.io.File(PathAsString))) {
//signed
} else {
//not signed
}

```

## Signature Info

``` java
//get the sha1


YourString = apk.getSHA1();

//get the sha256


YourString = apk.getSHA256();

//get the sha384

YourString = apk.getSHA384();

// get the sha512

YourString = apk.getSHA512();

// get the md5

YourString = apk.getMD5();

// get the signature issuer

YourString = apk.getSignatureIssuer();

// get the signature serial

YourString = apk.getSignatureSerial();

// get the signature created date

YourString = apk.getSignatureOfficalCreated();

// get the signature ended date

YourString = apk.getSignatureOfficalEnded();

// get the signature created date with custom format of showing date

YourString = apk.getSignatureCreated(format); //format is a String value like yyyy/MM/dd hh:mm:ss

// get the signature ended date with custom format of showing date

YourString = apk.getSignatureEnds(format); //format is a String value like yyyy/MM/dd hh:mm:ss

// get the signature created date in milliseconds as long variable

YourLongNumberVariable = apk.getSignatureCreated();

// get the signature ended date in milliseconds as long variable

YourLongNumberVariable = apk.getSignatureEnds();

	//it gets the activities tags in the AndroidManifest.xml with thier info like isEnabled key gives android:enabled="boolean" String value ! , the available keys are isExported , isEnabled , name , and orientation (portrait or landscape or undefined value as String !)

	ArrayList<HashMap<String,String>> YourListMap_ListOfHashMaps = apk.getActivitiesInfo();
	


	
	//it gets the services tags in the AndroidManifest.xml with thier info like isEnabled key gives android:enabled="boolean" String value ! , the available keys are isExported , isEnabled , name
	
	ArrayList<HashMap<String,String>> YourListMap_ListOfHashMaps = apk.getServicesInfo();
	


	
	//it gets the reciever tags in the AndroidManifest.xml with thier info like isEnabled key gives android:enabled="boolean" String value ! , the available keys are isExported , isEnabled , name
	
	ArrayList<HashMap<String,String>> YourListMap_ListOfHashMaps = apk.getRecieversInfo();
	


```



