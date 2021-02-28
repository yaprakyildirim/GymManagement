package Repository.Impl;

import Repository.IDataAccess;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class TxtRepository implements IDataAccess {
    private BufferedReader reader;
    private FileWriter writer;
    private String fileName;
    public TxtRepository(String _fileName) {
        this.fileName = System.getProperty("user.dir")+"\\src\\"+_fileName;
    }

    @Override
    public ArrayList<String> getAll() {
        try {
            this.reader = new BufferedReader(new FileReader(this.fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> returnList = new ArrayList<String>();
        try {
            String line = reader.readLine();
            while (line != null) {
                returnList.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnList;
    }

    @Override
    public boolean save(ArrayList<String> arr) {
        try {
            this.writer = new FileWriter(this.fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Iterator i = arr.iterator();
            String str = "";
            while(i.hasNext()){
                str = (String)i.next();
                this.writer.write(str);
            }
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int _id) {
        ArrayList<String> txtList = this.getAll();
        Boolean isDeleted = false;
        Iterator i = txtList.iterator();
        String str = "";
        while(i.hasNext()){
            str = (String)i.next();
            if (Integer.parseInt(str.split("\t")[0]) == _id) {
                isDeleted = true;
                i.remove();
            }
        }
        if (isDeleted) {
            this.save(txtList);
        }
        return isDeleted;
    }

    @Override
    public ArrayList<String> getById(int _id) {
        try {
            this.reader = new BufferedReader(new FileReader(this.fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> txtList = this.getAll();
        ArrayList<String> returnList = new ArrayList<String>();
        for (String item : txtList) {
            if (Integer.parseInt(item.split("\t")[0]) == _id) {
                returnList.add(item);
            }
        }
        return returnList;
    }
}
