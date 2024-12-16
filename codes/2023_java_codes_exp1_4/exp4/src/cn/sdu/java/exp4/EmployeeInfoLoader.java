package cn.sdu.java.exp4;

import java.util.ArrayList;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class EmployeeInfoLoader {
	
	
	public static void main(String args[])
	{
		String filename = "data/employee.txt";
		readEmployeesInfo(filename);
	}

	public static ArrayList<Employee> readEmployeesInfo(String filename){	
	    try {
	    	ArrayList<Employee> employeeList = new ArrayList<>();
	    	Reader reader = new FileReader(filename);
	    	BufferedReader br  = new BufferedReader(reader);
	        String line;
	        while ((line = br.readLine()) != null) {
	        	String[] employeeData = line.split("_");
	        	Employee employee = parseEmployeeLine(employeeData);
	        	employeeList.add(employee);
	        }
	        reader.close();
	        br.close();
	        employeeList.remove(0);
	        return employeeList;
	        
	    }catch (FileNotFoundException e) {
			System.out.println("文件未找到，请重新核对路径");
		} 
	    catch (IOException e) {
	        e.printStackTrace();
	    }  
	    return null;
	}
	
	private static Employee parseEmployeeLine(String[] employeeData) {
		Employee employee = new Employee();
		employee.name = employeeData[0];
		employee.department = employeeData[1];
		employee.phonenumber = employeeData[2];
		employee.email = employeeData[3];
		employee.isTop = employeeData[4];
		return employee;
	}
	
}
