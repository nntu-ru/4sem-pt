package util;

import organizer.iface.IReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Адаптер ввода
 */
public class Reader implements IReader {
    protected BufferedReader reader;

    public Reader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream));
    }
    
    @Override
    public String readLine() {
        String line;

        try {
            line = reader.readLine();
        } catch (Exception e) {
            return "";
        }
        
        return line;
    }
}
