package infrastructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.h2.tools.RunScript;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public abstract class SimpleJPQLTest {

    private static EntityManagerFactory emf;
    protected static EntityManager em;

    @BeforeAll
    public static void init() throws FileNotFoundException, SQLException {
        emf = Persistence.createEntityManagerFactory("ml-pu-test");
        em = emf.createEntityManager();
    }

    @BeforeEach
    public void initializeDatabase() {
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {

            public void execute(Connection connection) throws SQLException {
                try {
                    File script = new File(getClass().getResource("/data.sql").getFile());
                    RunScript.execute(connection, new FileReader(script));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException("could not initialize with script");
                }
            }

        });
    }

    @AfterAll
    public static void tearDown() {
        em.clear();
        em.close();
        emf.close();
    }

}
