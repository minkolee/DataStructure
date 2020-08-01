import java.io.File;

public class DeleteReduntFile {


    private String path;

    public DeleteReduntFile(String path) {
        this.path = path;
    }

    public void DeleteFiles() {

        int result = recursiveDeleteFiles(path);
        System.out.println("成功删除了 " + result + " 个文件");
    }

    private int recursiveDeleteFiles(String path) {

        int sum = 0;

        File[] files = new File(path).listFiles();

        if (files == null || files.length == 0) {
            return sum;
        }

        for (File file : files) {
            if (file.isFile()) {
                if (file.getName().contains("(1)")) {
                    if (file.delete()) {
                        sum++;
                    }
                }
            } else {
                sum = sum + recursiveDeleteFiles(file.getAbsolutePath());
            }

        }

        return sum;

    }

    public static void main(String[] args) {

        DeleteReduntFile deleter = new DeleteReduntFile("D:\\Downloads\\[FreeCourseSite.com] Udemy - The Complete Node.js Developer Course (3rd Edition)");

        deleter.DeleteFiles();

    }

}
