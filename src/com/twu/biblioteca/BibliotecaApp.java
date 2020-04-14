package com.twu.biblioteca;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BibliotecaApp {
    ArrayList<Book> books = new ArrayList<>();
    public class Book{
        int id;
        String name;
        int numberInStore;
        String author;
        // year-month-day
        String publishDate;
        public Book(int id, String name, int numberInStore, String author, String publishDate){
            this.id = id;
            this.name = name;
            this.numberInStore = numberInStore;
            this.author = author;
            this.publishDate = publishDate;
        }
        public void showInformation(){
            System.out.printf("《%s》, id: %d, author: %s, publish date: %s, rest of number: %d\n", name,id,author,publishDate,numberInStore);
        }
    }
    // make up some books because of lack of database
    public void MakeUpBooks(){
        Book b1 = new Book(1,"The Midwife's Tale",1,"Gretchen Moran Laskas","1998-08-05");
        Book b2 = new Book(2,"Bismarck: A Life",2,"Jonathan Steinberg","1998-08-06");
        Book b3 = new Book(3,"BPostwar:A History of Europe Since 1945",1,"Tony Judt","1998-08-07");
        Book b4 = new Book(4,"The Heritage of World Civilizations",1,"kagan","1998-08-08");
        Book b5 = new Book(5,"the complete Little House Nine-Book",2,"Laura Ingalls Wilder","1998-08-09");
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.add(b4);
        books.add(b5);
    }
    // print book's information
    public void ViewAllBooks(){
        // get number of books in store
        int numberOfBooks = books.size();
        if(numberOfBooks == 0){
            System.out.println("No books in store now.");
        }else{
            for (Book b: books) {
                // if the number of the book in store is 0, we don't show the information.
                if (b.numberInStore > 0){
                    b.showInformation();
                }
            }
        }
        System.out.println();
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void CheckOutABook(){
        System.out.println("Which book do you want to check out? Please in put a id number.");
        Scanner sc = new Scanner(System.in);
        // the index start from 0, so we need to minus 1
        int id = sc.nextInt() - 1;
        int numberOfBooks = books.size();
        if(id >= numberOfBooks || id < 0){
            System.out.println("Sorry, that book is not available.");
        }else{
            Book b = books.get(id);
            if(b.numberInStore <= 0){
                System.out.println("Sorry, that book is not available.");
            }else{
                b.numberInStore -= 1;
                System.out.println("Thank you! Enjoy the book.");
            }
        }
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void ReturnABook(){
        System.out.println("Which book do you want to return? Please in put a id number.");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt() - 1;
        sc.nextLine();
        System.out.println("Please input the name of book.");
        String name =  sc.nextLine();
        //System.out.println(name);
        int numberOfBooks = books.size();
        if(id >= numberOfBooks || id < 0){
            System.out.println("This is not a valid book to return.");
        }else{
            Book b = books.get(id);
            //System.out.println(b.name);
            if(b.name.equals(name)){
                b.numberInStore += 1;
                System.out.println("Thank you for returning the book.");

            }else{
                System.out.println("This is not a valid book to return.");
            }
        }
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void Quit(){
        System.out.println("Glad to help. See you next time!");
    }
    public void ShowMainMenu() {
        System.out.println("1:View all books\n" + "2.Check out a book\n" + "3.Return a book\n" + "4.Quit");
        System.out.println("Please input a number to select a option.");
        int numberOfOptions = 4;
        Scanner sc = new Scanner(System.in);
        int chooseNumber = 4;
        boolean isNumber = true;
        while(isNumber){
            try {
                chooseNumber = sc.nextInt();
                // the number of option should be valid.
                if(chooseNumber >= 0 && chooseNumber <= numberOfOptions){
                    isNumber = false;
                }else{
                    System.out.println("Please select a valid option(a number).");
                    sc.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please select a valid option(a number).");
                // if the number is not correct, we ask user to input again.
                // skip the current line.
                sc.nextLine();
            }
        }
        switch (chooseNumber){
            case 1:
                System.out.println("You choose to view all books.");
                ViewAllBooks();
                break;
            case 2:
                System.out.println("You choose to check out a book.");
                CheckOutABook();
                break;
            case 3:
                System.out.println("You choose to return a book.");
                ReturnABook();
                break;
            case 4:
                System.out.println("You choose to quit the Biblioteca application.");
                Quit();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + chooseNumber);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
        System.out.println("This is main menu of options. You can choose one of them.");
        BibliotecaApp b = new BibliotecaApp();
        b.MakeUpBooks();
        b.ShowMainMenu();
    }
}
