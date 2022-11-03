## The Open Source File Utils Class
### A Class that manage the user storage (internal and external) , with many features
#### Import These !!! , com.android.prime.arab.ware.everythingutils.listeners.*
com.android.prime.arab.ware.everythingutils.ArabWareFileManager

> first , we will learn how to create and initialize and instance the class

``` java

//first you will add a field in your own Class

private ArabWareFileManager awfm;

//second , now you can define(instance) it anywhere
// I an Very Very Very Aware Of Something , Always Try to use the third or forth one , Context is needed on many functions in this class

awfm = new ArabWareFileManager(); //no parameters
awfm = new ArabWareFileManager(File_Path_As_String); //String Parameter
awfm = new ArabWareFileManager(YourContext); //Context parameter
awfm = new ArabWareFileManager(YourPathAsString,YourContext); //String , Context parameters

```

> how to create a folder?

``` java

awfm.createFolder(YourFolderNameOnly,new CreateNewTask() {
@Override
public void done() {
//done , what to do?
}
@Override
public void error(String message) {
//error , what to do , message is the string of error message!
}
});

//note , YourFolderNameOnly like MyFolder , because we have already set when we want to create the folder in the instance of this class


```

> how to create a file?
``` java

awfm.createFile(YourFileNameOnly,new CreateNewTask() {
@Override
public void done() {
//done , what to do?
}
@Override
public void error(String message) {
//error , what to do , message is the string of error message!
}
});

//note , YourFileNameOnly like MyFile.txt , because we have already set when we want to create the folder in the instance of this class


```
> how to rename a file or folder

``` java

awfm.rename(Your_New_File_Or_Folder_Name_Only,new RenameTask() {
@Override
public void done() {
//done , what to do?
}

@Override
public void progress(String fileOrFolderPath) {
//progress ...
}

@Override
public void error(ArrayList<String> errors) {
//error , or errors(in future I will support multi renaming , this has nothing to do with)
}

});

```

> how to move file or folder to folder?

``` java

awfm.move(WHERE,new FileTasks() {
@Override
public void loading() {
//loading
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

```

> how to copy file or folder to folder
``` java

awfm.copy(WHERE,new CopyTask() {
@Override
public void loading() {
//loading
}
@Override
public void progress(String fileOrFolderName) {
//progress
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error , not by files or folders but the task
}
@Override
public void error(ArrayList<String> errors) {
//errors , by the files or folders
}
});
```
> how to delete file or folder
``` java

awfm.delete(new DeleteTask() {
@Override
public void loading() {
//loading
}
@Override
public void progress(String fileOrFolderName) {
//progress
}
@Override
public void done() {
//done
}
@Override
public void error(String error) {
//error , not by files or folders but the task
}
@Override
public void error(ArrayList<String> errors) {
//errors , by the files or folders
}
});
```
> how to change the last edit date

``` java

awfm.changeModifyDate((int)yearNumber,(int)month Number,(int)dayNumber,(int)hourNumber,(int)minuteNumber,(int)secondNumber);

```

> how to change access of a file (owner,group and other) access , it needs ROOT ACCESS
``` java
awfm.setAccess((int)YourAccessNumberData); //search Google to know why access is as number and what is it , file access permissions android
```

> how to share a file , note : file type should be mime type like pdf is "application/pdf" , keep it null or "" if you want all files types

``` java

awfm.share(YourContext,YourType,YourMessage);

```

> how to write a file (edit its raw text content)

``` java

//to rewrite the file (deletes previous Text!)

awfm.rewrite(YourStringText);

//to add text to old text

awfm.write(YourStringText);

```

> how to request storage permissions (READ_EXTERNAL_STORAGE , WRITE_EXTERNAL_STORAGE , MANAGE_EXTERNAL_STORAGE)

``` java

awfm.checkStoragePermissions(YourContext);

```


> how to get size of the file or folder

``` java

//in bytes unit

long size = awfm.size();

//in kilobytes unit

long size = awfm.sizeInKB();

//in megabytes unit

long size = awfm.sizeInMB();

//in Gigabytes unit

long size = awfm.sizeInGB();

//to display it in a String

YourString = "file or folder size is : " + size;

//if you want to get size of a specific file type

just add Custom at end of method name

long sizeOfMp3 = awfm.sizeCustom("mp3");

```

> how to get files or folders or both count in a folder

``` java

int files = awfm.files(); // only files count in a folder

int full_files = awfm.full_files(); // all files count in a folder and it's subfolders 

int folders = awfm.folders(); //same as previous

int full_folders = awfm.full_folders(); //same as previous 

int content = awfm.content(); //files and folders count in the folder

int full_content = awfm.full_content(); //same as above but also includes subfolders data

```

> file checking functions ... (with examples...)

``` java

awfm.isFile() //if the path is file
awfm.isFolder() //if the path is folder
awfm.isReadable() //if the path is readable to your app
awfm.isWriteable() //if the path is writable to your app
awfm.isExecutable() //if the path is executable to your app
awfm.isEmpty() //if the file or folder is empty (like 0 bytes)
awfm.isHidden() //if the file or folder is hidden
awfm.isSystem() //if the file or folder is a system thing
awfm.isPrimary() //if the file or folder exists at user internal device storage
awfm.isRemovalSDCard() //if the file or folder exists at external memory card storage
awfm.exists() //if the path exists
awfm.isFullAccessFiles(YourContext) // checks if device can manage all the storage files , if android 11 and up then that means NO MANAGE_EXTERNAL_STORAGE permission is granted


//example

if(awfm.isFolder()) {
//what to do ...
}

```


> String functions with examples

``` java

awfm.getName() // to get the name of file or folder

awfm.getParent() // to get the path without file or folder name like sdcard/file will be sdcard ... the parent !

awfm.getFolder() // to get the folder name that contains the file or folder

awfm.getText() // to get the raw text content of a file

awfm.getPrimaryStorage() // to get the user device storage path , it needs Context parameter in ArabWareFileManager instance

awfm.getRemovalSDCardStorage() // to get the external memory card storage path , it needs Context parameter in ArabWareFileManager instance

awfm.getNameOnlyOfFile() // to get name of file without type like quran.pdf will be qumran

```

> how get list of files or folders .... or both

``` java
//let's say you have ArrayList of String , ArrayList<String> , let's say its name is list

//there are 12 types of getting list ... 6 not sorted and 6 sorted

awfm.files_list(isHiddenBoolean,TypeString) // files list in a folder

awfm.full_files_list(isHiddenBoolean,TypeString) // files list in a folder and it's subfolders

awfm.folders_list(isHiddenBoolean) // folders list in a folder

awfm.full_folders_list(isHiddenBoolean) // folders list in a folder and it's subfolders

awfm.content_list(isHiddenBoolean,TypeString) // files and folders list in a folder

awfm.full_content_list(isHiddenBoolean,TypeString) // files and folders list in a folder and it's subfolders


//EXAMPLE !!
YourArrayList = awfm.files_list(false,""); //not only hidden + all files types
YourArrayList = awfm.files_list(true,"mp3") //only hidden + only mp3 ..


// NOW , WE WILL TALK about sorting
//there are 6 types of sorting
/*
1 - BY_NAME_UP

2 - BY_NAME_DOWN

3 - BY_DATE_UP

4 - BY_DATE_DOWN

5 - BY_SIZE_UP 

6 - BY_SIZE_DOWN
*/

//how to sort then???

//just add the ArabWareFileManager + . + one of the 6 sorting methods
to parameters of arraylist functions


//example

YourArrayList = awfm.files_list(false,"",ArabWareFileManager.BY_NAME_UP);

```












