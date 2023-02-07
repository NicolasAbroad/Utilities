package com.nicolas_abroad.Utilities.Utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** String utility functions class. 文字列関連ユーティリティ関数クラス。 */
public class StringUtils {

    /**
     * Checks if string is blank or null.
     * 文字列が空文字列か確認する。
     * @param string 文字列
     * @return true: is empty（空文字列）, false: is not empty（空文字列でない）
     */
    public static boolean isEmpty(String string) {
        if (string == null || string.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Remove line breaks and reduce text to one line.
     * テキストの改行コードを除外して一行にする。
     * @param text テキスト
     * @return text (one line) テキスト（一行）
     */
    public static String reduceToOneLine(String text) {
        StringBuilder sb = new StringBuilder();
        String[] strings = text.split(System.lineSeparator());
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Replace string contained in file.
     * 指定文字列を置換する。
     * @param path 指定ファイルパス
     * @param replaceStrBefore 置換前の文字列
     * @param replaceStrAfter 置換後の文字列
     * @param encodingBefore 置換前のエンコード
     * @param encodingAfter 置換後のエンコード
     */
    public static void replaceStringInFile(Path path, String replaceStrBefore, String replaceStrAfter,
            String encodingBefore, String encodingAfter) {
        try {
            boolean write = false;
            List<String> newLines = new ArrayList<>();
            for (String line : Files.readAllLines(path, Charset.forName(encodingBefore))) {
                if (line.contains(replaceStrBefore)) {
                    write = true;
                    newLines.add(line.replace(replaceStrBefore, replaceStrAfter));
                } else {
                    newLines.add(line);
                }
            }
            if (write) {
                Files.write(path, newLines, Charset.forName(encodingAfter));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Left pad a number with zeros.
     * 数値をゼロ埋めする。
     * @param number 数値
     * @param length 桁数
     * @return padded number ゼロ埋めした数値
     */
    public static String padZero(long number, int length) {
        return String.format("%0" + length + "d", number);
    }

    /**
     * Convert string to pascal case.
     * 文字列をパスカルケースに変換する。
     * @param text 文字列
     * @return converted string パスカルケースに変換した文字列
     */
    public static String toPascalCase(String text) {
        String[] w = text.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w.length; i++) {
            String s = w[i];
            sb.append(Character.toUpperCase(s.charAt(0)));
            sb.append(s.substring(1));
        }
        return sb.toString();
    }

    /**
     * Convert string to camel case.
     * 文字列をキャメルケースに変換する。
     * @param text 文字列
     * @return converted string キャメルケースに変換した文字列
     */
    public static String toCamelCase(String text) {
        String[] w = text.split("_");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < w.length; i++) {
            String s = w[i];
            if (i == 0) {
                sb.append(s);
            } else {
                sb.append(Character.toUpperCase(s.charAt(0)));
                sb.append(s.substring(1));
            }
        }
        return sb.toString();
    }

}
