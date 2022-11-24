## Normal Notification

![click me to see how it looks ، انقر هنا لمعرفة كيف تبدو](https://github.com/abodinagdat16/EveryThingUtils/blob/master/Doc/Screenshot_20221122-114245_ArabWareFileManager.jpg?raw=true)

![click me to see how it looks ، انقر هنا لمعرفة كيف تبدو](https://github.com/abodinagdat16/EveryThingUtils/blob/master/Doc/Screenshot_20221122-114248_ArabWareFileManager.jpg?raw=true)

## So let's explain every item

## app name , at top of notification and provided by system

## small icon , by you

## small bigger icon , by you

## title and short description, by you

## buttons (optional) by you

## notification click event (optional) , by you

## import this : notification.* (import notification.*;)


## First thing , we will create a variable with type NormalNotification

## let's say its name MyNotification

## let's start creating it

## you want default system notification sound ?

``` java

MyNotification = new NormalNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT);

```

## YourContext => the context you're trying to show notification from like if Activity then MyActivity.this or if fragment then MyFragment.this.getContext() or if service then MyService.this.getBaseContext() 

## YourChannelName => a string that is the channel name of your notification in the notification settings of user ( for android 8 and up , and it works on lower ! Don't worry , but it does no thing there )


## YourSystemDescription => Your String that describe the notification to system and not shown to user but the user can read it in the settings of notification,  same as above

## YourNotificationIdNumber => a very important number that can be used to identify the notification to close it or update it after publishing it




## What If You want to set the notification sound ?

``` java

MyNotification = new NormalNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT , NotificationSound);

```

## same as previous one but new thing at last


## NotificationSound => there are 4 sources to set sound from ,  يوجد أربعة مصادر لتعيين صوت الاشعار

## setSound(String) => from assets , replace String with a string that is assets file name or path

## setSound(new java.io.File(String)) => from file path , replace String with file path

## setSound(Uri.parse(StringOfUrl))

## setSound(R.raw. + sound name Without extension) , like setSound(R.raw.MySound)



## Example !

``` java

MyNotification = new NormalNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT , new NotificationSound(YourContext).setSound(Uri.parse("url رابط")).getUri());

```


## how to set title ?

``` java
MyNotification.setTitle(YourString);

// replace YourString with your String value of title

```

## how to set description ?

``` java

MyNotification.setDescription(YourString);

// replace YourString with your String value of description

```


## how to set small icon ?

## there are 5 sources to set small icon from !

## setting small icon from resource ( like R.drawable.icon )

``` java

MyNotification.setSmallIcon(R.drawable.quran);

```

## setting small icon from resource but by String (folder name + file name)

``` java

MyNotification.setSmallIcon("drawable","quran"); // you can replace Strings values with strings variables names

// example 2

MyNotification.setSmallIcon(FolderString,FileString);

```

## setting it from assets

``` java

MyNotification.setSmallIcon(YourAssetsFileNameOrPathAsString); // I think reading the text between () explains what to put ...

```

## setting from file path

``` java

MyNotification.setSmallIcon(new java.io.File(FilePathAsString)); // FilePathAsString => file path as string ....

```

## setting from bitmap ( like using glide library to get bitmap from url , you get it right ???

``` java

MyNotification.setSmallIcon(MyBitmap);

```



## NOW WE WILL TALK ABOUT BIG ICON

## Big icon same as small icon , just replace the (Small) word in codes with (Big) word ..



## how to set if the notification is going to be changed (updated) or no ( if the value is true then your notification (which has same id number) will never cause a sound or be shown again like it is new + now you can update it without disturbing user


``` java

MyNotification.setAlertOnce(true);

//if you don't want that , just don't add the code or :

MyNotification.setAlertOnce(false);

```


## how to set if notification can be removed by user or no

``` java

MyNotification.setCanBeRemoved(true); // replace (true) with (false) to make it impossible to be deleted by user by finger

```

## Adding Events For Notification Clicked , Optional!

> open activity when clicked :

``` java

MyNotification.openActivityWhenClick(MainActivity.class); //MainActivity Activity

```

> open service when clicked :

``` java

MyNotification.openServiceWhenClick(MyService.class); //MyService Service

```

> open broadcast when clicked :

``` java

MyNotification.openBroadcastWhenClick(BR.class); //BR broadcast

```

> open url when click :

``` java

MyNotification.openUrlWhenClick("My Url");

// or from String variable

MyNotification.openUrlWhenClick(MyUrl);

```


## setting title color ( android 5 and up , lower don't harm , still work but still system color )

``` java

MyNotification.setColor(Color.RED);

//OR

MyNotification.setColor(0xFF4527A0);

//OR

MyNotification.setColor("#FF4527A0");

//OR

MyNotification.setColor(100,200,150); //rgb

//OR

MyNotification.setColor(150,200,100,182); //argb


```


## how to show , get notification and cancel one

``` java
// to show
MyNotification.show();
// if setAlertOnce is used and set to true then if a previous notification with same id and channel name is available then it will be replaced .


MyNotification.cancel(0);
// here we are
// canceling a notification with id number = 0


android.app.Notification n = MyNotification.getNotification();

// here we are getting Notification,  so we can use it to another reason instead of showing it , like putting it on startForeground method or something

```









