import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JPanel panel;
    private ButtonGroup buttonGroup;
    private JRadioButton[] answerButtons;
    private JButton submitButton;
    private JLabel questionLabel;
    private int currentQuestionIndex = 0;
    private int score = 0;

    //  questions, options, and correct answers
    private final String[] questions = {
            "1. What is the size of an int in Java?",
            "2. Which keyword is used to inherit a class in Java?",
            "3. What is the default value of a boolean in Java?",
            "4. Which method is used to start a thread in Java?",
            "5. What is the parent class of all classes in Java?"
    };

    private final String[][] options = {
            {"4 bytes", "2 bytes", "8 bytes", "Depends on the platform"},
            {"extends", "inherits", "super", "base"},
            {"false", "true", "null", "undefined"},
            {"run()", "start()", "execute()", "begin()"},
            {"Object", "Class", "Super", "Main"}
    };

    private final int[] correctAnswers = {0, 0, 0, 1, 0};  // Indices of correct answers

    public Main() {
        //  frame and panel
        frame = new JFrame("Java Programming Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //  question label
        questionLabel = new JLabel(questions[currentQuestionIndex]);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(questionLabel);

        //  radio buttons for options
        answerButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JRadioButton(options[currentQuestionIndex][i]);
            answerButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            buttonGroup.add(answerButtons[i]);
            panel.add(answerButtons[i]);
        }

        // Submit button 
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitButton.setBackground(new Color(30, 144, 255)); 
        submitButton.setForeground(Color.WHITE);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                currentQuestionIndex++;

                if (currentQuestionIndex < questions.length) {
                    updateQuestion();
                } else {
                    showResults();
                }
            }
        });
        panel.add(submitButton);

        
        frame.add(panel);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Method to update the question and options
    private void updateQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(options[currentQuestionIndex][i]);
            answerButtons[i].setSelected(false);
        }
    }

    // Method to check the answer and display feedback
    private void checkAnswer() {
        int selectedAnswerIndex = -1;
        for (int i = 0; i < 4; i++) {
            if (answerButtons[i].isSelected()) {
                selectedAnswerIndex = i;
                break;
            }
        }

        // Check if the answer is correct and give feedback
        if (selectedAnswerIndex == correctAnswers[currentQuestionIndex]) {
            // Update button color for correct answer
            submitButton.setBackground(new Color(50, 205, 50));  // Green color
            submitButton.setText("Correct!");
            score++; 
        } else {
            // Update button color for incorrect answer
            submitButton.setBackground(new Color(255, 69, 0));  // Red color
            submitButton.setText("Incorrect! Correct answer: " + options[currentQuestionIndex][correctAnswers[currentQuestionIndex]]);
        }

        // Reset button color back after a short delay
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButton.setBackground(new Color(30, 144, 255));  
                submitButton.setText("Submit");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    // Method to show the results at the end of the quiz
    private void showResults() {
        String message = "Quiz Over! Your score: " + score + "/" + questions.length;
        JOptionPane.showMessageDialog(frame, message, "Results", JOptionPane.PLAIN_MESSAGE);
        frame.dispose(); 
    }

    //main function
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}




