package com.company;

public class ComplexNumber {
    double real, img;
    TrigonometricForm trigF = null;

    ComplexNumber() {
        this.real = 0;
        this.img = 0;
    }

    ComplexNumber(double real, double img) {
        this.real = real;
        this.img = img;
    }

    ComplexNumber(int real, int img) {
        this.real = real;
        this.img = img;
    }

    ComplexNumber(ComplexNumber src) {
        this.real = src.real;
        this.img = src.img;
        if (src.trigF != null) {
            this.trigF = new TrigonometricForm(src.trigF);
        } else {
            this.trigF = null;
        }
    }

    public ComplexNumber multByConst(double c) {
        this.real *= c;
        this.img *= c;
        return this;
    }

    public ComplexNumber add(ComplexNumber num) {
        this.real += num.real;
        this.img += num.img;
        return this;
    }

    public ComplexNumber sub(ComplexNumber num) {
        this.real -= num.real;
        this.img -= num.img;
        return this;
    }

    public ComplexNumber mult(ComplexNumber num) {
        this.real = this.real * num.real - this.img * num.img;
        this.img = this.real * num.img + this.img * num.real;
        return this;
    }

    public ComplexNumber divide(ComplexNumber num) {
        this.real = (this.real * num.real + this.img * num.img) / (num.real * num.real + num.img * num.img);
        this.img = (this.img * num.real - this.real * num.img) / (num.real * num.real + num.img * num.img);
        return this;
    }

    public double abs() {
        return Math.sqrt(real * real + img * img);
    }

    public String toTrigonometricStr() {
        trigF = new TrigonometricForm(real, img);
        return trigF.r + "(cos + (i*sin))\twhere\ncos = " + trigF.cosCoef + "\nsin = " + trigF.sinCoef;
    }

    @Override
    public String toString() {
        return "(" + real + " + " + img + "i)";
    }

    private static class TrigonometricForm {
        double cosCoef, sinCoef, r;

        TrigonometricForm(double real, double img) {
            r = Math.sqrt(real * real + img * img);
            cosCoef = real / r;
            sinCoef = img / r;
        }

        TrigonometricForm(TrigonometricForm src) {
            this.cosCoef = src.cosCoef;
            this.sinCoef = src.sinCoef;
            this.r = src.r;
        }
    }
}
