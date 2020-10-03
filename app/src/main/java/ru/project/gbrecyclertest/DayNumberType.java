package ru.project.gbrecyclertest;

public class DayNumberType implements   RowType {
    private String text;
    public DayNumberType(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
