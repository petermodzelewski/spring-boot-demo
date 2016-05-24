package pl.keyer.spring.daemon;

public class Greeting {
    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return String.format("Greeting{id=%d, content='%s'}", id, content);
    }
}
