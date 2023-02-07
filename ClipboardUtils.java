package com.nicolas_abroad.Utilities.Utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;
import java.util.List;

/** Clipboard utility functions class. クリップボード関連ユーティリティ関数クラス。 */
public class ClipboardUtils {

    /**
     * Get text contained in clipboard.
     * クリップボードに保持したテキストを取得する。
     * @return clipboard text テキスト
     * @throws Exception
     */
    public static String getTextFromClipboard() throws Exception {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        String clipboardText = clipboard.getData(DataFlavor.stringFlavor).toString();
        return clipboardText;
    }

    /**
     * Get text contained in clipboard as list (separated by newlines).
     * クリップボードに保持したテキストをリストで取得する。
     * @return clipboard text リスト
     * @throws Exception
     */
    public static List<String> getListFromClipboard() throws Exception {
        String clipboardText = getTextFromClipboard();
        String[] clipboardTextArray = clipboardText.split("(\r\n|\r|\n)");
        List<String> list = new ArrayList<>(clipboardTextArray.length);
        for (int i = 0; i < clipboardTextArray.length; i++) {
            String item = clipboardTextArray[i];
            list.add(item);
        }
        return list;
    }

    /**
     * Copy string to clipboard.
     * テキストをクリップボードに保持させる。
     * @param text テキスト
     */
    public static void copyTextToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

}
