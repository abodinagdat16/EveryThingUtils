package fragment;
import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.*;
import com.google.android.material.appbar.AppBarLayout;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
public class FragmentView extends FrameLayout {
    public Context context;
    public android.util.AttributeSet aS;
    public FragmentView(Context c) {
        super(c);
        context = c;
        }
        
        public FragmentView(Context c , android.util.AttributeSet as) {
            super(c,as);
            context = c;
            aS = as;
        }
        
        public void setFragment(String name , String tag) {
            
            ContextThemeWrapper ctw = (ContextThemeWrapper)context;
            
            AppCompatActivity aca = (AppCompatActivity)ctw;
            
    
    FragmentManager fm = aca.getSupportFragmentManager();
    
    int id = getId();

            Fragment containerFragment =
                    fm.getFragmentFactory().instantiate(context.getClassLoader(), name);
            containerFragment.onInflate(context, aS, null);
            fm.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(this.getId(), containerFragment, tag)
                    .commitNowAllowingStateLoss();
        
        
        }
    
    
}
