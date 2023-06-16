package nl.han.dea.onderzoek.spotitubespringboot.core.services.crud;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface ICrudResource <K,T>{

    public List<T> getAll();

    public T get(K key);

    public void create(T data);

    public void update(K key, T data);

    public void delete(K key);

    class DatabaseProperties {
        private Logger logger = Logger.getLogger(getClass().getName());
        private Properties properties;

        public DatabaseProperties() {
            properties = new Properties();
            try {
                properties.load(getClass().getClassLoader().getResourceAsStream("database.properties"));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Can't access property file database.properties", e);
            }

        }


        public String connectionString()
        {
            return properties.getProperty("connectionString");
        }

        public String driver(){ return properties.getProperty("driver");}
    }
}
