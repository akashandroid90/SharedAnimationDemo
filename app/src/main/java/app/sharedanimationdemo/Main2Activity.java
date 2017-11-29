package app.sharedanimationdemo;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {
    Fragment fragmentOne;
    Fragment fragmentTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void demo(View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Inflate transitions to apply

            fragmentOne=new FragmentOne();


            // Add second fragment by replacing first
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragmentOne);
            // Apply the transaction
            ft.commit();
        }
        else {
            // Code to run on older devices
        }
    }

    public void udpate(View view){
        Transition changeTransform = TransitionInflater.from(this).
                inflateTransition(android.R.transition.move);
        Transition explodeTransform = TransitionInflater.from(this).
                inflateTransition(android.R.transition.explode);

        // Setup exit transition on first fragment
        fragmentOne.setSharedElementReturnTransition(changeTransform);
        fragmentOne.setExitTransition(explodeTransform);
        fragmentTwo=new FragmentTwo();
        // Setup enter transition on second fragment
        fragmentTwo.setSharedElementEnterTransition(changeTransform);
        fragmentTwo.setEnterTransition(explodeTransform);
// Add second fragment by replacing first
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmentTwo)
                .addToBackStack("transaction")
                .addSharedElement(view, "image");
        // Apply the transaction
        ft.commit();
    }
}
