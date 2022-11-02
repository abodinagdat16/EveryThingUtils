## The Open Source Pdf Utils
### this class can preview , edit and create pdf files
#### it is easy to use ... very easy!

> first , we will learn about pdf previewing

``` java
//you will create a PdfUtils field in your class
private PdfUtils pu;

//you will then initialize it in your Context class
pu = new PdfUtils(YourContext); //YourContext is the context like MainActivity.this or FragmentName.this.getContext()

//if you want to load from assets
pu.setFromAssets(YourStringValueOfAssetsFileNameOrFilePath,new PdfUtils.PdfLoading() {
@Override
public void done() {
//done
}
@Override
public void loading() {
//loading
}
@Override
public void error(String errorDetails) {
//get the errorDetails String
}
});

//if you want from a file path then

pu.setFromFile(new java.io.File(YourFilePathString),new PdfUtils.PdfLoading() {
@Override
public void done() {
//done
}
@Override
public void loading() {
//loading
}
@Override
public void error(String errorDetails) {
//get the errorDetails String
}
});

//to set the pdf from InputStream (No Storage Permissions Required)

pu.setFromInputStream(YourInputStream,new PdfUtils.PdfLoading() {
@Override
public void done() {
//done
}
@Override
public void loading() {
//loading
}
@Override
public void error(String errorDetails) {
//get the errorDetails String
}
});

//if you want from resources
pu.setFromResources(R.raw.YourPdfFileNameInRawResourceFolder,new PdfUtils.PdfLoading() {
@Override
public void done() {
//done
}
@Override
public void loading() {
//loading
}
@Override
public void error(String errorDetails) {
//get the errorDetails String
}
});

//if you want to load from resource as string and not integer number (R + something)

pu.setFromResources(StringValueOfFolderName,StringValueOfFileName,new PdfUtils.PdfLoading() {
@Override
public void done() {
//done
}
@Override
public void loading() {
//loading
}
@Override
public void error(String errorDetails) {
//get the errorDetails String
}
});

//if you want to load the bitmap of a page
//pages start from 0 and not 1
//0 = 1 , 1 = 2 , 2 = 3 .....
pu.getPage((int)0);

//example about that
imageview1.setImageBitmap(pu.getPage((int)0));
//example two about that
Bitmap b = pu.getPage((int)0);


//if you want to get the pages as list of bitmaps....
YourArrayListOfBitmaps = pu.getPages();

//to get the pages count
int pagesCount = pu.getPagesCount();
//to get a page width or height
int width = pu.getPageWidth((int)0); // 0 is the page position
int height = pu.getPageHeight((int)0);

//to compress a pdf
//YourPercent , from 0% to 100%
//YourPath is a string variable
pu.compressPdf(YourPercent,YourPath,new PdfUtils() {
@Override
public void done() {
//done
}
@Override
public void loading() {
//loading
}
@Override
public void error(String error) {
//error details
}
});

```

> pdf editor and creator !!

``` java

//like the previous one , you also will create a field and initialize it

private PdfUtils.PdfCreator pc;

//initialize

pc = new PdfUtils.PdfCreator(YourContext,WhereToSave); /*as the previous, the context is like MainActivity.this or Fragment.this.getActivity() .... WhereToSave is a String variable that will contains where to save pdf and as what name ? like storage/emulated/0/Download/Islam.pdf*/


//you will then have many ways to add pages ..

//you can edit a pdf file

pc.setPdf(YourPdfUtils); //use PdfUtils , and put it instead of YourPdfUtils , it is just to load the pdf you want to edit

//or

pc.addPdf(YourPdfUtils); //if you want to merge a pre-prepared pdf file with your ongoing pdf creating task , like merging images with pdf.

pc.addPdfAtFirst(YourPdfUtils); //as the previous but the pdf pages will be at first

//if you want to add an image.
//the image should bitmap
//to convert an image to bitmap SEE ImageUtils class 

pc.addPage(YourBitmap);

//to add at specific position

pc.addPageAt(YourBitmap,YourPosition); //again...the numbers here start from zero ... 0 means at first , etc...

//if you want to set a page (change a page into another image ...

pc.setPage(YourBitmap,YourPosition);

//to remove a page at a specific position

pc.removePage(YourPosition);

//NOTE : YourPosition is an Integer Number
so if you want to use the position as double or any thing else

((int) YourPosition) instead of directly putting YourNumberVariable as YourPosition ...


//if you have list of bitmaps ...

pc.setBitmaps(YourBitmapArrayList);

pc.addBitmapsAtLast(YourBitmapArrayList);

pc.addBitmapsAtFirst(YourBitmapArrayList);



//to save the pdf

pc.save(new PdfUtils.PdfLoading() {
@Override
public void done() {
//done
}
@Override
public void loading() {
//loading
}
@Override
public void error(String errorDetails) {
//get the errorDetails String
}
});

/*other things are the same as PdfUtils ..
 you can get pages count ... page bitmap .*/
 
 imageview1.setImageBitmap(pu.getPage((int)YourNumber);
 
 
 
 
```
