package com.nariki.tpks.lab1.gui;

import com.nariki.tpks.lab1.action.MatrixConverter;
import com.nariki.tpks.lab1.model.IncidenceMatrix;
import com.nariki.tpks.lab1.util.FileWriter;
import com.nariki.tpks.lab1.util.InputFileChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame {

    private boolean fileHasBeenChosen;
    private final String FILE_STATUS_NOT_CHOSEN = "Файл не выбран";
    private  JLabel fileStatusLabel;

    private IncidenceMatrix incidenceMatrix;

    public MainFrame() {
        super("ТПКС - Лабораторная работа №1");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(450, 200);

        setResizable(false);



        setLocationRelativeTo(null); // установить окно по центру экрана


        // управляющая  панель
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(null);

        // управляющие кнопки:

        // кнопка выбора файла
        JButton selectFileButton = new JButton("Выберите файл");
        selectFileButton.setBounds(30, 20, 180, 50);
        selectFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int ret = fileChooser.showDialog(null, "Выбрать файл");
                if(ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile(); // получаем выбраный пользователем файл

                    // отправляем файл на проверку, в зависимости от результата выводим сообщение об ошибке или продолжаем работу
                    InputFileChecker inputFileChecker = new InputFileChecker(file);

                    if(inputFileChecker.isFileCorrect()) {
                        fileHasBeenChosen = true;
                        fileStatusLabel.setText("Выбран файл: " + file.getName());


                        // создание матрицы инфидентности по исходным данным из файла
                        incidenceMatrix = new IncidenceMatrix(inputFileChecker.getIncidenceMatrix());
                    } else {
                        // с файлом что-то не так определяем проблему и выводим соответствующее сообщение об ошибке
                        file = null;
                        fileHasBeenChosen = false;
                        fileStatusLabel.setText(FILE_STATUS_NOT_CHOSEN);

                        JOptionPane.showMessageDialog(null, inputFileChecker.getErrorMessage(), "Ошибка!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });


        JButton executeButton = new JButton("Выполнить преобразование");
        executeButton.setBounds(240, 20, 180, 50);
        executeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fileHasBeenChosen) {

                    // TODO: подготовить данные: кол-во вершин (int), массив (byte | short | int | long) и передать на обработку статичному методу

                    FileWriter fileWriter = new FileWriter();
                    //fileWriter.writeArrayOfIntsInFile(matrixConverter.getAdjacencyMatrix(), System.getProperty("user.home") + "\\Desktop\\adjacencyMatrix.txt");
                    // print bit vectors
                    for(int i = 0; i < incidenceMatrix.getMatrixAsBitVector().length; i++) {
                        System.out.println(Long.toBinaryString(incidenceMatrix.getMatrixAsBitVector()[i]));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Сначала выберите файл!", "Ошибка!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        fileStatusLabel = new JLabel(FILE_STATUS_NOT_CHOSEN);
        fileStatusLabel.setBounds(20, 70, 350, 40);

        controlPanel.add(fileStatusLabel);
        controlPanel.add(selectFileButton);
        controlPanel.add(executeButton);

        getContentPane().add(controlPanel, BorderLayout.CENTER);
    }
}
