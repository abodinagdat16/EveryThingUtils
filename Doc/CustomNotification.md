# Custom Notification

![click me to see how it looks ، انقر هنا لمعرفة كيف تبدو](https://github.com/abodinagdat16/EveryThingUtils/blob/master/Doc/Screenshot_20221123-215328_ArabWareFileManager.jpg?raw=true)

![click me to see how it looks ، انقر هنا لمعرفة كيف تبدو](https://github.com/abodinagdat16/EveryThingUtils/blob/master/Doc/Screenshot_20221123-215330_ArabWareFileManager.jpg?raw=true)

![click me to see how it looks ، انقر هنا لمعرفة كيف تبدو](https://github.com/abodinagdat16/EveryThingUtils/blob/master/Doc/Screenshot_20221123-215339_ArabWareFileManager.jpg?raw=true)

## So let's explain every item 

## حسنا ، دعنا نشرح كل عنصر

- app name , provided by system
- اسم التطبيق ، بواسطة النظام
- small custom view , By you
- واجهة مخصصة صغيرة ، بواسطتك
- Big custom view , by you
- واجهة مخصصة كبيرة ، بواسطتك
- small icon , by you
- الأيقونة الصغيرة ، بواسطتك
- small bigger icon , by you
- الأيقونة الصغيرة الأكبر نسبيا، بواسطتك
- notification sound (optional) , by you
- صوت الاشعار ، غير اجباري ، بواسطتك
- notification click event (optional) , by you
- حدث ضغط الأشعار ، غير إجباري ، بواسطتك
- notification buttons (optional) , by you
- ازرار الاشعار ، غير إجبارية ، بواسطتك
:warning: 
**Action buttons are same for the 4 notifications types**

### after you add the library to your project , add this import :
### بعدما تضيف المكتبة لمشروعك ، أضف هذا الامبورت

``` java
import notification.*;
```

**so , you will create a field (variable) in your class (activity , service , broadcast , etc) with Type (NormalNotification) , and name is by you , we will choose MyNotification here**

**سوف تصنع متغير في الكلاس الخاص بك**

**Activity,Service,Broadcast,......**

**نوعه هو**

**NormalNotification**

**الاسم اختياري ، هنا سنختار**

**MyNotification**

#### let's start creating it , دعنا نبدأ بالبرمجة

#### you want default system notification sound ? 
#### انت تريد نغمة الاشعار الخاصة بجهاز المستخدم ؟

``` java

MyNotification = new CustomNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT);

```

**replace YourContext with YourActivity.this or YourFragment.this.getContext() or YourFragment.this.getActivity() , or YourServive.this.getBaseContext() or any valid Context**

**استبدل**

**YourContext**

**يا اما ب**

**YourActivity.this**

**او**

**YourFragment.this.getContext()**

**او**

**YourFragment.this.getActivity()**

**او**

**YourService.this.getBaseContext()**

**طبعا كل واحدة حسب المكان فمثلا اول واحدة للاكتيفيتي و الثانية و الثالثة للفراجمنت و الرابعة للسيرفر، استبدل الأسماء بالخاصة بك**


**YourChannelName => a `String` that is the channel name of your notification in the notification settings of user ( for android 8 and up , and it works on lower ! Don't worry , but it does no thing there )**

**بالنسبة ل**

**YourChannelName**

**يمثل متغير او قيمة نصية لاسم القناة الخاصة بالاشعار ، القناة شيء جديد في إصدارات الاندرويد الحديثة لكن هذا لا يعني ان الكود لن يعمل على الإصدارات القديمة ، لا ابدا لكن لن تؤثر القناة بشيء ، و سنوضح لكم ما هي القناة ، القناة هي مثل اسمك ، شيء يحدد الاشعار ، بل هي اسم عشيرة عفوا ، توضيح أكثر،  اضغط مطولا على اشعار في هاتف حديث و سترى اعدادات الأشعار،  ادخل عليها ستجد اسماء قنوات الاشعارات ، حيث يمكن لك مثلا ان تصنع اشعار بصوت ما و إلى اخره من مميزات و المرة القادمة سيبقى بنفس المميزات الا اذا غيرتها ، و أيضا المستخدم يقدر يتحكم بجزء من اشعارات و ليس كلها و هذا شيء جيد مثلا يوقف الاشعارات الخاصة بالرسائل لكن اشعارات الإعلانات لا ، أو العكس و هكذا**



**YourSystemDescription => Your String that describe the notification to system and not shown to user but the user can read it in the settings of notification,  same as above**

**بالنسبة ل**

**YourSystemDescription**

**يمثل وصف الأشعار الذي سيظهر للنظام في اعدادات الأشعار و ليس للمستخدم**



**YourNotificationIdNumber => a very important number that can be used to identify the notification to close it or update it after publishing it**

**بالنسبة ل**

**YourNotificationIdNumber**

**رقم مهم هام جدا ، بواسطته تقدر تستبدل اشعار باشعار او تحديثه ، تغيير صورة أو نص او تقدم ، أو اغلاقه**

#### What If You want to set the notification sound ?

#### ماذا اذا اردت تعيين صوت الاشعار؟

``` java

MyNotification = new CustomNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT , NotificationSound);

```

#### same as previous one but new thing at last

### مثل السابق لكن إضافة في الاخر

#### NotificationSound => there are 4 sources to set sound from ,  يوجد أربعة مصادر لتعيين صوت الاشعار

#### `setSound(String)` => from assets , replace String with a string that is assets file name or path

**التي فوق تستعمل لتعيين الصوت من داخل تطبيقك من**

**assets**

**استبدل الكلمة**

**String**

**بالقيمة او المتغير النصي الذي يمثل الاسم او المسار هنالك**

#### `setSound(new java.io.File(String))` => from file path , replace String with file path

**التي فوق تستعمل لتعيين الصوت من مسار ملف**

**استبدل الكلمة**

**String**

**بالقيمة او المتغير النصي الذي يمثل المسار**

#### `setSound(Uri.parse(StringOfUrl))` => from url , replace StringOfUrl with direct url of sound file

**التي فوق تستعمل لتعيين الصوت من رابط**

**استبدل الكلمة**

**StringOfUrl**

**بالقيمة او المتغير النصي الذي يمثل الرابط المباشر**

#### `setSound(R.raw. + sound name Without extension)` , like setSound(R.raw.MySound)

**التي فوق تستعمل لتعيين الصوت من داخل تطبيقك من**

**Resources**

**R + . + موقع**

**R.raw.sound_name مثلا**




### Example ! مثال

``` java

MyNotification = new CustomNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT , new NotificationSound(YourContext).setSound(Uri.parse("url رابط")).getUri());

```


## HOW TO SET SMALL CUSTOM VIEW ?

## كيفية تعيين الواجهة المخصصة الصغيرة؟

``` java

MyNotification.setSmallCustomView(R.layout.small);
// custom view named small.xml
// واجهة مخصصة اسمها
// small.xml

```

## HOW TO SET BIG CUSTOM VIEW ?

## كيفية تعيين الواجهة المخصصة الكبيرة

``` java

MyNotification.setBigCustomView(R.layout.big);
// custom view named big.xml
// واجهة مخصصة اسمها
// big.xml

```

**note** : changing text after notification is shown ( or any thing related to its widget like events blablabla , will not be done without reshowing the notification again , so read the documentation again, and see setAlertOnce guide , it will help you !!!
**ملاحظة**
**تغيير النص او شيء في الاشعار بعد رفعه يتطلب تكرار استعمال دالة اظهار الأشعار و التي سنذكرها لاحقا و أيضا استعمال دالة**
**setAlertOnce(true);**
**اكمل الشرح و ستفهم**


## HOW TO ADD EVENT TO THE SMALL CUSTOM WIDGETS ?
## كيفية إضافة أحداث للواجهة المخصصة الصغيرة

``` java
// you can only add on click event that will run pending intent
// انت تستطيع فقط وضع حدث ضغط و الذي سيفتح شيء يشبه
//Intent
// it can open url , activity , service , broadcast
//يفتح رابط ، اكتيفيتي ، عمل بالخلفية ، برودكاست
// please see NOTIFICATION EVENT TUTORIAL which we explained so you can understand what is PendingIntent , keep scrolling
// رجاءا اقرأ شرح أحداث الاشعار للفهم ، تحت ستهم ما هو
//PendingIntent
// I will give only a fast example again
// ساعطي مثال سريع فقط

MyNotification.setSmallCustomViewOnClick(R.id.MyViewId , MyNotification.getActivityPendingIntent(HomeActivity.class)); // here we are opening an activity named HomeActivity by clicked MyViewId widget

```

## HOW TO SET SMALL CUSTOM VIEW WIDGETS TEXT ?
## كيفية تعيين نص شيء في الواجهة المخصصة الصغيرة

``` java

MyNotification.setSmallCustomViewText(R.id.textviewId , "Hello World");

```

## HOW TO SET SMALL CUSTOM VIEW WIDGETS TEXT COLOR ?
## كيفية تعيين لون نص شيء في الواجهة المخصصة الصغيرة

``` java

MyNotification.setSmallCustomViewTextColor(R.id.textviewId , "#ffffffaa"); // or replace "#ffffffaa" with 0xFFFFFFAA or Color.WHITE , or any color int or String...

```

## HOW TO SET PROGRESS BAR PROGRESS , TOTAL PROGRESS
## كيفية تعيين تقدم شريط التقدم في الواجهة المخصصة الصغيرة

``` java

MyNotification.setSmallCustomViewProgress(R.id.MyProgressBar , 50 , 100 , false);
// indeterminate = false
// استبدل
// false
// ب
// true
// لتغيير شكل الشريط

```

## HOW TO SET VIEW VISIBILITY
## كيفية تعيين ظهور شيء يعني مثلا اخفاءه في الواجهة المخصصة الصغيرة

``` java

MyNotification.setSmallCustomViewVisibility(R.id.MyViewId , View.GONE); // OR View.VISIBLE OR View.INVISIBLE

```

## WHAT ABOUT BIG CUSTOM VIEW ?

## نفس الشيء بالنسبة للواجهة المخصصة الكبيرة ، فقط استبدل كلمة صغيرة بالانجليزي هنالك بكلمة كبير بالانجليزي

## Small => Big

## SMALL CUSTOM VIEW == BIG CUSTOM VIEW , JUST REPLACE setSmallCustomView with setBigCustomView , same codes

## if you want to controll the small and big custom view yourself

## اذا اردت التحكم بالواجهات المخصصة بنفسك برمجيا

``` java

RemoteViews MyRemoteViews1 = MyNotification.rvs; //small custom view الواجهة الصغيرة

RemoteViews MyRemoteViews2 = MyNotification.rvs2; //big custom view الواجهة الكبيرة

```

### how to set small icon ?

### كيفية تعيين الصورة الصغيرة ؟


**there are 5 sources to set small icon from !**

**هنالك خمسة مصادر يمكنك تعيين الصورة الصغيرة من**


**setting small icon from resource ( like R.drawable.icon )**

**تعيين الصورة الصغيرة من داخل تطبيقك من**

**resource**

**R.drawable.logo مثلا**

``` java

MyNotification.setSmallIcon(R.drawable.quran);

```

**setting small icon from resource but by String (folder name + file name)**

**تعيين الصورة المصغرة مثل السابقة لكن تحديد اسم المجلد و الملف بواسطة متغير نصي ، أو قيمة نصية**

**"drawable" + "logo" مثلا**


``` java

MyNotification.setSmallIcon("drawable","quran"); // you can replace Strings values with strings variables names

// example 2 مثال ثاني

MyNotification.setSmallIcon(FolderString,FileString);

```

**setting it from assets**

**تعيينها من**

**assets**



``` java

MyNotification.setSmallIcon(YourAssetsFileNameOrPathAsString);
// I think reading the text between () explains what to put ...
//استبدل الكلمة بين القوسين بمتغير نصي او قيمة نصية تمثل اسم او مسار الصوت هنالك**


```

**setting from file path**

**التعيين من مسار الملف**

``` java

MyNotification.setSmallIcon(new java.io.File(FilePathAsString));
// FilePathAsString => file path as string ....
// FilePathAsString => متغير نصي او قيمة نصية تمثل مسار الملف
```

**setting from bitmap ( like using glide library to get bitmap from url , you get it right )**

**التعيين من**

**Bitmap**

**سيفيدك بالتعيين من رابط مثلا ، عبر مكتبة**

**glide**



``` java

MyNotification.setSmallIcon(MyBitmap);

```



### NOW WE WILL TALK ABOUT BIG ICON

**Big icon same as small icon , just replace the (Small) word in codes with (Big) word ..**

### الآن سوف نتحدث عن الأيقونة الكبيرة

**نفس الصغيرة و لكن استبدل كلمة**

**Small**

**بكلمة**

**Big**

### how to set if the notification is going to be changed (updated) or no ( if the value is true then your notification (which has same id number) will never cause a sound or be shown again like it is new + now you can update it without disturbing user

### كيفية تعيين اذا كان الاشعار قابل للتغيير يعني تغيير نص مثلا بعد عرضه ، يجب نفس الرقم التعريفي للاشعار و يفضل نفس اسم القناة طبعا

``` java

MyNotification.setAlertOnce(true);

//if you don't want that , just don't add the code or :

//اذا كنت لا تريد ذلك ، لا تضف الكود ببساطة او

MyNotification.setAlertOnce(false);

```


### how to set if notification can be removed by user or no

### كيفية تعيين اذا كان الاشعار قابل للحذف بواسطة المستخدم

``` java

MyNotification.setCanBeRemoved(true);
// replace (true) with (false) to make it impossible to be deleted by user by finger
//استبدل
//true
//ب
//false
//لجعلها غير قابلة للحذف
```

### Adding Events For Notification Clicked , Optional!

### إضافة أحداث ضغط الأشعار، اختيارية

> open activity when clicked :

> فتح اكتيفيتي عند الضغط

``` java

MyNotification.openActivityWhenClick(MainActivity.class);
//MainActivity Activity
//MainActivity اكتيفيتي اسمها

```

> open service when clicked :

> تشغيل سيرفس عند الضغط 

``` java

MyNotification.openServiceWhenClick(MyService.class);
//MyService Service
//MyService سيرفس اسمها

```

> open broadcast when clicked :

> تشغيل برودكاست عند الضغط

``` java

MyNotification.openBroadcastWhenClick(BR.class);
//BR broadcast
//BR برودكاست اسمه

```

> open url when click :

> فتح رابط عند الضغط 

``` java

MyNotification.openUrlWhenClick("My Url رابطي");

// or from String variable

// او من متغير نصي

MyNotification.openUrlWhenClick(MyUrl);

```


## setting title color ( android 5 and up , lower don't harm , still work but still system color )

## تعيين لون العنوان ، لن تؤثر بشيء الا على الهواتف أندرويد خمسة فما فوق ، امل تحت فلن تتعطل و لكن فقط لن يتغير اللون

``` java

MyNotification.setColor(Color.RED);

//OR او

MyNotification.setColor(0xFF4527A0);

//OR او

MyNotification.setColor("#FF4527A0");

//OR او

MyNotification.setColor(100,200,150); //rgb

//OR او

MyNotification.setColor(150,200,100,182); //argb


```


### how to show , get notification and cancel one

### كيفية اظهار ، استلام ، إغلاق الاشعار

``` java
// to show
//لإظهار الاشعار

MyNotification.show();

// if setAlertOnce is used and set to true then if a previous notification with same id and channel name is available then it will be replaced .
//اذا فعلت وضع الاشعار قابل للتغيير فلن يظهر صوت و سوف يتم استبدال الاشعاؤ الذي يملك رقم التعريف نفسه بالجديد هذا ، مثلا تغيير نص


//to close
//للاغلاق

MyNotification.cancel(0);

// here we are نحن هن
// canceling a notification with id number = 0
//نغلق اشعار رقمه التعريفي صفر

//لاستلام الأشعار لاغراض أخرى , مثلا وضعه في دالة تشغيل عمل بالخلفية او شيء ما اخر .......
//to receive notification for some reasons

android.app.Notification n = MyNotification.getNotification();

// there we are getting Notification,  so we can use it to another reason instead of showing it , like putting it on startForeground method or something


```

