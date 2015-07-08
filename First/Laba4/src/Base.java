
public class Base {

    static int people_on_base;
    static int vehicles_on_base;
    static double petrol_on_base;
    static double goods_on_base;

    public static void main(String[] args) {
        Vehicle v = new Vehicle(19.5, 60);

// get
        System.out.println(v.getPetrolAmount());
        System.out.println(v.getTankVolume());
        System.out.println();

// init
        Base.vehicles_on_base = 49;
        Base.people_on_base = 99;
        Base.petrol_on_base = 1000.0;
        Base.goods_on_base = 2000.0;

        System.out.println("Initially:");
        System.out.println(Base.vehicles_on_base);
        System.out.println(Base.people_on_base);
        System.out.println(Base.petrol_on_base);
        System.out.println(Base.goods_on_base);
        System.out.println();

// arrive
        v.arrive();
        System.out.println("Arrived:");

        System.out.println(Base.vehicles_on_base);	// arrived
        System.out.println(Base.people_on_base);

        System.out.println(Base.petrol_on_base);	// same
        System.out.println(Base.goods_on_base);
        System.out.println();

// leave
        v.leave();
        System.out.println("Left:");

        System.out.println(vehicles_on_base);	// left
        System.out.println(people_on_base);

// should be 1000-(60-19.5)
        System.out.println(petrol_on_base);	// changed!

        System.out.println(goods_on_base);	// same
        System.out.println();
    }
}

class Vehicle {

    protected double petrol_amount;
    protected double tank_volume;

    Vehicle(double petrol_amount, double tank_volume) {
        this.petrol_amount = petrol_amount;
        this.tank_volume = tank_volume;
    }

    double getTankVolume() {
        return tank_volume;
    }

    double getPetrolAmount() {
        return petrol_amount;
    }

    void arrive() {
        if (Base.vehicles_on_base > 0 && Base.people_on_base > 0) {
            Base.vehicles_on_base++;
            Base.people_on_base++;
        }

    }

    boolean leave() {
        if (Base.vehicles_on_base > 0 && Base.people_on_base > 0) {
            if (Base.petrol_on_base + getPetrolAmount() >= getTankVolume()) {
                Base.vehicles_on_base--;
                Base.people_on_base--;
                Base.petrol_on_base = Base.petrol_on_base - (getTankVolume() - getPetrolAmount());
                petrol_amount = getTankVolume();
                return true;
            }
            if (Base.petrol_on_base + getPetrolAmount() < getTankVolume()) {
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }
}

class Bus extends Vehicle {

    protected int people;
    protected int max_people;

    public Bus(int people, int max_people, double petrol_amount, double tank_volume) {
        super(petrol_amount, tank_volume);
        this.people = people;
        this.max_people = max_people;
    }

    int getPeopleCount() {
        return people;
    }

    int getMaxPeople() {
        return max_people;
    }

    void arrive() {
        if (Base.vehicles_on_base > 0 && Base.people_on_base > 0) {
            super.arrive();
            Base.people_on_base += people;
            people = 0;
        }
    }

    boolean leave() {
        if (Base.vehicles_on_base > 0 & Base.people_on_base > 0) {
            if (super.leave() == true) {
                if (Base.people_on_base >= getMaxPeople()) {
                    people = max_people;
                    Base.people_on_base -= max_people;
                    return true;
                }
                if (Base.people_on_base < getMaxPeople()) {
                    people = Base.people_on_base;
                    Base.people_on_base = 0;
                    return true;
                }
            }
        }
        return false;
    }
}

class Truck extends Vehicle {

    protected double load;
    protected double max_load;

    public Truck(double load, double max_load, double petrol_amount, double tank_volume) {
        super(petrol_amount, tank_volume);
        this.load = load;
        this.max_load = max_load;
    }

    public double getCurrentLoad() {
        return load;
    }

    public double getMaxLoad() {
        return max_load;
    }

    public void arrive() {
        if (Base.vehicles_on_base >= 0 & Base.people_on_base >= 0) {
            super.arrive();
            Base.goods_on_base += load;
            load = 0;
        }
    }

    public boolean leave() {

        if (super.leave() == true) {
            if (Base.goods_on_base + getCurrentLoad() >= getMaxLoad()) {
                Base.goods_on_base = Base.goods_on_base - max_load;
                load = max_load;
                return true;
            }
            if (Base.goods_on_base + getCurrentLoad() < getMaxLoad()) {
                load += Base.goods_on_base;
                Base.goods_on_base = 0;
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
