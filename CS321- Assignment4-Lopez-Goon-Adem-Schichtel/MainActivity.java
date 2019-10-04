package com.tutsplus.opengltutorial;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.tutsplus.opengltutorial.view.ColorPanelView;
import com.tutsplus.opengltutorial.view.ColorPickerView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 *  Creates and runs the OpenGL ES for displaying the 3d cake model
 *  and the options used to customize it
 *  Date: 4/22/2018
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ColorPickerView.OnColorChangedListener {

    private GLSurfaceView mySurfaceView;
    private ViewFlipper mViewFlipper;
    private Torus torus;

    //BUTTONS FOR PAGE 1
    private int tiers;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;

    //BUTTONS FOR PAGE 2
    private String size;
    private Button b2_1;
    private Button b2_2;
    private Button b2_3;
    private Button b2_4;

    //BUTTONS FOR PAGE 3
    private Button b3_1;
    private Button b3_2;
    private Button b3_3;
    private Button b3_4;

    //BUTTONS FOR PAGE 4
    private Button b4_1;
    private Button b4_2;
    private Button b4_3;
    private Button b4_4;

    //BUTTONS FOR PAGE 4
    private Button b5_1;
    private Button b5_2;
    private Button b5_3;
    private Button b5_4;

    int tierNum;
    String [] sizes;

    //EVERYTHING FOR PAGE 5
    ColorPanelView clrPanel;
    ColorPickerView clrPicker;
    Button clrButton;
    int colorPicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the system supports OpenGL ES 2.0.
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

        mySurfaceView = (GLSurfaceView) findViewById(R.id.my_surface_view);
        if (supportsEs2) {
            mySurfaceView.setEGLContextClientVersion(2);
        }

        mViewFlipper = (ViewFlipper)findViewById(R.id.myViewFlipper);
        // Declare in and out animations and load them using AnimationUtils class
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        // set the animation type to ViewFlipper
        mViewFlipper.setInAnimation(in);
        mViewFlipper.setOutAnimation(out);

        sizes = new String [4];

        //SETS UP OPENGL VIEW FOR CAKE
        mySurfaceView.setRenderer(new GLSurfaceView.Renderer() {

            @Override
            public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
                mySurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
                torus = new Torus(getApplicationContext());
            }

            @Override
            public void onSurfaceChanged(GL10 gl10, int width, int height) {
                GLES20.glViewport(0,0, width, height);
            }

            @Override
            public void onDrawFrame(GL10 gl10) {
                if(tiers != 0){
                    switch (size){
                        case "16 inch":
                            torus.draw(tierNum, 16, colorPicked);
                            break;
                        case "14 inch":
                            torus.draw(tierNum, 14, colorPicked);
                            break;
                        case "12 inch":
                            torus.draw(tierNum, 12, colorPicked);
                            break;
                        case "10 inch":
                            torus.draw(tierNum, 10, colorPicked);
                            break;
                        case "8 inch":
                            torus.draw(tierNum, 8, colorPicked);
                            break;
                        case "6 inch":
                            torus.draw(tierNum, 6, colorPicked);
                            break;
                        case "4 inch":
                            torus.draw(tierNum, 4, colorPicked);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        //WIDGETS FOR PAGE 1 OF VIEW FLIPPER
        tiers = 1;
        b1 = (Button)findViewById(R.id.button_id1);
        b1.setOnClickListener(this);
        b2 = (Button)findViewById(R.id.button_id2);
        b2.setOnClickListener(this);
        b3 = (Button)findViewById(R.id.button_id3);
        b3.setOnClickListener(this);
        b4 = (Button)findViewById(R.id.button_id4);
        b4.setOnClickListener(this);

        //WIDGETS FOR PAGE 2 OF VIEW FLIPPER
        size = "";
        b2_1 = (Button)findViewById(R.id.button2_id1);
        b2_1.setOnClickListener(this);
        b2_2 = (Button)findViewById(R.id.button2_id2);
        b2_2.setOnClickListener(this);
        b2_3 = (Button)findViewById(R.id.button2_id3);
        b2_3.setOnClickListener(this);
        b2_4 = (Button)findViewById(R.id.button2_id4);
        b2_4.setOnClickListener(this);

        //WIDGETS FOR PAGE 3 OF VIEW FLIPPER
        b3_1 = (Button)findViewById(R.id.button3_id1);
        b3_1.setOnClickListener(this);
        b3_2 = (Button)findViewById(R.id.button3_id2);
        b3_2.setOnClickListener(this);
        b3_3 = (Button)findViewById(R.id.button3_id3);
        b3_3.setOnClickListener(this);
        b3_4 = (Button)findViewById(R.id.button3_id4);
        b3_4.setOnClickListener(this);

        //WIDGETS FOR PAGE 4 OF VIEW FLIPPER
        b4_1 = (Button)findViewById(R.id.button4_id1);
        b4_1.setOnClickListener(this);
        b4_2 = (Button)findViewById(R.id.button4_id2);
        b4_2.setOnClickListener(this);
        b4_3 = (Button)findViewById(R.id.button4_id3);
        b4_3.setOnClickListener(this);
        b4_4 = (Button)findViewById(R.id.button4_id4);
        b4_4.setOnClickListener(this);

        //WIDGETS FOR PAGE 5 OF VIEW FLIPPER
        b5_1 = (Button)findViewById(R.id.button5_id1);
        b5_1.setOnClickListener(this);
        b5_2 = (Button)findViewById(R.id.button5_id2);
        b5_2.setOnClickListener(this);
        b5_3 = (Button)findViewById(R.id.button5_id3);
        b5_3.setOnClickListener(this);
        b5_4 = (Button)findViewById(R.id.button5_id4);
        b5_4.setOnClickListener(this);

        //WIDGETS FOR PAGE 6 OF VIEW FLIPPER
        clrPanel = (ColorPanelView)findViewById(R.id.colorpanel);   //current color box
        clrPicker = (ColorPickerView)findViewById(R.id.colorpicker);    //the view
        clrPicker.setOnColorChangedListener(this);
        clrButton = (Button)findViewById(R.id.colorButton);
        colorPicked = -1;
    }

    @Override
    public void onColorChanged(int newColor) {
        clrPanel.setColor(newColor);
    }

    @Override
    public void onClick(View v) {
        // show the next view of ViewFlipper
        switch (v.getId()) {

            case R.id.button_id1:
                mViewFlipper.showNext();
                break;

            case R.id.button_id2:
                tiers = 2;
                mViewFlipper.showNext();
                break;

            case R.id.button_id3:
                tiers = 3;
                mViewFlipper.showNext();
                break;

            case R.id.button_id4:
                tiers = 4;
                mViewFlipper.showNext();
                break;

            case R.id.button2_id1:
                tierNum = 1;
                size = "16 inch";
                sizes[0] = "16 inch";
                if(tiers == 1)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button2_id2:
                tierNum = 1;
                size = "14 inch";
                sizes[0] = "14 inch";
                b3_1.setEnabled(false);  //14
                if(tiers == 1)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button2_id3:
                tierNum = 1;
                size = "12 inch";
                sizes[0] = "12 inch";
                b3_1.setEnabled(false);  //14
                b3_2.setEnabled(false);  //12
                b4_1.setEnabled(false);  //12
                if(tiers == 1)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button2_id4:
                tierNum = 1;
                size = "10 inch";
                sizes[0] = "10 inch";
                b3_1.setEnabled(false);  //14
                b3_2.setEnabled(false);  //12
                b3_3.setEnabled(false);  //10
                b4_1.setEnabled(false);  //12
                b4_2.setEnabled(false);  //10
                b5_1.setEnabled(false);  //10
                if(tiers == 1)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button3_id1:
                tierNum = 2;
                size = "14 inch";
                sizes[1] = "14 inch";
                if(tiers == 2)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button3_id2:
                tierNum = 2;
                size = "12 inch";
                sizes[1] = "12 inch";
                b4_1.setEnabled(false);  //12
                if(tiers == 2)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button3_id3:
                tierNum = 2;
                size = "10 inch";
                sizes[1] = "10 inch";
                b4_1.setEnabled(false);  //12
                b4_2.setEnabled(false);  //10
                b5_1.setEnabled(false);  //10
                if(tiers == 2)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button3_id4:
                tierNum = 2;
                size = "8 inch";
                sizes[1] = "8 inch";
                b4_1.setEnabled(false);  //12
                b4_2.setEnabled(false);  //10
                b4_3.setEnabled(false);  //8
                b5_1.setEnabled(false);  //10
                b5_2.setEnabled(false);  //8
                if(tiers == 2)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button4_id1:
                tierNum = 3;
                size = "12 inch";
                sizes[2] = "12 inch";
                if(tiers == 3)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button4_id2:
                tierNum = 3;
                size = "10 inch";
                sizes[2] = "10 inch";
                b5_1.setEnabled(false);  //10
                if(tiers == 3)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button4_id3:
                tierNum = 3;
                size = "8 inch";
                sizes[2] = "8 inch";
                b5_1.setEnabled(false);  //10
                b5_2.setEnabled(false);  //8
                if(tiers == 3)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button4_id4:
                tierNum = 3;
                size = "6 inch";
                sizes[2] = "6 inch";
                b5_1.setEnabled(false);  //10
                b5_2.setEnabled(false);  //8
                b5_3.setEnabled(false);  //6
                if(tiers == 3)
                    mViewFlipper.setDisplayedChild(mViewFlipper.indexOfChild(findViewById(R.id.flipThree)));
                else
                    mViewFlipper.showNext();
                break;

            case R.id.button5_id1:
                tierNum = 4;
                size = "10 inch";
                sizes[3] = "10 inch";
                    mViewFlipper.showNext();
                break;

            case R.id.button5_id2:
                tierNum = 4;
                size = "8 inch";
                sizes[3] = "8 inch";
                    mViewFlipper.showNext();
                break;

            case R.id.button5_id3:
                tierNum = 4;
                size = "6 inch";
                sizes[3] = "6 inch";
                    mViewFlipper.showNext();
                break;

            case R.id.button5_id4:
                tierNum = 4;
                size = "4 inch";
                sizes[3] = "4 inch";
                mViewFlipper.showNext();
                break;

            case R.id.colorButton:
                tierNum = -1;
                colorPicked = clrPanel.getColor();
                //change activity
                break;

            default:
                break;
        }

        mySurfaceView.requestRender();
    }
}
