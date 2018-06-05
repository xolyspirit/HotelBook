package model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Orders {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "cost")
    private Double cost = 0.0;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "users_id")
    private Users user;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER, mappedBy = "order")
    private Set<Reservations> reservations = new HashSet<Reservations>();


    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Set<Reservations> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservations> reservations) {
        this.reservations = reservations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<tr><th>");
        sb.append(this.getId());
        sb.append("</th><th>");
        sb.append(status);
        sb.append("</th><th>");
        sb.append(cost);
        sb.append("</th></tr>");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orders orders = (Orders) o;

        if (id != null ? !id.equals(orders.id) : orders.id != null) return false;
        if (status != null ? !status.equals(orders.status) : orders.status != null) return false;
        if (cost != null ? !cost.equals(orders.cost) : orders.cost != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        return result;
    }
}
