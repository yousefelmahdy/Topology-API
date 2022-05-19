// use it in structs
public class Component {
    private final String id;
    private final String type;

    // error in printing and storing data
    @Override
    public String toString() {
        return "Component{ " +
                "id='" + id + "'" +
                ", type='" + type + "'" +
                '}';
    }

    public Component(String id, String type) {
        this.id = id;
        this.type = type;
    }

}