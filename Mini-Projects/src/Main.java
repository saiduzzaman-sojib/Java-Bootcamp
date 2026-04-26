import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        setupFiles();
        Scanner scanner = new Scanner(System.in);

        System.out.println("==========================================");
        System.out.println("   Welcome to Student Management System   ");
        System.out.println("==========================================");

        boolean isAuthenticated = false;
        while (!isAuthenticated) {
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (validateLogin(username, password)) {
                isAuthenticated = true;
                System.out.println("\n Login Successful! Welcome, " + username + ".");
            } else {
                System.out.println(" Incorrect Username or Password. Please try again.\n");
            }
        }

        while (true) {
            System.out.println("\n--- Officer Dashboard ---");
            System.out.println("1. Add Student Information");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID (Hides Password)");
            System.out.println("4. Assign Course to Student");
            System.out.println("5. Search Advised Courses by Student ID");
            System.out.println("6. Logout & Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                addStudent(scanner);
            } else if (choice == 2) {
                viewAllStudents();
            } else if (choice == 3) {
                System.out.print("Enter Student ID to search: ");
                String id = scanner.nextLine();
                searchStudentById(id);
            } else if (choice == 4) {
                assignCourse(scanner);
            } else if (choice == 5) {
                System.out.print("Enter Student ID to view courses: ");
                String id = scanner.nextLine();
                searchCoursesById(id);
            } else if (choice == 6) {
                System.out.println("Logging out... Goodbye!");
                break;
            } else {
                System.out.println("Invalid option! Please choose between 1 to 6.");
            }
        }
        scanner.close();
    }

    static void setupFiles() {
        try {
            File officerFile = new File("officers.txt");
            if (!officerFile.exists()) {
                officerFile.createNewFile();
                FileWriter fw = new FileWriter(officerFile);
                fw.write("admin,1234\n");
                fw.close();
            }
            new File("students.txt").createNewFile();
            new File("courses.txt").createNewFile();
        } catch (IOException e) {
            System.out.println("Error initializing system files.");
        }
    }

    static boolean validateLogin(String username, String password) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("officers.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    if (parts[0].equals(username) && parts[1].equals(password)) {
                        reader.close();
                        return true;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading officer data.");
        }
        return false;
    }

    static void addStudent(Scanner scanner) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Program (e.g., CSE): ");
        String program = scanner.nextLine();
        System.out.print("Enter Batch: ");
        String batch = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter CGPA: ");
        String cgpa = scanner.nextLine();

        String record = id + "," + name + "," + password + "," + program + "," + batch + "," + cgpa + "\n";
        writeToFile("students.txt", record);
        System.out.println("Student added successfully!");
    }

    static void viewAllStudents() {
        System.out.println("\n--- All Students List ---");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading student data.");
        }
    }

    static void searchStudentById(String searchId) {
        boolean found = false;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("students.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6 && parts[0].equals(searchId)) {
                    System.out.println("\n--- Student Details ---");
                    System.out.println("ID      : " + parts[0]);
                    System.out.println("Name    : " + parts[1]);
                    System.out.println("Program : " + parts[3]);
                    System.out.println("Batch   : " + parts[4]);
                    System.out.println("CGPA    : " + parts[5]);
                    found = true;
                    break;
                }
            }
            reader.close();
            if (!found) {
                System.out.println("No student found with ID: " + searchId);
            }
        } catch (IOException e) {
            System.out.println("Error reading student data.");
        }
    }

    static void assignCourse(Scanner scanner) {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Course Code (e.g., CSE101): ");
        String courseCode = scanner.nextLine();

        String record = id + "," + courseCode + "\n";
        writeToFile("courses.txt", record);
        System.out.println("Course assigned successfully to Student ID: " + id);
    }

    static void searchCoursesById(String searchId) {
        boolean found = false;
        System.out.println("\n--- Advised Courses for ID: " + searchId + " ---");
        try {
            BufferedReader reader = new BufferedReader(new FileReader("courses.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(searchId)) {
                    System.out.println("- " + parts[1]);
                    found = true;
                }
            }
            reader.close();
            if (!found) {
                System.out.println(" No courses found for this student.");
            }
        } catch (IOException e) {
            System.out.println("Error reading course data.");
        }
    }

    static void writeToFile(String fileName, String data) {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
        }
    }
}
