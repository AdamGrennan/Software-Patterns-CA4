package com.example.demo.strategy;

import com.example.demo.model.Book;
import java.util.List;

public class SortByCategory implements Sorter {
    @Override
    public void sort(List<Book> books, boolean ascending) {
        for (int i = 0; i < books.size(); i++) {
        	for (int j=books.size()-1; j > i; j--){
        		if (compare(books, j, j-1, ascending)){
        			   Book temp = books.get(j);
                       books.set(j, books.get(j - 1));
                       books.set(j - 1, temp);
        			}
            }
        }
    }

    private static boolean compare(List<Book> books, int i, int j, boolean ascending) {
    	 Book b1 = books.get(i);
         Book b2 = books.get(j);
         int result = b1.getCategory().compareToIgnoreCase(b2.getCategory());
         if(ascending) {
        	 return result < 0;
         }else {
        	 return result > 0;
         }
    }
}
