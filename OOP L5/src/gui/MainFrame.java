package gui;

import model.Employee;
import model.Person;
import model.Student;
import model.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainFrame extends JFrame {
    private final Store store;
    private final JTextField firstNameField;
    private final JTextField lastNameField;
    private final JTextField dobField;
    private final JTextField regDateField;
    private final JComboBox<String> typeCombo;
    private final JPanel cardPanel;
    private final JPanel studentPanel;
    private final JPanel employeePanel;
    private final JTextField courseField;
    private final JTextField specialNeedsField;
    private final JComboBox<String> dietCombo;
    private final JTextField jobTitleField;
    private final JTextField salaryField;
    private final JTextArea displayArea;
    private final JTextArea summaryArea;
    private final SimpleDateFormat dateFormat;

    public MainFrame() {
        super("University Hall of Residence Manager");
        this.store = new Store();
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        firstNameField = new JTextField(15);
        lastNameField = new JTextField(15);
        dobField = new JTextField(12);
        regDateField = new JTextField(12);
        typeCombo = new JComboBox<>(new String[]{"Student", "Employee"});

        courseField = new JTextField(15);
        specialNeedsField = new JTextField(15);
        dietCombo = new JComboBox<>(new String[]{"None", "Vegan", "Vegetarian"});

        jobTitleField = new JTextField(15);
        salaryField = new JTextField(15);

        displayArea = new JTextArea(10, 40);
        displayArea.setEditable(false);
        displayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        summaryArea = new JTextArea(5, 40);
        summaryArea.setEditable(false);
        summaryArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        studentPanel = buildStudentPanel();
        employeePanel = buildEmployeePanel();
        cardPanel = new JPanel(new CardLayout());
        cardPanel.add(studentPanel, "Student");
        cardPanel.add(employeePanel, "Employee");

        initLayout();
        initActions();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        updateSummary();
    }

    private JPanel buildStudentPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1;
        panel.add(courseField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Special Needs:"), gbc);
        gbc.gridx = 1;
        panel.add(specialNeedsField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Diet:"), gbc);
        gbc.gridx = 1;
        panel.add(dietCombo, gbc);

        return panel;
    }

    private JPanel buildEmployeePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Job Title:"), gbc);
        gbc.gridx = 1;
        panel.add(jobTitleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Salary:"), gbc);
        gbc.gridx = 1;
        panel.add(salaryField, gbc);

        return panel;
    }

    private void initLayout() {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = createGbc();

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("First Name:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(firstNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Last Name:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(lastNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Date of Birth (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(dobField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Registration Date (yyyy-MM-dd):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(regDateField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        inputPanel.add(new JLabel("Record Type:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(typeCombo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        inputPanel.add(cardPanel, gbc);

        JButton addRecordButton = new JButton("Add Record");
        JButton nextRecordButton = new JButton("Next Record");
        JButton saveButton = new JButton("Save to File");
        JButton loadButton = new JButton("Load from File");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 6));
        buttonPanel.add(addRecordButton);
        buttonPanel.add(nextRecordButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        JPanel displayPanel = new JPanel(new BorderLayout(8, 8));
        displayPanel.setBorder(BorderFactory.createTitledBorder("Current Record"));
        displayPanel.add(new JScrollPane(displayArea), BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout(8, 8));
        outputPanel.setBorder(BorderFactory.createTitledBorder("Hall Summary"));
        outputPanel.add(new JScrollPane(summaryArea), BorderLayout.CENTER);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(10, 10));
        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
        contentPane.add(displayPanel, BorderLayout.SOUTH);
        contentPane.add(outputPanel, BorderLayout.EAST);

        addRecordButton.addActionListener(this::handleAddRecord);
        nextRecordButton.addActionListener(this::handleNextRecord);
        saveButton.addActionListener(evt -> handleSaveToFile());
        loadButton.addActionListener(evt -> handleLoadFromFile());
    }

    private void initActions() {
        typeCombo.addActionListener(e -> showCard((String) typeCombo.getSelectedItem()));
        showCard((String) typeCombo.getSelectedItem());
    }

    private void showCard(String type) {
        CardLayout layout = (CardLayout) cardPanel.getLayout();
        layout.show(cardPanel, type);
    }

    private void handleAddRecord(ActionEvent event) {
        try {
            Person record = buildRecord();
            if (record == null) {
                return;
            }
            store.addRecord(record);
            displayArea.setText("Added record:\n" + record.toString());
            updateSummary();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private Person buildRecord() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String dobText = dobField.getText().trim();
        String regText = regDateField.getText().trim();
        String recordType = (String) typeCombo.getSelectedItem();

        if (firstName.isEmpty() || lastName.isEmpty() || dobText.isEmpty()) {
            throw new IllegalArgumentException("First name, last name, and date of birth are required.");
        }

        Date dob = parseDate(dobText);
        Date regDate = regText.isEmpty() ? new Date() : parseDate(regText);

        if ("Student".equals(recordType)) {
            String course = courseField.getText().trim();
            String specialNeeds = specialNeedsField.getText().trim();
            String diet = (String) dietCombo.getSelectedItem();
            if (course.isEmpty()) {
                throw new IllegalArgumentException("Course is required for student records.");
            }
            return new Student(firstName, lastName, dob, regDate, course, specialNeeds, diet);
        }

        if ("Employee".equals(recordType)) {
            String jobTitle = jobTitleField.getText().trim();
            String salaryText = salaryField.getText().trim();
            if (jobTitle.isEmpty()) {
                throw new IllegalArgumentException("Job title is required for employee records.");
            }
            double salary = 0.0;
            try {
                salary = Double.parseDouble(salaryText);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Salary must be a valid number.");
            }
            return new Employee(firstName, lastName, dob, regDate, jobTitle, salary);
        }

        return null;
    }

    private void handleNextRecord(ActionEvent event) {
        Person next = store.nextRecord();
        if (next == null) {
            JOptionPane.showMessageDialog(this, "No records available.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        displayArea.setText(next.toString());
    }

    private void handleSaveToFile() {
        File file = chooseFile(true);
        if (file == null) {
            return;
        }
        try {
            store.saveToFile(file.getAbsolutePath());
            JOptionPane.showMessageDialog(this, "Records saved to " + file.getName(), "Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to save file: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleLoadFromFile() {
        File file = chooseFile(false);
        if (file == null) {
            return;
        }
        try {
            store.loadFromFile(file.getAbsolutePath());
            updateSummary();
            Person first = store.nextRecord();
            displayArea.setText(first == null ? "No records loaded." : first.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load file: " + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private File chooseFile(boolean save) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = save ? chooser.showSaveDialog(this) : chooser.showOpenDialog(this);
        return result == JFileChooser.APPROVE_OPTION ? chooser.getSelectedFile() : null;
    }

    private Date parseDate(String value) {
        try {
            return dateFormat.parse(value);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date must be in yyyy-MM-dd format.");
        }
    }

    private void updateSummary() {
        summaryArea.setText(store.getHallSummary());
    }

    private GridBagConstraints createGbc() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;
        return gbc;
    }
}
