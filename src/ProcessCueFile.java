import java.io.*;
import java.nio.charset.StandardCharsets;

public class ProcessCueFile {

    public void process(String path, boolean override) throws IOException {
        File file = new File(path);
        String fileName = file.getName();
        FileInputStream inputStream = new FileInputStream(path);
        byte[] result = inputStream.readAllBytes();
        String changedString = new String(result, StandardCharsets.UTF_8);
        byte[] toWrite = changedString.getBytes("GBK");

        String newFileName;
        //如果不覆盖, 生成新文件名
        if (!override) {
            newFileName = file.getParent() + "\\new" + fileName;
        //如果覆盖, 就是原来文件名
        } else {
            newFileName = path;
        }

        FileOutputStream fileOutputStream = new FileOutputStream(newFileName);
        fileOutputStream.write(toWrite);
        fileOutputStream.close();
        System.out.println("已经将文件写入至: " + newFileName);
    }

    public static void main(String[] args) throws IOException {
        String path = "D:\\downloads\\music\\王菲";
        ProcessCueFile processor = new ProcessCueFile();
        processor.recursionProcessFiles(path,true);
    }

    private void recursionProcessFiles(String path, boolean override) throws IOException {
        File currentPath = new File(path);
        //是文件就判断cue文件然后处理, 默认是覆盖
        if (currentPath.isFile()) {
            if (currentPath.getName().endsWith(".cue")) {
                System.out.println("当前处理的是: " + currentPath);
                //调用处理路径的方法
                process(currentPath.getAbsolutePath(), override);
            }
        //不是文件就递归处理, 将其中所有路径遍历然后继续处理
        } else {
            File[] fileList = currentPath.listFiles();
            if (fileList == null) {
                return;
            }
            for (File f : fileList) {
                recursionProcessFiles(f.getAbsolutePath(), override);
            }
        }
    }

}
