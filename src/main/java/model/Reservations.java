package model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Reservations {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Basic
    @Column(name = "room")
    private Integer room;
    @Basic
    @Column(name = "start_time")
    private Date startTime;
    @Basic
    @Column(name = "days")
    private Integer days;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr><th>");
        sb.append(id);
        sb.append("</th><th>");
        sb.append(startTime);
        sb.append("</th><th>");
        sb.append(days);
        sb.append("</th></tr>");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reservations that = (Reservations) o;

        if (id != that.id) return false;
        if (room != null ? !room.equals(that.room) : that.room != null) return false;
        if (startTime != null ? !startTime.equals(that.startTime) : that.startTime != null) return false;
        if (days != null ? !days.equals(that.days) : that.days != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (room != null ? room.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (days != null ? days.hashCode() : 0);
        return result;
    }
}
