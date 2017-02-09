package it.terrinoni.gdgtorino.hashcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;

/**
 * Main class, it is automatically executed by Spring.
 *
 * @author Marco Terrinoni <marco.terrinoni@gmail.com>
 */
@SpringBootApplication
public class Application implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    Worker worker; // automatically linked

    public static void main (String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void onApplicationEvent (ApplicationReadyEvent e) {
        worker.execute(); // call to the real code execution
    }
}
