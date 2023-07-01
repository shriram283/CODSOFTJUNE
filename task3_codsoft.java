import javax.swing.*;
import java.awt.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

class StudentManagementSystem {
    private java.util.List<Student> students;

    public StudentManagementSystem() {
        students = new java.util.ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Student searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                return student;
            }
        }
        return null;
    }

    public java.util.List<Student> getAllStudents() {
        return students;
    }
}

class StudentManagementPanel extends JPanel {
    private JTextField nameTextField, rollNumberTextField, gradeTextField;
    private JButton addButton, searchButton, displayButton, editButton;

    private StudentManagementSystem system;

    public StudentManagementPanel() {
        system = new StudentManagementSystem();

        setBackground(new Color(215, 230, 255));
        setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        rollNumberTextField = new JTextField();
        JLabel gradeLabel = new JLabel("Grade:");
        gradeTextField = new JTextField();

        addButton = new JButton("Add");
        addButton.addActionListener(e -> addStudent());
        searchButton = new JButton("Search");
        searchButton.addActionListener(e -> searchStudent());
        displayButton = new JButton("Display All");
        displayButton.addActionListener(e -> displayAllStudents());
        editButton = new JButton("Edit");
        editButton.addActionListener(e -> editStudent());

        add(nameLabel);
        add(nameTextField);
        add(rollNumberLabel);
        add(rollNumberTextField);
        add(gradeLabel);
        add(gradeTextField);
        add(addButton);
        add(editButton);
        add(searchButton);
        add(displayButton);
    }

    private void addStudent() {
        String name = nameTextField.getText();
        String rollNumberStr = rollNumberTextField.getText();
        String grade = gradeTextField.getText();

        if (name.isEmpty() || rollNumberStr.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int rollNumber = Integer.parseInt(rollNumberStr);
            Student student = new Student(name, rollNumber, grade);
            system.addStudent(student);
            JOptionPane.showMessageDialog(this, "Student added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid roll number.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        nameTextField.setText("");
        rollNumberTextField.setText("");
        gradeTextField.setText("");
    }

    private void searchStudent() {
        String rollNumberStr = JOptionPane.showInputDialog(this, "Enter the roll number:");
        if (rollNumberStr != null && !rollNumberStr.isEmpty()) {
            try {
                int rollNumber = Integer.parseInt(rollNumberStr);
                Student student = system.searchStudent(rollNumber);
                if (student != null) {
                    JOptionPane.showMessageDialog(this, "Student found:\nName: " + student.getName() + "\nRoll Number: " + student.getRollNumber() + "\nGrade: " + student.getGrade(), "Student Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid roll number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayAllStudents() {
        java.util.List<Student> students = system.getAllStudents();
        StringBuilder sb = new StringBuilder();
        if (students.isEmpty()) {
            sb.append("No students found.");
        } else {
            for (Student student : students) {
                sb.append("Name: ").append(student.getName()).append("\n")
                        .append("Roll Number: ").append(student.getRollNumber()).append("\n")
                        .append("Grade: ").append(student.getGrade()).append("\n\n");
            }
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "All Students", JOptionPane.INFORMATION_MESSAGE);
    }

    private void editStudent() {
        String rollNumberStr = JOptionPane.showInputDialog(this, "Enter the roll number:");
        if (rollNumberStr != null && !rollNumberStr.isEmpty()) {
            try {
                int rollNumber = Integer.parseInt(rollNumberStr);
                Student student = system.searchStudent(rollNumber);
                if (student != null) {
                    String newName = JOptionPane.showInputDialog(this, "Enter the new name:");
                    if (newName != null && !newName.isEmpty()) {
                        student.setName(newName);
                        JOptionPane.showMessageDialog(this, "Student information updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid name.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid roll number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

public class task3_codsoft extends JFrame {
    public task3_codsoft() {
        setTitle("Student Management System");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Create an instance of the custom panel
        StudentManagementPanel panel = new StudentManagementPanel();
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(task3_codsoft::new);
    }
}
