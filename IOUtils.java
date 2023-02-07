package com.nicolas_abroad.Utilities.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/** I/O utility functions class. I/O関連ユーティリティ関数クラス。 */
public class IOUtils {

    /**
     * Get target file's text content.
     * ファイルのテキスト内容を取得する。
     * @param path ファイルパス
     * @param encoding エンコード
     * @return target file's text content ファイルのテキスト内容
     * @throws IOException
     */
    public static String getFileText(Path path, String encoding) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(path.toFile());
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, encoding);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String currentLine = null;
            StringBuilder sb = new StringBuilder();
            while ((currentLine = bufferedReader.readLine()) != null) {
                sb.append(currentLine);
                sb.append(System.lineSeparator());
            }

            return sb.toString();
        }
    }

    /**
     * Get target file's text content as one line.
     * ファイルのテキスト内容を一行で取得する。
     * @param path ファイルパス
     * @return target file's text content ファイルのテキスト内容（一行）
     */
    public static String getFileTextOneLine(Path path) {
        String allLines = null;
        try {
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));

            StringBuilder sb = new StringBuilder();
            sb.append(path.getFileName().toString() + " ");
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                line = line.trim();
                sb.append(line);
                sb.append(" ");
            }
            allLines = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLines;
    }

    /**
     * Append string to target file's text content.
     * 文字列をファイルに書き込む。
     * @param path ファイルパス
     * @param text テキスト
     * @throws IOException
     */
    public static void writeToFile(Path path, String text) throws IOException {
        try (FileWriter fw = new FileWriter(path.toString(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(text);
        }
    }

    /**
     * Append list of strings to target file's text content.
     * 文字列のリストをファイルに書き込む。
     * @param path ファイルパス
     * @param list 文字列のリスト
     * @throws IOException
     */
    public static void writeToFile(Path path, List<String> list) throws IOException {
        try (FileWriter fw = new FileWriter(path.toString(), true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            for (int i = 0, n = list.size(); i < n; i++) {
                out.println(list.get(i));
            }
        }
    }

    /**
     * Get all paths of files contained in target folder and subfolders.
     * 指定フォルダのファイル一覧を再帰的に取得する。
     * @param folder 指定フォルダ
     * @return list of all paths ファイル一覧
     * @throws IOException
     */
    public static List<Path> getAllFiles(Path folder) throws IOException {
        return Files.walk(folder).filter(Files::isRegularFile).collect(Collectors.toList());
    }

    /**
     * Create backup copy of target file.
     * 指定ファイルのバックアップファイルを作成する。
     * @param path 指定ファイルのパス
     * @throws IOException
     */
    public static void backupFile(Path path) throws IOException {
        String originalPath = path.toString();
        String strBackupPath = originalPath.replaceAll("(\\.\\w+)$", ".bak");
        Path backupPath = Paths.get(strBackupPath);
        Files.copy(path, backupPath);
    }

    /**
     * Rename file.
     * ファイル名を変更する。
     * @param path ファイルパス
     * @param newPath 新しいファイルパス
     * @throws IOException
     */
    public static void renameFile(String path, String newPath) throws IOException {
        File file = new File(path);
        File newFile = new File(newPath);
        if (newFile.exists()) {
            throw new RuntimeException();
        }
        file.renameTo(newFile);
    }

}
