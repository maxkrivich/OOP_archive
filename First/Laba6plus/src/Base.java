
public class Base {

    static int people_on_base;
    static int vehicles_on_base;
    static double petrol_on_base;
    static double goods_on_base;

    public static void main(String[] args) {
        Base.petrol_on_base = 1000;
        Base.people_on_base = 10;
        Base.goods_on_base = 20.000;
        Base.vehicles_on_base = 10;

        // polymorphism!
        IVehicle v = new Expeditor(3, 5, 3.0, 5.0, 200, 300);
        v.arrive();

        assert (Base.people_on_base == 14);   // 10 + 3 + driver
        assert (Base.goods_on_base == 23.0);  // 20 + 3
        assert (Base.vehicles_on_base == 11); // 10 + 1 

        v.leave();

        assert (Base.people_on_base == 14 - 6);       // 5+driver left
        assert (Base.goods_on_base == 23.0 - 5.0);    // 5 tons left
        assert (Base.petrol_on_base == 900);          // minus (300-200)
        assert (Base.vehicles_on_base == 10);

        // additional: test cast to Bus and Truck
        IBus b = (IBus) v;
        ITruck t = (ITruck) v;
        assert (b != null);
        assert (t != null);

        System.out.println("OK");
    }
}

interface IVehicle {

    double getTankVolume();

    double getPetrolAmount();

    void arrive();

    boolean leave();
}

interface IBus extends IVehicle {

    int getPeopleCount();

    int getMaxPeople();
}

interface ITruck extends IVehicle {

    double getCurrentLoad();

    double getMaxLoad();
}

class Vehicle implements IVehicle {

    protected double petrol_amount;
    protected double tank_volume;

    Vehicle(double petrol_amount, double tank_volume) {
        this.petrol_amount = petrol_amount;
        this.tank_volume = tank_volume;
    }

    public double getTankVolume() {
        return tank_volume;
    }

    public double getPetrolAmount() {
        return petrol_amount;
    }

    public void arrive() {
        if (Base.vehicles_on_base >= 0 & Base.people_on_base >= 0) {
            Base.vehicles_on_base++;
            Base.people_on_base++;
        }

    }

    public boolean leave() {
        if (Base.vehicles_on_base > 0 & Base.people_on_base > 0) {
            if (Base.petrol_on_base + getPetrolAmount() >= getTankVolume()) {
                Base.vehicles_on_base--;
                Base.people_on_base--;
                Base.petrol_on_base = Base.petrol_on_base
                        - (getTankVolume() - getPetrolAmount());
                petrol_amount = getTankVolume();
                return true;
            }
            if (Base.petrol_on_base + getPetrolAmount() < getTankVolume()) {
                /*petrol_amount += Base.petrol_on_base;
                 Base.petrol_on_base = 0;
                 Base.vehicles_on_base--;
                 Base.people_on_base--;*/
                return false;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}

class Bus extends Vehicle implements IBus {

    protected int people;
    protected int max_people;

    public Bus(int people, int max_people, double petrol_amount, double tank_volume) {
        super(petrol_amount, tank_volume);
        this.people = people;
        this.max_people = max_people;
    }

    public double getTankVolume() {
        return tank_volume;
    }

    public double getPetrolAmount() {
        return petrol_amount;
    }

    public int getPeopleCount() {
        return people;
    }

    public int getMaxPeople() {
        return max_people;
    }

    public void arrive() {
        if (Base.vehicles_on_base >= 0 & Base.people_on_base >= 0) {
            super.arrive();
            Base.people_on_base += people;
            people = 0;
        }
    }

    public boolean leave() {
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

class Truck extends Vehicle implements ITruck {

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

class Expeditor implements IBus, ITruck {
    private Bus b;
    private Truck t;
    private double petrol;
    private double max_petrol;

    Expeditor(int people, int max_people, double load, double max_load,
            double petrol, double max_petrol) {
        petrol /= 2;
        max_petrol /= 2;
        b = new Bus(people, max_people, petrol, max_petrol);
        t = new Truck(load, max_load, petrol, max_petrol);
        this.petrol = 2 * petrol;
        this.max_petrol = 2 * max_petrol;
    }

    public double getTankVolume() {
        return petrol;
    }

    public double getPetrolAmount() {
        return max_petrol;
    }

    public void arrive() {
        b.arrive();
        t.arrive();
        Base.vehicles_on_base -= 1;
        Base.people_on_base -= 1;
    }

    public boolean leave() {
        if (b.leave() == true & t.leave() == true) {
            Base.people_on_base += 1;
            Base.vehicles_on_base += 1;
            //Base.petrol_on_base += max_petrol - petrol;
            return true;
        }
        return false;
    }

    public double getCurrentLoad() {
        return t.getCurrentLoad();
    }

    public double getMaxLoad() {
        return t.getMaxLoad();
    }

    public int getPeopleCount() {
        return b.getPeopleCount();
    }

    public int getMaxPeople() {
        return b.getMaxPeople();
    }

}
