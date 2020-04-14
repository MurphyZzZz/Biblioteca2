package com.twu.biblioteca;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BibliotecaApp {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Movie> movies = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    public class Customer{
        String id;
        String name;
        int password;
        ArrayList<Book> books;
        ArrayList<Movie> movies;
        public Customer(String id, String name, int password){
            this.id = id;
            this.name = name;
            this.password = password;
            books = new ArrayList<>();
            movies = new ArrayList<>();
        }
        public void ShowInformation(){
            System.out.printf("Your library number is %s, your name is %s\n", id,name);
            if (books.size() > 0){
                System.out.println("You borrowed some books. They are:");
                for(Book b: books){
                    System.out.println(b.name);
                }
            }else{
                System.out.println("You didn't borrow any books.");
            }
            if (movies.size() > 0){
                System.out.println("You borrowed some movies. They are:");
                for(Movie m: movies){
                    System.out.println(m.name);
                }
            }else{
                System.out.println("You didn't borrow any movies.");
            }

        }
    }
    public class Movie{
        int id;
        String name;
        String director;
        int numberInStore;
        int rating;
        public Movie(int id, String name, String director,int numberInStore, int rating){
            this.id = id;
            this.name = name;
            this.director = director;
            this.numberInStore = numberInStore;
            this.rating = rating;
        }
        public void showInformation(){
            System.out.printf("《%s》, id: %d, director: %s, rating: %d, rest of number: %d\n", name,id,director,rating,numberInStore);
        }
    }
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
    public void MakeUpMovies(){
        Movie m1 = new Movie(1,"The Gentlemen","Guy Ritchie",1,8);
        Movie m2 = new Movie(2,"The Hunt","Craig Zobel",2,9);
        Movie m3 = new Movie(3,"Little Women","Greta Grewig",3,7);
        Movie m4 = new Movie(4,"1917","Sam Mendes",1,9);
        Movie m5 = new Movie(5,"Les miserables","Ladj Ly",3,8);
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);
        movies.add(m4);
        movies.add(m5);
    }
    public void MakeUpCustomer(){
        Customer c1 = new Customer("000-0001","mingman.zheng", 123);
        Customer c2 = new Customer("000-0002","murphy.zheng", 321);
        customers.add(c1);
        customers.add(c2);
    }
    public int FindCustomer(String id,int password){
        int index = -1;
        for (Customer c: customers){
            index += 1;
            if(c.id.equals(id) && (c.password == password)){
                return index;
            }
        }
        return -1;
    }
    public int VerifyCustomer(){
        System.out.println("To do next option, you need to log in your account.");
        System.out.println("Please input your id number.(format:xxx-xxxx)");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        System.out.println("Please input your password.");
        int password =  sc.nextInt();
        //System.out.printf("%s,%d\n",id,password);
        int index = FindCustomer(id,password);
        if(index < 0){
            System.out.println("This is not a valid account.");
        }else{
            System.out.println("Successfully verified!");
        }
        return index;
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
        int index = VerifyCustomer();
        if(index >= 0){
            System.out.println("Which book do you want to check out? Please input a id number.");
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
                    // update information for the customer
                    UpdateAccountInf(index,id,"book","Check");
                }
            }
        }
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void ReturnABook(){
        int index = VerifyCustomer();
        if(index >= 0){
            System.out.println("Which book do you want to return? Please input a id number.");
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
                    if(UpdateAccountInf(index,id,"book","Return")){
                        b.numberInStore += 1;
                        System.out.println("Thank you for returning the book.");
                    }else{
                        System.out.println("This is not a valid book to return.");
                    }
                }else{
                    System.out.println("This is not a valid book to return.");
                }
            }
        }
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void CheckOutAMovie(){
        int index = VerifyCustomer();
        if(index >= 0){
            System.out.println("Which movie do you want to check out? Please input a id number.");
            Scanner sc = new Scanner(System.in);
            // the index start from 0, so we need to minus 1
            int id = sc.nextInt() - 1;
            int numberOfMoives = movies.size();
            if(id >= numberOfMoives || id < 0){
                System.out.println("Sorry, that movie is not available.");
            }else{
                Movie m = movies.get(id);
                if(m.numberInStore <= 0){
                    System.out.println("Sorry, that movie is not available.");
                }else{
                    m.numberInStore -= 1;
                    System.out.println("Thank you! Enjoy the movie.");
                    UpdateAccountInf(index,id,"movie","Check");
                }
            }
        }
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void ReturnAMovie(){
        int index = VerifyCustomer();
        if(index >= 0){
            System.out.println("Which movie do you want to return? Please input a id number.");
            Scanner sc = new Scanner(System.in);
            int id = sc.nextInt() - 1;
            sc.nextLine();
            System.out.println("Please input the name of movie.");
            String name =  sc.nextLine();
            //System.out.println(name);
            int numberOfMovies = movies.size();
            if(id >= numberOfMovies || id < 0){
                System.out.println("This is not a valid movie to return.");
            }else{
                Movie m = movies.get(id);
                //System.out.println(b.name);
                if(m.name.equals(name)){
                    if(UpdateAccountInf(index,id,"movie","Return")){
                        m.numberInStore += 1;
                        System.out.println("Thank you for returning the movie.");
                    }else{
                        System.out.println("This is not a valid movie to return.");
                    }
                }else{
                    System.out.println("This is not a valid movie to return.");
                }
            }
        }
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void ViewAllMovies(){
        // get number of books in store
        int numberOfMovies = movies.size();
        if(numberOfMovies == 0){
            System.out.println("No books in store now.");
        }else{
            for (Movie m: movies) {
                // if the number of the book in store is 0, we don't show the information.
                if (m.numberInStore > 0){
                    m.showInformation();
                }
            }
        }
        System.out.println();
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void ViewAccountInformation(){
        int index = VerifyCustomer();
        if(index >= 0){
            System.out.println("There is your account information:");
            Customer c = customers.get(index);
            c.ShowInformation();
        }
        System.out.println("What do you want to next?");
        ShowMainMenu();
    }
    public void Quit(){
        System.out.println("Glad to help. See you next time!");
    }
    public boolean UpdateAccountInf(int customerIdex, int itemIdex, String BookOrMovie, String CheckOrReturn){
        Customer c = customers.get(customerIdex);
        if(BookOrMovie.equals("book")){
            Book b = books.get(itemIdex);
            if(CheckOrReturn.equals("Check")){
                c.books.add(b);
                return true;
            }else{
                if(c.books.contains(b)){
                    c.books.remove(b);
                    return true;
                }else{
                    return false;
                }
            }
        }else{
            Movie m = movies.get(itemIdex);
            if(CheckOrReturn.equals("Check")){
                c.movies.add(m);
                return true;
            }else{
                if(c.movies.contains(m)){
                    c.movies.remove(m);
                    return true;
                }else{
                    return false;
                }
            }
        }
    }
    public void ShowMainMenu() {
        System.out.println("1.View all books\n" + "2.Check out a book\n" + "3.Return a book\n" + "4.View all movies\n" +
                "5.Check out a movie\n" + "6.Return a movie\n" + "7.View account information\n" + "8.Quit");
        System.out.println("Please input a number to select a option.");
        int numberOfOptions = 8;
        Scanner sc = new Scanner(System.in);
        int chooseNumber = 8;
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
                System.out.println("You choose to view all movies.");
                ViewAllMovies();
                break;
            case 5:
                System.out.println("You choose to check out a movie.");
                CheckOutAMovie();
                break;
            case 6:
                System.out.println("You choose to return a movie.");
                ReturnAMovie();
                break;
            case 7:
                System.out.println("You choose to view your account information");
                ViewAccountInformation();
                break;
            case 8:
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
        b.MakeUpMovies();
        b.MakeUpCustomer();
        b.ShowMainMenu();
    }
}
