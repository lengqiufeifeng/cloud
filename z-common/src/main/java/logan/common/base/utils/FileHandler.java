package logan.common.base.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 文件处理
 *
 * @author user
 */
public class FileHandler {

    /**
     * 建立文件件
     *
     * @return
     */
    public static String newFilePath(String path) {
        String mpath = path;
        File dir = new File(mpath);
        if (!dir.exists()) {
            dir.mkdir();// 新建文件夹
        }
        return mpath;
    }

    /**
     * 递归删除 文件夹下文件
     *
     * @param filepath
     */
    public static void delChildFile(String filepath) {
        delChildFile(new File(filepath));
    }

    /**
     * 递归删除 文件夹下文件
     *
     * @param file
     */
    public static void delChildFile(File file) {

        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                return;
            }
            for (File f : childFile) {
                if (f.isFile())
                    f.delete();
            }
        }
    }

    /**
     * 递归删除文件和文件夹包括本身
     *
     * @param filepath 要删除的根目录
     */
    public static void delFile(String filepath) {
        delFile(new File(filepath));
    }

    public static void delFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                delFile(f);
            }
            file.delete();
        }
    }

    /**
     * 打开日志文件并写入日志
     *
     * @return
     **/
    public static void writeFile(String filedir, String filename, String msg) {
        // 新建或打开日志文件


        File file = new File(newFilePath(filedir), filename);
        try {
            FileWriter filerWriter = new FileWriter(file, true);// 后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(msg);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
