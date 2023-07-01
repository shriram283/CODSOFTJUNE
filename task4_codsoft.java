import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class ATMGUI extends JFrame {
    private BankAccount bankAccount;
    private JLabel balanceLabel;

    public ATMGUI() {
        this.bankAccount = new BankAccount(0.0);

        setTitle("ATM");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLACK);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel atmImageLabel = new JLabel(new ImageIcon("ATM_image.jpg"));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        panel.add(atmImageLabel, constraints);

        JLabel balanceTextLabel = new JLabel("Balance:");
        balanceTextLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(balanceTextLabel, constraints);

        balanceLabel = new JLabel();
        balanceLabel.setForeground(Color.WHITE);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(balanceLabel, constraints);

        JButton withdrawButton = new JButton("Withdraw");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        panel.add(withdrawButton, constraints);

        JButton depositButton = new JButton("Deposit");
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(depositButton, constraints);

        JButton checkBalanceButton = new JButton("Check Balance");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(checkBalanceButton, constraints);

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(ATMGUI.this, "Enter the amount to withdraw:");
                if (input != null) {
                    try {
                        double amount = Double.parseDouble(input);
                        boolean success = bankAccount.withdraw(amount);
                        if (success) {
                            JOptionPane.showMessageDialog(ATMGUI.this, "Withdrawal successful. Please take your money.");
                        } else {
                            JOptionPane.showMessageDialog(ATMGUI.this, "Insufficient balance. Withdrawal failed.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ATMGUI.this, "Invalid amount. Withdrawal failed.");
                    }
                    updateBalanceLabel();
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(ATMGUI.this, "Enter the amount to deposit:");
                if (input != null) {
                    try {
                        double amount = Double.parseDouble(input);
                        bankAccount.deposit(amount);
                        JOptionPane.showMessageDialog(ATMGUI.this, "Deposit successful. Current balance: " + bankAccount.getBalance());
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(ATMGUI.this, "Invalid amount. Deposit failed.");
                    }
                    updateBalanceLabel();
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ATMGUI.this, "Current balance: " + bankAccount.getBalance());
            }
        });

        updateBalanceLabel();

        add(panel);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText(String.valueOf(bankAccount.getBalance()));
    }
}

public class task4_codsoft {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ATMGUI atmGUI = new ATMGUI();
                atmGUI.setVisible(true);
            }
        });
    }
}
