import java.io.*;
import java.util.*;

public class schoolsearch{
		
	private static String FILENAME = "students.txt";
	private static String  
	public static List<String[]> studentList = new ArrayList<String[]>();
	private static String PROMPT = "";
	
	public static void main(String[] args) {
	
		if(readFile() < 0)
			return;
	
	controller();
	}



	public static void controller() {
	
		String input = "";
		Scanner keyboard = new Scanner(System.in);
		System.out.println(PROMPT + "Now enter your search parameters.");
		
		String temp;
		String tempNum;
		String tempPlaceHolder;

		input = keyboard.nextLine();
		temp = input.substring(input.indexOf(' ') + 1, input.length());
		
		while((!input.isEmpty()) && input.charAt(0) != 'Q')
		{
			switch(input.charAt(0))
			{
				case 'S':
					if(temp.indexOf(' ') < 0) // BUS IS NOT SPECIFIED, proceed with Student Search
						studentSearch(temp);	
					else //Assume you were asked for Bus routes
					{
						tempNum = temp.substring(0, temp.indexOf(' '));
						studentBusSearch(tempNum);
					}
					break;

				case 'T':
					teacherSearch(temp);
					break;
				
				case 'B':
					busSearch(temp);
					break;
				
				case 'G':
					if(temp.indexOf(' ') < 0) //if there is no additional Strings, search the Grade
						gradeSearch(temp);
					else //there is an extra word that specifies High, Low, or nothing 
						{
							//tempNum holds the last half of the string, hopefully "High" or "Low"
							tempNum = temp.substring(temp.indexOf(' ') + 1, temp.length());
							//tempPlaceHolder holds the grade of the students we are searching
							tempPlaceHolder = temp.substring(0, temp.indexOf(' '));
							switch (tempNum.charAt(0))
							{
									case 'H':
										gradeOutlierSearch(true ,tempPlaceHolder);
										break;
									case 'L':
										gradeOutlierSearch(false, tempPlaceHolder);
										break;
									default:
										gradeSearch(tempPlaceHolder);
							}
						}
					break;
				
				case 'A':
					averageSearch(temp);
					break;
					
				case 'I':
					infoSearch();	
					break;

				default:
					System.out.println("Please enter valid input for the Search.");
			}
			input = keyboard.nextLine();
			temp = input.substring(input.indexOf(' ') + 1, input.length());
		}
	}
	
	public static void infoSearch() {
		int[] students = new int[8];
		for(int i = 0; i < studentList.size(); i++)
		{
			students[Integer.parseInt(studentList.get(i)[2])] += 1;
		}
		for(int x = 0; x < 7; x++)
			System.out.println(x + ": " + students[x]);

	}
	
	public static void averageSearch(String grade) {
		float average = 0;
		float sum = 0;
		int students = 0;
		for(int i = 0; i < studentList.size(); i++)
		{
			if(studentList.get(i)[2].equals(grade))
			{
				sum += Float.valueOf(studentList.get(i)[5]);
				students += 1;
			}
		}
		if (sum == 0 || students == 0)
			return;
		average = sum / students;
		System.out.print("Grade: " + grade + " " + "Average GPA: ");
		System.out.format("%.2f%n", average);
	}

	public static void gradeOutlierSearch(boolean outlier, String grade) {
		float tester;
		int studentIndex = 0;
		String[] temp;
		if(outlier == true) //outlier == true --> Find highest GPA
		{
			tester = 0;
			for(int i = 0; i < studentList.size(); i++)
			{
				if(studentList.get(i)[2].equals(grade))
				{
					if(Float.valueOf(studentList.get(i)[5]) > tester)
					{
						tester = Float.valueOf(studentList.get(i)[5]);
						studentIndex = i;
					}
				}
			}
		}
		else
		{
			tester = 100;
			for(int i = 0; i < studentList.size(); i++)
			{
				if(studentList.get(i)[2].equals(grade))
				{
					if(Float.valueOf(studentList.get(i)[5]) < tester)
					{
						tester = Float.valueOf(studentList.get(i)[5]);
						studentIndex = i;
					}
				}
			}

		}

		temp = studentList.get(studentIndex);
		System.out.println(temp[0] + " " + temp[1] + " " + temp[5] + " " + temp[6] + " " + temp[7] + " " + temp[4]);

	}

	public static void gradeSearch(String grade) {
		String[] temp;
		for(int i = 0; i < studentList.size(); i++)
		{
			if(studentList.get(i)[2].equals(grade))
			{
				temp = studentList.get(i);
				System.out.println(temp[0] + " " + temp[1]);
			}
		}

	}

	public static void busSearch(String busName) {
		String[] temp;
		for(int i = 0; i < studentList.size(); i++)
		{
			if(studentList.get(i)[4].equals(busName))
			{
				temp = studentList.get(i);
				System.out.println(temp[0] + " " + temp[1]);
			}
		}
	}

	
	public static void teacherSearch(String teacherLastName) {
		String[] temp;
		for(int i = 0; i < studentList.size(); i++)
		{
			if(studentList.get(i)[6].equals(teacherLastName))
			{
				temp = studentList.get(i);
				System.out.println(temp[0] + " " + temp[1]);
			}
		}
	}
		
	public static void studentBusSearch(String lastname) {
		String[] temp;
		for(int i = 0; i < studentList.size(); i++)
		{
			if(studentList.get(i)[0].equals(lastname))
			{
				temp = studentList.get(i);
				System.out.println(temp[0] + " " + temp[1] + " " + temp[4]);
			}
		}
	}


	public static void studentSearch(String lastname) {
		String[] temp;
		for(int i = 0; i < studentList.size(); i++)
		{
			if(studentList.get(i)[0].equals(lastname))
			{
				temp = studentList.get(i);
				System.out.println(temp[0] + " " + temp[1] + " " + temp[2] + " " + temp[3] + " " + temp[5] + " " + temp[6]);
			}
		}
		
	}

	public static int readFile() {

		BufferedReader buffReader = null;
		FileReader fileReader = null;
		
		//List<String[]> studentList = new ArrayList<String[]>();
		//String[][] table = new String[][];

		try {
			fileReader = new FileReader(FILENAME);
			buffReader = new BufferedReader(fileReader);

			String currentLine;
			String delim = "[,]+";
			
			while ((currentLine = buffReader.readLine()) != null)
			{
			
				String[] temp = currentLine.split(delim);
				studentList.add(temp);	
			}
		}
		catch(IOException e) {
			return -1;
			//e.printStackTrace();
		}
		return 0;
	}
		
}
