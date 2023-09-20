package com.example.database.services;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Service
public class FileServices {

    public InputStream getResource(String path,String fileName) throws FileNotFoundException {
        String fullPath=path+ File.separator+fileName;
        InputStream file=new FileInputStream(fullPath);
        return file;
    }

}
