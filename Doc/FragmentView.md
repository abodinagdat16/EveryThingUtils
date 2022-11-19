## FragmentView
## The Open Source Class 2022 @ArabWare

> how to use it ?

## add a widget in your layout xml file with name fragment.FragmentView

``` xml

<fragment.FragmentView
android:id="@+id/myFragmentView"
android:layout_width="match_parent"
android:layout_height="match_parent"
/>


```

> how to set the fragments ?

## You Can Set And Change The Fragment On Runtime By Calling Its Package + . + Its Name As String

## MyContext is the context like MainActivity.this or MyFragment.getContext() or any thing that can contain view ...

``` java

((fragment.FragmentView)MyContext.findViewById(R.id.myFragmentView)).setFragment("com.package.FragmentName","AnyThingRandomItsTheTag");

```

