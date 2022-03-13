import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {
    @Override
    protected void before() {
        DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker_test", "moringa", "Access");
    }
    @Override
    public void after() {
        String deleteAnimalsQuery = "DELETE FROM animals *";
        String deleteSightingsQuery = "DELETE FROM sightings *";
        try (Connection con = DB.sql2o.open()) {
           //con.createQuery(deleteAnimalsQuery).executeUpdate();
           //con.createQuery(deleteSightingsQuery).executeUpdate();
        }
    }
}

