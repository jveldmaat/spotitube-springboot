package nl.han.dea.onderzoek.spotitubespringboot.core.services.crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Service
    class DatabaseProperties {
        private Logger logger = Logger.getLogger(getClass().getName());
        private Properties properties;

        @Autowired
        public DatabaseProperties() {
            properties = new Properties();
            try {
                properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Can't access property file database.properties", e);
            }

        }


        public String connectionString()
        {
            return properties.getProperty("spring.datasource.url");
        }

        public String driver(){ return properties.getProperty("driver");}
    }
}
