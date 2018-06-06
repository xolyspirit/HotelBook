package model;

import javax.persistence.*;
import java.sql.Date;
/**Сущность бронирования
 * @version 1.0
 * @author Xolyspirit */
@Entity
public class Reservations {
    /**id бронирования*/
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    /**забронированная комната*/
    @Basic
    @Column(name = "room")
    private Integer room;
    /**дата заезда*/
    @Basic
    @Column(name = "start_time")
    private Date startTime;
    /**количество дней проживания*/
    @Basic
    @Column(name = "days")
    private Integer days;
    /**какому заказу {@link Orders}принадлежит */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;
    /**переопределенный toString для корректного отображения*/
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
    /**стандартные геттеры и сеттеры*/
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
