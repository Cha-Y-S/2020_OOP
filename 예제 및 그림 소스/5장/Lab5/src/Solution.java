abstract class Dog {
    protected String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String speak() {
        return "woof";
    }
    abstract public int avgBreedWeight();

}

class Labrador extends Dog {
    private String color; // black, yellow, or chocolate?
    private int breedWeight = 75;

    public Labrador(String name, String color) {
        super(name);
        this.color = color;
    }

    public String speak() {
        return "Wool";
    }

    public int avgBreedWeight() {
        return this.breedWeight;
    }

}

class Yorkshire extends Dog {
    public Yorkshire(String name) {
        super(name);
    }

    public String speak() {
        return "woor";
    }

    public int avgBreedWeight(){
        return 45;
    }

}

class DogTest {
    public String showDogInfo(Dog d) {
        String result = "";
        if (d instanceof Yorkshire)
        {
            d = (Yorkshire)d;
            result = "York says " + d.speak();
        }
        else if(d instanceof Labrador)
        {
            d = (Labrador)d;
            result = "Lab says " + d.speak();
        }
        return result;
    }
}

// ���� ����!!!!! �Ʒ� �ڵ�� �������� ������. �������� �ȵ˴ϴ�.
class Solution {
  public static void main(String[] args) {
	  Yorkshire y = new Yorkshire("York");
	  Labrador l = new Labrador("Lab", "Black");
	  DogTest dog = new DogTest();
	  System.out.println(dog.showDogInfo(y));
	  System.out.println(dog.showDogInfo(l));
  }
}