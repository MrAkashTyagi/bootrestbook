package com.api.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Service
public class BookService {

@Autowired
private BookRepository bookRepository;

    // private static List<Book> list = new ArrayList<>();

    // static{
    // list.add(new Book(101,"Java Complete Refrence","ABC"));
    // list.add(new Book(102, "Think java","XYZ"));
    // list.add(new Book(103,"Python Programming", "LMN"));
    // }

    //get all books
    public List<Book> getAllBooks(){
        // return list;
    
     List<Book> list =  (List<Book>)this.bookRepository.findAll();
     return list;
    }

    //get single book by id
    public Book getBookById(int id){        
        Book book = null;
        try{
        // book = list.stream().filter(e->e.getId()==id).findFirst().get();
        book = this.bookRepository.findById(id);

    }catch(Exception e){
    e.printStackTrace();
    }
        return book;
    }

    //adding the book
    public Book addBook(Book book){
        Book result = this.bookRepository.save(book);
        // list.add(book);
        return result;
    }
    
    //deleting book
    public void deleteBook(int bid){
        // list=list.stream().filter(book->book.getId()!=bid).collect(Collectors.toList());
        this.bookRepository.deleteById(bid);
}

    //updating book
    public void updateBook(Book book, int id){
        // list=list.stream().map(b->{
        //     if (b.getId()==id) {
        //         b.setName(book.getName());
        //         b.setAuthor(book.getAuthor());
        //     }
        //     return b;
        // }).collect(Collectors.toList());

        book.setId(id);
        bookRepository.save(book);
    }

}
