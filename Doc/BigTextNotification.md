## Big Text Notification

## الاشعارات كبيرة النص

![click me to see how it looks ، انقر هنا لمعرفة كيف تبدو](https://github.com/abodinagdat16/EveryThingUtils/blob/master/Doc/Screenshot_20221121-155937_ArabWareFileManager.jpg?raw=true)

![click me to see how it looks ، انقر هنا لمعرفة كيف تبدو](https://github.com/abodinagdat16/EveryThingUtils/blob/master/Doc/Screenshot_20221121-155939_ArabWareFileManager.jpg?raw=true)


## So let's explain every item 

## حسنا ، دعنا نشرح كل عنصر

- app name , provided by system
- اسم التطبيق ، بواسطة النظام
- title , by you
- العنوان ، بواسطتك
- long title , by you
- عنوان طويل ، بواسطتك
- description , by you
- الوصف ، بواسطتك
- long description , by you
- وصف طويل ، بواسطتك
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

MyNotification = new NormalNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT);

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

MyNotification = new NormalNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT , NotificationSound);

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

MyNotification = new NormalNotification(YourContext , YourChannelName , YourSystemDescription , (int)YourNotificationIdNumber , android.app.NotificationManager.IMPORTANCE_DEFAULT , new NotificationSound(YourContext).setSound(Uri.parse("url رابط")).getUri());

```


#### how to set title ?

#### كيفية تعيين العنوان ؟

``` java
MyNotification.setTitle(YourString);
```
**replace YourString with your String value/variable of title**

**استبدل الكلمة**

**YourString**

**بالعنوان ، متغير او قيمة نصية**

## how to set long title ? كيفية تعيين العنوان الطويل

``` java
MyNotification.setBigTitle(YourString);
```
**replace YourString with your String value of long title**

**استبدل الكلمة**

**YourString**

**بالعنوان الطويل ، متغير او قيمة نصية**



#### how to set description ?

#### كيفية تعيين الوصف ؟

``` java

MyNotification.setDescription(YourString);

```

**replace YourString with your String value/variable of description**

**استبدل الكلمة**

**YourString**

**بالوصف ، متغير او قيمة نصية**

## how to set long description ? كيفية تعيين الوصف الطويل

``` java

MyNotification.setBigText(YourString);

```
 **replace YourString with your String value of long description**

**استبدل الكلمة**

**YourString**

**بالوصف الطويل ، متغير او قيمة نصية**


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

## تعيين لون العنوان ، لن تؤثر بشيء الا على الهواتف أندرويد خمسة فما فوق ، اما تحت فلن تتعطل و لكن فقط لن يتغير اللون

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


