public class Person {
    private final String name;
    private final String gender;
    private final int age;
    private Person (String name, String gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }
    public void introduce() {
        System.out.println("Hello, my neme is " + name + ". I am a" + gender.toLowerCase() + "and I am" + age + "yaers old.");
    }
}
