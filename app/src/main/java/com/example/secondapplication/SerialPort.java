package com.example.secondapplication;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SerialPort {
    private static final  String TAG = "SerialPort";
    /*
     * Do not remove or rename the field mFd: it is used
     * close();
     */
    private FileDescriptor mFd;
    private FileInputStream mFileInputStream;
    private FileOutputStream mFileOutputStream;
    public SerialPort(File device, int baudrate, int flags)
            throws SecurityException, IOException {
//        if(!device.canRead()||device.){
//
//        }
//
    }
}
