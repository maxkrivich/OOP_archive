
public class Stakan {

    private float mass;
    private float volume;
    private float content = 0;

    public Stakan() {
        mass = 30;
        volume = 200;
    }

    public Stakan(float m, float v) {
        if (m <= 0 | v <= 0) {
            m = 30;
            v = 200;
        }
        mass = m;
        volume = v;
    }

    public float getMass() {
        return mass;
    }

    public float getVolume() {
        return volume;
    }

    public float getContent() {
        return content;
    }

    public boolean setContent(float c) {
        if (c <= getVolume() & c > 0) {
            content = c;
            return true;
        } else {
            return false;
        }
    }

    public boolean setMass(float m) {
        if (m > 0) {
            mass = m;
            return true;
        } else {
            return false;
        }
    }

    public boolean setVolume(float v) {
        if (v > 0) {
            volume = v;
            return true;
        } else {
            return false;
        }

    }

    public void print() {
        System.out.println("Mass: " + getMass() + " g, Volume: " + getVolume() + " ml, Content: " + getContent() + " ml");
    }

    public static void main(String[] args) {
        Stakan s = new Stakan(10, 100);
        System.out.println(s.getMass() + " " + s.getVolume());

        s.setMass(20);
        s.setVolume(150);
        System.out.println(s.getMass() + " " + s.getVolume());

    }

}
