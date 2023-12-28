package org.example;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] number;
    private JButton[] operation;
    private JButton plus,minus,multi,divid,equal,clear;
    private JButton decimal,negative;
    private double num1,num2,result;
    private char operator;
    public Main() {
        setTitle("Calculator");
        setSize(400,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5,4));
        display = new JTextField();
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 24));


        display.setPreferredSize(new Dimension(Integer.MAX_VALUE, display.getPreferredSize().height));

        add(display, BorderLayout.NORTH);

        number=new JButton[10];
        for (int i = 0; i < number.length; i++) {
            number[i]=new JButton(String.valueOf(i));
            number[i].addActionListener(this);
        }

        operation =new JButton[4];
        plus=new JButton("+");
        minus=new JButton("-");
        multi=new JButton("*");
        divid=new JButton("/");
        equal=new JButton("=");
        clear=new JButton("C");
        operation[0]=plus;
        operation[1]=minus;
        operation[2]=multi;
        operation[3]=divid;
        for (JButton but : operation) {
            but.addActionListener(this);
        }
        equal.addActionListener(this);
        clear.addActionListener(this);
        decimal =new JButton(".");
        decimal.addActionListener(this);
        negative=new JButton("+/-");
        negative.addActionListener(this);
        add(clear);
        add(negative);
        add(divid);
        for (int i = 7; i <=9; i++) {
            add(number[i]);
        }
        add(multi);
        for (int i = 4; i <=6; i++) {
            add(number[i]);
        }
        add(minus);
        for (int i = 1; i <=3; i++) {
            add(number[i]);
        }
        add(plus);
        add(number[0]);
        add(decimal);
        add(equal);
        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o=e.getSource();
        for (int i = 0; i < 10; i++) {
            if (o==number[i]) {
                display.setText(display.getText()+i);
                return;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (o==operation[i]) {
                num1=Double.parseDouble(display.getText());
                display.setText(display.getText()+i);
                operator=operation[i].getText().charAt(0);
                display.setText("");
                return;
            }
        }
        if (o==equal) {
            num2=Double.parseDouble(display.getText());
            switch(operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
            return;
        }
        if (o==clear) {
            display.setText("");
            num1=num2=result=0;
            operator=' ';
            return;
        }
        if (o==decimal) {
            if (!display.getText().contains(".")) {
                display.setText(display.getText().concat("."));
            }
            return;
        }
        if (o==negative) {
            double current=Double.parseDouble(display.getText());
            display.setText(String.valueOf(-current));
        }
    }
    public static void main(String[] args) {
        new Main();
    }
}