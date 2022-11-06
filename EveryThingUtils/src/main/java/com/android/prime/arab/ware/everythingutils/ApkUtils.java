/* ArabWare 2022 Copyright.The Open Source Project ApkUtils java class
this class created to help android developers create an apps manager or
apps info app with all information and functions

you can modify or use or share this class in your projects
but you can not modify the copy right


the class is so explained , every code line there is something
can explain why I made this .....


Happy coding everyone ... I hope this class was helpful!

*/

//package com.android.prime.arab.ware.everythingutils;

package com.android.prime.arab.ware.everythingutils;

//imports of classes

import android.content.Intent; /* opening an app or an activity */
import android.content.Context; /*Context Class*/
import android.content.pm.PackageInfo; /*Package Info from installed or file apk*/
import android.content.pm.ApplicationInfo; /*same as above but with more info*/
import android.app.Activity; /* the base class of an activity */
import android.app.Fragment; /* the base class of a fragment */
import android.app.DialogFragment; /* the base class of a dialog fragment */
import android.graphics.drawable.Drawable; /* this class for drawing the icon */
import android.net.Uri;
import android.provider.Settings;
import com.android.prime.arab.ware.everythingutils.listeners.CopyTask;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.String; /* the String class */
import android.os.Build; /* User Device Info */
import java.security.cert.X509Certificate;
import java.security.cert.CertificateFactory;
import java.util.ArrayList; /* This Class Can Contains List Of String values */
import java.io.File; /* file management class */
import android.content.pm.Signature; /* a class that can check and get the signature of an app file or installed app*/
import android.content.pm.PackageManager; /* the class which GIVE THE POWER TO THIS PROJECT */
import java.util.Arrays;
import java.util.List; /* a list that contain values. like ArrayList but not ArrayList :) */
import android.content.pm.ActivityInfo; /* the class that is used to get full info about an activity of an app */
import android.content.pm.ProviderInfo; /* the class that is used to get full info about a provider of an app */
import android.content.pm.ServiceInfo; /* the class that is used to get full info about a service of an app */
import java.io.File; /*to list and get info about apk files , see getApkPaths method to understand */
import java.util.Date; /*just a temporary & fast way to convert long into Date to use Into Temporary SimpleDateFormat */
import java.text.SimpleDateFormat; /* just a temporary & fast way to convert Date into String */

//definition of the whole class

@java.lang.SuppressWarnings("deprecation") /*this just for ignoring warnings since this class is using if then else codes to work on all android versions.*/
public class ApkUtils {

	//fields
	
	//sdk level
	
	public static int sdk = Build.VERSION.SDK_INT;

	//two important fields

	private ApplicationInfo ai;
	private PackageInfo pckgInfo;

	/*context of your app*/
	public Context cntx;
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
	/*just for signing info*/
	private PackageInfo packageInfo;
	private Signature[] signatures;
	/*permissions of the app from file or package*/
	public ArrayList<String> permissions = new ArrayList<>();
	public ArrayList<String> granted_permissions = new ArrayList<>();
	public ArrayList<String> denied_permissions = new ArrayList<>();
	/*activities of the app from file or package*/
	public ArrayList<String> activities = new ArrayList<>();
	/*services of the app from file or package*/
	public ArrayList<String> services = new ArrayList<>();
	/*receivers of the app from file or package*/
	public ArrayList<String> receivers = new ArrayList<>();
	/*providers of the app from file or package*/
	public ArrayList<String> providers = new ArrayList<>();
	/*signature info*/
	public String issuer = "null";
	public String serial = "null";
	public String created = "null";
	public String ends = "null";
	public long CREATED = 0;
	public long ENDS = 0;
	public String md5 = "null";
	
	
	/*if the developer (YOU) wanted to get info of an app from package and not file path*/
	private boolean fromPackage;
	private boolean temp = false;

	public static String[] s2;

	//constructors

	public ApkUtils(Context c) {

		cntx = c;

	}

	//methods (functions)

	/*
	you have to YES YOU HAVE TO , add the MANAGE_EXTERNAL_STORAGE permission
	in order to make your app run this method on android 11 and later .... 
	*/

	public void setApkPath(String p) {
		path = p;
		fromPackage = false;
		doSomething();
	}

	/*
	
	if you choose this , you dont need to use MANAGE_EXTERNAL_STORAGE PERMISSION
	
	you will convert your file to input stream
	
	it does not need any permission ! NOT EVEN NORMAL STORAGE PERMISSIONS
	
	*/

	public void setInputStreamAsApk(InputStream is) throws Exception {
		InputStream cacheApk = is;
		FileOutputStream output = null;
		File myFile = new File(cntx.getCacheDir(), "temp.apk");
		output = new java.io.FileOutputStream(myFile);
		byte[] buffer = new byte[1024];
		int a = 0;
		while ((a = cacheApk.read(buffer)) != -1) {
			output.write(buffer, 0, a);
		}
		cacheApk.close();
		output.close();
		setApkPath(myFile.getAbsolutePath());
	}

	/*
	you have to YES YOU HAVE TO , add the QUERY_ALL_PACKAGES permission
	in order to make your app run this method on android 11 and later
	*/

	public void setPackageName(String pkgName) {
		pkg = pkgName;
		fromPackage = true;
		doSomething();
	}

	/* with this you can get an icon drawable value . and put it in ImageView (for example)*/

	public Drawable getIcon() {
		return drawable;
	}

	/*name of the app from file or package*/

	public String getName() {
		return name;
	}

	/*version name of the app from file or package*/

	public String getVersionName() {
		return versionName;
	}

	/*version code of the app from file or package*/

	public String getVersionCode() {
		return String.valueOf(versionCode);
	}

	/*package of the app from file or package*/

	public String getPackage() {
		return pkg;
	}

	/*target sdk version of the app from file or package*/

	public String getTargetSdkVersion() {
		return String.valueOf(targetSdkVersion);
	}

	/*min sdk version of the app from file or package*/

	public String getMinSdkVersion() {
		return String.valueOf(minSdkVersion);
	}

	/*manage space activity name if found of the app from file or package*/

	public String getManageSpaceActivityName() {
		if (manageSpaceActivityName == null) {
			return "";
		}
		return manageSpaceActivityName;
	}

	/*Application class name if found of the app from file or package*/

	public String getClassName() {
		if (className == null) {
			return "";
		}
		return className;
	}

	/*uid of the app from file or package*/

	public String getUID() {
		return String.valueOf(uid);
	}

	/* data dir of the app from file or package , if installed */

	public String getDataDir() {
		return dataDir;
	}

	/* source dir of the app from file or package if installed */

	public String getSourceDir() {
		if (sourceDir == null) {
			return "";
		}
		return sourceDir;
	}

	/*public source dir of the app from file or package if installed*/

	public String getPublicSourceDir() {
		if (publicSourceDir == null) {
			return "";
		}
		return publicSourceDir;
	}

	/* name of installed app that has same package name of app from file*/

	public String getInstalledName() {
		if (installedName == null) {
			return "";
		}
		return installedName;
	}

	/* version name of installed app that has same package name of app from file*/

	public String getInstalledVerName() {
		if (installedVerName == null) {
			return "";
		}
		return installedVerName;
	}

	/* version code of installed app that has same package name of app from file*/

	public String getInstalledVerCode() {
		return String.valueOf(installedVerCode);
	}

	/* target sdk version of installed app that has same package name of app from file*/

	public String getInstalledTargetSdk() {
		return String.valueOf(installedTargetSdk);
	}

	/* min sdk version of installed app that has same package name of app from file*/

	public String getInstalledMinSdk() {
		return String.valueOf(installedMinSdk);
	}

	/* names list of split files of the app if found and installed*/

	public ArrayList<String> getSplitNamesIfFound() {
		if (names == null) {
			names = new ArrayList<>();
			return names;
		}
		return names;
	}

	/* source dirs list of split files of the app if found and installed*/

	public ArrayList<String> getSplitSourcesIfFound() {
		if (sources == null) {
			sources = new ArrayList<>();
			return sources;
		}
		return sources;
	}

	/* public source dirs list of split files of the app if found and installed*/

	public ArrayList<String> getSplitPublicSourcesIfFound() {
		if (publicSources == null) {
			publicSources = new ArrayList<>();
			return publicSources;
		}
		return publicSources;
	}

	/* if the user installed the apk file and it is same SIGNATURE ,
	then it will return TRUE !*/

	public boolean installedWithSameSign() {
		return isInstalledWithSameSignature;
	}

	/*you can get the sha1 of the app from file or package*/

	public String getSHA1() {
		if (SHA1 == null) {
			return "";
		}
		return SHA1;
	}

	/*you can get the sha256 of the app from file or package*/

	public String getSHA256() {
		if (SHA256 == null) {
			return "";
		}
		return SHA256;
	}

	/*you can get the permissions list of the app from file or package*/

	public ArrayList<String> getPermissions() {
		if (permissions == null) {
			permissions = new ArrayList<>();
		}
		return permissions;
	}

	/*you can get the activities list of the app from file or package*/

	public ArrayList<String> getActivities() {
		if (activities == null) {
			activities = new ArrayList<>();
		}
		return activities;
	}

	/*you can get the services list of the app from file or package*/

	public ArrayList<String> getServices() {
		if (services == null) {
			services = new ArrayList<>();
		}
		return services;
	}

	/*you can get the receivers list of the app from file or package*/

	public ArrayList<String> getReceivers() {
		if (receivers == null) {
			receivers = new ArrayList<>();
		}
		return receivers;
	}

	/*you can get the providers list of the app from file or package*/

	public ArrayList<String> getProviders() {
		if (providers == null) {
			providers = new ArrayList<>();
		}
		return providers;
	}

	/*to get information about the time of first install and last update*/

	public String getInstallTime(String format) {
		return new SimpleDateFormat(format).format(new Date(pckgInfo.firstInstallTime));
	}

	public String getUpdateTime(String format) {
		return new SimpleDateFormat(format).format(new Date(pckgInfo.lastUpdateTime));
	}

	public long firstInstallTime() {
		return pckgInfo.firstInstallTime;
	}

	public long lastUpdateTime() {
		return pckgInfo.lastUpdateTime;
	}

	/*this is the MAIN METHOD OF THIS CLASS , 90% OF DATA ARE BEING GOT HERE*/

	private void doSomething() {

		init(0);

		try {

			if (!fromPackage) {

				ai.sourceDir = path;

			}

		} catch (Throwable e) {

		}

		try {

			if (!fromPackage) {

				ai.publicSourceDir = path;

			}

		} catch (Throwable e) {

		}

		try {

			drawable = ai.loadIcon(cntx.getPackageManager());

		} catch (Throwable e) {

		}

		try {

			name = "" + ai.loadLabel(cntx.getPackageManager());

		} catch (Throwable e) {

		}

		try {

			versionName = pckgInfo.versionName;

		} catch (Throwable e) {

		}

		try {

			if (sdk >= 28) {

				versionCode = (int) pckgInfo.getLongVersionCode();

			} else {

				versionCode = pckgInfo.versionCode;

			}

		} catch (Throwable e) {

			versionCode = pckgInfo.versionCode;

		}

		try {

			pkg = pckgInfo.packageName;

		} catch (Throwable e) {
			throw new RuntimeException(new Exception(e));
		}

		try {

			targetSdkVersion = ai.targetSdkVersion;

		} catch (Throwable e) {

		}

		try {

			minSdkVersion = ai.minSdkVersion;
			

		} catch (Throwable e) {

		}

		try {

			dataDir = ai.dataDir;

		} catch (Throwable e) {

		}

		try {

			manageSpaceActivityName = ai.manageSpaceActivityName;

		} catch (Throwable e) {

		}

		try {

			className = ai.className;

		} catch (Throwable e) {

		}

		try {

			uid = ai.uid;

		} catch (Throwable e) {

		}

		/*here we are checking if the app is installed or no and get some info*/

		if (!fromPackage) {
			fromPackage = true;
			temp = true;
		}

		try {

			init(0);

		} catch (Throwable e) {

		}

		try {

			publicSourceDir = ai.publicSourceDir;

		} catch (Throwable e) {

		}

		try {

			sourceDir = ai.sourceDir;

		} catch (Throwable e) {

		}

		try {

			installedVerName = pckgInfo.versionName;

		} catch (Throwable e) {

		}

		try {

			if (sdk >= 28) {

				versionCode = (int) pckgInfo.getLongVersionCode();

			} else {

				versionCode = pckgInfo.versionCode;

			}

		} catch (Throwable e) {
			versionCode = pckgInfo.versionCode;
		}

		try {

			installedTargetSdk = ai.targetSdkVersion;

		} catch (Throwable e) {

		}

		try {

			installedMinSdk = ai.minSdkVersion;

		} catch (Throwable e) {

		}

		try {

			installedName = "" + ai.loadLabel(cntx.getPackageManager());

		} catch (Throwable e) {

		}

		try {

			names = new ArrayList<>();

			String[] list = ai.splitNames;

			for (String s : list) {

				names.add(s);

			}

		} catch (Throwable e) {

		}

		try {

			sources = new ArrayList<>();

			String[] list2 = ai.splitSourceDirs;

			for (String s : list2) {

				sources.add(s);

			}

		} catch (Throwable e) {

		}

		try {

			publicSources = new ArrayList<>();

			String[] list3 = ai.splitPublicSourceDirs;

			for (String s : list3) {

				publicSources.add(s);

			}

		} catch (Throwable e) {

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

		} catch (Throwable e) {

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

		} catch (Throwable e) {

		}

		try {

			init(PackageManager.GET_PERMISSIONS);

			permissions = new ArrayList<>();
			granted_permissions = new ArrayList<>();

			if (pckgInfo.requestedPermissions != null) {

				for (String p : pckgInfo.requestedPermissions) {

					permissions.add(p);

				}

			}
			
			/*
			checking granted and denied permissions credits
			https://stackoverflow.com/a/37294804/17808329
			*/
			if(pckgInfo.requestedPermissionsFlags != null) {
			
			for (int i = 0; i < pckgInfo.requestedPermissions.length; i++) {
				if ((pckgInfo.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0 ||(pckgInfo.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) == PackageInfo.REQUESTED_PERMISSION_GRANTED) {
					granted_permissions.add(pckgInfo.requestedPermissions[i]);
				} else {
					denied_permissions.add(pckgInfo.requestedPermissions[i]);
				}
			}
			
			}

		} catch (Throwable e) {

		}

		try {

			init(PackageManager.GET_ACTIVITIES);

			activities = new ArrayList<>();

			if (pckgInfo.activities != null) {

				for (ActivityInfo a : pckgInfo.activities) {

					activities.add(a.name);

				}

			}

		} catch (Throwable e) {

		}

		try {

			init(PackageManager.GET_SERVICES);

			services = new ArrayList<>();

			if (pckgInfo.services != null) {

				for (ServiceInfo a : pckgInfo.services) {

					services.add(a.name);

				}

			}

		} catch (Throwable e) {

		}

		try {

			init(PackageManager.GET_RECEIVERS);

			receivers = new ArrayList<>();

			if (pckgInfo.receivers != null) {

				for (ActivityInfo a : pckgInfo.receivers) {

					receivers.add(a.name);

				}

			}

		} catch (Throwable e) {

		}

		try {

			init(PackageManager.GET_PROVIDERS);

			providers = new ArrayList<>();

			if (pckgInfo.providers != null) {

				for (ProviderInfo a : pckgInfo.providers) {

					providers.add(a.name);

				}

			}

		} catch (Throwable e) {

		}

		/*here , we are just doing this to make the first install time and last update time functions work*/

		init(0);

	}

	//don't care about these, they are available at ArabWareFileManager class but I took them for a reason

	private boolean isAndroid11() {
		return (sdk == 30 || sdk > 30);
	}

	private boolean isAndroid6() {
		return (sdk == 23 || sdk > 23);
	}

	private boolean isFullAccessFiles(Context c) {
		if (isAndroid11()) {

			return android.os.Environment.isExternalStorageManager();
		}
		if (isAndroid6()) {

			if (c.checkSelfPermission(
					android.Manifest.permission.READ_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
				return false;
			}

			if (c.checkSelfPermission(
					android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == android.content.pm.PackageManager.PERMISSION_DENIED) {
				return false;
			}

		} else {

			return true;

		}

		return true;
	}

	//credits of signatures methods (StackOverFlow)

	public String getSignture(String type) {

		try {

			if (sdk < 28) {

				packageInfo = cntx.getPackageManager().getPackageArchiveInfo(path, PackageManager.GET_SIGNATURES);

			} else {

				if (sdk >= 33) {

					packageInfo = cntx.getPackageManager().getPackageArchiveInfo(path,
							PackageManager.PackageInfoFlags.of(PackageManager.GET_SIGNING_CERTIFICATES));

				} else {

					packageInfo = cntx.getPackageManager().getPackageArchiveInfo(path,
							PackageManager.GET_SIGNING_CERTIFICATES);

				}

			}

			try {

				if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {

					signatures = packageInfo.signingInfo.getApkContentsSigners();

				} else {

					signatures = packageInfo.signatures;

				}

			} catch (Throwable e) {

				signatures = packageInfo.signingInfo.getApkContentsSigners();

			}

			for (android.content.pm.Signature signature : signatures) {

				String shaType = getSHAOfType_(signature.toByteArray(), type);
				// check is matches hardcoded value
				return shaType;
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

		return "";
	}

	public String getSignture(String type, String pkgName) {

		try {

			if (sdk < 28) {

				packageInfo = cntx.getPackageManager().getPackageInfo(pkgName, PackageManager.GET_SIGNATURES);

			} else {

				if (sdk >= 33) {

					packageInfo = cntx.getPackageManager().getPackageInfo(pkgName,
							PackageManager.PackageInfoFlags.of(PackageManager.GET_SIGNING_CERTIFICATES));

				} else {

					packageInfo = cntx.getPackageManager().getPackageInfo(pkgName,
							PackageManager.GET_SIGNING_CERTIFICATES);

				}

			}

			try {

				if (packageInfo.signatures == null || packageInfo.signatures.length == 0) {

					signatures = packageInfo.signingInfo.getApkContentsSigners();

				} else {

					signatures = packageInfo.signatures;

				}

			} catch (Throwable e) {

				signatures = packageInfo.signingInfo.getApkContentsSigners();

			}

			for (android.content.pm.Signature signature : signatures) {

				String shaType = getSHAOfType_(signature.toByteArray(), type);
				
				CertificateFactory certFactory = CertificateFactory.getInstance("X509");
				X509Certificate x509Cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(signature.toByteArray()));
				
				serial = ""+x509Cert.getSerialNumber();
				issuer = x509Cert.getIssuerDN() + "";
				ends = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(x509Cert.getNotAfter());
				created = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss").format(x509Cert.getNotBefore());
				CREATED = x509Cert.getNotBefore().getTime();
				ENDS = x509Cert.getNotAfter().getTime();
				md5 = getSHAOfType_(signature.toByteArray(),"MD5");
				
				
				
				// check is matches hardcoded value
				return shaType;
			}
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

		return "";
	}

	//computed the sha1 hash of the signature
	public String getSHAOfType_(byte[] sig, String type) {
		try {
			java.security.MessageDigest digest = java.security.MessageDigest.getInstance(type);
			digest.update(sig);
			byte[] hashtext = digest.digest();
			return bytes_To_Hex_(hashtext);
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}

	}

	//util method to convert byte array to hex string
	public String bytes_To_Hex_(byte[] bytes) {
		final char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	//Legendary streamer class calling , all credits go to his help to me.

	public static ArrayList<String> getInstalledUserAppPackages(Context c) {

		List<android.content.pm.PackageInfo> listn;

		ArrayList<String> tempList = new ArrayList<>();

		try {

			/*Android 13 and up*/

			if (sdk >= 33) {

				listn = c.getPackageManager()
						.getInstalledPackages(PackageManager.PackageInfoFlags.of(PackageManager.GET_META_DATA));

				/*Android 12 and below*/

			} else {

				listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);

			}

			for (android.content.pm.PackageInfo packageInfo : listn) {

				if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

					tempList.add(packageInfo.packageName);

				} else {

				}
			}

		} catch (Throwable e) {
			throw new RuntimeException(new Throwable(
					"system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
		}

		if (tempList == null) {
			throw new RuntimeException(new Throwable(
					"system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
		}

		return tempList;

	}

	public static ArrayList<String> getInstalledSystemAppPackages(Context c) {

		List<android.content.pm.PackageInfo> listn;

		ArrayList<String> tempList = new ArrayList<>();

		try {

			/*Android 13 and up*/

			if (sdk >= 33) {

				listn = c.getPackageManager()
						.getInstalledPackages(PackageManager.PackageInfoFlags.of(PackageManager.GET_META_DATA));

				/*Android 12 and below*/

			} else {

				listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);

			}

			for (android.content.pm.PackageInfo packageInfo : listn) {

				if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {

				} else {

					tempList.add(packageInfo.packageName);

				}
			}

		} catch (Throwable e) {
			throw new RuntimeException(new Throwable(
					"system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
		}

		if (tempList == null) {
			throw new RuntimeException(new Throwable(
					"system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
		}

		return tempList;

	}

	/*get both of user & system apps list*/

	public static ArrayList<String> getInstalledAppPackages(Context c) {

		List<android.content.pm.PackageInfo> listn;

		ArrayList<String> tempList = new ArrayList<>();

		try {

			/*Android 13 and up*/

			if (sdk >= 33) {

				listn = c.getPackageManager()
						.getInstalledPackages(PackageManager.PackageInfoFlags.of(PackageManager.GET_META_DATA));

				/*Android 12 and below*/

			} else {

				listn = c.getPackageManager().getInstalledPackages(PackageManager.GET_META_DATA);

			}

			for (android.content.pm.PackageInfo packageInfo : listn) {

				tempList.add(packageInfo.packageName);

			}

		} catch (Throwable e) {
			throw new RuntimeException(new Throwable(
					"system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
		}

		if (tempList == null) {
			throw new RuntimeException(new Throwable(
					"system refused to give your app permission to list apps , are you sure your app has QUERY_ALL_PACKAGES permission?"));
		}

		return tempList;

	}

	/*you can list the apk files using this , where is the full path of folder String value*/

	public static ArrayList<String> getApkPaths(String where) {

		return new ArabWareFileManager(where).full_files_list(false, "apk");

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

				if (sdk < 28) {

					pckgInfo = cntx.getPackageManager().getPackageInfo(pkg, mode);

				} else {

					if (sdk >= 33) {

						pckgInfo = cntx.getPackageManager().getPackageInfo(pkg,
								PackageManager.PackageInfoFlags.of(mode));

					} else {

						pckgInfo = cntx.getPackageManager().getPackageInfo(pkg, mode);

					}

				}

			} else {

				if (sdk < 28) {

					pckgInfo = cntx.getPackageManager().getPackageArchiveInfo(path, mode);

				} else {

					if (sdk >= 33) {

						pckgInfo = cntx.getPackageManager().getPackageArchiveInfo(path,
								PackageManager.PackageInfoFlags.of(mode));

					} else {

						pckgInfo = cntx.getPackageManager().getPackageArchiveInfo(path, mode);

					}

				}

			}

			ai = pckgInfo.applicationInfo;

		} catch (Throwable e) {

			throw new RuntimeException(e);

		}

	}

	// do they work?

	//test them

	public static ArrayList<String> getInstalledUserAppPackages(Context c, int sorting_mode) {
		ArrayList<String> sort = getInstalledUserAppPackages(c);
		sort(c, sort, sorting_mode);
		return sort;
	}

	public static ArrayList<String> getInstalledSystemAppPackages(Context c, int sorting_mode) {
		ArrayList<String> sort = getInstalledSystemAppPackages(c);
		sort(c, sort, sorting_mode);
		return sort;
	}

	public static ArrayList<String> getInstalledAppPackages(Context c, int sorting_mode) {
		ArrayList<String> sort = getInstalledAppPackages(c);
		sort(c, sort, sorting_mode);
		return sort;
	}

	public static void sort(Context c, ArrayList<String> s, int mode) {

		if (mode == SORT_BY_NAME_UP_TO_DOWN) {

			s2 = new String[s.size()];

			for (int a = 0; a < s.size(); a++) {
				s2[a] = s.get(a);
			}

			java.util.Arrays.sort(s2, new java.util.Comparator<String>() {
				@Override
				public int compare(String a, String b) {

					ApkUtils au = new ApkUtils(c);
					au.setPackageName(a);
					ApkUtils au2 = new ApkUtils(c);
					au2.setPackageName(b);

					return au.getName().compareTo(au2.getName());

				}
			});
		}

		if (mode == SORT_BY_NAME_DOWN_TO_UP) {
			s2 = new String[s.size()];

			for (int a = 0; a < s.size(); a++) {
				s2[a] = s.get(a);
			}

			java.util.Arrays.sort(s2, new java.util.Comparator<String>() {
				@Override
				public int compare(String a, String b) {

					ApkUtils au = new ApkUtils(c);
					au.setPackageName(a);
					ApkUtils au2 = new ApkUtils(c);
					au2.setPackageName(b);

					return au2.getName().compareTo(au.getName());
				}
			});
		}

		if (mode == SORT_BY_INSTALL_TIME_UP_TO_DOWN) {
			s2 = new String[s.size()];

			for (int a = 0; a < s.size(); a++) {
				s2[a] = s.get(a);
			}

			java.util.Arrays.sort(s2, new java.util.Comparator<String>() {
				@Override
				public int compare(String a, String b) {

					ApkUtils au = new ApkUtils(c);
					au.setPackageName(a);
					ApkUtils au2 = new ApkUtils(c);
					au2.setPackageName(b);
					if (au.firstInstallTime() > au2.firstInstallTime()) {
						return 1;
					}

					if (au.firstInstallTime() < au2.firstInstallTime()) {
						return -1;
					}

					return 0;

				}
			});
		}

		if (mode == SORT_BY_INSTALL_TIME_DOWN_TO_UP) {
			s2 = new String[s.size()];

			for (int a = 0; a < s.size(); a++) {
				s2[a] = s.get(a);
			}

			java.util.Arrays.sort(s2, new java.util.Comparator<String>() {
				@Override
				public int compare(String a, String b) {

					ApkUtils au = new ApkUtils(c);
					au.setPackageName(a);
					ApkUtils au2 = new ApkUtils(c);
					au2.setPackageName(b);
					if (au.firstInstallTime() > au2.firstInstallTime()) {
						return -1;
					}

					if (au.firstInstallTime() < au2.firstInstallTime()) {
						return 1;
					}

					return 0;
				}
			});
		}

		if (mode == SORT_BY_LAST_UPDATE_UP_TO_DOWN) {
			s2 = new String[s.size()];

			for (int a = 0; a < s.size(); a++) {
				s2[a] = s.get(a);
			}

			java.util.Arrays.sort(s2, new java.util.Comparator<String>() {
				@Override
				public int compare(String a, String b) {

					ApkUtils au = new ApkUtils(c);
					au.setPackageName(a);
					ApkUtils au2 = new ApkUtils(c);
					au2.setPackageName(b);
					if (au.lastUpdateTime() > au2.lastUpdateTime()) {
						return 1;
					}
					if (au.lastUpdateTime() < au2.lastUpdateTime()) {
						return -1;
					}
					return 0;

				}
			});
		}

		if (mode == SORT_BY_LAST_UPDATE_DOWN_TO_UP) {
			s2 = new String[s.size()];

			for (int a = 0; a < s.size(); a++) {
				s2[a] = s.get(a);
			}

			java.util.Arrays.sort(s2, new java.util.Comparator<String>() {
				@Override
				public int compare(String a, String b) {

					ApkUtils au = new ApkUtils(c);
					au.setPackageName(a);
					ApkUtils au2 = new ApkUtils(c);
					au2.setPackageName(b);
					if (au.lastUpdateTime() > au2.lastUpdateTime()) {
						return -1;
					}
					if (au.lastUpdateTime() < au2.lastUpdateTime()) {
						return 1;
					}
					return 0;
				}
			});
		}

		if (mode == SORT_BY_SIZE_UP_TO_DOWN) {
			s2 = new String[s.size()];

			for (int a = 0; a < s.size(); a++) {
				s2[a] = s.get(a);
			}

			java.util.Arrays.sort(s2, new java.util.Comparator<String>() {
				@Override
				public int compare(String a, String b) {

					ApkUtils au = new ApkUtils(c);
					au.setPackageName(a);
					ApkUtils au2 = new ApkUtils(c);
					au2.setPackageName(b);
					if (new File(au.getPublicSourceDir()).length() > new File(au2.getPublicSourceDir()).length()) {
						return 1;
					}

					if (new File(au.getPublicSourceDir()).length() < new File(au2.getPublicSourceDir()).length()) {
						return -1;
					}

					return 0;

				}
			});
		}

		if (mode == SORT_BY_SIZE_DOWN_TO_UP) {
			s2 = new String[s.size()];

			for (int a = 0; a < s.size(); a++) {
				s2[a] = s.get(a);
			}

			java.util.Arrays.sort(s2, new java.util.Comparator<String>() {
				@Override
				public int compare(String a, String b) {

					ApkUtils au = new ApkUtils(c);
					au.setPackageName(a);
					ApkUtils au2 = new ApkUtils(c);
					au2.setPackageName(b);
					if (new File(au.getPublicSourceDir()).length() > new File(au2.getPublicSourceDir()).length()) {
						return -1;
					}

					if (new File(au.getPublicSourceDir()).length() < new File(au2.getPublicSourceDir()).length()) {
						return 1;
					}

					return 0;
				}
			});

		}

		s.clear();
		for (int a = 0; a < s2.length; a++) {
			s.add(s2[a]);
		}

	}

	public static final int SORT_BY_INSTALL_TIME_UP_TO_DOWN = 0;
	public static final int SORT_BY_INSTALL_TIME_DOWN_TO_UP = 1;
	public static final int SORT_BY_NAME_UP_TO_DOWN = 2;
	public static final int SORT_BY_NAME_DOWN_TO_UP = 3;
	public static final int SORT_BY_SIZE_UP_TO_DOWN = 4;
	public static final int SORT_BY_SIZE_DOWN_TO_UP = 5;
	public static final int SORT_BY_LAST_UPDATE_UP_TO_DOWN = 6;
	public static final int SORT_BY_LAST_UPDATE_DOWN_TO_UP = 7;

	public static void openAppSettings(Context c, String packageName) {
		
		c.startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,Uri.parse("package:" + packageName)));
		
	}
	
	

	public static void openApp(Context c, String packageName , String activityPackageAndName) {
		
		c.startActivity(it(new Intent(Intent.ACTION_MAIN),packageName,activityPackageAndName));
		
	}
	
	public static Intent it(Intent intent , String p , String c) {
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		intent.setPackage(p);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setClassName(p, c);
		return intent;
	}

	public static void uninstallApp(Context c, String packageName) {
		c.startActivity(new Intent(Intent.ACTION_DELETE,Uri.parse("package:"+packageName)));
	}

	public static void shareApp(Context c, String packageName,String typeMime , String message) {
		ApkUtils au = new ApkUtils(c);
		au.setPackageName(packageName);
		new ArabWareFileManager(au.getPublicSourceDir()).share(c,typeMime,message);
	}

	public static void saveApp(Context c, String packageName, String where , CopyTask ct) {
		
		
		ApkUtils au = new ApkUtils(c);
		au.setPackageName(packageName);
		new ArabWareFileManager(au.getPublicSourceDir()).copy(where,ct);
	}

	public static boolean isInstalled(Context c, String packageName) {
		ApkUtils au = new ApkUtils(c);
		au.setPackageName(packageName);
		if(new java.io.File(au.getPublicSourceDir()).exists()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isSigned(Context c, File file) {
		ApkUtils au = new ApkUtils(c);
		if(au.signatures == null || au.SHA1 == "null" || au.SHA256 == "null") {
			return false;
		}
		return true;
	}

}
