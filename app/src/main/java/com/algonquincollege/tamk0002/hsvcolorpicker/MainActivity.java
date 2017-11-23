package com.algonquincollege.tamk0002.hsvcolorpicker;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;
import java.util.Observer;

import model.HSVModel;

/**
 * HSVModel Color Picker
 * To implement, test and deploy a guess a HSV color picker Android app.
 * Users can change the colorSwatch accordingly with the different seekBars (hue, saturation,
 * and value). There are also set colour buttons at the bottom.
 *
 * @author Christine Tamkican (tamk0002@AlgonquinCollege.com)
 */

public class MainActivity extends Activity implements Observer, SeekBar.OnSeekBarChangeListener {


    private static final String LOG_TAG = "HSV";

    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueSB;
    private TextView mHueTV;
    private TextView mSaturationTV;
    private TextView mValueTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mModel = new HSVModel();
        mModel.setHue(Math.round(HSVModel.MIN_HUE) * 100);
        mModel.setSaturation(Math.round(HSVModel.MIN_SATURATION_VALUE) * 100);
        mModel.setValue(Math.round(HSVModel.MIN_SATURATION_VALUE) * 100);

        mModel.addObserver(this);


        mColorSwatch = findViewById(R.id.colorSwatch);
        mHueSB = findViewById(R.id.hueSB);
        mSaturationSB = findViewById(R.id.saturationSB);
        mValueSB = findViewById(R.id.valueSB);
        mHueTV = findViewById(R.id.hue);
        mSaturationTV = findViewById(R.id.saturation);
        mValueTV = findViewById(R.id.value);


        mHueSB.setMax(Math.round(HSVModel.MAX_HUE));
        mSaturationSB.setMax(Math.round(HSVModel.MAX_SATURATION_VALUE) * 100);
        mValueSB.setMax(Math.round(HSVModel.MAX_SATURATION_VALUE) * 100);


        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);


        this.updateView();

        mColorSwatch.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(getApplicationContext(), "H: " + NumberFormat.getInstance().format(mModel.getHue()) + "\u00B0 \nS: " + NumberFormat.getInstance().format(mModel.getSaturation() * 100) + "% \nV: " + NumberFormat.getInstance().format(mModel.getValue() * 100) + "%", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }


    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if (!fromUser) {
            return;
        }

        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue((float) mHueSB.getProgress());
                mHueTV.setText(getResources().getString(R.string.hueProgress, progress).toUpperCase() + "\u00B0");
                break;

            case R.id.saturationSB:
                mModel.setSaturation((float) mSaturationSB.getProgress() / 100);
                mSaturationTV.setText(getResources().getString(R.string.saturationProgress, progress).toUpperCase() + "%");
                break;

            case R.id.valueSB:
                mModel.setValue((float) mValueSB.getProgress() / 100);
                mValueTV.setText(getResources().getString(R.string.valueProgress, progress).toUpperCase() + "%");
                break;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
        switch (seekBar.getId()) {
            case R.id.hueSB:
                mHueTV.setText(getResources().getString(R.string.hue));
                break;

            case R.id.saturationSB:
                mSaturationTV.setText(getResources().getString(R.string.saturation));
                break;

            case R.id.valueSB:
                mValueTV.setText(getResources().getString(R.string.value));
                break;
        }
    }

    @Override
    public void update(Observable observable, Object data) {
        Log.i(LOG_TAG, "The color (int) is: " + mModel.getColor() + "");

        this.updateView();
    }

    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(mModel.getColor());
    }

    private void updateHueSB() {
        mHueSB.setProgress(Math.round(mModel.getHue()));
    }

    private void updateSaturationSB() {
        mSaturationSB.setProgress(Math.round(mModel.getSaturation() * 100));
    }

    private void updateValueSB() {
        mValueSB.setProgress(Math.round(mModel.getValue() * 100));
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.blackBtn:
                mModel.asBlack();
                break;
            case R.id.redBtn:
                mModel.asRed();
                break;
            case R.id.limeBtn:
                mModel.asLime();
                break;
            case R.id.blueBtn:
                mModel.asBlue();
                break;
            case R.id.yellowBtn:
                mModel.asYellow();
                break;
            case R.id.cyanBtn:
                mModel.asCyan();
                break;
            case R.id.magentaBtn:
                mModel.asMagenta();
                break;
            case R.id.silverBtn:
                mModel.asSilver();
                break;
            case R.id.grayBtn:
                mModel.asGray();
                break;
            case R.id.maroonBtn:
                mModel.asMaroon();
                break;
            case R.id.oliveBtn:
                mModel.asOlive();
                break;
            case R.id.greenBtn:
                mModel.asGreen();
                break;
            case R.id.purpleBtn:
                mModel.asPurple();
                break;
            case R.id.tealBtn:
                mModel.asTeal();
                break;
            case R.id.navyBtn:
                mModel.asNavy();
                break;
            default:
                break;
        }
        Toast.makeText(this, "H: " + NumberFormat.getInstance().format(mModel.getHue()) + "\u00B0 \nS: " + NumberFormat.getInstance().format(mModel.getSaturation() * 100) + "% \nV: " + NumberFormat.getInstance().format(mModel.getValue() * 100) + "%", Toast.LENGTH_SHORT).show();
        updateColorSwatch();

    }


    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    private static final String ABOUT_DIALOG_TAG = "About Dialog";
}
