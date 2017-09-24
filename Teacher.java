
public class Teacher {
	public String tLastName;
	public String tFirstName;
	public int classroom;
	
	public Teacher(String line) {
		String [] info = line.trim().split("\\s?,\\s?");
		if(info.length == 3) {
			tLastName = info[0];
			tFirstName = info[1];
			classroom = Integer.parseInt(info[2]);
		}else {
			System.out.println("Error - bad input line");
		}//close else
	}//close constructor

}//close Teacher
