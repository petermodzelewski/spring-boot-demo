package pl.keyer.spring.somerestservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Value("${application.text.debug}")
    private String debugMessage;
    @Value("${application.text.info}")
    private String infoMessage;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        System.out.println(debugMessage);
        System.out.println(infoMessage);
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }
}
