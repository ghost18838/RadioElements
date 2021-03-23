package ru.sapteh.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "radio_elements")
public class RadioElement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String manufacturer;

    @Column
    private String description;

    @Column
    private int quantity;

    @Column
    private int cost;

    @Override
    public String toString() {
        return "RadioElement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
