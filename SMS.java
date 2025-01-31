import java.util.Scanner;

class School
{
    static int count = 0;
    static int count2 = 0;
    static int countLoan = 0;
    static int index;
    //Students
    static String[] name = new String[20];
    static int[] rNum = new int[20];
    int[] clas = new int[20];
    int[] due = new int[20];
    int[] atten = new int[20];
    float[] exam = new float[20];
    //Library
    String[] bookName = new String[20];
    String[] bookAuthor = new String[20];
    int[] bookCopies = new int[20];
    int[] bookCode = new int[20];
    int[] loan = new int[20];
    int[] leanded = new int[20];
    int group1 = 4000;
    int group2 = 8000;
    int group3 = 10000;
    void feeStructure(){
        System.out.println("*Fees structure*");
        System.out.println("---------------------------------------------\n");
        System.out.println("Class 1 to 4 : Rs. "+group1+" /-");
        System.out.println("Class 5 to 7 : Rs. "+group2+" /-");
        System.out.println("Class 8 to 10 : Rs. "+group3+" /-");
    }
    int validate(){ //Validate roll number
    try{
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        int rn;
        System.out.print("Enter roll number : ");
        rn = s.nextInt();
        for(int i = 0; i < count; i++){
            if(rn == rNum[i]){
                return i+1;
            }
        }
    }
    catch(Exception ex){
        System.out.println("\t\t\tException occured!\n");
    }
        return 0;
    }
    int validate(int rn){ //Validate roll number
        for(int i = 0; i < count; i++){
            if(rn == rNum[i]){
                return i+1;
            }
        }
        return 0;
    }
    void common(int i){
        System.out.println("\nName        : "+name[i]);
        System.out.println("Roll No     : "+rNum[i]);
        System.out.println("Class       : "+clas[i]);
        System.out.println("Attendance  : "+atten[i]+" Days");
    }
}
class Students extends School
{
    int stChoise;
    void yourSelect(int ch){
        stChoise = ch;
        if(stChoise == 1){
            feeStructure();
        }
        else if(stChoise == 2){
            addStudent();
        }
        else if(stChoise == 3){
            showStudents();
        }
        else if(stChoise == 4){
            searchStudent();
        }
        else if(stChoise == 5){
            totalAtten();
        }
        else if(stChoise == 6){
            updateData();
        }
        else if(stChoise == 7){
            feePayment();
        }
        else if(stChoise == 8){
            examReport();
        }
        else{
            //Nothing
        }
    }
    void addStudent(){
        try{
            @SuppressWarnings("resource")
            Scanner s = new Scanner(System.in);
            System.out.println("\n*Enter student details*");
            System.out.println("---------------------------------------------\n");
            System.out.print("Roll No.          : ");
            rNum[count] = s.nextInt();
            if(validate(rNum[count]) != 0){
                System.out.println("\t\t\tRoll number already exist!");
                throw new Exception();
            }
            System.out.print("Name              : ");
            s.nextLine();
            name[count] = s.nextLine();
            System.out.print("Class             : ");
            clas[count] = s.nextInt();
            if(clas[count] > 10){
                System.out.println("\t\t\tInvalid class value!");
                throw new Exception();
            }
            System.out.print("Attendance out 90 : ");
            atten[count] = s.nextInt();
            if(atten[count] > 90){
                System.out.println("\t\t\tInvalid attendance value!");
                throw new Exception();
            }
            System.out.print("Fees Due          : ");
            due[count] = s.nextInt();
            if((due[count] > group1 && clas[count] <= 4) || (due[count] > group2 && clas[count] <= 7) || (due[count] > group3 && clas[count] <= 10)){
                System.out.println("\t\t\tInvalid due fee amount!\n\t\t\tPls check fees structure!");
                throw new Exception();
            }
            count++;
            System.out.println("\n\t\t\tStudent Added Successfully!");
        }
        catch(Exception ex){
            System.out.println("\t\t\tException occured!\n");
        }
    }
    void showStudents(){
        System.out.println("\n*List of all students*");
        System.out.println("---------------------------------------------\n");
        for(int i = 0; i < count; i++){
            common(i);
            System.out.println("Marks %     : "+exam[i]);
            System.out.println("Due fees    : Rs. "+due[i]);
            System.out.println("---------------------------------------------\n");
        }
    }
    void searchStudent(){
        int i;
        System.out.println("\n*Search students*");
        System.out.println("---------------------------------------------\n");
        i = validate();
            if(i != 0){
                common(i-1);
                System.out.println("Marks %     : "+exam[i-1]);
                System.out.println("Due fees    : Rs. "+due[i-1]);
            }
            else{
                System.out.println("\t\t\tNo student found!");
            }
    }
    void totalAtten(){
        System.out.println("\n*Total attendance*");
        System.out.println("---------------------------------------------\n");
        int i = validate();
        if(i != 0){
            common(i-1);
            System.out.println("Attendance(%) : "+(atten[i-1]/90f)*100f+"%");
        }
        else{
            System.out.println("\t\t\tNo student found!");
        }
    }
    void updateData(){
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        System.out.println("*Update data from records*");
        System.out.println("---------------------------------------------\n");
        System.out.print("1. Class \n2. Attendance \n \t\t\tSelected : ");
        int chs = s.nextInt();
        System.out.println("\n");
        int i = validate();
        try{
            if(chs == 1 && i != 0){//For updating class value
                System.out.print("Enter new class : ");
                int newClass = s.nextInt();
                clas[i-1] = newClass;
                System.out.println("\t\t\tData updated successfully!");
            }
            if(chs == 2 && i != 0){//for updating attendance
                System.out.print("Enter current attendace : ");
                int crtAtten = s.nextInt();
                atten[i] = crtAtten;
                System.out.println("\t\t\tData updated successfully!");
            }
            else{
                System.out.println("\t\t\tNo student found!");
            }
        }
        catch(Exception ex){
            System.out.println("\t\t\tException occured!\n");
        }
    }
    void feePayment(){
        try{
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        System.out.println("\n*Fees payments*");
        System.out.println("---------------------------------------------\n");
        int i = validate();
            if(i != 0){
                System.out.println("\nName               : "+name[i-1]);
                System.out.println("Roll No.             : "+rNum[i-1]);
                System.out.println("Class                : "+clas[i-1]);
                System.out.println("Due fees             : Rs. "+due[i-1]);
                System.out.print("Enter Payment Amount : Rs.");
                int pay = s.nextInt();
                if(pay >= 0 && pay <= due[i-1]){//Payment amount must be equal or less than due amount
                    due[i-1] = due[i-1] - pay;
                    if(due[i-1] == 0){
                        System.out.println("\n\t\t\tFees settled!");
                    }
                    System.out.println("\t\t\tFees payment done!\n");
                }
                else{
                    System.out.println("\t\t\tInvalid Amount!");
                }
            }
            else{
                System.out.println("\t\t\tNo student found!");
            }
        }
        catch(Exception ex){
            System.out.println("\t\t\tException occured!\n");
        }
    }
    void examReport(){
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        int i;
        System.out.println("\n*Exam Report*");
        System.out.println("---------------------------------------------\n");
        try{
        i = validate();
            if(i != 0){
                System.out.println("Enter the marks :");
                System.out.print("Marathi       \t\t\t ");
                int mrt = s.nextInt();
                System.out.print("\nEnglish     \t\t\t ");
                int eng = s.nextInt();
                System.out.print("\nMathematics \t\t\t ");
                int math = s.nextInt();
                System.out.print("\nScience     \t\t\t ");
                int sci = s.nextInt();
                System.out.println("\nTotal Marks  : "+(mrt+eng+math+sci));
                exam[i-1] = ((mrt+eng+math+sci)/400f)*100f;
                System.out.println("Total Per(%) : "+exam[i-1]);
            }
            else{
                System.out.println("\t\t\tNo student found!");
            }
        }
        catch(Exception ex){
            System.out.println("\t\t\tException occured!\n");
        }
    }
}

class Library extends School
{
    int libChoise;
    void yourSelctOpt(int ch){
        libChoise = ch;
        if(libChoise == 1){
            addBook();
        }
        else if(libChoise == 2){
            availBooks();
        }
        else if(libChoise == 3){
            upStock();
        }
        else if(libChoise == 4){
            bookLoan();
        }
        else if(libChoise == 5){
            allocatedList();
        }
        else{
            //Nothing
        }
    }
    void common(int i){//Example of method overriding
        System.out.println("\tBook Name   : "+bookName[i]);
        System.out.println("\tbook Author : "+bookAuthor[i]);
        System.out.println("\tBook Copies : "+bookCopies[i]);
    }
    void addBook(){
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        try{
        System.out.println("\n*Adding book*");
        System.out.println("---------------------------------------------\n");
        System.out.print("Enter book code        : ");
        bookCode[count2] = s.nextInt();
        System.out.print("Enter book name        : ");
        s.nextLine();
        bookName[count2] = s.nextLine();
        System.out.print("Enter Author name      : ");
        bookAuthor[count2] = s.nextLine();
        System.out.print("Enter Copies available : ");
        bookCopies[count2] = s.nextInt();
        count2++;
        System.out.println("\t\t\tBook successfully added!\n");
        }
        catch(Exception ex){
            System.out.println("\t\t\tException occured!\n");
        }
    }
    void availBooks(){
        int srNo = 1;
        System.out.println("\n*Available books in library*");
        System.out.println("---------------------------------------------\n");
        for(int i = 0; i < count2; i++){
            System.out.println("     "+srNo+". Book Code   : "+bookCode[i]);
            common(i);
            System.out.println("\n");
            srNo++;
        }
    }
    void upStock(){
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        int bcode;
        char confirm;
        try{
            System.out.println("\n*Search and update books*");
            System.out.println("---------------------------------------------\n");
            System.out.print("Enter book code : ");
            bcode = s.nextInt();
            for(int i = 0; i < count2; i++){
                if(bcode == bookCode[i]){
                    System.out.println("\tBook found : \n");
                    System.out.println("\tBook Code   : "+bookCode[i]);
                    common(i);
                    System.out.print("Want to update stock y/n : ");
                    confirm = s.next().charAt(0);
                    if(confirm == 'y'){
                        System.out.print("\nEnter new stock : ");
                        bookCopies[i] = s.nextInt();
                        System.out.println("\n\t\t\tStock details updated!");
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println("\t\t\tException occured!\n");
        }
    }
    void bookLoan(){
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        int  period, bcode, i;
        System.out.println("\n*Lending Books*");
        System.out.println("---------------------------------------------\n");
        try{
            i = validate();
            if(i != 0){
                System.out.print("Enter book code   : ");
                bcode = s.nextInt();
                System.out.print("Enter loan period : ");
                period = s.nextInt();
            }
            else{
                System.out.println("\t\t\tNo student found!");
                return;
            }
            for(int j = 0; j < count2; j++){
                if(bcode == bookCode[j]){
                    System.out.println("\n\t\t\t*Book loan issued*");
                    System.out.println("Roll No.          : "+rNum[i-1]);
                    System.out.println("Student Name      : "+name[i-1]);
                    System.out.println("Book Code         : "+bookCode[j]);
                    System.out.println("Book Name         : "+bookName[j]);
                    System.out.println("Loan Period(days) : "+period);
                    loan[countLoan] = i-1;//store the index of student name and roll number
                    leanded[countLoan] = j;//store leanded book code's index
                    countLoan++;
                    bookCopies[j]--;
                    break;
                }
            }
        }
        catch(Exception ex){
            System.out.println("\t\t\tException occured!\n");
        }
    }
    void allocatedList(){
        System.out.println("\n*Book loan list*");
        System.out.println("---------------------------------------------");
        for(int i = 0; i < countLoan; i++){
            System.out.println("\nRoll No.          : "+rNum[loan[i]]);
            System.out.println("Student Name      : "+name[loan[i]]);
            System.out.println("Book Code         : "+bookCode[leanded[i]]);
            System.out.println("Book Name         : "+bookName[leanded[i]]);
        }
    }
}
class SMS 
{
    void optStudents(){//Options for student department
        System.out.println("\nSelect one of following function ");
        System.out.println("1. Fees Structure.");
        System.out.println("2. Add Student.");
        System.out.println("3. Show All Students List.");
        System.out.println("4. Search Students.");
        System.out.println("5. Attendance Records.");
        System.out.println("6. Update Students Data.");
        System.out.println("7. Due Fees Payment.");
        System.out.println("8. Exam Report.");
    }
    void optLibrary(){//Options for library department
        System.out.println("\nSelect one of following function ");
        System.out.println("1. Add Books.");
        System.out.println("2. Show Available Books.");
        System.out.println("3. Search & Update Stock.");
        System.out.println("4. Book Loan.");
        System.out.println("5. Total lended books.");
    }
    public static void main(String arg[])
    {
        char again;
        SMS obj = new SMS();
        Students stud = new Students();
        Library lib = new Library();
        @SuppressWarnings("resource")
        Scanner s = new Scanner(System.in);
        for(int i = 0; i <= 100; i++)
        {
        System.out.print("\nSelect departments  \n1. Students \n2. Library \n3. Exit \nEnter selection : ");
        int depart = s.nextInt();
        if(depart > 3){
            System.out.println("Invalid input!");
        }
        if(depart == 1){
            again = 'y';
            while(again == 'y'){
            obj.optStudents();
            System.out.print("\t\tSelected : ");
            int stuChoise = s.nextInt();
            if(stuChoise > 8){
                System.out.println("\t\t\tGoing back!");
                break;
            }
            stud.yourSelect(stuChoise);
            System.out.print("\n*Want any service further within Students(y/n): ");
            again = s.next().charAt(0);
            }
        }
        else if(depart == 2){
            again = 'y';
            while(again == 'y'){
            obj.optLibrary();
            System.out.print("\t\tSelected : ");
            int libChoise = s.nextInt();
            if(libChoise > 6){
                System.out.println("\t\t\tGoing back!");
                break;
            }
            lib.yourSelctOpt(libChoise);
            System.out.print("\n*Want any service further within Library(y/n): ");
            again = s.next().charAt(0);
            }
        }
        else if(depart == 3){
            System.out.println("\nThank You!");
            break;
        }
        }
    }
}
