import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    private JFrame frame;
    private JPanel panel;
    private JButton nextButton;
    private JLabel questionLabel;
    private JCheckBox[] answerCheckBoxes;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private final String[] questions = {
            "1. What is the size of int in Java?",
            "2. Which keyword is used to inherit a class?",
            "3. What is the default value of a boolean in Java?",
            "4. Which method is used to start a thread in Java?",
            "5. Which access modifier makes a member visible only in the same package?",
            "6. What is the parent class of all classes in Java?",
            "7. Which data type is used to store decimal numbers?",
            "8. What is the extension of compiled Java files?",
            "9. Which collection allows duplicate elements?",
            "10. Which keyword is used to define constants in Java?"
    };

    private final String[][] options = {
            {"4 bytes", "2 bytes", "8 bytes", "Depends on the platform"},
            {"inherits", "extends", "super", "base"},
            {"false", "true", "null", "undefined"},
            {"run()", "start()", "execute()", "begin()"},
            {"private", "protected", "public", "default"},
            {"Object", "Class", "Super", "Main"},
            {"float", "char", "double", "int"},
            {".java", ".class", ".jar", ".exe"},
            {"List", "Set", "Map", "Array"},
            {"const", "define", "static", "final"}
    };

    private final int[] correctAnswers = {0, 1, 2, 1, 0, 3, 2, 1, 0, 3};
    private final boolean[] userAnswers = new boolean[questions.length];

    public Main() {
        frame = new JFrame("Java Programming Quiz");
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        questionLabel = new JLabel(questions[currentQuestionIndex]);
        panel.add(questionLabel);

        answerCheckBoxes = new JCheckBox[4];
        ButtonGroup buttonGroup = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            answerCheckBoxes[i] = new JCheckBox();
            buttonGroup.add(answerCheckBoxes[i]);
            panel.add(answerCheckBoxes[i]);
        }
        updateQuestion();

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recordAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < questions.length) {
                    updateQuestion();
                } else {
                    showResults();
                }
            }
        });
        panel.add(nextButton);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void updateQuestion() {
        questionLabel.setText(questions[currentQuestionIndex]);
        for (int i = 0; i < 4; i++) {
            answerCheckBoxes[i].setText(options[currentQuestionIndex][i]);
            answerCheckBoxes[i].setSelected(false);
        }
    }

    private void recordAnswer() {
        for (int i = 0; i < 4; i++) {
            if (answerCheckBoxes[i].isSelected()) {
                userAnswers[currentQuestionIndex] = (i == correctAnswers[currentQuestionIndex]);
                if (userAnswers[currentQuestionIndex]) {
                    score++;
                }
                break;
            }
        }
    }

    private void showResults() {
        StringBuilder resultBuilder = new StringBuilder("Quiz Over!\nYour score: " + score + "/" + questions.length + "\n\n");
        for (int i = 0; i < questions.length; i++) {
            resultBuilder.append("Q").append(i + 1).append(": ").append(questions[i]).append("\n");
            resultBuilder.append("Your answer: ");
            boolean answeredCorrectly = userAnswers[i];
            if (answeredCorrectly) {
                resultBuilder.append(options[i][correctAnswers[i]]).append(" (Correct)\n");
            } else {
                resultBuilder.append("Wrong\n");
                resultBuilder.append("Correct answer: ").append(options[i][correctAnswers[i]]).append("\n");
            }
            resultBuilder.append("\n");
        }

        JOptionPane.showMessageDialog(frame, resultBuilder.toString());
        frame.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}
