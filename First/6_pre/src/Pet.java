
interface Pet {
    public void talk();
}

class Cat implements Pet {

    public void talk() {
        System.out.println("Meow!");
    }
}

class Dog implements Pet {

    public void talk() {
        System.out.println("Woof!");
    }
}
