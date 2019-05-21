package lesson4.hw1;

public class Demo {
    public static void main(String[] args) throws Exception {

        String[] formatsArray = {"txt", "jpg"};

        Storage storage1 = new Storage(110, formatsArray, "Ukraine", 156200000);
        Storage storage2 = new Storage(111, formatsArray, "UK", 265400000);
        Storage storage3 = new Storage(112, formatsArray, "Canada", 199900000);

        File file1 = new File(1, "guide", "txt", 6465, storage1);
        File file2 = new File(2, "PCS", "txt", 12999, storage2);
        File file3 = new File(3, "text", "txt", 500, storage2);
        File file4 = new File(4, "newdoc", "txt", 100, null);
        File file5 = new File(5, "story", "jpg", 6000, null);

        StorageDAO sd = new StorageDAO();
        FileDAO fd = new FileDAO();
        Controller controller = new Controller();

//        sd.save(storage2);
//        sd.save(storage3);
//        sd.delete(112);
//        sd.findById(110);
//        sd.update(storage3);
//        fd.save(file1);
//        fd.save(file4);
//        fd.delete(3);
//        fd.findById(2);
//        fd.update(file2);
//        controller.transferFile(storage1, storage2, 1);
//       controller.transferFile(storage3, storage2, 4);
        controller.put(storage2, file2);
//        System.out.println(file2.toString());








    }
}
