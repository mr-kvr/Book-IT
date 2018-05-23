package kvr.bookit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;



/**
 * Created by Kasi_Visu on 4/8/2018.
 */

public class SplashScreen extends Activity {

    private static final int SPLASH_DURATION = 3000; //3 second
    private Handler myHandler;
    private boolean backbtnPress;
    ImageView img;
    TextView tv;
    Context contex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);


        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        Animation anim1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_from_left);

        img = (ImageView) findViewById(R.id.splashScreenImage);
        img.setAnimation(anim);

        tv = (TextView) findViewById(R.id.splashAppName);
        tv.setAnimation(anim1);

        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationEnd(Animation animation) {


                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(intent);

                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}


