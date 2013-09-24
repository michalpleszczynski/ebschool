package com.ebschool.ejb.model.time;

import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;

import javax.persistence.*;
import java.util.Objects;

/**
 * User: michau
 * Date: 9/20/13
 */
@Embeddable
public class ClassTime {

    public enum Day {
        MONDAY(1),
        TUESDAY(2),
        WEDNESDAY(3),
        THURSDAY(4),
        FRIDAY(5),
        SATURDAY(6),
        SUNDAY(7);

        private int index;

        private Day(int index){
            this.index = index;
        }

        public int getIndex(){
            return index;
        }
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Day day;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
    @Column(nullable = false)
    private LocalTime time;

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object object){
        if (this == object) {
            return true;
        }

        if (object == null ||
                !ClassTime.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        final ClassTime classTime = (ClassTime) object;
        return Objects.equals(getTime(), classTime.getTime()) && Objects.equals(getDay(), classTime.getDay());
    }

    @Override
    public int hashCode(){
        int result = 17;
        result = result*37 + (getDay() != null ? getDay().toString().hashCode() : 0);
        result = result*37 + (getTime() != null ? getTime().hashCode() : 0);
        return result;
    }

}
