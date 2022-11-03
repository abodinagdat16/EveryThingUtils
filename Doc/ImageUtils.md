## Full Guide To Image Utils Class 
### This Class Can Get Info And Edit An Image With Many Options
#### the class codes are easy to write and use


> how to create an ImageUtils? , just pass the Context of activity or fragment or dialog fragment or service or BroadcastReceiver....
``` java

ImageUtils iu = new ImageUtils(YourContext);

```

> note : you can add a listener for progress of loading or editing , also it makes your app fast and without slow running at runtime , add the code between every () of or at last of () params , i wrote some examples , I am currently tired to edit all the old guide and make it for all , the code :
``` java
new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
}
```

> how to set an Image from file ? , YourPath is the file path as string

``` java
try {

iu.setFromFile(new java.io.File(YourPath));

} catch(Throwable e) {

}

//or you can load without slow running + with events
try {
iu.setFromFile(new java.io.File(YourPath),new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});
} catch(Throwable e) {

}

```

> how to set an Image from assets ? , YourAssetsPath is the file path in assets as String like "folder/image.jpg" or "image.jpg"
``` java
try {
iu.setFromAssets(YourAssetsPath);
} catch(Throwable e) {

}

//or you can load without slow running + with events
try {
iu.setFromAssets(YourAssetsPath,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});
} catch(Throwable e) {

}


```

> how to create an image from resources, like R.raw.image or R.drawable.image

``` java

try {
iu.setFromResources(YourResourcesIDAsInteger);
} Throwable e) {
}

//or you can load without running slowly and with events
try {
iu.setFromResources(YourResourceIDAsInteger,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});
} catch(Throwable e) {

}


```

> how to set an Image from resources by name and folder name , both are String values

``` java

try {
iu.setFromResources(FileName,FolderName);
} Throwable e) {
}

//or you can load without running slowly and with events
try {
iu.setFromResources(FileName,FolderName,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});
} catch(Throwable e) {

}

```

> how to set from input stream

``` java
try {

iu.setFromInputStream(YourInputStream);

} catch(Throwable e) {

}

//or you can load without slow running + with events
try {
iu.setFromInputStream(YourInputStream,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});
} catch(Throwable e) {

}





```


> how to convert Image Being Edited Into Input Stream!

``` java

try {

YourInputStream = iu.getInputStream();

} catch(Throwable e) {

}

```

> how to get the bitmap (so you can get the result every time)
Here we will put it into ImageView widget,  just as example

``` java

try {

Imageview1.setImageBitmap(iu.getResult());

} catch(Exception e) {

}

```

> how to get the pixels count or width (pixels units) or height (pixels count)
``` java



try {

int width = iu.getWidthPx();

} catch(Throwable e) {

}

try {

int height = iu.getHeightPx();

} catch(Throwable e) {

}

try {

int pixelsCount = iu.getPixels().size(); //or

int pixelsCount = width*height; //same value as above

} catch(Throwable e) {

}

```

> how to rotate the image with angle between 0-360

``` java

try {

iu.rotate(90);

} catch(Throwable e) {

}



//or with progress + no slow running

try {

iu.rotate(90,new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});

} catch(Throwable e) {

}

```

> how to flip the image horizontally 

``` java

try {

iu.flipHorizontally();

} catch(Throwable e) {

}

//or without running slowly + events

try {

iu.flipHorizontally(new com.android.prime.arab.ware.everythingutils.listeners.BitmapTasks() {
@Override
public void loading() {
//loaded
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error
}
});

} catch(Throwable e) {

}

```

> how to flip the image vertically

``` java

try {

iu.flipVertically();

} catch(Throwable e) {

}

```

> how to set Contrast of the image (0-100)

``` java
try {

iu.setContrast(50);

} catch(Throwable e) {

}


```

> how to set Brightness of the image (0-100)

``` java

try {

iu.setBrightness(50);

} catch(Throwable e) {

}

```

> how to set alpha of the image (0-100) , 0 means full transparent and 100 means normal
``` java

try {

iu.setAlpha(50);

} catch(Throwable e) {

}


```

> how to set filter of the image , currently we support negative and oldBlackWhite 

``` java
try {
iu.setFilter("oldBlackWhite");
} catch(Throwable e) {

}
```

> how to set blur effect to the image , 3 is the value , change it!
``` java
try {
iu.blur(3);
} catch(Throwable e) {

}
```

> how to get the most used color in the image as Integer (number)

``` java
try {
int color = iu.getMostUsedColor();
} catch(Throwable e) {

}

```

> how to get the most used color in the image as string (like #ffffffff)

``` java
try {
String hexColor = iu.getMostUsedColorAsString();
} catch(Throwable e) {

}
```
> how to set width and height (pixels units) , width and height are Integer numbers values

``` java
try {
iu.setWidthAndHeightPx(width,height);
} catch(Throwable e) {

}
```
> how to get all pixels of the image as arraylist of Integers which are the color values
``` java
try {
ArrayList<Integer> al = new ArrayList<>();

al = iu.getPixels();

} catch(Throwable e) {

}

```

> how to get one pixel color by coordinators X and Y

``` java

try {

int color = iu.getColorAt(x,y):

String colorHex = iu.getColorStringAt(x,y);

} catch(Throwable e) {

}

```

> how to change a color to another (all the image) , color1 and color2 are integers numbers or String hex colors like #ffffffff or 0xFFFFFFFF

``` java
try {

iu.changeColor(color1,color2);

} catch(Throwable e) {

}

```

> how to change only one pixel color by coordinators X and Y

``` java
try {

iu.setPixel(x,y,color);
} catch(Throwable e) {

}

/*color is an Integer only here but you can set the value from String by using android.graphics.Color.parseColor(YourColorAsString)
*/
```

> how to get a color count , the count of pixels which are are the same color you chose , it accepts String and Integer colors types

``` java

try {

int countOfAColor = iu.getColorCount(YourColor);

} catch(Throwable e) {

}

```

> how to crop the image , it uses two X values (from x to x ) and two Y values (from y to y) , coordinators data...

``` java

try {

iu.crop(fromx,toX,fromY,toY);

} catch(Throwable e) {

}

```

> how to hide part of bitmap (making transparent) , same as above , fromX toX and fromY toY

``` java

try {

iu.hide(fromX,toX,fromY,toY);

} catch(Throwable e) {

}

```

> how to convert rgb color to Integer color or String color ...

``` java

try {

int color = ImageUtils.getColorFromRGB(red,green,blue);

String colorHex = ImageUtils.getColorFromRGB(red,green,blue);

} catch(Throwable e) {

}

```
> how to get the history bitmap,  like the first bitmap before editing or previous etc ... it will help to add undo redo or previous next buttons ... something

``` java


//to get the count of history changes
int count = iu.getHistoryEditingsCount();
//to get the bitmap by position (position starts from 0 so if it is the 4th image (example) it will be 3)
imageview1.setImageBitmap(iu.getHistoryImageAt(3));
//how to delete the history
iu.deleteHistory();

```

> how to convert bitmap to String and vice versa 

``` java

try {

YourString = iu.getBitmap();

} catch(Throwable e) {

}


//to set the value back again

try {

iu.setBitmap(YourString);

} catch(Throwable e) {

}

```

> how to add water marks on the image ..

These are the supported modes currently :
1-Center

2-TopCenter

3-TopRight

4-TopLeft

5-BottomCenter

6-BottomRight

7-BottomLeft

8-CenterRight

9-CenterLeft

example :

``` java
try {

iu.addWaterMarkCenterRight(YourBitmapOfWaterMark,width,height);

} catch(Throwable e) {

}

// and you can add the events loading as any one of functions...

```

> how to get bitmap from file/assets/resources without creating ImageUtils 
``` java
try {
YourBitmap = ImageUtils.getBitmapFromFile(new java.io.File(YourFilePath));
YourBitmap2 = ImageUtils.getBitmapFromAssets(YourContext,YourAssetsPath);
YourBitmap3 = ImageUtils.getBitmapFromResource(YourContext,YourInt); //YourInt : like R.raw.image or R.drawable.logo
YourBotmap4 = ImageUtils.getBitmapFromResource(YourContext , FolderName , FileName);

} catch(Throwable e) {
}

```

> how to get resize an bitmap without creating the ImageUtils

``` java
try {
YourBitmap = ImageUtils.changeWidthAndHeight(YourBitmap,width,height);
} catch(Throwable e) {
}
```


> how to get bitmap of a view

``` java

Bitmap b = ImageUtils.getBitmap(YourViewName);

//or

iu.setBitmap(YourViewName);

//it can also work for ImageView (only its image) but if you want the ImageView itself with its content image , you should do (View)yourImageView and not yourImageView directly

```

> how to create a Bitmap from zero
``` java


YourBitmap = ImageUtils.createNewBitmap(widthPx,heightPx,ColorAsInteger);

YourBitmap = ImageUtils.createNewBitmap(widthPx,heightPx,ColorAsString);

```

> how to recreate the current ImageUtils instance as new Bitmap
``` java


iu.recreateOrSetNewBitmap(widthPx,heightPx,ColorAsInteger);

iu.recreateOrSetNewBitmap(widthPx,heightPx,ColorAsString);

```
> how to set bitmap

``` java

iu.setBitmap(YourBitmap);

```

> how to erase color

``` java

//examples

iu.erase(0xFF4527A0); //Integer

iu.erase("#ff4527a0"); //String

iu.erase(200,100,255); //RGB (red , green , blue , 0-255)

iu.erase(100,200,254,255); //ARGB (alpha , red , green , blue , 0-255)

```

> converting rgb to Integer color or String color

``` java

int rgbAsInteger = ImageUtils.getColorFromRGB(red,green,blue);
int argbAsInteger = ImageUtils.getColorFromRGB(alpha,red,green,blue);
String rgbAsString = ImageUtils.getStringColorFromRGB(red,green,blue);
String argbAsString = ImageUtils.getStringColorFromRGB(alpha,red,green,blue);


```


