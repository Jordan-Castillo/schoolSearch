
public class Student {
	public String stLastName;
	public String stFirstName;
	public int grade;
	public int classroom;
	public int bus;
	public float gpa;
	
	public Student(String line) {
		String [] info = line.trim().split("\\s?,\\s?");
		if(info.length == 6) {
			stLastName = info[0];
			stFirstName = info[1];
			grade = Integer.parseInt(info[2]);
			classroom = Integer.parseInt(info[3]);
			bus = Integer.parseInt(info[4]);
			gpa = Float.parseFloat(info[5]);
		}else {
			System.out.println("Error - bad input line");
		}
	}
}