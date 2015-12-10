package ru.unn.agile.Vec3.Infrastructure;

import ru.unn.agile.Vec3.ViewModel.ILogger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Vector3TxtLogger implements ILogger {
    private static final String FILENAME_EMPTY =
            "Text logger can't be initialize without filename.";
    private static final String FILE_NOT_EXIST = "File not exist.";
    private static final String DATE_FORMAT_NOW = "dd-MM-yyyy HH:mm:ss";
    private final String filename;
    private final BufferedWriter writerLog;

    private final StringBuilder stringBuilder = new StringBuilder();
    private final Formatter formatter = new Formatter(stringBuilder, Locale.ENGLISH);

    private static String now() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dataFormat = new SimpleDateFormat(DATE_FORMAT_NOW, Locale.ENGLISH);
        return dataFormat.format(calendar.getTime());
    }

    public Vector3TxtLogger(final String filename) {
        if (filename == null) {
            throw new IllegalArgumentException(FILENAME_EMPTY);
        }

        this.filename = filename;

        BufferedWriter logWriter = null;
        try {
            logWriter = new BufferedWriter(new FileWriter(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }

        writerLog = logWriter;
    }

    @Override
    public void pushMessage(final String message) {
        try {
            writerLog.write(now() + " > " + message);
            writerLog.newLine();
            writerLog.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void pushMessage(final String format, final Object ... args) {
        pushMessage(formatter.format(format, args).toString());
    }

    @Override
    public List<String> getLog() {
        ArrayList<String> log = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));

            String line = reader.readLine();
            while (line != null) {
                log.add(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return log;
    }
}
