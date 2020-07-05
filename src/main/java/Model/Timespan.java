package Model;

import java.time.LocalTime;

public class Timespan {
    private LocalTime start;
    private LocalTime end;

    public Timespan(String start, String end){
        this.start = LocalTime.parse(start);
        this.end = LocalTime.parse(end);
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

}
