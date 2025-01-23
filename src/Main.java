import java.util.*; // import java utilize class

class w2051784_PlaneManagement{
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        // create object to access ticket class
        Ticket ticket = new Ticket();

        // set all row's seats as 0
        int[] rowA = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int[] rowB = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] rowC = {0,0,0,0,0,0,0,0,0,0,0,0};
        int[] rowD = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};

        // creating 2D arrays to store ticket information one by one
        String[][] ticketA = new String[14][6];
        String[][] ticketB = new String[12][6];
        String[][] ticketC = new String[12][6];
        String[][] ticketD = new String[14][6];

        // creating arrays to store price each price
        double[] priceStoreA = new double[14];
        double[] priceStoreB = new double[12];
        double[] priceStoreC = new double[12];
        double[] priceStoreD = new double[14];

        // store all price array in one 2D array.
        double[][] priceStore = {priceStoreA,priceStoreB,priceStoreC,priceStoreD};

        boolean quit = false; //when quit is true program will be finished.

        System.out.println();
        System.out.println("\tWelcome to the Plane Management application");
        System.out.println();

        while (!quit) { // when quit is false exit this loop.

            // printing menu options
            System.out.print("*************************************************** \n*               MENU OPTIONS                      * \n*************************************************** \n");
            System.out.print("     1) Buy a seat \n     2) Cancel a seat \n     3) Find first available seat \n     4) Show seating plan \n     5) Print tickets information and total sales \n     6) Search ticket \n     0) Quit");
            System.out.print("\n***************************************************");

            int options;// declaring variable as options

            try {
                System.out.println(" ");
                System.out.print("Please enter an option: ");
                options = input.nextInt();

                switch (options) {
                    case(0):
                        System.out.println();
                        System.out.println("Thank You! Safe Journey.");
                        quit = true;
                        break;

                    case (1):
                        //calling the buy_seat method when enter the option 1
                        buy_seat(rowA, rowB, rowC, rowD,ticketA, ticketB, ticketC, ticketD, priceStoreA,priceStoreB,priceStoreC,priceStoreD);
                        break;

                    case (2):
                        //calling the cancel_seat method when enter the option 2
                        cancel_seat(rowA, rowB, rowC, rowD,ticketA,ticketB,ticketC,ticketD,priceStoreA,priceStoreB,priceStoreC,priceStoreD);
                        break;

                    case (3):
                        //calling the find_first_available method when enter the option 3
                        find_first_available(rowA, rowB, rowC, rowD);
                        break;

                    case (4):
                        //calling the show_seating_plan method when enter the option 4
                        System.out.println();
                        System.out.println("\t   SEATING PLAN");

                        System.out.println("-----------------------------");
                        show_seating_plan(rowA);

                        System.out.println();
                        show_seating_plan(rowB);

                        System.out.println();

                        System.out.println();
                        show_seating_plan(rowC);

                        System.out.println();
                        show_seating_plan(rowD);
                        System.out.println("\n-----------------------------");
                        break;

                    case (5):
                        //calling the print_tickets_info method when enter the option 5
                        System.out.println();
                        System.out.println("----------- Ticket Information----------- ");
                        ticket.print_tickets_info(ticketA);
                        ticket.print_tickets_info(ticketB);
                        ticket.print_tickets_info(ticketC);
                        ticket.print_tickets_info(ticketD);

                        ticket.total_amount(priceStore);
                        break;

                    case(6):
                        //calling the search_ticket method when enter the option 6
                        search_ticket(rowA,ticketA,rowD, ticketD, rowB, ticketB, rowC,ticketC);
                        break;

                    default:
                        System.out.println();
                        System.out.println("Please enter only menu options");
                        System.out.println();
                        break;
                }
            }
            catch(InputMismatchException e){ // handling the InputMismatchException error when input a string
                System.out.println();
                System.out.println("Invalid input.");
                System.out.println();
                input.next(); //For avoid infinite loop
            }
        }
    }

    // create buy_seat method for booked seats and create text file.
    public static void buy_seat(int[] rowA, int[] rowB, int[] rowC, int[] rowD, String[][] ticketA, String[][] ticketB, String[][] ticketC, String[][] ticketD, double[] priceStoreA, double[] priceStoreB, double[] priceStoreC, double[] priceStoreD) {
        Scanner input = new Scanner(System.in);

        double PriceTotal = 0;// declare a variable as PriceTotal and assign it to 0

        while (true) {
            System.out.println();
            System.out.print("Please enter a row: ");
            String SeatRow = input.nextLine();
            String NewSeatRow = SeatRow.toUpperCase();// to pass lowercase row letter

            //to only get seat rows between A and D
            if (NewSeatRow.equals("A") || NewSeatRow.equals("B") || NewSeatRow.equals("C") || NewSeatRow.equals("D")) {
                while (true) {
                    try {
                        int SeatNum;// declare a variable as SeatNum
                        if (NewSeatRow.equals("A") || NewSeatRow.equals("D")) {
                            //print the seat numbers and prices
                            System.out.println();
                            System.out.println(NewSeatRow.toUpperCase() + "[1-5] - £200");
                            System.out.println(NewSeatRow.toUpperCase() + "[6-9] - £150");
                            System.out.println(NewSeatRow.toUpperCase() + "[10-14] - £180");

                            System.out.print("Please enter seat number: ");
                            SeatNum = input.nextInt();

                            if(SeatNum >= 1 && SeatNum <=14){// to only get seat numbers between 1 and 14
                                if(NewSeatRow.equals("A")){
                                    if(rowA[SeatNum - 1] == 0) {
                                        // calling purchase_seat method if seat is available
                                        purchase_seat(rowA,SeatNum, PriceTotal,priceStoreA,ticketA,NewSeatRow);
                                        break;
                                    }
                                    else{
                                        System.out.println();
                                        System.out.println(NewSeatRow.toUpperCase()+SeatNum+" seat is already booked.");

                                    }
                                }
                                else{
                                    if(rowD[SeatNum - 1] == 0) {
                                        // calling purchase_seat method if seat is available
                                        purchase_seat(rowD,SeatNum, PriceTotal,priceStoreD,ticketD,NewSeatRow);
                                        break;
                                    }
                                    else{
                                        System.out.println();
                                        System.out.println(NewSeatRow.toUpperCase()+SeatNum+" seat is already booked.");
                                    }

                                }
                            }
                            else{
                                System.out.println("Please enter valid seat number.");
                            }
                        }
                        else {
                            System.out.println();
                            //print the seat numbers and prices
                            System.out.println(NewSeatRow.toUpperCase() + "[1-5] - £200");
                            System.out.println(NewSeatRow.toUpperCase() + "[6-9] - £150");
                            System.out.println(NewSeatRow.toUpperCase() + "[10-12] - £180");

                            System.out.print("Please enter seat number: ");
                            SeatNum = input.nextInt();

                            if (SeatNum >= 1 && SeatNum <= 12) {// to only get seat numbers between 1 and 12
                                if(NewSeatRow.equals("B")){
                                    if(rowB[SeatNum - 1] == 0) {//check selected seat is available
                                        // calling purchase_seat method if seat is available
                                        purchase_seat(rowB,SeatNum, PriceTotal,priceStoreB,ticketB,NewSeatRow);
                                        break;
                                    }
                                    else{
                                        System.out.println();
                                        System.out.println(NewSeatRow.toUpperCase()+SeatNum+" seat is already booked.");
                                    }

                                }
                                else{
                                    if(rowC[SeatNum - 1] == 0) {//check selected seat is available
                                        // calling purchase_seat method if seat is available
                                        purchase_seat(rowC,SeatNum, PriceTotal,priceStoreC,ticketC,NewSeatRow);
                                        break;
                                    }
                                    else{
                                        System.out.println();
                                        System.out.println(NewSeatRow.toUpperCase()+SeatNum+" seat is already booked.");
                                    }

                                }
                            }
                            else {
                                System.out.println();
                                System.out.println("Please enter available seat number");
                            }

                        }
                    }
                    catch (InputMismatchException e) { //handling the InputMismatchException error
                        System.out.println("Please enter a valid seat number.");
                        input.next();
                    }
                }
                break;
            }
            else {
                System.out.println("Please enter valid row letter");
            }
        }

    }

    // creating a purchase_seat method for print confirmed massage, price list store information on tickets
    static void purchase_seat(int[] RowLetter, int SeatNum, double PriceTotal, double[] PriceStoreLetter, String[][] TicketLetter, String NewSeatRow){
        // creating object for Person class
        Person Myperson = new Person();

        //calling client_information method to get user information.
        Myperson.client_information();
        System.out.println();
        //printing book confirmed massage.
        System.out.println("------------------------------------");
        System.out.println("Your reservation has been confirmed!");
        System.out.println("------------------------------------");
        System.out.println();

        RowLetter[SeatNum - 1] = 1;// when seat is booked set this seat index as 1 which means this seat is booked.

        //--price list--
        double price;// declare a variable as price
        if (SeatNum >=1 && SeatNum <= 5) {
            price = 200;
            PriceTotal += price;//adding price to PriceTotal
            PriceStoreLetter[SeatNum -1] = PriceTotal;// store PriceTotal in PriceStore array

        } else if (SeatNum >= 6 && SeatNum <= 9) {
            price = 150;
            PriceTotal += price;//adding price to PriceTotal
            PriceStoreLetter[SeatNum -1] = PriceTotal;// store PriceTotal in PriceStore array

        } else {
            price = 180;
            PriceTotal += price;//adding price to PriceTotal
            PriceStoreLetter[SeatNum -1] = PriceTotal;// store PriceTotal in PriceStore array

        }
        // store the information of person and ticket
        TicketLetter[SeatNum - 1][0] = NewSeatRow.toUpperCase();
        TicketLetter[SeatNum - 1][1] = Integer.toString(SeatNum);
        TicketLetter[SeatNum - 1][2] = Myperson.getName();
        TicketLetter[SeatNum - 1][3] = Myperson.getSurname();
        TicketLetter[SeatNum - 1][4] = Myperson.getEmail();
        TicketLetter[SeatNum - 1][5] = Double.toString(price);

        //calling the save method to create a text file
        Ticket.save(SeatNum, NewSeatRow,TicketLetter);

    }

    //creating a cancel_seat method for cancel booked seats.
    public  static void cancel_seat(int[] rowA, int[] rowB, int[] rowC, int[] rowD, String[][] ticketA, String[][] ticketB, String[][] ticketC, String[][] ticketD, double[] priceStoreA, double[] priceStoreB, double[] priceStoreC, double[] priceStoreD) {
        Scanner input = new Scanner(System.in);

        System.out.println();
        System.out.println("Which seat do you want to cancel? ");

        while (true) {
            System.out.println();
            System.out.print("Please enter row: ");
            String CancelSeatRow = input.nextLine();
            String NewCancelSeatRow = CancelSeatRow.toUpperCase();// to pass related lowercase letters.

            //to only get seat rows between A and D
            if (NewCancelSeatRow.equals("A") || NewCancelSeatRow.equals("B") || NewCancelSeatRow.equals("C") || NewCancelSeatRow.equals("D")) {
                while (true) {
                    try {
                        int CancelSeatNum;// declare a variable as CancelSeatNum.
                        if(NewCancelSeatRow.equals("A") || NewCancelSeatRow.equals("D")) {
                            System.out.print("Please enter seat number: ");
                            CancelSeatNum = input.nextInt();

                            if(CancelSeatNum >= 1 && CancelSeatNum <= 14) {// to only get seat numbers between 1 and 14
                                if(NewCancelSeatRow.equals("A")) {
                                    if (rowA[CancelSeatNum - 1] == 1) { //check entered seat is already booked.
                                        // calling cancel_ticket_info method to cancel entered seat.
                                        cancel_ticket_info(rowA,CancelSeatNum,ticketA,priceStoreA,NewCancelSeatRow);
                                        break;

                                    }
                                    else {
                                        System.out.println();
                                        System.out.println("No corresponding reserved seat.");
                                        System.out.println();
                                        break;

                                    }
                                }
                                else{
                                    if(rowD[CancelSeatNum -1] == 1){//check entered seat is already booked.
                                        // calling cancel_ticket_info method to cancel entered seat.
                                        cancel_ticket_info(rowD,CancelSeatNum,ticketD,priceStoreD,NewCancelSeatRow);
                                        break;

                                    }
                                    else{
                                        System.out.println();
                                        System.out.println("No corresponding reserved seat.");
                                        System.out.println();
                                        break;
                                    }
                                }
                            }
                            else{
                                System.out.println("Please enter a valid seat number");
                            }
                        }
                        else{
                            System.out.print("Please enter seat number: ");
                            CancelSeatNum = input.nextInt();

                            if(CancelSeatNum >= 1 && CancelSeatNum <= 12) {// to only get seat numbers between 1 and 12
                                if(NewCancelSeatRow.equals("B")){
                                    if(rowB[CancelSeatNum - 1] == 1){//check entered seat is already booked.
                                        // calling cancel_ticket_info method to cancel entered seat.
                                        cancel_ticket_info(rowB,CancelSeatNum,ticketB,priceStoreB,NewCancelSeatRow);
                                        break;
                                    }
                                    else{
                                        System.out.println();
                                        System.out.println("No corresponding reserved seat.");
                                        System.out.println();
                                        break;
                                    }
                                }
                                else{
                                    if(rowC[CancelSeatNum - 1] == 1){//check entered seat is already booked.
                                        // calling cancel_ticket_info method to cancel entered seat.
                                        cancel_ticket_info(rowC,CancelSeatNum,ticketC,priceStoreC,NewCancelSeatRow);
                                        break;

                                    }
                                    else{
                                        System.out.println();
                                        System.out.println("No corresponding reserved seat.");
                                        System.out.println();
                                        break;
                                    }
                                }

                            }
                            else{
                                System.out.println("Please input the correct seat number.");
                            }
                        }
                    }
                    catch (Exception e) {// handling errors when input string for seat number
                        System.out.println("Please input the correct seat number.");
                        input.next();
                    }
                }
                break;
            }
            else {
                System.out.println("Please enter valid row index.");
            }
        }
    }

    // creating cancel_ticket_info method for print cancel seat confirmation massage and set all inputs as null.
    static void cancel_ticket_info(int[] RowLetter,int CancelSeatNum,String[][] TicketLetter,double[] PriceStoreLetter,String NewCancelSeatRow){
        RowLetter[CancelSeatNum - 1] = 0;// when seat is cancel set this seat index as 0 which means this seat is available again.

        // set all inputs as null that user input information before.
        TicketLetter[CancelSeatNum - 1][0] = null;
        TicketLetter[CancelSeatNum - 1][1] = null;
        TicketLetter[CancelSeatNum - 1][2] = null;
        TicketLetter[CancelSeatNum - 1][3] = null;
        TicketLetter[CancelSeatNum - 1][4] = null;
        TicketLetter[CancelSeatNum - 1][5] = null;

        PriceStoreLetter[CancelSeatNum - 1] = 0;// removal of price of removed price.
        Ticket.delete_save_file(NewCancelSeatRow, CancelSeatNum);// calling delete_save_file in Ticket class for delete created each file when cancel the seat.

        //print the remove massage.
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Your reservation has been successfully removed!");
        System.out.println("-----------------------------------------------");


    }

    // creating find_first_available method for find the first seat still available.
    static void find_first_available(int[] rowA, int[] rowB, int[] rowC, int[] rowD){
        for (int d = 0; d <= 14; d++) {// loop through each element in the array RowD
            for (int c = 0; c <= 12; c++) {// loop through each element in the array RowC
                for (int b = 0; b <= 12; b++) {// loop through each element in the array RowB
                    for (int a = 0; a <= 14; a++) {// loop through each element in the array RowA
                        if (rowA[a] == 0) {// check if the seat at the index 'a' in the array RowA is available.
                            System.out.println();
                            System.out.println("The first seat that becomes available is A" + (a + 1));
                            System.out.println();
                            return;
                        }
                    }
                    if (rowB[b] == 0) {// check if the seat at the index 'b' in the array RowB is available.
                        System.out.println();
                        System.out.println("The first seat that becomes available is B" + (b + 1));
                        System.out.println();
                        return;
                    }
                }
                if (rowC[c] == 0) {// check if the seat at the index 'c' in the array RowC is available.
                    System.out.println();
                    System.out.println("The first seat that becomes available is C" + (c + 1));
                    System.out.println();
                    return;
                }
            }
            if (rowD[d] == 0) {// check if the seat at the index 'd' in the array RowD is available.
                System.out.println();
                System.out.println("The first seat that becomes available is D" + (d + 1));
                System.out.println();
                return;
            }
        }
    }

    // creating show_seating_plan method that shows the seats that are available and the seats that have been sold.
    static void show_seating_plan(int[] RowIndex) {
        // loop through each element in the array.
        for(int i=0; i< RowIndex.length; i++){
            if(RowIndex[i] == 0){// check if the seat is available then it assign 'O'.
                System.out.print("O" + " ");
            }
            else{// or if it booked assign 'X'.
                System.out.print("X" + " ");
            }
        }

    }

    // creating search_ticket that asks the user to input a row letter and a seat number and searches if someone has bought that seat.
    static void search_ticket(int[] rowA,String[][] ticketA, int[] rowD, String[][] ticketD, int[] rowB, String[][] ticketB, int[] rowC, String[][] ticketC){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println();
            System.out.print("Please enter a row letter: ");
            String SearchRowLetter = scanner.next();
            String NewSearchRowLetter = SearchRowLetter.toUpperCase();// to pass related lowercase letters.

            //to only get seat rows between A and D
            if (NewSearchRowLetter.equals("A") || NewSearchRowLetter.equals("B") || NewSearchRowLetter.equals("C") || NewSearchRowLetter.equals("D")) {
                while(true) {
                    try {
                        System.out.print("Please enter a seat number: ");
                        int SearchSeatNum = scanner.nextInt();

                        if (NewSearchRowLetter.equals("A") || NewSearchRowLetter.equals("D")) {
                            if (SearchSeatNum >= 1 && SearchSeatNum <= 14) {// to only get seat numbers between 1 and 14
                                if (NewSearchRowLetter.equals("A")) {
                                    // calling ticket_information print the person information when entered booked seat number.
                                    ticket_information(SearchSeatNum, rowA, NewSearchRowLetter, ticketA);
                                    break;

                                }
                                else {
                                    // calling ticket_information print the person information when entered booked seat number.
                                    ticket_information(SearchSeatNum, rowD, NewSearchRowLetter, ticketD);
                                    break;
                                }
                            }
                            else {
                                System.out.println();
                                System.out.println("Please enter valid seat number.");
                            }
                        }
                        else {
                            if (SearchSeatNum >= 1 && SearchSeatNum <= 12) {// to only get seat numbers between 1 and 12
                                if (NewSearchRowLetter.equals("B")) {
                                    // calling ticket_information print the person information when entered booked seat number.
                                    ticket_information(SearchSeatNum, rowB, NewSearchRowLetter, ticketB);
                                    break;
                                } else {
                                    // calling ticket_information print the person information when entered booked seat number.
                                    ticket_information(SearchSeatNum, rowC, NewSearchRowLetter, ticketC);
                                    break;
                                }
                            } else {
                                System.out.println();
                                System.out.println("Please enter valid seat number.");
                            }
                        }

                    }
                    catch (InputMismatchException e) {//handling the InputMismatchException error
                        System.out.println();
                        System.out.println("Invalid input.");
                        scanner.next();
                    }
                }
                break;
            }
            else {
                System.out.println();
                System.out.println("Please enter valid seat row letter.");
            }
        }
    }

    // creating  ticket_information to print person information one by one.
    static void ticket_information(int SearchSeatNum, int[] RowLetter, String NewSearchRowLetter, String[][] TicketLetter){
        for(int i = 0; i< RowLetter.length; i++){// loop through each element in array.
            if(RowLetter[SearchSeatNum - 1] == 1){// check if seat was booked.
                System.out.println();
                System.out.println(NewSearchRowLetter.toUpperCase()+SearchSeatNum+" seat has already been bought!");
                System.out.println("This seat was bought by: ");
                System.out.println("-------------------------");
                System.out.println("Name - "+TicketLetter[SearchSeatNum - 1][2] +" "+ TicketLetter[SearchSeatNum - 1][3]);
                System.out.println("Email - "+ TicketLetter[SearchSeatNum - 1][4]);
                System.out.println("Seat row letter - "+ TicketLetter[SearchSeatNum - 1][0]);
                System.out.println("Seat number - "+ TicketLetter[SearchSeatNum - 1][1]);
                System.out.println("-------------------------");
                System.out.println();
                break;
            }
            else{// if entered seat is available print this seat is available.
                System.out.println();
                System.out.println("----------------------");
                System.out.println(NewSearchRowLetter.toUpperCase()+SearchSeatNum+" seat is available");
                System.out.println("----------------------");
                System.out.println();
                break;
            }
        }
    }
}
