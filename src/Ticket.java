import java.io.FileWriter;// import file class to write to files
import java.io.IOException; // import the IOException class to handle the errors
import java.io.File;
import java.util.Arrays;

public class Ticket {

    private String SeatRow;
    private int SeatNum;
    private String email;
    private double total=0;

    Ticket() {

    }

    public Ticket(String SeatRow, int SeatNum, String email, double total) {
        this.SeatRow = SeatRow;
        this.SeatNum = SeatNum;
        this.email = email;
    }

    public String getSeat_row() {
        return SeatRow;
    }

    public void setSeat_row(String seat_row) {
        this.SeatRow = seat_row;
    }

    public int getSeat_num() {
        return SeatNum;
    }

    public void setSeat_num(int seat_num) {
        this.SeatNum = seat_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getTotal() {
        return total;
    }


    //for get total amount of price
    public void total_amount( double[][] priceStore){
        double sum = 0;// create a variable as sum and assign it to 0.

        for(int i =0; i< priceStore.length; i++){// loop through PriceStore 2D array length.
            for(int j = 0; j< priceStore[i].length; j++){// loop through each element in priceStore 2D array.
                sum += priceStore[i][j];// adding variable sum to each price.
            }
        }

        System.out.println();
        System.out.println("\tFinal price - £"+sum);// printing final price
    }

    //for print all the information about ticket
    public void print_tickets_info(String[][] TicketNum) {

        for (String[] strings : TicketNum) {// loop through each element in 2D array.
            for (String string : strings) {

                if (string != null) {//check if not string include null.

                    System.out.println();
                    System.out.println("\t1)Full name - " + strings[2] + " " + strings[3]);
                    System.out.println("\t2)Email - " + strings[4]);
                    System.out.println("\t3)Seat row letter- " + strings[0]);
                    System.out.println("\t4)Seat number - " + strings[1]);
                    System.out.println("\t5)price - £" + strings[5]);

                }
                break;
            }
        }
    }


    public void setTotal(double total) {
        this.total = total;
    }

    // creating save method in the class Ticket that saves the information of the Ticket (including the Person) in a file.
    static void save(int SeatNum, String NewSeatRow, String[][] ticket){
        try{
            //write ticket information in text file
            FileWriter writer = new FileWriter(NewSeatRow.toUpperCase()+SeatNum+".txt");
            writer.write("-------Ticket Information-------");
            writer.write("\n");
            writer.write("\n\tName - "+ticket[SeatNum - 1][2]+" "+ticket[SeatNum - 1][3]);
            writer.write("\n\tEmail - "+ticket[SeatNum - 1][4]);
            writer.write("\n\tSeat row - "+NewSeatRow.toUpperCase());
            writer.write("\n\tSeat number - "+ SeatNum);
            writer.write("\n\tPrice - £"+ticket[SeatNum - 1][5]);
            writer.write("\n------------------------------");
            // Close the writer to release resources
            writer.close();


        }
        catch(IOException ex){
            // handle any error while write in to file.
            System.out.println("Error: File could be not created.");

        }
    }

    static void delete_save_file(String NewCancelSeatRow, int CancelSeatNum ){

        File file = new File(NewCancelSeatRow.toUpperCase()+CancelSeatNum+".txt");
        file.delete();

    }


}


