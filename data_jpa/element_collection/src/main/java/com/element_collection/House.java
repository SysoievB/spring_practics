package com.element_collection;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Table
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String city;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "house_door",
            joinColumns = @JoinColumn(name = "house_id")
    )
    @Column(name = "door")
    @OrderColumn(name = "list_index")
    List<String> doors;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "house_windows", joinColumns = @JoinColumn(name = "house_id"))
    @Column(name = "windows")
    Set<String> windows;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "house_room", joinColumns = @JoinColumn(name = "house_id"))
    @MapKeyColumn(name = "square_meters")
    @Column(name = "room_name")
    Map<String, String> squareMetersAndRoomNames;

    public House(String city, List<String> doors, Set<String> windows, Map<String, String> squareMetersAndRoomNames) {
        this.city = city;
        this.doors = doors;
        this.windows = windows;
        this.squareMetersAndRoomNames = squareMetersAndRoomNames;
    }
}