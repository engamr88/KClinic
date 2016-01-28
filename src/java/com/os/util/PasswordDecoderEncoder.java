/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.os.util;

import java.awt.event.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import org.apache.commons.net.util.Base64;

/**
 *
 * @author Eng.Amr
 */
public class PasswordDecoderEncoder implements ActionListener {

    private static String keyst = "e8ffc7e56311679f1bd8fc91aa77a5eb";
    private static byte[] key;

    JFrame frame = new JFrame("Password decoder/encoder");
    JPanel mainPanel = new JPanel();
    JPanel textPanel = new JPanel();
    JPanel contentPanel = new JPanel();
    JTextField passText = new JTextField();
    JButton encryptBtn = new JButton("Encode");
    JButton decryptBtn = new JButton("Decode");
    JLabel outputPassword = new JLabel();

    public PasswordDecoderEncoder() {
        encryptBtn.addActionListener(this);
        decryptBtn.addActionListener(this);
        passText.setColumns(20);
        GridLayout grid = new GridLayout(0, 2);
        textPanel.add(passText);
        textPanel.add(outputPassword);
        contentPanel.setLayout(grid);
        contentPanel.add(encryptBtn);
        contentPanel.add(decryptBtn);
        mainPanel.add(textPanel);
        mainPanel.add(contentPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String args[]) {
        new PasswordDecoderEncoder();
    }

    public static byte[] convertHexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String encrypt(String plainPassword) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        key = convertHexToBytes(keyst);
        final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        final String encryptedString = Base64.encodeBase64String(cipher.doFinal(plainPassword.getBytes("UTF8")));
        System.out.println(encryptedString);
        String passwordEncrypted = encryptedString.trim();

        return passwordEncrypted;

    }

    public static String decrypt(String encryptPassword) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        key = convertHexToBytes(keyst);
        final SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        String encryptedString = new String(cipher.doFinal(Base64.decodeBase64(encryptPassword.getBytes())), "UTF-8");
        System.out.println(encryptedString);
        String passwordDecrypted = encryptedString.trim();

        return passwordDecrypted;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(encryptBtn)) {
            try {
                String password = encrypt(passText.getText());
                outputPassword.setText(password);
            } catch (Exception ex) {
                Logger.getLogger(PasswordDecoderEncoder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource().equals(decryptBtn)) {
            try {
                String password = decrypt(passText.getText());
                outputPassword.setText(password);
            } catch (Exception ex) {
                Logger.getLogger(PasswordDecoderEncoder.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
