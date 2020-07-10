package com.example.blood_line;

public class AlertCLASS {

    public String text;
    public String date;

    public String getText ()
    {
        return text;
    }
    public void setText (String text)
    {
        this.text=text;
    }
    public String  getDate ()
    {
        return date;
    }
    public void setDate(String date)
    {
        this.date=date;
    }

    public AlertCLASS (String text, String date){
        this.text=text;
        this.date=date;
    }
}
