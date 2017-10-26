package model;

import android.graphics.Color;

import java.util.Observable;

/**
 * Created by Christine on 2017-10-25.
 */

public class HSVModel extends Observable {

    public static final float MAX_HUE = 359;
    public static final float MIN_HUE = 0;
    public static final float MAX_SATURATION_VALUE = 1;
    public static final float MIN_SATURATION_VALUE = 0;


    private float hue;
    private float saturation;
    private float value;

    public HSVModel() {
        this(MAX_HUE, MAX_SATURATION_VALUE, MAX_SATURATION_VALUE);
    }

    public HSVModel(Float hue, Float saturation, Float value) {
        super();

        this.hue = hue;
        this.saturation = saturation;
        this.value = value;
    }


    public void asBlack() {
        this.setHSV(MIN_HUE, MIN_SATURATION_VALUE, MIN_SATURATION_VALUE);
    }

    public void asRed() {
        this.setHSV(MIN_HUE, MAX_SATURATION_VALUE, MAX_SATURATION_VALUE);
    }

    public void asLime() {
        this.setHSV(120, MAX_SATURATION_VALUE, MAX_SATURATION_VALUE);
    }

    public void asBlue() {
        this.setHSV(240, MAX_SATURATION_VALUE, MAX_SATURATION_VALUE);
    }

    public void asYellow() {
        this.setHSV(60, MAX_SATURATION_VALUE, MAX_SATURATION_VALUE);
    }

    public void asCyan() {
        this.setHSV(180, MAX_SATURATION_VALUE, MAX_SATURATION_VALUE);
    }

    public void asMagenta() {
        this.setHSV(300, MAX_SATURATION_VALUE, MAX_SATURATION_VALUE);
    }

    public void asSilver() {
        this.setHSV(MIN_HUE, MIN_SATURATION_VALUE, .75f);
    }

    public void asGray() {
        this.setHSV(MIN_HUE, MIN_SATURATION_VALUE, .5f);
    }

    public void asMaroon() {
        this.setHSV(MIN_HUE, MAX_SATURATION_VALUE, .5f);
    }

    public void asOlive() {
        this.setHSV(60, MAX_SATURATION_VALUE, .5f);
    }

    public void asGreen() {
        this.setHSV(120, MAX_SATURATION_VALUE, .5f);
    }

    public void asPurple() {
        this.setHSV(300, MAX_SATURATION_VALUE, .5f);
    }

    public void asTeal() {
        this.setHSV(180, MAX_SATURATION_VALUE, .5f);
    }

    public void asNavy() {
        this.setHSV(240, MAX_SATURATION_VALUE, .5f);
    }


    //GETTERS
    public float getHue() {
        return hue;
    }

    public float getSaturation() {
        return saturation;
    }

    public float getValue() {
        return value;
    }

    public int getColor() {
        return Color.HSVToColor(new float[]{hue, saturation, value});
    }

    //SETTERS
    public void setHue(float hue) {
        this.hue = hue;

        this.updateObservers();
        this.getColor();
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;

        this.updateObservers();
        this.getColor();
    }

    public void setValue(float value) {
        this.value = value;

        this.updateObservers();
        this.getColor();
    }


    public void setHSV(float hue, float saturation, float value) {
        this.hue = hue;
        this.saturation = saturation;
        this.value = value;

        this.updateObservers();
    }

    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "HSV: [Hue=" + this.hue + "\u00B0 \nSaturation=" + this.saturation + "% \nValue=" + this.value + "%]";
    }


}
