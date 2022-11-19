## Permission Utils

## The Open Source Class 2022 @ArabWare

## context => Your Context like MainActivity.this or MyFragment.this.getActivity() or any thing extends Activity
## randomNumber => a random number like 777 , it will be used on Activity result (optional)
## permission_name => like "READ_EXTERNAL_STORAGE" , it is a string

> how to request a permission



``` java

new PermissionUtils(context).requestPermission(permission_name,randomNumber);

//example :

new PermissionUtils(MainActivity.this).requestPermission("READ_PHONE_STATE",963);

```

> how to request multiple permissions

``` java

// create an array list of Strings of permissions
//lets say your list String name is YourList

YourList.add("WRITE_EXTERNAL_STORAGE");
YourList.add("READ_EXTERNAL_STORAGE");

new PermissionUtils(MainActivity.this).requestPermissions(YourList,963);

```

> how to check a permission

``` java

if(new PermissionUtils(MainActivity.this).checkPermission("READ_PHONE_STATE")) {
//user granted it .. what to do below ?
} else {
//user didnot grant it .. what to do below ?
}

```

> how to request floating window ( appear on top ) permission
``` java
new PermissionUtils(MainActivity.this).requestAppearOnTopPermission();
```

> how to check floating window ( appear on top ) permission , SYSTEM_ALERT_WINDOW........

``` java

if(new PermissionUtils(MainActivity.this).checkAppearOnTopPermission()) {
//user granted it , what to do below ?
} else {
//user did not grant it , what to do below ?
}

```

> how to request write settings permission

``` java

new PermissionUtils(MainActivity.this).requestWriteSettings();

```

> how to check write settings permission

``` java

if(new PermissionUtils(MainActivity.this).checkWriteSettings()) {
//user granted it , what to do below ?
} else {
//user did not grant it , what to do below ?
}

```

> request manage all storage permission for android 11 and later ( it does not crash on below versions ) MANAGE_EXTERNAL_STORAGE PERMISSION ... 

``` java

new PermissionUtils(MainActivity.this).requestManageAllStorage();

```

> how to check manage all storage permission for android 11 and later , it doesn't crash on below versions ) MANAGE_EXTERNAL_STORAGE PERMISSION ...

``` java

if(new PermissionUtils(MainActivity.this).checkManageAllStorage()) {
//user granted it , what to do below ?
} else {
//user did not grant it , what to do below ?
}

```

> how to check and request all storage permissions ( read external storage + write external storage + manage external storage ) , it works on all android versions .

``` java

if(new PermissionUtils(MainActivity.this).isFullStorageAccess()) {
//your app works well and can manage all files
} else {
// we will request them
new PermissionUtils(MainActivity.this).checkFullStoragePermissions();
}

```

> how to request and check battery anti saving permission ( batter optimisation )
``` java

if(new PermissionUtils(MainActivity.this).canIgnoreBatterySaving()) {

// yes your app don't care about the battery

} else {

// we will request it

new PermissionUtils(MainActivity.this).requestOptimiseBatteryPermission();

}

}
