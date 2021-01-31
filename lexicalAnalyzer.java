import java.util.regex.*;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;

//REFERENCES:
// http://tutorials.jenkov.com/java-regex/index.html
// https://www.baeldung.com/java-regexp-escape-char
//https://www.tutorialspoint.com/java/java_regular_expressions.htm
//https://www.javatpoint.com/java-regex
//https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
//https://www.baeldung.com/java-buffered-reader

public class lexicalAnalyzer{
  
  public static void main(String args[]) throws Exception {
       
  try{
    /* Open File and Read File */
    File read_File = new File("C:/Users/laiam/OneDrive/Documents/Programming/ICSI311/Assignment/lexical Analyzer file/lexicalCopy.java");
    
    Scanner keyword = new Scanner(new File("C:/Users/laiam/OneDrive/Documents/Programming/ICSI311/Assignment/lexical Analyzer file/lexicalCopy.java")); 
        
    BufferedReader br = new BufferedReader(new BufferedReader(new FileReader(read_File)));
   
    /* Want to make File into a String */
    String text_Content = keyword.useDelimiter("\\A").next();//converting File to String
    
    String start_Tokens = "\\Q/* \\E"; //identified Token as start symbol using \\Q as the start and \\E is where to end
    String end_Tokens = "\\Q */\\E"; //identified Token as end symbol using \\Q as the start and \\E is where to end 
              
    Pattern start_Content_Pattern = Pattern.compile(start_Tokens); //create a pattern to find Token
    Pattern end_Content_Pattern = Pattern.compile(end_Tokens);

     /* Create a line counter to fine where the comment is */
     LineNumberReader lnr = new LineNumberReader(new FileReader(read_File));
     
     //LineNumberReader lnr = new LineNumberReader(text_Content);
     int i = lnr.getLineNumber();
     
    /* read til the end of the program */
        while ((text_Content = br.readLine())!= null){ 
             i++;//increment lines
          String array1[] = text_Content.split("[\\Q/*\\E\\Q*/\\E]");
              Matcher match_Start = start_Content_Pattern.matcher(text_Content); //Use a match function
              Matcher match_End = end_Content_Pattern.matcher(text_Content);  //Use a match function

              if(match_Start.find()){ // If found Start_Token
                   if( match_End.find())
                   {
                          for (String temp:array1){
                               System.out.println("Comment:" + match_Start.group(0) +temp + match_End.group(0) +i);
                          }
                   }
                   else{
                        System.out.println("Found Start Token, Can't Find End Token");
                   }
              }
        }
    br.close();
    keyword.close();
       } 
  catch(FileNotFoundException ex){
       System.err.print("Error: File cannot be found");
        System.exit(1);
  }
  catch(IOException ex){
       System.err.print("Caught IOException" + ex.getMessage());
  }
}
}