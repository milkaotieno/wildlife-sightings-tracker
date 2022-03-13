import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animal {
    private String name;
    private int id;

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return Objects.equals(getName(), animal.getName());
    }
    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name) VALUES (:name)";
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", this.name)
                    .executeUpdate()
                    .getKey();
        }
    }
    public static List<Animal> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT name  FROM animals";
            return con.createQuery(sql)
                    .executeAndFetch(Animal.class);
        }
    }
    public static Animal find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT name FROM animals WHERE id=:id";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Animal.class);
        }
    }



    public List<Sighting> getSigSightinghtings() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM sightings WHERE animalId=:id";
            return con.createQuery(sql)
                    .addParameter("id", this.id)
                    .executeAndFetch(Sighting.class);
        }
    }

}
