package ba.unsa.etf.rpr.tutorijal02;

import java.util.Objects;

public class Interval {
    private double pocetna, krajnja;
    boolean pocetnaPripada, krajnjaPripada;


    public Interval(double pocetnaTacka, double krajnjaTacka, boolean pocetnaPripada, boolean krajnjaPripada) throws IllegalArgumentException {
        if(krajnjaTacka < pocetnaTacka)
            throw new IllegalArgumentException("krajnja manja od pocetne!");
        this.pocetna = pocetnaTacka;
        this.krajnja = krajnjaTacka;
        this.pocetnaPripada = pocetnaPripada;
        this.krajnjaPripada = krajnjaPripada;
    }

    public Interval() {
        pocetna = 0;
        krajnja = 0;
        pocetnaPripada = false;
        krajnjaPripada = false;
    }


    public boolean isNull() {
        return pocetna == krajnja;
    }

    public boolean isIn(double tacka) {
        if(tacka < pocetna  ||  (tacka == pocetna  &&  !pocetnaPripada))    // provjera da li se tacka nalazi ispod donje granice intervala
            return false;
        if(tacka > krajnja  ||  (tacka == krajnja  &&  !krajnjaPripada))    // provjera da li se tacka nalazi "iznad" gornje granice intervala
            return false;
        return true;
    }


    public Interval intersect(Interval i) {
        // provjera da li je presjek prazan interval
        if(pocetna > i.krajnja  ||  i.pocetna > krajnja)
            return new Interval(0, 0, false, false);

        // trazimo donju granicu intervala
        double novaPocetna;
        boolean pocPripada;
        if(pocetna > i.pocetna){
            novaPocetna = pocetna;
            pocPripada = pocetnaPripada;
        }
        else if(i.pocetna > pocetna){
            novaPocetna = i.pocetna;
            pocPripada = i.pocetnaPripada;
        }
        else {
            novaPocetna = pocetna;
            if(pocetnaPripada == true  &&  i.pocetnaPripada == true)
                pocPripada = true;
            else
                pocPripada = false;
        }

        // trazimo gornju granicu intervala
        double novaKrajnja;
        boolean krajPripada;
        if(krajnja < i.krajnja){
            novaKrajnja = krajnja;
            krajPripada = krajnjaPripada;
        }
        else if(i.krajnja < krajnja) {
            novaKrajnja = i.krajnja;
            krajPripada = i.krajnjaPripada;
        }
        else {
            novaKrajnja = krajnja;
            if(krajnjaPripada == true  && i.krajnjaPripada == true)
                krajPripada = true;
            else
                krajPripada = false;
        }

        return new Interval(novaPocetna, novaKrajnja, pocPripada, krajPripada);
    }

    public static Interval intersect(Interval i1, Interval i2) {
            return i1.intersect(i2);        // pozivamo vec napravljenu metodu intersects nad jednim od primljenih parametara, koja daje presjek 2 intervala,
    }                                       // tj presjek intervala nad kojim se pozvala metoda i onog koji je proslijedjen kao argument

    @Override
    public String toString() {
        String ispis = "";
        if(pocetna != krajnja) {
            if(pocetnaPripada)
                ispis = ispis + "[";
            else
                ispis = ispis + "(";
            ispis = ispis + pocetna + "," + krajnja;
            if(krajnjaPripada)
                ispis = ispis + "]";
            else
                ispis = ispis + ")";
        }
        else
            ispis = "()";
        return ispis;

    }

    @Override
    public boolean equals(Object o) {
        Interval interval = (Interval) o;
        return  pocetna == interval.pocetna &&
                krajnja == interval.krajnja &&
                pocetnaPripada == interval.pocetnaPripada &&
                krajnjaPripada == interval.krajnjaPripada;
    }

}

