package org.massimomauro.entities;


public class Magazine extends ReadingElement{

    Periodicity periodicity;

    public Magazine(int ISBN, String title, int pageNumber, int year, Periodicity periodicity) {
        super(ISBN, title, pageNumber, year);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "ISBN="+ getISBN() +
                ", title='" + getTitle() + '\'' +
                ", year=" + getYear() +
                ", pageNumber=" + getPageNumber() +
                ", periodicity=" + periodicity +
                '}';
    }
}