import java.io.*;
import java.util.*;
public class task2_codsoft 
{
    public static void main(String[] args) 
    {
        System.out.println("Enter 'T' for text input or 'F' for file input:");
        String inputType = System.console().readLine().trim();
        String inputText;
        if (inputType.equalsIgnoreCase("T")) 
        {
            System.out.println("Enter your text:");
            inputText = System.console().readLine();
        } 
        else if (inputType.equalsIgnoreCase("F")) 
        {
            System.out.println("Enter the file path:");
            String filePath = System.console().readLine();
            try 
            {
                inputText = readFile(filePath);
            } 
            catch (IOException e) 
            {
                System.out.println("Error reading file: " + e.getMessage());
                return;
            }
        } 
        else 
        {
            System.out.println("Invalid input type. Program exiting.");
            return;
        }
        String[] words = inputText.split("[\\s\\p{Punct}]+");
        int wordCount = 0;
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "and", "or", "a", "an"));
        for (String word : words) 
        {
            if (!stopWords.contains(word.toLowerCase())) 
            {
                wordCount++;
            }
        }
        System.out.println("Total number of words: " + wordCount);
    }
    private static String readFile(String filePath) throws IOException 
    {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) 
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}