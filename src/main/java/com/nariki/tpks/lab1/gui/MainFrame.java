package com.nariki.tpks.lab1.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame{

    private boolean fileHasBeenChoosen;

    public MainFrame() {
        super("ТПКС - Лабораторная работа №1");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 250);

        setResizable(false);



        setLocationRelativeTo(null); // установить окно по центру экрана


        // управляющая  панель
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BorderLayout());

        // управляющие кнопки:

        // кнопка выбора файла
        JButton selectFileButton = new JButton("Выберите файл");
        selectFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Выбрать файл");
                if(ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile(); // получаем выбраный пользователем файл

                    // отправляем файл на проверку, в зависимости от результата выводим сообщение об ошибке или продолжаем работу
                    InputFileChecker inputFileChecker = new InputFileChecker(file);

                    if(inputFileChecker.isFileCorrect()) {
                        fileHasBeenChoosen = true;
                        // TODO: создание объекта графа по исходным данным из файла
                    } else {
                        // с файлом что-то не так определяем проблему и выводим соответствующее сообщение об ошибке
                        file = null;
                        fileHasBeenChoosen = false;

                        JOptionPane.showMessageDialog(null, inputFileChecker.getErrorMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        JButton executeButton = new JButton("Выполнить преобразование");
        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                if(fileHasBeenChoosen) {

                    // TODO: создать объект графа и передать его на обработку
                } else {
                    JOptionPane.showMessageDialog(null, "Сначала выберите файл!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        controlPanel.add(selectFileButton, BorderLayout.WEST);
        controlPanel.add(executeButton, BorderLayout.CENTER);

        getContentPane().add(controlPanel, BorderLayout.CENTER);

        pack();
    }
}
