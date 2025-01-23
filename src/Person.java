import java.util.*;
public class Person{

    private String name;
    private String surname;
    private String email;

    public Person() {
    }

    public Person(String name, String surname, String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // creating client_information method t asks for all the information of a Person.
    public void client_information() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------Personal information------------");

        System.out.print("Enter your name: ");
        this.name = scanner.nextLine();

        System.out.print("Enter your surname: ");
        this.surname = scanner.nextLine();

        while(true) { //this loop for get valid email address
            System.out.print("Enter your Email: ");
            this.email = scanner.nextLine();

            if (email.contains("@") && email.contains(".")) {// check if entered email contain '@' and '.'.
                break;
            }
            else{
                System.out.println("Please enter valid email.");
            }
        }


    }
}