## DialogUtils

## The Open Source Class 2022 @ArabWare

## You Can Show Customizable Dialog With Custom Color , Width , Height , Gravity and more ...

## First of all , you will create a DialogUtils variable in your projects
## let's say its name is myDialog

## how to initialize (instance) it ?

## MainActivity.this ==> My Context , if it is a fragment then MyFragment.this.getContext() or MyFragment.this.getActivity()

``` java

myDialog = new DialogUtils(MainActivity.this);

```

## how to set the layout ... by xml file in your res folder in layout folder

``` java

myDialog.setView(R.layout.MyLayout);

```

## how to set the layout by a view created by codes

``` java

myDialog.setView(MyView);

```

## how to set gravity ?

``` java

// center

myDialog.setGravity(android.view.Gravity.CENTER);

// right

myDialog.setGravity(android.view.Gravity.RIGHT);

//left

myDialog.setGravity(android.view.Gravity.LEFT);

// top

myDialog.setGravity(android.view.Gravity.TOP);

// bottom

myDialog.setGravity(android.view.Gravity.BOTTOM);

// IF YOU WANT TO MIX THEM !

//lets say center horizontal + bottom

myDialog.setGravity(android.view.Gravity.BOTTOM | android.view.Gravity.CENTER_HORIZONTAL);

```

## change width and height (pixels unit)
## CALL IT AFTER SHOWING THE DIALOG !!!!!!

``` java

try {

myDialog.setWidthAndHeight(500,500);

} catch(Throwable e) {

throw new RuntimeException(android.util.Log.getStackTraceString(e));

}

```

### How To Change The Color Of Dialog Background So You Can Set Corners to its view

``` java

myDialog.setBackgroundColor(Color.TRANSPARENT); // TRANSPARENT COLOR !! VERY USEFUL

// or

myDialog.setBackgroundColor(0xFF4527A0); // Integer color

// or

myDialog.setBackgroundColor("#ffffffaa"); // String color

// or

myDialog.setBackgroundColor(red,green,blue); // rgb color

// or

myDialog.setBackgroundColor(alpha,red,green,blue); // argb color


```

## How To Change Dim ( blur effect out of Dialog when showing )

``` java

myDialog.setDim(0); // no effect if 0

```

## how to show the Dialog

``` java

myDialog.show();

```

## how to close the Dialog

``` java

myDialog.dismiss();

```

## how to change the Dialog cancel-able property

``` java

myDialog.setCancelable(true); // or change true to false to make it un-cancelable

```

## how to change the Dialog cancel-able property when touching outside of Dialog

``` java

myDialog.setCancelableOnTouchOutSide(true); // or change true to false to make it un-cancel-able

```

## how to access the views (widgets) inside the custom Dialog
## let's say our view id is myView
## let's say our view type is TextView
## let's say we want to use setText on that myView ...

``` java

myDialog.getView(R.id.myView,TextView.class).setText("Thanks To Allah");

```

## how to check if it is showing or no

``` java

if(myDialog.isShowing()) {

// yes it is 

} else {

// no it is not

}

```


